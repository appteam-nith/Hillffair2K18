package appteam.nith.hillffair2k18.activity;

import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.ArrayList;
import java.util.List;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.RecyclerItemClickListener;
import appteam.nith.hillffair2k18.adapter.ScrollAdapter;
import appteam.nith.hillffair2k18.fragment.WallFragment;
import appteam.nith.hillffair2k18.model.Scroll;

public class DashActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ScrollAdapter scrollAdapter;
    private List<Scroll> scrollList = new ArrayList<>();
    private ScrollView scrollView;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, new WallFragment(DashActivity.this));
        ft.commit();
        setupdata();
    }

    public void setupdata() {
        title = findViewById(R.id.title_name);
        scrollView = findViewById(R.id.scrollView);
        recyclerView = findViewById(R.id.recyclerview);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                    if (title.getTranslationY() <= 0 && title.getTranslationY() >= -title.getHeight() && oldScrollY < scrollY) {
//                        title.setTranslationY((float) (-scrollY)*2);
//                        scrollView.setTranslationY((float) (-scrollY)*2);
//                        recyclerView.setTranslationY((float) (-scrollY)*2);
//                    } else if (title.getTranslationY() <= 0 && title.getTranslationY() >= -title.getHeight() && oldScrollY >= scrollY) {
//                        title.setTranslationY((float) (scrollY)*2);
//                        scrollView.setTranslationY((float) (scrollY)*2);
//                        recyclerView.setTranslationY((float) (scrollY)*2);
//                    }
                }
            });

        recyclerView.setLayoutManager(new LinearLayoutManager(DashActivity.this, LinearLayoutManager.HORIZONTAL, false));
        getData();
        scrollAdapter = new ScrollAdapter(scrollList, DashActivity.this);
        recyclerView.setAdapter(scrollAdapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        animateViewsOfRecyclerView(position);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        SnapHelper snapHelperStart = new GravitySnapHelper(Gravity.START);
        snapHelperStart.attachToRecyclerView(recyclerView);

    }

    public void animateViewsOfRecyclerView(int position) {
        for (int i = 0; i < scrollAdapter.getItemCount(); ++i) {
            if (i != position)
                animateViewDown(scrollAdapter.getView((ScrollAdapter.MyViewHolder) recyclerView.findViewHolderForAdapterPosition(i)));
            else
                animateViewUp(scrollAdapter.getView((ScrollAdapter.MyViewHolder) recyclerView.findViewHolderForAdapterPosition(i)));
        }
    }

    public void animateViewUp(TextView textView) {
        if (textView.getTranslationY() == 0) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(textView, "translationY", -15f);
            objectAnimator.setInterpolator(new AnticipateOvershootInterpolator());
            objectAnimator.setDuration(500);
            objectAnimator.start();
        }
    }

    public void animateViewDown(TextView textView) {
        if (textView.getTranslationY() != 0) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(textView, "translationY", 0f);
            objectAnimator.setInterpolator(new AnticipateOvershootInterpolator());
            objectAnimator.setDuration(500);
            objectAnimator.start();
        }
    }

    public void getData() {
        scrollList.add(new Scroll("Live Feed", R.drawable.pic));
        scrollList.add(new Scroll("Quiz & Games", R.drawable.pic));
        scrollList.add(new Scroll("Leaderboard", R.drawable.pic));
        scrollList.add(new Scroll("Clubs", R.drawable.pic));
        scrollList.add(new Scroll("Core Members", R.drawable.pic));
        scrollList.add(new Scroll("Sponsors", R.drawable.pic));
    }
}
