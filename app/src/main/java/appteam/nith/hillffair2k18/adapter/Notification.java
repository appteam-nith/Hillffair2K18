package appteam.nith.hillffair2k18.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTarget;
;

import java.util.List;

import appteam.nith.hillffair2k18.Notification.Notification2;
import appteam.nith.hillffair2k18.Notification.notifications;
import appteam.nith.hillffair2k18.R;


/**
 * Created by root on 19/10/16.
 */


public class Notification extends RecyclerView.Adapter<Notification.viewHolder> {

    private List<notifications> arrayList;
    private Context context;

    public Notification(List<notifications> arrayList, Context context) {
        this.arrayList=arrayList;
        this.context = context;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.home_model_card,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(final viewHolder holder, int position) {

        final notifications home_post = arrayList.get(position);

        holder.title.setText(home_post.getTitle().getTitle());
        holder.notification_id.setText(home_post.getNotification_id());
        String ab="R.drawable."+home_post.getSmall_icon();
        Log.v("ab:",""+home_post.getTitle()+"small_icon"+position);
        final Context context=holder.title.getContext();
        if (home_post.getSmall_icon() == null || home_post.getSmall_icon().isEmpty() || home_post.getSmall_icon().length() == 0) {
            Glide.with(context).load(R.drawable.hillffair2).into(holder.small_icon);
        } else {
            Glide.with(context).asBitmap().load(ab).apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)).apply(RequestOptions.errorOf(R.drawable.hillffair2)).into(new ImageViewTarget<Bitmap>(holder.small_icon) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    drawable.setCircular(true);
                    holder.small_icon.setImageDrawable(drawable);
                }
            });

        }
        holder.lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,Notification2.class);
                Bundle assign = new Bundle();
                Log.d("body",home_post.getImg());
                assign.putString("title",home_post.getTitle().getTitle().toString());
                assign.putString("body",home_post.getBody().getTitle().toString());
                assign.putString("bigpicture",home_post.getImg());
                assign.putString("launchurl",home_post.getLaunchurl());
                i.putExtra("assign",assign);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public  void  refresh(List<notifications> list){
        this.arrayList=list;
        notifyDataSetChanged();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        ImageView small_icon;
        TextView title,notification_id;
        LinearLayout lay;
        public viewHolder(View itemView) {
            super(itemView);
            small_icon=(ImageView) itemView.findViewById(R.id.small_icon);

            title=(TextView)itemView.findViewById(R.id.not_title);
            lay=(LinearLayout)itemView.findViewById(R.id.not_click_lay);
            notification_id=(TextView)itemView.findViewById(R.id.not_id);
        }
    }
}