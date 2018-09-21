package appteam.nith.hillffair2k18.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import appteam.nith.hillffair2k18.R;

/**
 * Created by naman on 19-09-2018.
 */

public class ImageUI extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui);

        ImageView imageView = (ImageView) findViewById(R.id.img1);
        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray("byteArray");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imageView.setImageBitmap(bmp);
    }
}
