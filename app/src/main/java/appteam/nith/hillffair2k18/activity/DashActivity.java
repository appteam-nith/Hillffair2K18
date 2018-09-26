package appteam.nith.hillffair2k18.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.adapter.ScrollAdapter;
import appteam.nith.hillffair2k18.dialog.Infodialog2;
import appteam.nith.hillffair2k18.listener.RecyclerItemClickListener;
import appteam.nith.hillffair2k18.model.Scroll;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Coded by ThisIsNSH on Someday.
 */

public class DashActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    Bitmap bmp, img;
    private LinearLayoutManager linearLayoutManager;
    private ViewPager viewPager;
    private CircleImageView profile;
    private View nav;
    private LottieAnimationView navAnim;
    private RecyclerView recyclerView;
    private ScrollAdapter scrollAdapter;
    private List<Scroll> scrollList = new ArrayList<>();
    private ScrollView scrollView;
    private TextView title, profileNav, aboutNav, settingNav, sponsorNav, callNav, notifNav, mapNav, hillffairNav, contributorNav;
    private RelativeLayout linearLayout;
    private boolean check = true;
    private RelativeLayout navDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
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
        callNav = findViewById(R.id.callNav);
        notifNav = findViewById(R.id.notifNav);
        hillffairNav = findViewById(R.id.hillffairNav);
        mapNav = findViewById(R.id.mapNav);
        profile = findViewById(R.id.profile);
        viewPager = findViewById(R.id.viewpager);
        contributorNav = findViewById(R.id.contributorNav);
        setupdata();
    }

    public void setupdata() {

        profileNav.setOnClickListener(this);
        profile.setOnClickListener(this);
        aboutNav.setOnClickListener(this);
        callNav.setOnClickListener(this);
        notifNav.setOnClickListener(this);
        mapNav.setOnClickListener(this);
        settingNav.setOnClickListener(this);
        nav.setOnClickListener(this);
        hillffairNav.setOnClickListener(this);
        viewPager.addOnPageChangeListener(this);
        contributorNav.setOnClickListener(this);

        Infodialog2 infodialog2 = new Infodialog2(DashActivity.this);
        infodialog2.show();

        SharedPreferences prefs = getSharedPreferences("number", Context.MODE_PRIVATE);
        String check2 = prefs.getString("Image", "https://www.fluigent.com/wp-content/uploads/2018/07/default-avatar-BW.png");
        if (!check2.equals("https://www.fluigent.com/wp-content/uploads/2018/07/default-avatar-BW.png")) {
            Bitmap img = setProfile(check2);
            profile.setImageBitmap(img);
        } else {
            Picasso.get().load(check2).resize(80, 80).centerCrop().into(profile);
        }


        final SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), DashActivity.this);
        viewPager.setAdapter(adapter);

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
        }, 100);

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

    public Bitmap setProfile(String image) {
        byte[] decodedByte = Base64.decode(image, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile:
            case R.id.profileNav:
                startActivity(new Intent(this, ProfileMain.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            case R.id.aboutNav:
                startActivity(new Intent(DashActivity.this, AppTeam.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            case R.id.settingNav:
                startActivity(new Intent(DashActivity.this, SettingsActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;

            case R.id.hillffairNav:
                startActivity(new Intent(DashActivity.this, AboutHillffair.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            case R.id.callNav:
                startActivity(new Intent(DashActivity.this, EmergencyContact.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            case R.id.notifNav:
                Toast toast = Toast.makeText(DashActivity.this, "Coming Soon", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.mapNav:
                Toast toast1 = Toast.makeText(DashActivity.this, "Coming Soon", Toast.LENGTH_SHORT);
                toast1.show();
                break;
            case R.id.contributorNav:
                startActivity(new Intent(DashActivity.this, ContributorsActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            case R.id.nav:
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
                    settingNav.setVisibility(View.GONE);
                    sponsorNav.setVisibility(View.GONE);
                    callNav.setVisibility(View.VISIBLE);
                    notifNav.setVisibility(View.VISIBLE);
                    mapNav.setVisibility(View.GONE);
                    hillffairNav.setVisibility(View.VISIBLE);
                    contributorNav.setVisibility(View.VISIBLE);

                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(navDrawer, "alpha", 0, 1);
                    ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(recyclerView, "alpha", 1, 0);
                    ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(aboutNav, "alpha", 0, 1);
                    ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(profileNav, "alpha", 0, 1);
                    ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(settingNav, "alpha", 0, 1);
                    ObjectAnimator objectAnimator5 = ObjectAnimator.ofFloat(sponsorNav, "alpha", 0, 1);
                    ObjectAnimator objectAnimator1a = ObjectAnimator.ofFloat(mapNav, "alpha", 0, 1);
                    ObjectAnimator objectAnimator2a = ObjectAnimator.ofFloat(notifNav, "alpha", 0, 1);
                    ObjectAnimator objectAnimator3a = ObjectAnimator.ofFloat(callNav, "alpha", 0, 1);
                    ObjectAnimator objectAnimator3aa = ObjectAnimator.ofFloat(hillffairNav, "alpha", 0, 1);

                    ObjectAnimator objectAnimator6 = ObjectAnimator.ofFloat(aboutNav, "translationY", 15f, 0f);
                    ObjectAnimator objectAnimator7 = ObjectAnimator.ofFloat(profileNav, "translationY", 15f, 0f);
                    ObjectAnimator objectAnimator8 = ObjectAnimator.ofFloat(settingNav, "translationY", 15f, 0f);
                    ObjectAnimator objectAnimator9 = ObjectAnimator.ofFloat(sponsorNav, "translationY", 15f, 0f);
                    ObjectAnimator objectAnimator4a = ObjectAnimator.ofFloat(callNav, "translationY", 15f, 0f);
                    ObjectAnimator objectAnimator5a = ObjectAnimator.ofFloat(mapNav, "translationY", 15f, 0f);
                    ObjectAnimator objectAnimator6a = ObjectAnimator.ofFloat(notifNav, "translationY", 15f, 0f);
                    ObjectAnimator objectAnimator6aa = ObjectAnimator.ofFloat(hillffairNav, "translationY", 15f, 0f);

                    objectAnimator5.setDuration(500);
                    objectAnimator4.setDuration(500);
                    objectAnimator3.setDuration(500);
                    objectAnimator6.setDuration(500);
                    objectAnimator7.setDuration(500);
                    objectAnimator8.setDuration(500);
                    objectAnimator9.setDuration(500);
                    objectAnimator2.setDuration(500);
                    objectAnimator1.setDuration(500);
                    objectAnimator.setDuration(500);

                    objectAnimator1a.setDuration(500);
                    objectAnimator2a.setDuration(500);
                    objectAnimator3aa.setDuration(500);
                    objectAnimator3a.setDuration(500);
                    objectAnimator4a.setDuration(500);
                    objectAnimator5a.setDuration(500);
                    objectAnimator6a.setDuration(500);
                    objectAnimator6aa.setDuration(500);

                    objectAnimator2.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator3.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator4.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator5.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator6.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator7.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator8.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator9.setInterpolator(new AnticipateOvershootInterpolator());

                    objectAnimator1a.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator2a.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator3a.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator3aa.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator4a.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator5a.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator6a.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator6aa.setInterpolator(new AnticipateOvershootInterpolator());

                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(objectAnimator, objectAnimator1, objectAnimator2, objectAnimator3, objectAnimator4, objectAnimator5, objectAnimator6, objectAnimator7, objectAnimator8, objectAnimator9, objectAnimator1a, objectAnimator2a, objectAnimator3a, objectAnimator3aa, objectAnimator4a, objectAnimator5a, objectAnimator6a, objectAnimator6aa);
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
                            settingNav.setVisibility(View.GONE);
                            sponsorNav.setVisibility(View.GONE);
                            callNav.setVisibility(View.VISIBLE);
                            notifNav.setVisibility(View.VISIBLE);
                            hillffairNav.setVisibility(View.VISIBLE);
                            mapNav.setVisibility(View.GONE);
                            contributorNav.setVisibility(View.VISIBLE);

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
                    valueAnimator.setDuration(750);
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

                    ObjectAnimator objectAnimator4a = ObjectAnimator.ofFloat(callNav, "translationY", 0f, 15f);
                    ObjectAnimator objectAnimator5a = ObjectAnimator.ofFloat(mapNav, "translationY", 0f, 15f);
                    ObjectAnimator objectAnimator6a = ObjectAnimator.ofFloat(notifNav, "translationY", 0f, 15f);
                    ObjectAnimator objectAnimator6aa = ObjectAnimator.ofFloat(hillffairNav, "translationY", 0f, 15f);

                    ObjectAnimator objectAnimator1a = ObjectAnimator.ofFloat(mapNav, "alpha", 1, 0);
                    ObjectAnimator objectAnimator2a = ObjectAnimator.ofFloat(notifNav, "alpha", 1, 0);
                    ObjectAnimator objectAnimator3aa = ObjectAnimator.ofFloat(hillffairNav, "alpha", 1, 0);
                    ObjectAnimator objectAnimator3a = ObjectAnimator.ofFloat(callNav, "alpha", 1, 0);

                    objectAnimator.setDuration(250);
                    objectAnimator5.setDuration(250);
                    objectAnimator1.setDuration(250);
                    objectAnimator4.setDuration(250);
                    objectAnimator3.setDuration(250);
                    objectAnimator6.setDuration(250);
                    objectAnimator7.setDuration(250);
                    objectAnimator8.setDuration(250);
                    objectAnimator9.setDuration(250);
                    objectAnimator2.setDuration(250);

                    objectAnimator1a.setDuration(250);
                    objectAnimator2a.setDuration(250);
                    objectAnimator3a.setDuration(250);
                    objectAnimator3aa.setDuration(250);
                    objectAnimator4a.setDuration(250);
                    objectAnimator5a.setDuration(250);
                    objectAnimator6a.setDuration(250);
                    objectAnimator6aa.setDuration(250);

                    objectAnimator1a.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator2a.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator3a.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator3aa.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator4a.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator5a.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator6a.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator6aa.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator2.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator3.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator4.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator5.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator6.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator7.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator8.setInterpolator(new AnticipateOvershootInterpolator());
                    objectAnimator9.setInterpolator(new AnticipateOvershootInterpolator());

                    objectAnimator1.setStartDelay(0);
                    objectAnimator.setStartDelay(0);

                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(objectAnimator, objectAnimator1, objectAnimator2, objectAnimator3, objectAnimator4, objectAnimator5, objectAnimator6, objectAnimator7, objectAnimator8, objectAnimator9, objectAnimator1a, objectAnimator2a, objectAnimator3a, objectAnimator3aa, objectAnimator4a, objectAnimator5a, objectAnimator6a, objectAnimator6aa);
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
                            callNav.setVisibility(View.GONE);
                            notifNav.setVisibility(View.GONE);
                            hillffairNav.setVisibility(View.GONE);
                            mapNav.setVisibility(View.GONE);

                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                }
                break;

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.smoothScrollToPosition(recyclerView, null, position);
        animateViewsOfRecyclerView(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            Uri photoUri = data.getData();
            Bitmap selectedImage = null;
            try {
                selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            selectedImage.compress(Bitmap.CompressFormat.JPEG, 50, bs);
            byte[] byteArray = bs.toByteArray();
            Intent i = new Intent(this, Upload.class);
            i.putExtra("imageUpload", byteArray);
            startActivity(i);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }


    }


}
