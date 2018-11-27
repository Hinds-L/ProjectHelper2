package com.bluecirclesquare.projecthelper2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bluecirclesquare.projecthelper2.R;
import com.bluecirclesquare.projecthelper2.model.entity.Quote;
import java.util.List;

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

    public Holder(@NonNull View itemView) {
      super(itemView);
      contact = itemView.findViewById(R.id.contact);
      amount = itemView.findViewById(R.id.amount);
      quoteNumber = itemView.findViewById(R.id.quote_number);
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
    }
  }

}
