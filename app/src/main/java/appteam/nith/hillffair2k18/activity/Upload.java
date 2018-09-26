package appteam.nith.hillffair2k18.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import appteam.nith.hillffair2k18.R;

/**
 * Coded by ThisIsNSH on Someday
 */

public class Upload extends AppCompatActivity {

    CardView cardView;
    ImageView image;
    RelativeLayout relativeLayout1;
    Bitmap img, bmp;
    String base64b, check;
    byte[] byteArray;

    public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);
        setupdata();

        Map config = new HashMap();
        config.put("cloud_name", "appteam");
        MediaManager.init(this, config);
        Bundle bundle = getIntent().getExtras();
        byteArray = bundle.getByteArray("imageUpload");
        image = findViewById(R.id.image);
        try {
            bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            img = getResizedBitmap(bmp, 700);
            image.setImageBitmap(img);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setupdata() {
        relativeLayout1 = findViewById(R.id.rel1);
        cardView = findViewById(R.id.upload1);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout1.setVisibility(View.VISIBLE);
                String requestId = MediaManager.get().upload(byteArray)
                        .unsigned("k5vtuu12")
                        .callback(new UploadCallback() {
                            @Override
                            public void onStart(String requestId) {
                            }

                            @Override
                            public void onProgress(String requestId, long bytes, long totalBytes) {
                            }

                            @Override
                            public void onSuccess(String requestId, Map resultData) {
                                System.out.println(resultData.get("url"));
                                String asd = String.valueOf(resultData.get("url"));
                                SharedPreferences prefs = getSharedPreferences("number", Context.MODE_PRIVATE);
                                check = prefs.getString("roll number", "17mi524");
                                Toast toast = Toast.makeText(Upload.this, "Uploaded", Toast.LENGTH_SHORT);
                                toast.show();
                                try {
                                    byte[] data1 = asd.getBytes("UTF-8");
                                    base64b = Base64.encodeToString(data1, Base64.DEFAULT);
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                AndroidNetworking.get("http://hillffair.tk/postwall/" + check + "/" + base64b)
                                        .build()
                                        .getAsJSONObject(new JSONObjectRequestListener() {

                                            @Override
                                            public void onResponse(org.json.JSONObject response) {
                                                startActivity(new Intent(Upload.this, DashActivity.class));
                                                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                            }

                                            @Override
                                            public void onError(ANError error) {
                                                // handle error
                                            }
                                        });

                            }

                            @Override
                            public void onError(String requestId, ErrorInfo error) {
                                // your code here
                            }

                            @Override
                            public void onReschedule(String requestId, ErrorInfo error) {
                                // your code here
                            }
                        })
                        .dispatch(Upload.this);
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