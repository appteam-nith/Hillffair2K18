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
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import appteam.nith.hillffair2k18.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    EditText studentName, rollNumber, branch, contactNumber;
    String Name, RollNumber, Branch, ContactNumber;
    CircleImageView profilePicture;
    TextView buttonLoadImage, save;
    Bitmap  bmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        buttonLoadImage = findViewById(R.id.galleryView);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                buttonLoadImage.setText("     ");
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, Gallery.PICK_PHOTO_CODE);
                }
            }
        });
        initUI();
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
            Intent i = new Intent(this, ImageUI.class);
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            selectedImage.compress(Bitmap.CompressFormat.JPEG, 50, bs);
            byte[] byteArray = bs.toByteArray();
            bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            profilePicture = findViewById(R.id.profilePicture);
            profilePicture.setImageBitmap(bmp);
        }


    }

    public void initUI() {
        SharedPreferences prefs = getSharedPreferences("number", Context.MODE_PRIVATE);
        String check = prefs.getString("name", "gsbs");
//        studentName.setText(check);
        if (!check.equals("gsbs"))
        {
            startActivity(new Intent(Profile.this, DashActivity.class));
            finish();
        }

        studentName = findViewById(R.id.studentName);
        rollNumber = findViewById(R.id.rollNumber);
        branch = findViewById(R.id.branch);
        contactNumber = findViewById(R.id.contactNumber);
        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                setdata();
            }

        });
    }

    public void setdata() {

            Name = (studentName.getText()).toString();
            RollNumber = rollNumber.getText().toString();
            Branch = branch.getText().toString();
            ContactNumber = contactNumber.getText().toString();
            if (Name.length() == 0 || RollNumber.length() == 0 || Branch.length() == 0 || ContactNumber.length() == 0) {
                Toast.makeText(Profile.this, "Seems You Didn`t enter all the details", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences sharedPreferences = getSharedPreferences("number", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", Name);
                editor.putString("roll number", RollNumber);
                editor.putString("Branch", Branch);
                editor.putString("Phone", ContactNumber);
                editor.putString("Image",encodeTobase64(bmp));
                editor.commit();
                startActivity(new Intent(Profile.this, DashActivity.class));
                finish();
            }
    }
    public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 50, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }
}
