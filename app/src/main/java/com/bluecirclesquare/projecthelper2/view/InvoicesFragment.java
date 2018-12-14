package com.bluecirclesquare.projecthelper2.view;

import static android.text.format.DateFormat.getDateFormat;

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
import com.bluecirclesquare.projecthelper2.adapter.InvoiceViewAdapter;
import com.bluecirclesquare.projecthelper2.model.db.ProjectDatabase;
import com.bluecirclesquare.projecthelper2.model.entity.Invoice;
import java.text.DateFormat;
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
    FloatingActionButton fabInvoice = view.findViewById(R.id.add_invoice);
    fabInvoice.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        InvoiceFragment fragment = new InvoiceFragment();
        fragment.show(getFragmentManager(), fragment.getClass().getSimpleName());
      }
    });
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

public class InvoiceViewAdapter extends RecyclerView.Adapter<InvoiceViewAdapter.Holder>{

    private final Context context;
    private final List<Invoice> invoices;

    public InvoiceViewAdapter(Context context, List<Invoice> invoices){
      this.context = context;
      this.invoices = invoices;
    }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.invoice_list_item, parent, false);
  view.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View v) {
      InvoiceFragment fragment = InvoiceFragment.newInstance(Long.parseLong(((TextView)view.findViewById(R.id.quote_id_for_list)).getText().toString()));
      fragment.show(getFragmentManager(),fragment.getClass().getSimpleName());
    }
  });
  return new Holder(view);
    }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
holder.bind(invoices.get(position));
  }

  @Override
  public int getItemCount() {
    Log.v("InvoiceViewAdapter", "Count Called: "+invoices.size());
    return invoices.size();
  }

  public class Holder extends RecyclerView.ViewHolder {
      private Invoice invoice;
      private TextView id;
      TextView  invoiced;
      TextView price;
      TextView address;
      TextView format;

      public Holder(@NonNull View itemView){
        super(itemView);
        format = itemView.findViewById(R.id.date_invoiced);
        id = itemView.findViewById(R.id.invoice_id);
        invoiced = itemView.findViewById(R.id.date_invoiced);
        price = itemView.findViewById(R.id.price);
        address = itemView.findViewById(R.id.job_address);
      }

      private void bind(Invoice invoice){
        DateFormat format = getDateFormat(context);
        this.invoice = invoice;
        id.setText(Long.toString(invoice.getId()));
        invoiced.setText(format.format(invoice.getInvoiced()));
        price.setText(context.getString(R.string.amount_format, invoice.getPrice() / 100d));
        address.setText(invoice.getAddress());
      }
  }
}
}
