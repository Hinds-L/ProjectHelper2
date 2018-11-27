package com.bluecirclesquare.projecthelper2.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bluecirclesquare.projecthelper2.R;
import com.bluecirclesquare.projecthelper2.adapter.InvoiceViewAdapter;
import com.bluecirclesquare.projecthelper2.model.db.ProjectDatabase;
import com.bluecirclesquare.projecthelper2.model.entity.Invoice;
import java.util.ArrayList;
import java.util.List;

/**
This is the InvoicesFragment class that inflates the view to populate the items held by
 the recyclerview
 */

public class InvoicesFragment extends Fragment {

  private InvoiceViewAdapter adapter;
  private RecyclerView invoicesView;
  private List<Invoice> invoices = new ArrayList<>();


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_invoices, container, false);
    adapter = new InvoiceViewAdapter(getActivity(), this.invoices);
    invoicesView = view.findViewById(R.id.invoices_view);
    invoicesView.setAdapter(adapter);
    new QueryTask().execute();
    return view;
  }

  /**
   * Clears the previous content on the screen
   */
  private class QueryTask extends AsyncTask<Void, Void, List<Invoice>> {

    @Override
    protected void onPostExecute(List<Invoice> invoices) {
      InvoicesFragment.this.invoices.clear();
      InvoicesFragment.this.invoices.addAll(invoices);
      adapter.notifyDataSetChanged();
    }

    @Override
    protected List<Invoice> doInBackground(Void... voids) {
      return ProjectDatabase.getInstance(getContext()).getInvoiceDao().select();
    }

  }


}
