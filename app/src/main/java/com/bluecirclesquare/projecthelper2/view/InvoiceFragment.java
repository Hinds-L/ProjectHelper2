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
import com.bluecirclesquare.projecthelper2.model.entity.Invoice;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class InvoiceFragment extends DialogFragment {

  Invoice fromDBInvoice;
  View view;

  public static InvoiceFragment newInstance(Long invoiceId) {

    Bundle args = new Bundle();
    args.putLong("invoiceId", invoiceId);
    InvoiceFragment fragment = new InvoiceFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    AlertDialog.Builder builder = new Builder(getActivity());
    LayoutInflater inflater = LayoutInflater.from(getContext());
    View view = inflater.inflate(R.layout.fragment_invoice, null);
    builder.setView(view);
    builder.setPositiveButton("OK", (dialog, id) -> {
      if (fromDBInvoice == null) {
        Invoice invoice = new Invoice();
        invoice.setId(Long.parseLong(
            ((TextInputEditText) view.findViewById(R.id.invoice_id)).getText().toString()));
        invoice.setPrice(Long.parseLong(
            ((TextInputEditText) view.findViewById(R.id.invoice_price)).getText().toString()));
        invoice
            .setAddress(((TextInputEditText) view.findViewById(R.id.address)).getText().toString());
        invoice.setCompleted(new Date());
        invoice.setInvoiced(new Date());
        invoice.setJobId(Long.parseLong(
            ((TextInputEditText) view.findViewById(R.id.job_id)).getText().toString()));
        new InsertInvoice().execute(invoice);
      } else {
        fromDBInvoice.setId(Long.parseLong(
            ((TextInputEditText) view.findViewById(R.id.invoice_id)).getText().toString()));
        fromDBInvoice.setPrice(Long.parseLong(
            ((TextInputEditText) view.findViewById(R.id.invoice_price)).getText().toString()));
        fromDBInvoice
            .setAddress(((TextInputEditText) view.findViewById(R.id.address)).getText().toString());
        fromDBInvoice.setCompleted(new Date());
        fromDBInvoice.setInvoiced(new Date());
        fromDBInvoice.setJobId(Long.parseLong(
            ((TextInputEditText) view.findViewById(R.id.job_id)).getText().toString()));
        new UpdateInvoice().execute(fromDBInvoice);
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
      new GetInvoice().execute(getArguments().getLong("invoiceId"));
      return;
    }
  }

  private class InsertInvoice extends AsyncTask<Invoice, Void, Void> {

    @Override
    protected Void doInBackground(Invoice... invoices) {
      ProjectDatabase.getInstance(getContext()).getInvoiceDao().insert(invoices[0]);
      return null;
    }
  }

    private class UpdateInvoice extends AsyncTask<Invoice, Void, Void> {

      @Override
      protected Void doInBackground(Invoice... invoices) {
        ProjectDatabase.getInstance(getContext()).getInvoiceDao().update(invoices[0]);
        return null;
      }
    }

    private class GetInvoice extends AsyncTask<Long, Void, Void> {

      @Override
      protected Void doInBackground(Long... longs) {
        fromDBInvoice = ProjectDatabase.getInstance(getContext()).getInvoiceDao().get(longs[0]);
        return null;
      }


      @Override
      protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        setInvoice();
      }
    }

    private void setInvoice() {
      ((TextInputEditText) view.findViewById(R.id.invoice_id))
          .setText(Long.toString(fromDBInvoice.getId()));
      ((TextInputEditText) view.findViewById(R.id.job_id))
          .setText(Long.toString(fromDBInvoice.getJobId()));
      ((TextInputEditText) view.findViewById(R.id.price))
          .setText(Long.toString(fromDBInvoice.getPrice()));
      ((TextInputEditText) view.findViewById(R.id.address))
          .setText(fromDBInvoice.getAddress());
      if (fromDBInvoice.getCompleted() != null) {
        ((TextInputEditText) view.findViewById(R.id.date_completed))
            .setText(fromDBInvoice.getCompleted().toString());
      } else {
        ((TextInputEditText) view.findViewById(R.id.date_completed)).setText("");
      }
      if (fromDBInvoice.getInvoiced() != null) {
        ((TextInputEditText) view.findViewById(R.id.date_invoiced))
            .setText(fromDBInvoice.getInvoiced().toString());
      } else {
        ((TextInputEditText) view.findViewById(R.id.date_invoiced)).setText("");
      }
    }
  }


