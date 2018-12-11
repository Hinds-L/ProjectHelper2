package com.bluecirclesquare.projecthelper2.view;


import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;

import com.bluecirclesquare.projecthelper2.R;
import com.bluecirclesquare.projecthelper2.model.db.ProjectDatabase;
import com.bluecirclesquare.projecthelper2.model.entity.Quote;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteFragment extends DialogFragment {

  Quote fromDBQuote;
  View view;

  public static QuoteFragment newInstance(Long quoteId) {

    Bundle args = new Bundle();
    args.putLong("quoteId", quoteId);
    QuoteFragment fragment = new QuoteFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    AlertDialog.Builder builder = new Builder(getActivity());
    LayoutInflater inflater = LayoutInflater.from(getContext());
    view = inflater.inflate(R.layout.fragment_quote, null);
    builder.setView(view);
    builder.setPositiveButton("OK", (dialog, id) -> {
      if (fromDBQuote == null) {
        Quote quote = new Quote();
        quote.setAddress(
            ((TextInputEditText) view.findViewById(R.id.address)).getText().toString());
        quote.setAmount(Long.parseLong(
            ((TextInputEditText) view.findViewById(R.id.amount)).getText().toString()));
        quote
            .setContact(((TextInputEditText) view.findViewById(R.id.contact)).getText().toString());
        quote.setDescription(
            ((TextInputEditText) view.findViewById(R.id.description)).getText().toString());
        quote.setEvent(new Date());
        TextInputEditText quoteInput = view.findViewById(R.id.quote_number);
        if (quoteInput.getText() != null && !quoteInput.getText().toString().trim().isEmpty()) {
          quote.setQuoteNumber(quoteInput.getText().toString().trim());
        }

        new InsertQuote().execute(quote);
      } else {
        fromDBQuote.setAddress(
            ((TextInputEditText) view.findViewById(R.id.address)).getText().toString());
        TextInputEditText amountInput = view.findViewById(R.id.amount);
        if (amountInput.getText() != null && !amountInput.getText().toString().trim().isEmpty()){
          double amount = Double.parseDouble(amountInput.getText().toString().trim());
          fromDBQuote.setAmount((long) (amount * 100));
        }
        fromDBQuote
            .setContact(((TextInputEditText) view.findViewById(R.id.contact)).getText().toString());
        fromDBQuote.setDescription(
            ((TextInputEditText) view.findViewById(R.id.description)).getText().toString());
        fromDBQuote.setEvent(new Date());
        fromDBQuote.setQuoteNumber(
            ((TextInputEditText) view.findViewById(R.id.quote_number)).getText().toString());
        new UpdateQuote().execute(fromDBQuote);
      }
      dialog.dismiss();
    });

    builder.setNegativeButton("CANCEL", (dialog, id) -> {
      dialog.dismiss();
    });
    return builder.create();
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    if (getArguments() != null) {
      new GetQuote().execute(getArguments().getLong("quoteId"));
      return;
    }
  }

  private class InsertQuote extends AsyncTask<Quote, Void, Void> {

    @Override
    protected Void doInBackground(Quote... quotes) {
      ProjectDatabase.getInstance(getContext()).getQuoteDao().insert(quotes[0]);
      return null;
    }
  }

  private class UpdateQuote extends AsyncTask<Quote, Void, Void> {

    @Override
    protected Void doInBackground(Quote... quotes) {
      ProjectDatabase.getInstance(getContext()).getQuoteDao().update(quotes[0]);
      return null;
    }
  }

  private class GetQuote extends AsyncTask<Long, Void, Void> {

    @Override
    protected Void doInBackground(Long... longs) {
      fromDBQuote = ProjectDatabase.getInstance(getContext()).getQuoteDao().get(longs[0]);
      return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
      super.onPostExecute(aVoid);
      setQuote();
    }
  }

  private void setQuote() {
    ((TextInputEditText) view.findViewById(R.id.quote_id))
        .setText(Long.toString(fromDBQuote.getId()));
    ((TextInputEditText) view.findViewById(R.id.quote_number))
        .setText(fromDBQuote.getQuoteNumber());
    ((TextInputEditText) view.findViewById(R.id.amount))
        .setText(Long.toString(fromDBQuote.getAmount()));
    ((TextInputEditText) view.findViewById(R.id.contact)).setText(fromDBQuote.getContact());
    ((TextInputEditText) view.findViewById(R.id.description)).setText(fromDBQuote.getDescription());
    if (fromDBQuote.getEvent() != null) {
      ((TextInputEditText) view.findViewById(R.id.event))
          .setText(fromDBQuote.getEvent().toString());
    } else {
      ((TextInputEditText) view.findViewById(R.id.event)).setText("");
    }
  }
}
