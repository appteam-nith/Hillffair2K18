package appteam.nith.hillffair2k18.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.model.Team;

/**
 * Coded by ThisIsNSH on 9/20/2018.
 */

public class SponsorAdapter extends RecyclerView.Adapter<SponsorAdapter.MyViewHolder> {

    List<Team> teamList;
    Activity activity;

    public SponsorAdapter(List<Team> teamList, Activity activity) {
        this.activity = activity;
        this.teamList = teamList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.adapter_sponsor, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Team team = teamList.get(position);
        holder.title.setText(team.getName());
        holder.position.setText(team.getPosition());
        if (!team.getImage().isEmpty())
            Picasso.get().load(team.getImage()).resize(80, 80).centerCrop().into(holder.image);
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView position;

        public MyViewHolder(View itemView) {
            super(itemView);
            position = itemView.findViewById(R.id.position);
            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.image);
        }
    }
}
