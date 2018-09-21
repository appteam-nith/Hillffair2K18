package appteam.nith.hillffair2k18.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.ArrayList;
import java.util.List;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.adapter.ScrollAdapter;
import appteam.nith.hillffair2k18.fragment.WallFragment;
import appteam.nith.hillffair2k18.listener.RecyclerItemClickListener;
import appteam.nith.hillffair2k18.model.Scroll;
import de.hdodenhof.circleimageview.CircleImageView;

public class DashActivity extends AppCompatActivity {

    private CircleImageView profile;
    private View nav;
    private LottieAnimationView navAnim;
    private RecyclerView recyclerView;
    private ScrollAdapter scrollAdapter;
    private List<Scroll> scrollList = new ArrayList<>();
    private ScrollView scrollView;
    private TextView title;
    private RelativeLayout linearLayout;
    private boolean check = true;

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
        linearLayout = findViewById(R.id.ll);
        profile = findViewById(R.id.profile);
        nav = findViewById(R.id.nav);
        navAnim = findViewById(R.id.navAnim);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);


        nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check) {
                    ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
                    valueAnimator.setDuration(1500);
                    valueAnimator.setInterpolator(new AnticipateOvershootInterpolator());
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            if (animation.getAnimatedFraction() <= 0.7f)
                                navAnim.setProgress(animation.getAnimatedFraction());
                        }
                    });
                    valueAnimator.start();
                    check = false;
                } else {
                    ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
                    valueAnimator.setDuration(1500);
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            if (animation.getAnimatedFraction() <= 0.7f)
                                navAnim.setProgress(0.7f - animation.getAnimatedFraction());
                        }
                    });
                    valueAnimator.start();
                    check = true;
                }
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

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
//        SnapHelper snapHelperStart = new GravitySnapHelper(Gravity.START);
//        snapHelperStart.attachToRecyclerView(recyclerView);
        viewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        recyclerView.smoothScrollToPosition(position);
                    }
                });
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
        scrollList.add(new Scroll("Live Feed", R.drawable.live));
        scrollList.add(new Scroll("Quiz & Games", R.drawable.games));
        scrollList.add(new Scroll("Schedule", R.drawable.schedule));
        scrollList.add(new Scroll("Leaderboard", R.drawable.leaderboard));
        scrollList.add(new Scroll("Clubs", R.drawable.club));
        scrollList.add(new Scroll("Core Members", R.drawable.core));
        scrollList.add(new Scroll("Sponsors", R.drawable.sponsor));
    }
}
