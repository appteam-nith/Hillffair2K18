package appteam.nith.hillffair2k18.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import appteam.nith.hillffair2k18.R;

/**
 * Created by naman on 21-09-2018.
 */

public class Upload extends AppCompatActivity {

    CardView cardView;
    ImageView image;
    RelativeLayout relativeLayout1;
    Bitmap img, bmp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);
        Bundle bundle = getIntent().getExtras();
        byte[] byteArray = bundle.getByteArray("imageUpload");
        image = findViewById(R.id.image);
        try {
            bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            img = getResizedBitmap(bmp, 700);
            image.setImageBitmap(img);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setupdata();
    }

    public void setupdata() {
        relativeLayout1 = findViewById(R.id.rel1);
        cardView = findViewById(R.id.upload1);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout1.setVisibility(View.VISIBLE);
                Toast toast = Toast.makeText(Upload.this, "Processing", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        findViewById(R.id.backBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Upload.this, DashActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}