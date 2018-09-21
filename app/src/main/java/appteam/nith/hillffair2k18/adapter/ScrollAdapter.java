package appteam.nith.hillffair2k18.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.model.Scroll;

public class ScrollAdapter extends RecyclerView.Adapter<ScrollAdapter.MyViewHolder> {

    private List<Scroll> scrollList;
    private Activity activity;
    private TextView textView;

    public ScrollAdapter(List<Scroll> scrollList, Activity activity) {
        this.activity = activity;
        this.scrollList = scrollList;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.adapter_scroll, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Scroll scroll = scrollList.get(position);
        holder.text.setText(scroll.getName());
        holder.imageView.setImageResource(scroll.getImage());
    }

    public TextView getView(MyViewHolder viewHolder) {
        if (viewHolder != null)
            return viewHolder.text;
        return new TextView(activity);
    }

    @Override
    public int getItemCount() {
        return scrollList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView text;
        RelativeLayout backLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            backLayout = itemView.findViewById(R.id.backLayout);
            imageView = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);
        }
    }
}
