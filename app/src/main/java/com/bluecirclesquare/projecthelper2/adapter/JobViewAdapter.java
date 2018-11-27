package com.bluecirclesquare.projecthelper2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bluecirclesquare.projecthelper2.R;
import com.bluecirclesquare.projecthelper2.model.entity.Job;
import java.util.List;

public class JobViewAdapter extends RecyclerView.Adapter<JobViewAdapter.Holder> {

  private final Context context;
  private final List<Job> jobs;

  public JobViewAdapter(Context context, List<Job> jobs){
    this.context = context;
    this.jobs = jobs;
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
    View view = LayoutInflater.from(context).inflate(R.layout.job_list_item, parent, false);
    return new Holder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull JobViewAdapter.Holder holder, int position){
   holder.bind(jobs.get(position));
  }

  @Override
  public int getItemCount(){
    return jobs.size();
  }

  public class Holder extends RecyclerView.ViewHolder{
    private Job job;
    private TextView jobNumber;
    private TextView jobDescription;
    private TextView jobAddress;

    public Holder(@NonNull View itemView) {
      super(itemView);
      jobNumber = itemView.findViewById(R.id.job_number);
      jobDescription = itemView.findViewById(R.id.job_description);
      jobAddress = itemView.findViewById(R.id.job_address);
    }

   // @Override
 //   public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
//
  //  }
private void bind(Job job){
      this.job = job;
      jobNumber.setText(job.getJobNumber());
      jobDescription.setText(job.getDescription());
      jobAddress.setText(job.getAddress());

}
  }
}
