package appteam.nith.hillffair2k18.adapter;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.squareup.picasso.Picasso;

import java.util.List;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.model.Wall;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ThisIsNSH on 9/20/2018.
 */

public class WallAdapter extends RecyclerView.Adapter<WallAdapter.MyViewHolder> {

    List<Wall> wallList;
    Activity activity;
    int likes;
    private boolean check = true;

    public WallAdapter(List<Wall> wallList, Activity activity) {
        this.activity = activity;
        this.wallList = wallList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.adapter_wall, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Wall wall = wallList.get(position);
        holder.like_count.setText(wall.getLikes());
        holder.desc.setText(wall.getDesc());
        holder.title.setText(wall.getName());
        Picasso.get().load(wall.getProfile()).resize(80, 80).into(holder.profile);
        Picasso.get().load(wall.getImage()).resize(300, 300).centerCrop().into(holder.image);

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
                    holder.like_count.setText(String.valueOf(Integer.parseInt(wall.getLikes())+1));
                    check = false;
                } else {
                    ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
                    valueAnimator.setDuration(100);
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            holder.like.setProgress(1 - animation.getAnimatedFraction());
                        }
                    });
                    holder.like_count.setText(String.valueOf(Integer.parseInt(wall.getLikes())-1));
                    valueAnimator.start();
                    check = true;
                }
            }

        });
    }

    @Override
    public int getItemCount() {
        return wallList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profile;
        TextView title, desc, like_count;
        ImageView image,share;
        LottieAnimationView like;


        public MyViewHolder(View itemView) {
            super(itemView);
            like = itemView.findViewById(R.id.like);
            share = itemView.findViewById(R.id.share);
            image = itemView.findViewById(R.id.upload);
            like_count = itemView.findViewById(R.id.like_count);
            desc = itemView.findViewById(R.id.desc);
            title = itemView.findViewById(R.id.title);
            profile = itemView.findViewById(R.id.image);
        }
    }
}
