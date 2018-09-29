package appteam.nith.hillffair2k18.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;

import appteam.nith.hillffair2k18.R;

public class Housie extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Integer> list = new ArrayList<Integer>();
    ArrayList<TextView> numb = new ArrayList<TextView>();
    ArrayList<Integer> checknumber = new ArrayList<>();
    int total = 15, count = 0, i;
    int Cnumber = 0;
    int[] checked = new int[15];
    TextView number_1, number_2, number_3, number, number_4, number_5, number_6, number_7, number_8, number_9, number_10, number_11, number_12, number_13, number_14, number_15;
    String currentNumber;
    private CardView button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housie);
        AndroidNetworking.initialize(getApplicationContext());
        init();
        getdata();
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Housie.this, R.style.MyDialogTheme);
                builder.create();
                builder.setTitle("EXIT");
                builder.setMessage("You won't get another chance today");
                builder.setPositiveButton("AGREE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    }
                });
                builder.setNegativeButton("DISAGREE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                builder.show();
            }
        });

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Housie.this, R.style.MyDialogTheme);
                builder.create();
                builder.setTitle("EXIT");
                builder.setMessage("You won't get another chance today");
                builder.setPositiveButton("AGREE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    }
                });
                builder.setNegativeButton("DISAGREE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                builder.show();
            }
        });

    }

    public void getdata() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentNumber = checknumber.get(Cnumber).toString();
                number.setText(currentNumber);
                Cnumber += 1;
                if (Cnumber < 40)
                    getdata();
            }
        }, 7000);

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
            checknumber.add(i);
        }
        Collections.shuffle(list);
        Collections.shuffle(checknumber);
        array();
    }


    public void array() {
        for (i = 0; i < 15; i++) {
            String nu = list.get(i).toString();
            numb.get(i).setText(nu);
            checked[i] = 0;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.number_1:
                if (number_1.getText() == currentNumber) {
                    number_1.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                    if (checked[0] == 0) {
                        checked[0] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }
                    if (count == 15)
                        win();
                } else {
                    Toast.makeText(this, "NOT MATCHED", Toast.LENGTH_SHORT).show();
                    //post
                }

                break;
            case R.id.number_2:
                if (number_2.getText() == currentNumber) {

                    number_2.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                    if (checked[1] == 0) {
                        checked[1] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }
                    if (count == 15)
                        win();
                } else {
//                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    Toast.makeText(this, "NOT MATCHED", Toast.LENGTH_SHORT).show();
//                    disqualified();
                }

                break;
            case R.id.number_3:
                if (number_3.getText() == currentNumber) {

                    number_3.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                    if (checked[2] == 0) {
                        checked[2] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }
                    if (count == 15)
                        win();
                } else {
//                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    Toast.makeText(this, "NOT MATCHED", Toast.LENGTH_SHORT).show();
//                    disqualified();
                }

                break;
            case R.id.number_4:
                if (number_4.getText() == currentNumber) {

                    number_4.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                    if (checked[3] == 0) {
                        checked[3] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }
                    if (count == 15)
                        win();
                } else {
//                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    Toast.makeText(this, "NOT MATCHED", Toast.LENGTH_SHORT).show();
//                    disqualified();
                }

                break;
            case R.id.number_5:
                if (number_5.getText() == currentNumber) {

                    number_5.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                    if (checked[4] == 0) {
                        checked[4] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }
                    if (count == 15)
                        win();
                } else {
//                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    Toast.makeText(this, "NOT MATCHED", Toast.LENGTH_SHORT).show();
//                    disqualified();
                }

                break;
            case R.id.number_6:
                if (number_6.getText() == currentNumber) {

                    number_6.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                    if (checked[5] == 0) {
                        checked[5] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }
                    if (count == 15)
                        win();
                } else {
//                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    Toast.makeText(this, "NOT MATCHED", Toast.LENGTH_SHORT).show();
//                    disqualified();
                }

                break;
            case R.id.number_7:
                if (number_7.getText() == currentNumber) {

                    number_7.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                    if (checked[6] == 0) {
                        checked[6] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }
                    if (count == 15)
                        win();
                } else {
//                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    Toast.makeText(this, "NOT MATCHED", Toast.LENGTH_SHORT).show();
//                    disqualified();
                }

                break;
            case R.id.number_8:
                if (number_8.getText() == currentNumber) {

                    number_8.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                    if (checked[7] == 0) {
                        checked[7] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }
                    if (count == 15)
                        win();
                } else {
//                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    Toast.makeText(this, "NOT MATCHED", Toast.LENGTH_SHORT).show();
//                    disqualified();
                }

                break;
            case R.id.number_9:
                if (number_9.getText() == currentNumber) {

                    number_9.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                    if (checked[8] == 0) {
                        checked[8] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }

                    if (count == 15)
                        win();
                } else {
//                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    Toast.makeText(this, "NOT MATCHED", Toast.LENGTH_SHORT).show();
//                    disqualified();
                }

                break;
            case R.id.number_10:
                if (number_10.getText() == currentNumber) {

                    number_10.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                    if (checked[9] == 0) {
                        checked[9] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }
                    if (count == 15)
                        win();
                } else {
                    //Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    Toast.makeText(this, "NOT MATCHED", Toast.LENGTH_SHORT).show();
//                    disqualified();
                }

                break;
            case R.id.number_11:
                if (number_11.getText() == currentNumber) {

                    number_11.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                    if (checked[10] == 0) {
                        checked[10] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }
                    if (count == 15)
                        win();
                } else {
//                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    Toast.makeText(this, "NOT MATCHED", Toast.LENGTH_SHORT).show();
//                    disqualified();
                }

                break;
            case R.id.number_12:
                if (number_12.getText() == currentNumber) {

                    number_12.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                    if (checked[11] == 0) {
                        checked[11] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }
                    if (count == 15)
                        win();
                } else {
//                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    Toast.makeText(this, "NOT MATCHED", Toast.LENGTH_SHORT).show();
//                    disqualified();
                }

                break;
            case R.id.number_13:
                if (number_13.getText() == currentNumber) {

                    number_13.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                    if (checked[12] == 0) {
                        checked[12] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }

                    if (count == 15)
                        win();
                } else {
//                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    Toast.makeText(this, "NOT MATCHED", Toast.LENGTH_SHORT).show();
//                    disqualified();
                }

                break;
            case R.id.number_14:
                if (number_14.getText() == currentNumber) {

                    number_14.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                    if (checked[13] == 0) {
                        checked[0] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }

                    if (count == 15)
                        win();
                } else {
//                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    Toast.makeText(this, "NOT MATCHED", Toast.LENGTH_SHORT).show();
//                    disqualified();

                }

                break;
            case R.id.number_15:
                if (number_15.getText() == currentNumber) {

                    number_15.setBackgroundTintList(ColorStateList.valueOf(R.color.signin));
                    if (checked[14] == 0) {
                        checked[14] = 1;
                        count++;
                    } else {
                        Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                    }

                    if (count == 15)
                        win();
                } else {
//                    Toast.makeText(this, "Disqualified", Toast.LENGTH_SHORT).show();
                    //post
                    Toast.makeText(this, "NOT MATCHED", Toast.LENGTH_SHORT).show();
//                    disqualified();
                }
                break;
        }
    }

    public void win() {
        Toast.makeText(this, "You WIN!", Toast.LENGTH_SHORT).show();
        SharedPreferences prefs = getSharedPreferences("number", Context.MODE_PRIVATE);
        String roll = prefs.getString("roll number", "gsbs");
        AndroidNetworking.get(getString(R.string.baseUrl) + "postpoint/" + roll + "/" + String.valueOf(15))
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

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        builder.setTitle("EXIT");
        builder.setMessage("You won't get another chance today");
        builder.setPositiveButton("AGREE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Housie.super.onBackPressed();
            }
        });
        builder.setNegativeButton("DISAGREE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.show();
    }
}
