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
import appteam.nith.hillffair2k18.model.Team;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ThisIsNSH on 9/20/2018.
 */

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.MyViewHolder> {

    List<Team> teamList;
    Activity activity;

    public TeamAdapter(List<Team> teamList, Activity activity) {
        this.activity = activity;
        this.teamList = teamList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.adapter_core_team, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Team team = teamList.get(position);
        holder.title.setText(team.getName());
        holder.position.setText(team.getPosition());
        Picasso.get().load(team.getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView image;
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
