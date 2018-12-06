package com.bluecirclesquare.projecthelper2.view;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bluecirclesquare.projecthelper2.R;
import com.bluecirclesquare.projecthelper2.adapter.QuoteViewAdapter;
import com.bluecirclesquare.projecthelper2.model.db.ProjectDatabase;
import com.bluecirclesquare.projecthelper2.model.entity.Quote;
import java.util.ArrayList;
import java.util.List;

/*
This is the QuotesFragment class that inflates the view to populate the quote database.
 */
public class QuotesFragment extends Fragment {

  private QuoteViewAdapter adapter;
  private RecyclerView quotesView;
  private List<Quote> quotes = new ArrayList<>();

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_quotes, container, false);
    adapter = new QuoteViewAdapter(getActivity(), this.quotes);
    quotesView = view.findViewById(R.id.quotes_view);
    quotesView.setAdapter(adapter);
    FloatingActionButton fab = view.findViewById(R.id.add_quote);
    fab.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        QuoteFragment fragment = new QuoteFragment();
        fragment.show(getFragmentManager(), fragment.getClass().getSimpleName());
      }
    });
    new QueryTask().execute();
    return view;
  }

  private class QueryTask extends AsyncTask<Void, Void, List<Quote>> {

    @Override
    protected void onPostExecute(List<Quote> quotes) {
      QuotesFragment.this.quotes.clear();
      QuotesFragment.this.quotes.addAll(quotes);
      adapter.notifyDataSetChanged();
    }

    @Override
    protected List<Quote> doInBackground(Void... voids) {
      return ProjectDatabase.getInstance(getContext()).getQuoteDao().select();
    }

  }

  public class QuoteViewAdapter extends RecyclerView.Adapter<QuoteViewAdapter.Holder> {

    private final Context context;
    private final List<Quote> quotes;

    public QuoteViewAdapter(Context context, List<Quote> quotes) {
      this.context = context;
      this.quotes = quotes;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(context).inflate(R.layout.quote_list_item, parent, false);
      view.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          QuoteFragment fragment = QuoteFragment.newInstance(Long.parseLong(((TextView)view.findViewById(R.id.quote_id_for_list)).getText().toString()));
          fragment.show(getFragmentManager(), fragment.getClass().getSimpleName());
        }
      });
      return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
      holder.bind(quotes.get(position));
    }

    @Override
    public int getItemCount() {
      Log.v("QuoteViewAdapter", "Count Called: "+quotes.size());
      return quotes.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

      private Quote quote;
      private TextView contact;
      private TextView amount;
      private TextView quoteNumber;
      private TextView id;

      public Holder(@NonNull View itemView) {
        super(itemView);
        contact = itemView.findViewById(R.id.contact);
        amount = itemView.findViewById(R.id.amount);
        quoteNumber = itemView.findViewById(R.id.quote_number);
        id = itemView.findViewById(R.id.quote_id_for_list);
      }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
//
//    }

      private void bind(Quote quote) {
        this.quote = quote;
        contact.setText(quote.getContact());
        quoteNumber.setText(quote.getQuoteNumber());
        amount.setText(context.getString(R.string.amount_format, quote.getAmount() / 100d));
        id.setText(Long.toString(quote.getId()));
      }
    }

  }

}
