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
import com.bluecirclesquare.projecthelper2.model.db.ProjectDatabase;
import com.bluecirclesquare.projecthelper2.model.entity.Job;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the JobsFragment.class that is used for populating the recyclerview items in a layout
 */

public class JobsFragment extends Fragment {

  private JobViewAdapter adapter;
  private RecyclerView jobsView;
  private List<Job> jobs = new ArrayList<>();


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_jobs, container, false);
    adapter = new JobViewAdapter(getActivity(), this.jobs);
    jobsView = view.findViewById(R.id.jobs_view);
    jobsView.setAdapter(adapter);
    FloatingActionButton fabJob = view.findViewById(R.id.add_job);
    fabJob.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        JobFragment fragment = new JobFragment();
        fragment.show(getFragmentManager(), fragment.getClass().getSimpleName());
      }
    });
    new QueryTask().execute();
    return view;
  }

  /**
   * Gives the ability to clear the view with new content
   */
  private class QueryTask extends AsyncTask<Void, Void, List<Job>> {

    @Override
    protected void onPostExecute(List<Job> jobs) {
      JobsFragment.this.jobs.clear();
      JobsFragment.this.jobs.addAll(jobs);
      adapter.notifyDataSetChanged();
    }

    @Override
    protected List<Job> doInBackground(Void... voids) {
      return ProjectDatabase.getInstance(getContext()).getJobDao().select();
    }

  }

  public class JobViewAdapter extends RecyclerView.Adapter<JobViewAdapter.Holder> {

    private final Context context;
    private final List<Job> jobs;

    public JobViewAdapter(Context context, List<Job> jobs) {
      this.context = context;
      this.jobs = jobs;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(context).inflate(R.layout.job_list_item, parent, false);
      view.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          JobFragment fragment = JobFragment.newInstance(
              Long.parseLong(((TextView) view.findViewById(R.id.job_id)).getText().toString()));
          fragment.show(getFragmentManager(), fragment.getClass().getSimpleName());
        }
      });
      return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
      holder.bind(jobs.get(position));
    }

    @Override
    public int getItemCount() {
      Log.v("JobViewAdapter", "Count Called: " + jobs.size());
      return jobs.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

      private Job job;
      private TextView jobNumber;
      private TextView description;
      private TextView address;
      private TextView id;

      public Holder(@NonNull View itemView) {
        super(itemView);
        jobNumber = itemView.findViewById(R.id.job_number);
        description = itemView.findViewById(R.id.job_description);
        address = itemView.findViewById(R.id.job_address);
        id = itemView.findViewById(R.id.job_id);
      }

      private void bind(Job job) {
        this.job = job;
        jobNumber.setText(job.getJobNumber());
        description.setText(job.getDescription());
        address.setText(job.getAddress());
        id.setText(Long.toString(job.getId()));


      }
    }
  }
}
