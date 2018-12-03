package com.bluecirclesquare.projecthelper2.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import com.bluecirclesquare.projecthelper2.R;
import com.bluecirclesquare.projecthelper2.model.entity.Job;

public class JobFragment extends DialogFragment {

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    AlertDialog.Builder builder = new Builder(getActivity());
    LayoutInflater inflater = LayoutInflater.from(getContext());
    View view = inflater.inflate(R.layout.fragment_job, null);
    builder.setView(view);
    builder.setPositiveButton("OK", (dialog, id) -> {
      Job job = new Job();
      job.setAddress();
      job.setEvent();
      job.setDescription();
      job.setJobNumber();
      job.setId();
      dialog.dismiss();
      //TODO get all values in the dialog and set them in the job

    });

    builder.setNegativeButton("CANCEL", (dialog, id) -> {dialog.dismiss();});
    return builder.create();
  }

}
