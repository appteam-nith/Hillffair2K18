package appteam.nith.hillffair2k18.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.model.Schedule;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Coded by ThisIsNSH on 9/20/2018.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder> {

    List<Schedule> scheduleList;
    Activity activity;
    int likes;
    private boolean check = true;

    public ScheduleAdapter(List<Schedule> scheduleList, Activity activity) {
        this.activity = activity;
        this.scheduleList = scheduleList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.adapter_schedule, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Schedule schedule = scheduleList.get(position);
        holder.title.setText(schedule.getTitle());
        holder.timing.setText(schedule.getTime());
        holder.position.setText(schedule.getSubtitle());
        Picasso.get().load(schedule.getImg()).resize(80, 80).centerCrop().into(holder.image);


    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView image;
        TextView title, position;
        TextView timing;

        public MyViewHolder(View itemView) {
            super(itemView);
            timing = itemView.findViewById(R.id.timea);
            title = itemView.findViewById(R.id.title);
            position = itemView.findViewById(R.id.position);
            image = itemView.findViewById(R.id.image);
        }
    }
}
