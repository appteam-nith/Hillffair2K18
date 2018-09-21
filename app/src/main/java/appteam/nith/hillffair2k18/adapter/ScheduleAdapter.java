package appteam.nith.hillffair2k18.adapter;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.squareup.picasso.Picasso;

import java.util.List;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.model.Schedule;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ThisIsNSH on 9/20/2018.
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
        View view = View.inflate(parent.getContext(), R.layout.adapter_main, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Schedule schedule = scheduleList.get(position);
        holder.title.setText(schedule.getTitle());
        holder.subtitle.setText(schedule.getSubtitle());
        Picasso.get().load(schedule.getImg()).into(holder.image);
        likes = Integer.parseInt(holder.like_count.getText().toString());

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check) {
                    ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
                    valueAnimator.setDuration(1000);
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            holder.like.setProgress(animation.getAnimatedFraction());
                        }
                    });
                    valueAnimator.start();
                    likes += 1;
                    check = false;
                    Log.e("a", "onClick: ");
                } else {
                    ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
                    valueAnimator.setDuration(1000);
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            holder.like.setProgress(1 - animation.getAnimatedFraction());
                        }
                    });
                    likes -= 1;
                    valueAnimator.start();
                    check = true;
                }
            }

        });
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView image;
        TextView like_count, title, subtitle;
        LottieAnimationView like;

        public MyViewHolder(View itemView) {
            super(itemView);
            like = itemView.findViewById(R.id.like);
            title = itemView.findViewById(R.id.title);
            like_count = itemView.findViewById(R.id.like_count);
            subtitle = itemView.findViewById(R.id.subtitle);
            image = itemView.findViewById(R.id.image);
        }
    }
}
