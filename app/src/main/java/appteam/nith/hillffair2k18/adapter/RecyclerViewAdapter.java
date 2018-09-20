package appteam.nith.hillffair2k18.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.activity.DashActivity;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public static List<View> itemViewList = new ArrayList<>();
    String[] SubjectValues;
    Context context;
    View view1;
    ViewHolder viewHolder1;
    int numPosition;
    List<String> selected = new ArrayList<>();

    public RecyclerViewAdapter(Context context1, String[] SubjectValues1) {

        SubjectValues = SubjectValues1;
        context = context1;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view1 = LayoutInflater.from(context).inflate(R.layout.recyclerview_items, parent, false);

        viewHolder1 = new ViewHolder(view1);
        itemViewList.add(view1);
        numPosition = itemViewList.size();
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final int[] numposi = new int[numPosition];
        for (int i = 0; i < numPosition; i++) {
            numposi[i] = 0;
        }
        holder.textView.setText(DashActivity.subjects[position]);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setData1(position);

            }
        });

    }

    public void setData1(int position) {

    }

    @Override
    public int getItemCount() {

        return DashActivity.subjects.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ViewHolder(View v) {

            super(v);

            textView = (TextView) v.findViewById(R.id.subject_textview);
        }
    }
}
