package appteam.nith.hillffair2k18.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.model.contributorsItem;
import de.hdodenhof.circleimageview.CircleImageView;

public class ContributorsAdaptor extends RecyclerView.Adapter<ContributorsAdaptor.MyViewHolder> {

    private List<contributorsItem> contributorList;
    private Context context;

    public ContributorsAdaptor(List<contributorsItem> contributorList, Context context) {
        this.contributorList = (ArrayList<contributorsItem>) contributorList;
        this.context = context;
    }

    @NonNull
    @Override
    public ContributorsAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.adapter_contributors, null);
        return new ContributorsAdaptor.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContributorsAdaptor.MyViewHolder holder, int position) {
        if (!(contributorList.get(position).getName().isEmpty())) {
            holder.name.setText(contributorList.get(position).getName());
        }
        if (!(contributorList.get(position).getGithubUrl().isEmpty())) {
            final String url = (contributorList.get(position).getGithubUrl());
            holder.github.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    context.startActivity(i);
                }
            });

        }

        if (!(contributorList.get(position).getImage() == null)) {
            Picasso.get().load(contributorList.get(position).getImage()).resize(80, 80).centerCrop().into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return contributorList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView image;
        TextView name, github;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.image);
            github = itemView.findViewById(R.id.github);
        }
    }
}
