package appteam.nith.hillffair2k18.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import appteam.nith.hillffair2k18.R;

public class ProfileMain extends AppCompatActivity {
    TextView name1,rollNumber1,branch1,mobile1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_main);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileMain.this, DashActivity.class);
                startActivity(intent);
            }
        });
initUI();
        SharedPreferences prefs = getSharedPreferences("number", Context.MODE_PRIVATE);
        String check = prefs.getString("name", "nullaaa");
        if (!check.equals("nullaaa")) {
            name1.setText(check);
        }
        String check1 = prefs.getString("roll number", "nullaaa");
        if (!check.equals("nullaaa")) {
            name1.setText(check1);
        }
        String check2 = prefs.getString("Branch", "nullaaa");
        if (!check.equals("nullaaa")) {
            name1.setText(check2);
        }
        String check3 = prefs.getString("Phone", "nullaaa");
        if (!check.equals("nullaaa")) {
            name1.setText(check3);
        }

    }
    public void initUI(){
        name1 = findViewById(R.id.name1);
        rollNumber1=findViewById(R.id.rollNumber1);
        branch1=findViewById(R.id.branch1);
        mobile1=findViewById(R.id.mobile1);
    }
}
