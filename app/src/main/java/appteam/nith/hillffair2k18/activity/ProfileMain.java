package appteam.nith.hillffair2k18.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import appteam.nith.hillffair2k18.R;
import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileMain extends AppCompatActivity {
    TextView name1, rollNumber1, referral, branch1, mobile1, reffaralDone;
    CircleImageView profilemain, buttonLoadImage;
    Bitmap bmp;
    String base64a;
    private int PICK_PHOTO_CODE = 1046;

    public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        return imageEncoded;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_main);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        });
        initUI();

        try {
            SharedPreferences prefs = getSharedPreferences("number", Context.MODE_PRIVATE);
            String check = prefs.getString("name", "nullaaa");
            if (!check.equals("nullaaa")) {
                name1.setText(check);
            }
            String check1 = prefs.getString("roll number", "nullaaa");
            if (!check1.equals("nullaaa")) {
                rollNumber1.setText(check1);
                byte[] data = check1.getBytes("UTF-8");
                base64a = Base64.encodeToString(data, Base64.DEFAULT);
                referral.setText(base64a);
            } else {
                referral.setText("No Referral Generated");
            }
            String check2 = prefs.getString("Branch", "nullaaa");
            if (!check2.equals("nullaaa")) {
                branch1.setText(check2);
            }
            String check3 = prefs.getString("Phone", "nullaaa");
            if (!check3.equals("nullaaa")) {
                mobile1.setText(check3);
            }
            String check4 = prefs.getString("Score", "0");
            if (!check3.equals("0")) {
                reffaralDone.setText(check4);
            }
            String image = prefs.getString("Image", "https://www.fluigent.com/wp-content/uploads/2018/07/default-avatar-BW.png");
            if (image.equals("https://www.fluigent.com/wp-content/uploads/2018/07/default-avatar-BW.png")) {
                Picasso.get().load(image).resize(80, 80).centerCrop().into(profilemain);
            } else {
                Bitmap img = decodeBase64(image);
                Bitmap img1 = getResizedBitmap(img, 300);
                profilemain.setImageBitmap(img1);
//            changeProfile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void changeProfile() {
        buttonLoadImage = findViewById(R.id.profilePicture);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, PICK_PHOTO_CODE);
                }
            }
        });
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
            bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            Bitmap img1 = getResizedBitmap(bmp, 300);
            buttonLoadImage.setImageBitmap(img1);
            SharedPreferences sharedPreferences = getSharedPreferences("number", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Image", encodeTobase64(img1));
            editor.commit();
        }
    }

    public Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    public void initUI() {
        name1 = findViewById(R.id.name1);
        referral = findViewById(R.id.referral);
        rollNumber1 = findViewById(R.id.rollNumber1);
        branch1 = findViewById(R.id.branch1);
        mobile1 = findViewById(R.id.mobile1);
        profilemain = findViewById(R.id.profilePicture);
        reffaralDone = findViewById(R.id.referralDone);
    }
}
