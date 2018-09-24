package appteam.nith.hillffair2k18.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.adapter.ScrollAdapter;
import appteam.nith.hillffair2k18.listener.RecyclerItemClickListener;
import appteam.nith.hillffair2k18.model.Scroll;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Coded by ThisIsNSH on Someday.
 */

public class DashActivity extends AppCompatActivity {

    private LinearLayoutManager linearLayoutManager;
    private ViewPager viewPager;
    private CircleImageView profile;
    private View nav;
    private LottieAnimationView navAnim;
    private RecyclerView recyclerView;
    private ScrollAdapter scrollAdapter;
    private List<Scroll> scrollList = new ArrayList<>();
    private ScrollView scrollView;
    private TextView title, profileNav, aboutNav, settingNav, sponsorNav;
    private RelativeLayout linearLayout;
    private boolean check = true;
    private RelativeLayout navDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
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
        navDrawer = findViewById(R.id.navDrawer);
        profileNav = findViewById(R.id.profileNav);
        aboutNav = findViewById(R.id.aboutNav);
        settingNav = findViewById(R.id.settingNav);
        sponsorNav = findViewById(R.id.sponsorNav);

        viewPager = findViewById(R.id.viewpager);

        final SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), DashActivity.this);
        viewPager.setAdapter(adapter);

        nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check) {
                    ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
                    valueAnimator.setDuration(1000);
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
                    navDrawer.setVisibility(View.VISIBLE);
                    profileNav.setVisibility(View.VISIBLE);
                    aboutNav.setVisibility(View.VISIBLE);
                    settingNav.setVisibility(View.VISIBLE);
                    sponsorNav.setVisibility(View.VISIBLE);
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(navDrawer, "alpha", 0, 1);
                    ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(recyclerView, "alpha", 1, 0);

                    ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(aboutNav, "alpha", 0, 1);
                    ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(profileNav, "alpha", 0, 1);
                    ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(settingNav, "alpha", 0, 1);
                    ObjectAnimator objectAnimator5 = ObjectAnimator.ofFloat(sponsorNav, "alpha", 0, 1);

                    ObjectAnimator objectAnimator6 = ObjectAnimator.ofFloat(aboutNav, "translationY", 15f, 0f);
                    ObjectAnimator objectAnimator7 = ObjectAnimator.ofFloat(profileNav, "translationY", 15f, 0f);
                    ObjectAnimator objectAnimator8 = ObjectAnimator.ofFloat(settingNav, "translationY", 15f, 0f);
                    ObjectAnimator objectAnimator9 = ObjectAnimator.ofFloat(sponsorNav, "translationY", 15f, 0f);

                    objectAnimator5.setDuration(700);
                    objectAnimator4.setDuration(700);
                    objectAnimator3.setDuration(700);
                    objectAnimator6.setDuration(700);
                    objectAnimator7.setDuration(700);
                    objectAnimator8.setDuration(700);
                    objectAnimator9.setDuration(700);
                    objectAnimator2.setDuration(700);
                    objectAnimator1.setDuration(500);
                    objectAnimator.setDuration(500);

                    objectAnimator2.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator3.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator4.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator5.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator6.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator7.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator8.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator9.setInterpolator(new AnticipateOvershootInterpolator());

                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(objectAnimator, objectAnimator1, objectAnimator2, objectAnimator3, objectAnimator4, objectAnimator5, objectAnimator6, objectAnimator7, objectAnimator8, objectAnimator9);
                    animatorSet.start();
                    animatorSet.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            navDrawer.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                            profileNav.setVisibility(View.VISIBLE);
                            aboutNav.setVisibility(View.VISIBLE);
                            settingNav.setVisibility(View.VISIBLE);
                            sponsorNav.setVisibility(View.VISIBLE);

                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });

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
                    recyclerView.setVisibility(View.VISIBLE);
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(navDrawer, "alpha", 1, 0);
                    ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(recyclerView, "alpha", 0, 1);

                    ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(aboutNav, "alpha", 1, 0);
                    ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(profileNav, "alpha", 1, 0);
                    ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(settingNav, "alpha", 1, 0);
                    ObjectAnimator objectAnimator5 = ObjectAnimator.ofFloat(sponsorNav, "alpha", 1, 0);

                    ObjectAnimator objectAnimator6 = ObjectAnimator.ofFloat(aboutNav, "translationY", 0f, 15f);
                    ObjectAnimator objectAnimator7 = ObjectAnimator.ofFloat(profileNav, "translationY", 0f, 15f);
                    ObjectAnimator objectAnimator8 = ObjectAnimator.ofFloat(settingNav, "translationY", 0f, 15f);
                    ObjectAnimator objectAnimator9 = ObjectAnimator.ofFloat(sponsorNav, "translationY", 0f, 15f);

                    objectAnimator.setDuration(500);
                    objectAnimator5.setDuration(500);
                    objectAnimator1.setDuration(500);
                    objectAnimator4.setDuration(500);
                    objectAnimator3.setDuration(500);
                    objectAnimator6.setDuration(500);
                    objectAnimator7.setDuration(500);
                    objectAnimator8.setDuration(500);
                    objectAnimator9.setDuration(500);
                    objectAnimator2.setDuration(500);

                    objectAnimator2.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator3.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator4.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator5.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator6.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator7.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator8.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator9.setInterpolator(new AnticipateOvershootInterpolator());

                    objectAnimator1.setStartDelay(350);
                    objectAnimator.setStartDelay(350);

                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(objectAnimator, objectAnimator1, objectAnimator2, objectAnimator3, objectAnimator4, objectAnimator5, objectAnimator6, objectAnimator7, objectAnimator8, objectAnimator9);
                    animatorSet.start();
                    animatorSet.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            recyclerView.setVisibility(View.VISIBLE);
                            navDrawer.setVisibility(View.GONE);
                            profileNav.setVisibility(View.GONE);
                            aboutNav.setVisibility(View.GONE);
                            settingNav.setVisibility(View.GONE);
                            sponsorNav.setVisibility(View.GONE);

                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                }
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                }
            });

        linearLayoutManager = new LinearLayoutManager(DashActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        getData();
        scrollAdapter = new ScrollAdapter(scrollList, DashActivity.this);
        recyclerView.setAdapter(scrollAdapter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.findViewHolderForAdapterPosition(0).itemView.performClick();
                animateViewsOfRecyclerView(0);
            }
        },100);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        animateViewsOfRecyclerView(position);
                        linearLayoutManager.setSmoothScrollbarEnabled(true);
                        linearLayoutManager.smoothScrollToPosition(recyclerView, null, position);
