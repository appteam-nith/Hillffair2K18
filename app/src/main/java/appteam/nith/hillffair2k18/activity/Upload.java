package appteam.nith.hillffair2k18.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.RelativeLayout;
import appteam.nith.hillffair2k18.R;

/**
 * Created by naman on 21-09-2018.
 */

public class Upload extends AppCompatActivity {

    CardView cardView;
    RelativeLayout relativeLayout1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);
        setupdata();
    }

    public void setupdata() {
        relativeLayout1=findViewById(R.id.rel1);
        cardView = findViewById(R.id.upload1);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             relativeLayout1.setVisibility(View.VISIBLE);
            }
        });
    }
}
