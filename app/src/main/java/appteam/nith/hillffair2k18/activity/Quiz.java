package appteam.nith.hillffair2k18.activity;
/**
 * Created by LENOVO on 24-09-2018.
 */
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.view.View;
import appteam.nith.hillffair2k18.R;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.model.Club;

public class Quiz extends AppCompatActivity implements View.OnClickListener{
TextView textTimer,q1;
Button o1,o2,o3,o4;
String option1,option2,check,option3,option4,ans,ques1;
int time,points=0;
ArrayList<String> optionA = new ArrayList<String>();
ArrayList<String> optionB = new ArrayList<String>();
    ArrayList<String> optionC = new ArrayList<String>();
    ArrayList<String> optionD = new ArrayList<String>();
    ArrayList<String> answers = new ArrayList<String>();
    ArrayList<String> question = new ArrayList<String>();
    public int count = 0,l;
    int color;
    JSONArray ques;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        AndroidNetworking.initialize(getApplicationContext());
            time=15;
            textTimer = (TextView)findViewById(R.id.timer);
            setdata();
//            getdata();
////            start();
        }

        public void setdata()
        {
            AndroidNetworking.get("http://hillffair.tk/getquiz")
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try
                            {
                                ques = response.getJSONArray("questions");
                                l = ques.length();
                                for (int i=0;i<l;i++) {
                                    JSONObject json = ques.getJSONObject(i);
//                                    q1setText(o);
                                    ques1 = json.getString("ques");
                                    option1= json.getString("option1");
                                    option2= json.getString("option2");
                                    option3= json.getString("option3");
                                    option4= json.getString("option4");
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
    int i = 0;
        public void getdata()
        {
            q1 = findViewById(R.id.ques);
            o1 = findViewById(R.id.optiona);
            o2 = findViewById(R.id.optionb);
            o3 = findViewById(R.id.optionc);
            o4 = findViewById(R.id.optiond);
            color = o2.getSolidColor();
            o1.setOnClickListener(this);
            o2.setOnClickListener(this);
            o3.setOnClickListener(this);
            o4.setOnClickListener(this);
            start();
        }
        public void start()
        {

            new CountDownTimer(15000, 1000) {

                public void onTick(long millisUntilFinished) {

                     check = answers.get(i);
                    textTimer.setText("0:"+checkDigit(time));
                    q1.setText(question.get(i));
                    o1.setText(optionA.get(i));
                    o2.setText(optionB.get(i));
                    o3.setText(optionC.get(i));
                    o4.setText(optionD.get(i));
                    time--;

                }
                public void onFinish() {
                    o1.setClickable(true);
                    o2.setClickable(true);
                    o3.setClickable(true);
                    o4.setClickable(true);
                    o1.setBackgroundColor(color);
                    o2.setBackgroundColor(color);
                    o3.setBackgroundColor(color);
                    o4.setBackgroundColor(color);
                    time  = 15;
                    i++;
                    if (i<10)
                        start();
                    else
                    {
                        textTimer.setText("FINISHED");
                    }

                }

            }.start();

        }
    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }
    public void visible(){
            o1.setClickable(false);
            o2.setClickable(false);
            o3.setClickable(false);
            o4.setClickable(false);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.optiona:
                if (check.equals("1"))
                {
                    points++;
                }
                visible();
                o1.setBackground(getResources().getDrawable(R.drawable.yellow_orange));
                break;
            case R.id.optionb:
                if (check.equals("2"))
                {
                    points++;
                }
                visible();
                o2.setBackground(getResources().getDrawable(R.drawable.yellow_orange));
                break;

            case R.id.optionc:
                if (check.equals("3"))
                {
                    points++;
                }
                visible();
                o3.setBackground(getResources().getDrawable(R.drawable.yellow_orange));
                break;
            case R.id.optiond:
                if (check.equals("4"))
                {
                    points++;
                }
                visible();
                o4.setBackground(getResources().getDrawable(R.drawable.yellow_orange));
                break;

        }

    }
}
