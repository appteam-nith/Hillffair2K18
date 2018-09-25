package appteam.nith.hillffair2k18.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

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
        relativeLayout1 = findViewById(R.id.rel1);
        cardView = findViewById(R.id.upload1);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout1.setVisibility(View.VISIBLE);
                Toast toast=Toast.makeText(Upload.this,"Processing",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 150);
                toast.show();
            }
        });
        findViewById(R.id.backBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent (Upload.this,DashActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }
}
