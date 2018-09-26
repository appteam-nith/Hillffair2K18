package appteam.nith.hillffair2k18.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import appteam.nith.hillffair2k18.R;

public class Housie extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Integer> list = new ArrayList<Integer>();
    ArrayList<TextView> numb = new ArrayList<TextView>();
    int total = 15, i, count = 0;
    int[] checked = new int[15];
    TextView number_1, number_2, number_3, number, number_4, number_5, number_6, number_7, number_8, number_9, number_10, number_11, number_12, number_13, number_14, number_15;
    String currentNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housie);
        AndroidNetworking.initialize(getApplicationContext());
        init();
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Housie.this, DashActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        getdata();
    }

    public void getdata() {

        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {

                                  @Override
                                  public void run() {
                                      AndroidNetworking.get("http://192.168.43.52:5000/gettambolanumber")
                                              .build()
                                              .getAsJSONObject(new JSONObjectRequestListener() {
                                                  @Override
                                                  public void onResponse(JSONObject response) {
                                                      // do anything with response
                                                      try {
                                                          currentNumber = response.getString("number");
                                                          number.setText(currentNumber);
                                                      } catch (JSONException e) {
                                                          e.printStackTrace();
                                                      }
                                                  }

                                                  @Override
                                                  public void onError(ANError error) {
                                                  }
                                              });

                                  }
                              },
                0,
                15000);
    }

    public void init() {
        number_1 = findViewById(R.id.number_1);
        number_2 = findViewById(R.id.number_2);
        number_3 = findViewById(R.id.number_3);
        number_4 = findViewById(R.id.number_4);
        number_5 = findViewById(R.id.number_5);
        number_6 = findViewById(R.id.number_6);
        number_7 = findViewById(R.id.number_7);
        number_8 = findViewById(R.id.number_8);
        number_9 = findViewById(R.id.number_9);
        number_10 = findViewById(R.id.number_10);
        number_11 = findViewById(R.id.number_11);
        number_12 = findViewById(R.id.number_12);
        number_13 = findViewById(R.id.number_13);
        number_14 = findViewById(R.id.number_14);
        number_15 = findViewById(R.id.number_15);
        number = findViewById(R.id.number);
        setdata();
    }

    public void setdata() {
        number_1.setOnClickListener(this);
        number_2.setOnClickListener(this);
        number_3.setOnClickListener(this);
        number_4.setOnClickListener(this);
        number_5.setOnClickListener(this);
        number_6.setOnClickListener(this);
        number_7.setOnClickListener(this);
        number_8.setOnClickListener(this);
        number_9.setOnClickListener(this);
        number_10.setOnClickListener(this);
        number_11.setOnClickListener(this);
        number_12.setOnClickListener(this);
        number_13.setOnClickListener(this);
        number_14.setOnClickListener(this);
        number_15.setOnClickListener(this);


        numb.add(number_1);
        numb.add(number_2);
        numb.add(number_3);
        numb.add(number_4);
        numb.add(number_5);
        numb.add(number_6);
        numb.add(number_7);
        numb.add(number_8);
        numb.add(number_9);
        numb.add(number_10);
        numb.add(number_11);
        numb.add(number_12);
        numb.add(number_13);
        numb.add(number_14);
        numb.add(number_15);

        for (i = 1; i <= 90; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        array();
    }


    public void array() {
        for (i = 0; i < 15; i++) {
            String nu = list.get(i).toString();
            numb.get(i).setText(nu);
            checked[i] = 0;
        }
    }

    public void disqualified() {
//        number_1.setClickable(false);
//        number_2.setClickable(false);
//        number_3.setClickable(false);
//        number_4.setClickable(false);
//        number_5.setClickable(false);
//        number_6.setClickable(false);
//        number_7.setClickable(false);
//        number_8.setClickable(false);
//        number_9.setClickable(false);
//        number_10.setClickable(false);
//        number_11.setClickable(false);
//        number_12.setClickable(false);
//        number_13.setClickable(false);
//        number_14.setClickable(false);
//        number_15.setClickable(false);


    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.number_1:
                number_1.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                if (number_1.getText() == currentNumber) {
                    if (checked[0] == 0) {
                        checked[0] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }
                    if (count == 15)
                        win();
                } else {
                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    disqualified();
                }

                break;
            case R.id.number_2:
                number_2.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                if (number_2.getText() == currentNumber) {
                    if (checked[1] == 0) {
                        checked[1] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }
                    if (count == 15)
                        win();
                } else {
                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    disqualified();
                }

                break;
            case R.id.number_3:
                number_3.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                if (number_3.getText() == currentNumber) {
                    if (checked[2] == 0) {
                        checked[2] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }
                    if (count == 15)
                        win();
                } else {
                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    disqualified();
                }

                break;
            case R.id.number_4:
                number_4.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                if (number_4.getText() == currentNumber) {
                    if (checked[3] == 0) {
                        checked[3] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }
                    if (count == 15)
                        win();
                } else {
                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    disqualified();
                }

                break;
            case R.id.number_5:
                number_5.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                if (number_5.getText() == currentNumber) {
                    if (checked[4] == 0) {
                        checked[4] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }
                    if (count == 15)
                        win();
                } else {
                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    disqualified();
                }

                break;
            case R.id.number_6:
                number_6.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                if (number_6.getText() == currentNumber) {
                    if (checked[5] == 0) {
                        checked[5] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }
                    if (count == 15)
                        win();
                } else {
                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    disqualified();
                }

                break;
            case R.id.number_7:
                number_7.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                if (number_7.getText() == currentNumber) {
                    if (checked[6] == 0) {
                        checked[6] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }
                    if (count == 15)
                        win();
                } else {
                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    disqualified();
                }

                break;
            case R.id.number_8:
                number_8.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                if (number_8.getText() == currentNumber) {
                    if (checked[7] == 0) {
                        checked[7] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }
                    if (count == 15)
                        win();
                } else {
                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    disqualified();
                }

                break;
            case R.id.number_9:
                number_9.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                if (number_9.getText() == currentNumber) {
                    if (checked[8] == 0) {
                        checked[8] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }

                    if (count == 15)
                        win();
                } else {
                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    disqualified();
                }

                break;
            case R.id.number_10:
                number_10.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                if (number_10.getText() == currentNumber) {
                    if (checked[9] == 0) {
                        checked[9] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }
                    if (count == 15)
                        win();
                } else {
                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    disqualified();
                }

                break;
            case R.id.number_11:
                number_11.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                if (number_11.getText() == currentNumber) {
                    if (checked[10] == 0) {
                        checked[10] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }
                    if (count == 15)
                        win();
                } else {
                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    disqualified();
                }

                break;
            case R.id.number_12:
                number_12.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                if (number_12.getText() == currentNumber) {
                    if (checked[11] == 0) {
                        checked[11] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }
                    if (count == 15)
                        win();
                } else {
                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    disqualified();
                }

                break;
            case R.id.number_13:
                number_13.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                if (number_13.getText() == currentNumber) {

                    if (checked[12] == 0) {
                        checked[12] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }

                    if (count == 15)
                        win();
                } else {
                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    disqualified();
                }

                break;
            case R.id.number_14:
                number_14.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                if (number_14.getText() == currentNumber) {
                    if (checked[13] == 0) {
                        checked[0] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }

                    if (count == 15)
                        win();
                } else {
                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    disqualified();
                }

                break;
            case R.id.number_15:
                number_15.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                if (number_15.getText() == currentNumber) {
                    if (checked[14] == 0) {
                        checked[14] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }

                    if (count == 15)
                        win();
                } else {
                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    disqualified();
                }
                break;
        }
    }

    public void win() {
        Toast.makeText(this, "You WIN!", Toast.LENGTH_SHORT).show();
    }
}
