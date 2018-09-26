package appteam.nith.hillffair2k18.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.squareup.picasso.Picasso;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.dialog.InfoDialog;
import appteam.nith.hillffair2k18.model.HomePostsModel;
import de.hdodenhof.circleimageview.CircleImageView;

public class Notification extends RecyclerView.Adapter<Notification.viewHolder> {

    private List<HomePostsModel> arrayList;
    private Activity activity;

    public Notification(List<HomePostsModel> arrayList, Context context) {
        this.arrayList=arrayList;
        this.activity = activity;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_clubs,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(final viewHolder holder, int position) {

        final HomePostsModel home_post = arrayList.get(position);
        holder.title.setText(home_post.getTitle());
        Picasso.get().load(home_post.getSmall_icon()).resize(80,80).centerCrop().into(holder.small_icon);
        holder.arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoDialog infoDialog = new InfoDialog(activity, home_post.getNotification_id());
                infoDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        CircleImageView small_icon;
        TextView title, arrow;

        public viewHolder(View itemView) {
            super(itemView);
            small_icon = itemView.findViewById(R.id.image);

            title = itemView.findViewById(R.id.title);

            arrow = itemView.findViewById(R.id.arrow);
        }
    }
}

