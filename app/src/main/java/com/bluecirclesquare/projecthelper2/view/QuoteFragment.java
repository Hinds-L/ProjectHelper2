package com.bluecirclesquare.projecthelper2.view;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluecirclesquare.projecthelper2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteFragment extends DialogFragment {

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    AlertDialog.Builder builder = new Builder(getActivity());
    LayoutInflater inflater = LayoutInflater.from(getContext());
    View view = inflater.inflate(R.layout.fragment_quote, null);
    builder.setView(view);
    //TODO fill in code for ok and cancel buttons
    builder.setPositiveButton("OK", (dialog, id) -> {dialog.dismiss();});
    builder.setNegativeButton("CANCEL", (dialog, id) -> {dialog.dismiss();});
    return builder.create();
  }
}
