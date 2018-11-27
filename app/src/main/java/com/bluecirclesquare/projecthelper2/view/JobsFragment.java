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
import com.bluecirclesquare.projecthelper2.adapter.JobViewAdapter;
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
}