//                        linearLayoutManager.scrollToPositionWithOffset(position, 100);
                        viewPager.setCurrentItem(position, true);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

//        SnapHelper snapHelperStart = new GravitySnapHelper(Gravity.START);
//        snapHelperStart.attachToRecyclerView(recyclerView);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int state) {
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
//              linearLayoutManager.scrollToPositionWithOffset(position, 100);
                linearLayoutManager.setSmoothScrollbarEnabled(true);
                linearLayoutManager.smoothScrollToPosition(recyclerView, null, position);
                animateViewsOfRecyclerView(position);
            }
        });


    }


    public void animateViewsOfRecyclerView(int position) {
        for (int i = 0; i < scrollAdapter.getItemCount(); ++i) {
            if (i != position) {
                animateViewDown(scrollAdapter.getView((ScrollAdapter.MyViewHolder) recyclerView.findViewHolderForAdapterPosition(i)));
                animateViewDown1(scrollAdapter.getView1((ScrollAdapter.MyViewHolder) recyclerView.findViewHolderForAdapterPosition(i)));
            } else {
                animateViewUp(scrollAdapter.getView((ScrollAdapter.MyViewHolder) recyclerView.findViewHolderForAdapterPosition(i)));
                animateViewUp1(scrollAdapter.getView1((ScrollAdapter.MyViewHolder) recyclerView.findViewHolderForAdapterPosition(i)));
            }
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

    public void animateViewUp1(final View view) {
        if (view.getTranslationY() == 0) {

            view.setVisibility(View.VISIBLE);
            ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(view, "alpha", 0, 1);
            objectAnimator1.setInterpolator(new AnticipateOvershootInterpolator());
            objectAnimator1.setDuration(500);
            ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(view, "scaleX", 0, 1);
            objectAnimator2.setInterpolator(new AnticipateOvershootInterpolator());
            objectAnimator2.setDuration(500);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationY", -15f);
            objectAnimator.setInterpolator(new AnticipateOvershootInterpolator());
            objectAnimator.setDuration(500);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(objectAnimator, objectAnimator1, objectAnimator2);
            animatorSet.start();
            animatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }
                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
    }

    public void animateViewDown1(final View view) {
        if (view.getTranslationY() != 0) {

            view.setVisibility(View.VISIBLE);
            ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(view, "alpha", 1, 0);
            objectAnimator1.setInterpolator(new AnticipateOvershootInterpolator());
            objectAnimator1.setDuration(500);
            ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(view, "scaleX", 1, 0);
            objectAnimator2.setInterpolator(new AnticipateOvershootInterpolator());
            objectAnimator2.setDuration(500);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationY", 0f);
            objectAnimator.setInterpolator(new AnticipateOvershootInterpolator());
            objectAnimator.setDuration(500);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(objectAnimator, objectAnimator1, objectAnimator2);
            animatorSet.start();
            animatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
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
