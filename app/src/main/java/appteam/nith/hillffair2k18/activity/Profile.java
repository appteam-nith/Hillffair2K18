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
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;

import org.json.JSONArray;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.dialog.CautionDialog;
import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    LinearLayout progress;
    String pass = "";
    private byte[] byteArray;
    private EditText studentName, rollNumber, branch, contactNumber, referral;
    private String Name, base64a, base64b, RollNumber, Branch, referal, ContactNumber, imgUrl;
    private CircleImageView profilePicture;
    private TextView buttonLoadImage, save;
    private Bitmap bmp, img;
    private int PICK_PHOTO_CODE = 1046;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        AndroidNetworking.initialize(getApplicationContext());
        progress = findViewById(R.id.loadwall);
        Map config = new HashMap();
        config.put("cloud_name", "appteam");
        MediaManager.init(this, config);

        buttonLoadImage = findViewById(R.id.galleryView);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                buttonLoadImage.setText("     ");
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, PICK_PHOTO_CODE);
                }
            }
        });
        initUI();
        CautionDialog cautionDialog = new CautionDialog(Profile.this);
        cautionDialog.show();
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
            byteArray = bs.toByteArray();
            bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            img = getResizedBitmap(bmp, 150);
            pass = encodeTobase64(img);
            profilePicture = findViewById(R.id.profilePicture);
            profilePicture.setImageBitmap(img);
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

    public void initUI() {
        SharedPreferences prefs = getSharedPreferences("number", Context.MODE_PRIVATE);
        String check = prefs.getString("name", "gsbs");

        if (!check.equals("gsbs")) {
            startActivity(new Intent(Profile.this, DashActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        }

        studentName = findViewById(R.id.studentName);
        rollNumber = findViewById(R.id.rollNumber);
        referral = findViewById(R.id.referal);
        branch = findViewById(R.id.branch);
        contactNumber = findViewById(R.id.contactNumber);
        final SharedPreferences sharedPreferences = getSharedPreferences("number", Context.MODE_PRIVATE);
        contactNumber.setText(sharedPreferences.getString("numberMobile", "None").replace("+91 ", ""));
        contactNumber.setEnabled(false);
        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                setdata();
            }

        });
    }

    public void setdata() {
        Name = String.valueOf(studentName.getText());
        RollNumber = String.valueOf(rollNumber.getText());
        Branch = String.valueOf(branch.getText());
        referal = String.valueOf(referral.getText());
        ContactNumber = contactNumber.getText().toString();

        if (Name.length() == 0 || RollNumber.length() == 0 || Branch.length() == 0 || ContactNumber.length() == 0) {
            Toast.makeText(Profile.this, "Seems You Didn`t enter all the details", Toast.LENGTH_SHORT).show();
        } else {
            final SharedPreferences sharedPreferences = getSharedPreferences("number", Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = sharedPreferences.edit();

            if (pass == "") {
                Toast.makeText(Profile.this, "Please select profile picture", Toast.LENGTH_SHORT).show();
            } else if (Name == "" || RollNumber == "" || Branch == "" || ContactNumber == "" || pass == "" || RollNumber =="0") {
                Toast.makeText(Profile.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                editor.putString("name", Name);
                editor.putString("roll number", RollNumber);
                editor.putString("Branch", Branch);
                editor.putString("Phone", ContactNumber);
                editor.putString("Image", pass);
                editor.commit();
                progress.setVisibility(View.VISIBLE);
                String requestId = MediaManager.get().upload(byteArray)
                        .unsigned("kifap7u6")
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
                                imgUrl = String.valueOf(resultData.get("url"));
                                post(ContactNumber);
                                startActivity(new Intent(Profile.this, DashActivity.class));
                                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                editor.putString("ImageURL", String.valueOf(resultData.get("url")));
                                editor.commit();
                                finish();
                            }

                            @Override
                            public void onError(String requestId, ErrorInfo error) {
                            }

                            @Override
                            public void onReschedule(String requestId, ErrorInfo error) {
                            }
                        })
                        .dispatch(Profile.this);

            }
        }
    }

    public void post(String ContactNumber) {
        try {
//            byte[] data = referal.getBytes("UTF-8");
            base64a = referal;
            if (base64a.equals(""))
                base64a = "0";
            byte[] data1 = imgUrl.getBytes("UTF-8");
            base64b = Base64.encodeToString(data1, Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.print(getString(R.string.baseUrl) + "postprofile/" + Name + "/" + RollNumber + "/" + ContactNumber);//22
        AndroidNetworking.get(getString(R.string.baseUrl) + "postprofile/" + Name + "/" + RollNumber + "/" + ContactNumber + "/" + base64a + "/" + base64b)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        progress.setVisibility(View.GONE);
                        // do anything with response
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//        finish();
        Toast.makeText(this, "Please fill details to register", Toast.LENGTH_SHORT).show();
    }
}
