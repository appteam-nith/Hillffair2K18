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
import appteam.nith.hillffair2k18.model.Contributor;
import de.hdodenhof.circleimageview.CircleImageView;

public class ContributorsAdaptor extends RecyclerView.Adapter<ContributorsAdaptor.MyViewHolder> {

    private List<Contributor> contributorList;
    Activity activity;
    public ContributorsAdaptor (List<Contributor> contributorList, Activity activity) {
        this.activity = activity;
        this.contributorList = contributorList;
    }
    @NonNull
    @Override
    public ContributorsAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.adapter_contributors, null);
        return new ContributorsAdaptor.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContributorsAdaptor.MyViewHolder holder, int position) {
        final Contributor contributor = contributorList.get(position);
        holder.name.setText(contributor.getName());
        holder.link.setText(contributor.getLink());
        Picasso.get().load(contributor.getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return contributorList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView image;
        TextView name,link;

        public MyViewHolder(View itemView) {
            super(itemView);
            link = itemView.findViewById(R.id.link);
            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.image);
        }
    }
}
