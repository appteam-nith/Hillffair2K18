package appteam.nith.hillffair2k18.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import appteam.nith.hillffair2k18.R;

public class Profile extends AppCompatActivity {

    EditText studentName, rollNumber, branch, contactNumber;
    String Name, RollNumber, Branch, ContactNumber;
    ImageView profilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Button buttonLoadImage = findViewById(R.id.galleryView);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, Gallery.PICK_PHOTO_CODE);
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
            Intent i = new Intent(this, ImageUI.class);
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            selectedImage.compress(Bitmap.CompressFormat.JPEG, 50, bs);
            byte[] byteArray = bs.toByteArray();
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            profilePicture = findViewById(R.id.profilePicture);
            profilePicture.setImageBitmap(bmp);
        }

        initUI();
    }

    public void initUI() {
        studentName = findViewById(R.id.studentName);
        rollNumber = findViewById(R.id.rollNumber);
        branch = findViewById(R.id.branch);
        contactNumber = findViewById(R.id.contactNumber);
        setdata();
    }

    public void setdata() {
        Name = (studentName.getText()).toString();
        RollNumber = rollNumber.getText().toString();
        Branch = branch.getText().toString();
        ContactNumber = contactNumber.getText().toString();
    }
}
