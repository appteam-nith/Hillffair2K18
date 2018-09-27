package appteam.nith.hillffair2k18.activity;
/**
 * Created by LENOVO on 24-09-2018.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import appteam.nith.hillffair2k18.R;

public class Quiz extends AppCompatActivity implements View.OnClickListener {

    public int count = 0, l;
    TextView textTimer, q1, point;
    Button o1, o2, o3, o4;
    String option1, option2, check, option3, option4, ans, ques1;
    int time, points = 0;
    ArrayList<String> optionA = new ArrayList<String>();
    ArrayList<String> optionB = new ArrayList<String>();
    ArrayList<String> optionC = new ArrayList<String>();
    ArrayList<String> optionD = new ArrayList<String>();
    ArrayList<String> answers = new ArrayList<String>();
    ArrayList<String> question = new ArrayList<String>();
    int color, status;
    JSONArray ques;
    int i = 0;
    int u;
    TextSwitcher textSwitcher;
    int touch = 0, col;
    private LinearLayout main1, main2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textSwitcher = findViewById(R.id.qu);
        textSwitcher.setInAnimation(Quiz.this, R.anim.fade_in);
        textSwitcher.setOutAnimation(Quiz.this, R.anim.fade_out);
        textSwitcher.addView(new TextView(Quiz.this));
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        AndroidNetworking.initialize(getApplicationContext());
        time = 15;
        setdata();
    }


    public void setdata() {
        AndroidNetworking.get(getString(R.string.baseUrl) + "getquiz")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ques = response.getJSONArray("questions");
                            l = ques.length();
                            for (int i = 0; i < l; i++) {
                                JSONObject json = ques.getJSONObject(i);
//                                    q1setText(o);
                                ques1 = json.getString("ques");
                                option1 = json.getString("option1");
                                option2 = json.getString("option2");
                                option3 = json.getString("option3");
                                option4 = json.getString("option4");
                                optionA.add(option1);
                                optionB.add(option2);
                                optionC.add(option3);
                                optionD.add(option4);
                                question.add(ques1);
//
                                ans = json.getString("ans");
                                answers.add(ans);
                            }
                            getdata();
//                                start();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                    }
                });

    }


    public void getdata() {
//        q1 = findViewById(R.id.ques);
        o1 = findViewById(R.id.optiona);
        o2 = findViewById(R.id.optionb);
        o3 = findViewById(R.id.optionc);
        o4 = findViewById(R.id.optiond);

        main1 = findViewById(R.id.main1);
        main2 = findViewById(R.id.main2);
        point = findViewById(R.id.points);

        color = o2.getSolidColor();
        o1.setOnClickListener(this);
        o2.setOnClickListener(this);
        o3.setOnClickListener(this);
        o4.setOnClickListener(this);
        start();
    }

    public void start() {
        textTimer = findViewById(R.id.timer);
        new CountDownTimer(15000, 1000) {

            public void onTick(long millisUntilFinished) {

                check = answers.get(i);
                textTimer.setText("0:" + checkDigit(time));
                textSwitcher.setText(question.get(i));
                touch = 0;
                col = o1.getSolidColor();
                o1.setText(optionA.get(i));
                o2.setText(optionB.get(i));
                o3.setText(optionC.get(i));
                o4.setText(optionD.get(i));
                time--;

            }

            public void onFinish() {
                int y;
                if (check.equals("1")) {
//                 y = o1.getSolidColor();
                    if (status == 1)
                        points++;
                }
                if (check.equals("2")) {
//                    y = o2.getSolidColor();
                    if (status == 2)
                        points++;
                }

                if (check.equals("3")) {
//                    y = o3.getSolidColor();
                    if (status == 3)
                        points++;
                }

                if (check.equals("4")) {
//                    y = o4.getSolidColor();
                    if (status == 4)
                        points++;
                }
                o1.setBackgroundColor(color);
                o2.setBackgroundColor(color);
                o3.setBackgroundColor(color);
                o4.setBackgroundColor(color);
                time = 15;
                i++;
                if (i < 10)
                    start();
                else {
                    textTimer.setText("FINISHED");
                    main1.setVisibility(View.GONE);
                    main2.setVisibility(View.VISIBLE);
                    point.setText("Correct: " + points + "/10");
                    SharedPreferences prefs = getSharedPreferences("number", Context.MODE_PRIVATE);
                    String roll = prefs.getString("roll number", "gsbs");
                    AndroidNetworking.get(getString(R.string.baseUrl) + "postpoint/" + roll + "/" + String.valueOf(points * 10))
                            .build()
                            .getAsJSONArray(new JSONArrayRequestListener() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    // do anything with response
                                }

                                @Override
                                public void onError(ANError error) {
                                    // handle error
                                }
                            });
                }
            }

        }.start();

    }

    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    public void visible() {
        o1.setBackgroundColor(col);
        o2.setBackgroundColor(col);
        o3.setBackgroundColor(col);
        o4.setBackgroundColor(col);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.optiona:
                visible();
                o1.setBackground(getResources().getDrawable(R.drawable.yellow_orange));
//                u = o1.getBack;
                status = 1;
                break;
            case R.id.optionb:
                visible();
                o2.setBackground(getResources().getDrawable(R.drawable.yellow_orange));
//                u = o2.getSolidColor();
                status = 2;
                break;

            case R.id.optionc:
                visible();
                o3.setBackground(getResources().getDrawable(R.drawable.yellow_orange));
//                u = o3.getSolidColor();
                status = 3;
                break;
            case R.id.optiond:
                visible();
                o4.setBackground(getResources().getDrawable(R.drawable.yellow_orange));
//                u = o4.getSolidColor();
                status = 4;
                break;
        }

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        builder.setTitle("EXIT");
        builder.setMessage("You won't get another chance today");
        builder.setPositiveButton("AGREE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Quiz.super.onBackPressed();
            }
        });
        builder.setNegativeButton("DISAGREE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.show();
    }
}
