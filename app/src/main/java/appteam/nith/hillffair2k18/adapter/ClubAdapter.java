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
import appteam.nith.hillffair2k18.dialog.InfoDialog;
import appteam.nith.hillffair2k18.model.Club;
import de.hdodenhof.circleimageview.CircleImageView;

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.MyViewHolder> {
    List<Club> clubList;
    Activity activity;

    public ClubAdapter(List<Club> clubList, Activity activity) {
        this.activity = activity;
        this.clubList = clubList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.adapter_clubs, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Club club = clubList.get(position);
        holder.title.setText(club.getName());
        Picasso.get().load(club.getImage()).into(holder.image);
        holder.arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoDialog infoDialog = new InfoDialog(activity, club.getInfo());
                infoDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return clubList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView image;
        TextView title;
        ImageView arrow;

        public MyViewHolder(View itemView) {
            super(itemView);
            arrow = itemView.findViewById(R.id.arrow);
            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.image);
        }
    }
}
