package com.bluecirclesquare.projecthelper2.view;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import com.bluecirclesquare.projecthelper2.R;
import com.bluecirclesquare.projecthelper2.model.db.ProjectDatabase;
import com.bluecirclesquare.projecthelper2.model.entity.Job;
import java.util.Date;

public class JobFragment extends DialogFragment {

  Job fromDBJob;
  View view;

  public static JobFragment newInstance(Long jobId) {
    Bundle args = new Bundle();
    args.putLong("jobId", jobId);
    JobFragment fragment = new JobFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    AlertDialog.Builder builder = new Builder(getActivity());
    LayoutInflater inflater = LayoutInflater.from(getContext());
    View view = inflater.inflate(R.layout.fragment_job, null);
    builder.setView(view);
    builder.setPositiveButton("OK", (dialog, id) -> {
      if (fromDBJob == null) {
        Job job = new Job();
        job.setAddress(((TextInputEditText) view.findViewById(R.id.address)).getText().toString());
        job.setEvent(new Date());
        job.setDescription(
            ((TextInputEditText) view.findViewById(R.id.description)).getText().toString());
        job.setJobNumber(
            ((TextInputEditText) view.findViewById(R.id.job_number)).getText().toString());
        job.setId(Long.parseLong(
            ((TextInputEditText) view.findViewById(R.id.job_id)).getText().toString()));
        job.setQuoteId(Long.parseLong(((TextInputEditText)view.findViewById(R.id.quote_id)).getText()
            .toString()));
        new InsertJob().execute(job);
      } else {
        fromDBJob
            .setAddress(((TextInputEditText) view.findViewById(R.id.address)).getText().toString());
        fromDBJob.setEvent(new Date());
        fromDBJob.setDescription(
            ((TextInputEditText) view.findViewById(R.id.description)).getText().toString());
        fromDBJob.setJobNumber(
            ((TextInputEditText) view.findViewById(R.id.job_number)).getText().toString());
        fromDBJob.setId(Long.parseLong(
            ((TextInputEditText) view.findViewById(R.id.job_id)).getText().toString()));
        fromDBJob.setQuoteId(Long.parseLong(((TextInputEditText)view.findViewById(R.id.quote_id))
            .getText().toString()));
        new UpdateJob().execute(fromDBJob);
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
      new GetJob().execute(getArguments().getLong("jobId"));
      return;
    }
  }

  private class InsertJob extends AsyncTask<Job, Void, Void> {

    @Override
    protected Void doInBackground(Job... jobs) {
      ProjectDatabase.getInstance(getContext()).getJobDao().insert(jobs[0]);
      return null;
    }
  }


    private class UpdateJob extends AsyncTask<Job, Void, Void> {

      @Override
      protected Void doInBackground(Job... jobs) {
        ProjectDatabase.getInstance(getContext()).getJobDao().update(jobs[0]);
        return null;
      }
    }

    private class GetJob extends AsyncTask<Long, Void, Void> {

      @Override
      protected Void doInBackground(Long... longs) {
        fromDBJob = ProjectDatabase.getInstance(getContext()).getJobDao().get(longs[0]);
        return null;
      }

      @Override
      protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        setJob();
      }
    }
    private void setJob() {
      ((TextInputEditText) view.findViewById(R.id.address)).setText(fromDBJob.getAddress());
      ((TextInputEditText) view.findViewById(R.id.description)).setText(fromDBJob.getDescription());
      ((TextInputEditText) view.findViewById(R.id.job_number)).setText(fromDBJob.getJobNumber());
      ((TextInputEditText) view.findViewById(R.id.job_id)).setText(Long.toString(fromDBJob.getId()));
      ((TextInputEditText)view.findViewById(R.id.quote_id)).setText(Long.toString(fromDBJob.getQuoteId()));
      if (fromDBJob.getEvent() != null) {
        ((TextInputEditText) view.findViewById(R.id.event))
            .setText(fromDBJob.getEvent().toString());
      } else {
        ((TextInputEditText) view.findViewById(R.id.event)).setText("");
      }
    }
  }
