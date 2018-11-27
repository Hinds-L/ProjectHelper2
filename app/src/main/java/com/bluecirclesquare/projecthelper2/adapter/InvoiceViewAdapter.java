package com.bluecirclesquare.projecthelper2.adapter;

import static android.text.format.DateFormat.getDateFormat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bluecirclesquare.projecthelper2.R;
import com.bluecirclesquare.projecthelper2.model.entity.Invoice;
import java.text.DateFormat;
import java.util.List;

public class InvoiceViewAdapter extends RecyclerView.Adapter<InvoiceViewAdapter.Holder> {


  private final Context context;
  private final List<Invoice> invoices;

  public InvoiceViewAdapter(Context context, List<Invoice> invoices) {
    this.context = context;
    this.invoices = invoices;
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.invoice_list_item, parent, false);
    return new Holder(view);

  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(invoices.get(position));
  }

  @Override
  public int getItemCount() {
    return invoices.size();
  }

  public class Holder extends RecyclerView.ViewHolder {

    private Invoice invoice;
    private TextView id;
    private TextView invoiced;
    private TextView price;
    private TextView address;

    public Holder(@NonNull View itemView) {
      super(itemView);
      id = itemView.findViewById(R.id.invoice_id);
      invoiced = itemView.findViewById(R.id.date_invoiced);
      price = itemView.findViewById(R.id.price);
      address = itemView.findViewById(R.id.job_address);
    }

    //  @Override
    //   public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)}


    private void bind(Invoice invoice) {
      DateFormat format = getDateFormat(context);
      this.invoice = invoice;
      id.setText(Long.toString(invoice.getId()));
      invoiced.setText(format.format(invoice.getInvoiced()));
      price.setText(context.getString(R.string.amount_format, invoice.getPrice() / 100d));
      address.setText(invoice.getAddress());
    }
  }
}
