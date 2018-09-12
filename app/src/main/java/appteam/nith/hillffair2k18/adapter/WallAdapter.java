package appteam.nith.hillffair2k18.adapter;

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

    WallAdapter(List<Wall> wallList, Activity activity) {
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
        Wall wall = wallList.get(position);
        holder.like_count.setText(wall.getLikes());
        holder.title.setText(wall.getName());
        Picasso.get().load(wall.getProfile()).into(holder.profile);
        Picasso.get().load(wall.getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return wallList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profile;
        TextView title, like_count;
        ImageView image;
        LottieAnimationView like, share;

        public MyViewHolder(View itemView) {
            super(itemView);
            like = itemView.findViewById(R.id.like);
            share = itemView.findViewById(R.id.share);
            image = itemView.findViewById(R.id.upload);
            like_count = itemView.findViewById(R.id.like_count);
            title = itemView.findViewById(R.id.title);
            profile = itemView.findViewById(R.id.image);
        }
    }
}
