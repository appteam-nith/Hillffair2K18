package appteam.nith.hillffair2k18.Notification;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import appteam.nith.hillffair2k18.R;


/**
 * Created by root on 20/10/16.
 */

public class Notification2 extends AppCompatActivity {

    TextView title, body, launch_url;
    ImageView big_picture;
    CardView sec_card, thrd_card;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_post_expand);

        findViewById(R.id.backBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Notification2.this, NotificationActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        });


        title = (TextView) findViewById(R.id.not2_title);
        body = (TextView) findViewById(R.id.not2_body);
        launch_url = (TextView) findViewById(R.id.launch_url);
        sec_card = (CardView) findViewById(R.id.secondcard);
        thrd_card = (CardView) findViewById(R.id.thiredcard);
        big_picture = (ImageView) findViewById(R.id.not2_big_picture);
        //String id = b.getString("id");
        //String id = bundle.getString("id",null);


        //  Cursor cursor = dbHelper.homeposteinnerdata(id);

        // cursor.moveToFirst();
        Intent i = getIntent();
        Bundle b = i.getBundleExtra("assign");
        String ftitle = b.getString("title");
        String fbody = b.getString("body");
        String fbig_pic = b.getString("bigpicture");
        final String l_url = b.getString("launchurl");
        Log.d("sdgvajdsf", "getstringextras" + body + "_" + fbig_pic + "_" + l_url);
        title.setText(ftitle);
        body.setText(fbody);
        if (l_url != null) {
            launch_url.setText(l_url);
            launch_url.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(l_url));
                    startActivity(i);
                }
            });
        } else {
            thrd_card.setVisibility(View.GONE);
        }
        final ProgressBar progressBar1 = (ProgressBar) findViewById(R.id.progress_not2);
        if (fbig_pic != null) {

            Glide.with(getApplicationContext()).load(fbig_pic).apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    progressBar1.setVisibility(View.GONE);
                    return false;
                }
            }).into(big_picture);

        } else {
            sec_card.setVisibility(View.GONE);
        }


    }
}