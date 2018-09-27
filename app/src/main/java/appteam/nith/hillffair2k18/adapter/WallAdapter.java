package appteam.nith.hillffair2k18.adapter;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.model.Wall;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

/**
 * Coded by ThisIsNSH on 9/20/2018.
 */

public class WallAdapter extends RecyclerView.Adapter<WallAdapter.MyViewHolder> {

    String roll;
    List<Wall> wallList;
    Activity activity;
    Wall wall;
    int likes;
    String image_id, user_id;
    private boolean check = true;

    public WallAdapter(List<Wall> wallList, Activity activity) {
        this.activity = activity;
        this.wallList = wallList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AndroidNetworking.initialize(getApplicationContext());
        View view = View.inflate(parent.getContext(), R.layout.adapter_wall, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        wall = wallList.get(position);
        image_id = wall.getShare();
        user_id = wall.getDesc();
        SharedPreferences prefs = activity.getSharedPreferences("number", Context.MODE_PRIVATE);
        roll = prefs.getString("roll number", "gsb");

        holder.like_count.setText(String.valueOf(Integer.parseInt(wall.getLikes())));
        holder.desc.setText(wall.getDesc());
        holder.title.setText(wall.getName());

        if (wall.getLiked() == 0) {
            check = true;
        } else {
            check = false;
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
            valueAnimator.setDuration(1000);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    holder.like.setProgress(animation.getAnimatedFraction());
                }
            });
            valueAnimator.start();
        }

        Picasso.get().load(wall.getProfile()).resize(80, 80).centerCrop().into(holder.profile);
        Picasso.get().load(wall.getImage()).resize(300, 300).centerCrop().into(holder.image);
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check) {
                    getlike(holder);
                    holder.like.setEnabled(false);
                    post();

                } else {
                    getlike1(holder);
                    holder.like.setEnabled(false);
                    remove();
                }
            }

        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return wallList.size();
    }

    public void post() {
        AndroidNetworking.get(activity.getString(R.string.baseUrl) + "postlike/" + image_id + "/" + roll + "/1")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // do anything with response
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    public void getlike(final MyViewHolder holder) {
        AndroidNetworking.get(activity.getString(R.string.baseUrl) + "getlike/" + image_id)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            likes = Integer.parseInt(response.getString("likes"));
                            likes = likes + 1;
                            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
                            valueAnimator.setDuration(1000);
                            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator animation) {
                                    holder.like.setProgress(animation.getAnimatedFraction());
                                }
                            });
                            valueAnimator.start();
                            holder.like_count.setText(String.valueOf(likes));
                            check = false;
                            holder.like.setEnabled(true);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    public void getlike1(final MyViewHolder holder) {
        AndroidNetworking.get(activity.getString(R.string.baseUrl) + "getlike/" + image_id)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            likes = Integer.parseInt(response.getString("likes"));
                            likes = likes - 1;
                            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
                            valueAnimator.setDuration(100);
                            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator animation) {
                                    holder.like.setProgress(1 - animation.getAnimatedFraction());
                                }
                            });
                            holder.like_count.setText(String.valueOf(likes));
                            valueAnimator.start();
                            holder.like.setEnabled(true);
                            check = true;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }


    public void remove() {
        AndroidNetworking.get(activity.getString(R.string.baseUrl) + "postlike/" + image_id + "/" + roll + "/0")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // do anything with response
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profile;
        TextView title, desc, like_count;
        ImageView image, share;
        LottieAnimationView like;


        public MyViewHolder(View itemView) {
            super(itemView);
            like = itemView.findViewById(R.id.like);
//            share = itemView.findViewById(R.id.share);
            image = itemView.findViewById(R.id.upload);
            like_count = itemView.findViewById(R.id.like_count);
            desc = itemView.findViewById(R.id.desc);
            title = itemView.findViewById(R.id.title);
            profile = itemView.findViewById(R.id.image);
        }
    }
}
