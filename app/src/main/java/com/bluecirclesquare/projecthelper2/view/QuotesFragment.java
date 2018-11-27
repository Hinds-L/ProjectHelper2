package com.bluecirclesquare.projecthelper2.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
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

}
