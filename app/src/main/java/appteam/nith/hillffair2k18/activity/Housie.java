package appteam.nith.hillffair2k18.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import appteam.nith.hillffair2k18.R;

public class Housie extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Integer> list = new ArrayList<Integer>();
    ArrayList<TextView> numb = new ArrayList<TextView>();
    int total = 15, i, count = 0;
    int[] checked = new int[15];
    TextView number_1, number_2, number_3, number_4, number_5, number_6, number_7, number_8, number_9, number_10, number_11, number_12, number_13, number_14, number_15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housie);
        init();
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

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.number_1:
                number_1.setBackgroundColor(R.color.colorPrimaryDark);
                if (checked[0] == 0) {
                    checked[0] = 1;
                    count++;
                } else {
                    Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                }
                if (count == 15)
                    win();
                break;
            case R.id.number_2:
                number_2.setBackgroundColor(R.color.colorPrimaryDark);
                if (checked[1] == 0) {
                    checked[1] = 1;
                    count++;
                } else {
                    Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                }
                if (count == 15)
                    win();
                break;
            case R.id.number_3:
                number_3.setBackgroundColor(R.color.colorPrimaryDark);
                if (checked[2] == 0) {
                    checked[2] = 1;
                    count++;
                } else {
                    Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                }
                if (count == 15)
                    win();
                break;
            case R.id.number_4:
                number_4.setBackgroundColor(R.color.colorPrimaryDark);
                if (checked[3] == 0) {
                    checked[3] = 1;
                    count++;
                } else {
                    Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                }
                if (count == 15)
                    win();
                break;
            case R.id.number_5:
                number_5.setBackgroundColor(R.color.colorPrimaryDark);
                if (checked[4] == 0) {
                    checked[4] = 1;
                    count++;
                } else {
                    Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                }
                if (count == 15)
                    win();
                break;
            case R.id.number_6:
                number_6.setBackgroundColor(R.color.colorPrimaryDark);
                if (checked[5] == 0) {
                    checked[5] = 1;
                    count++;
                } else {
                    Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                }
                if (count == 15)
                    win();
                break;
            case R.id.number_7:
                number_7.setBackgroundColor(R.color.colorPrimaryDark);
                if (checked[6] == 0) {
                    checked[6] = 1;
                    count++;
                } else {
                    Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                }
                if (count == 15)
                    win();
                break;
            case R.id.number_8:
                number_8.setBackgroundColor(R.color.colorPrimaryDark);
                if (checked[7] == 0) {
                    checked[7] = 1;
                    count++;
                } else {
                    Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                }
                if (count == 15)
                    win();
                break;
            case R.id.number_9:
                number_9.setBackgroundColor(R.color.colorPrimaryDark);
                if (checked[8] == 0) {
                    checked[8] = 1;
                    count++;
                } else {
                    Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                }

                if (count == 15)
                    win();
                break;
            case R.id.number_10:
                number_10.setBackgroundColor(R.color.colorPrimaryDark);
                if (checked[9] == 0) {
                    checked[9] = 1;
                    count++;
                } else {
                    Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                }
                if (count == 15)
                    win();
                break;
            case R.id.number_11:
                number_11.setBackgroundColor(R.color.colorPrimaryDark);
                if (checked[10] == 0) {
                    checked[10] = 1;
                    count++;
                } else {
                    Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                }
                if (count == 15)
                    win();
                break;
            case R.id.number_12:
                number_12.setBackgroundColor(R.color.colorPrimaryDark);
                if (checked[11] == 0) {
                    checked[11] = 1;
                    count++;
                } else {
                    Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                }
                if (count == 15)
                    win();
                break;
            case R.id.number_13:
                number_13.setBackgroundColor(R.color.colorPrimaryDark);
                if (checked[12] == 0) {
                    checked[12] = 1;
                    count++;
                } else {
                    Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                }

                if (count == 15)
                    win();
                break;
            case R.id.number_14:
                number_14.setBackgroundColor(R.color.colorPrimaryDark);
                if (checked[13] == 0) {
                    checked[0] = 1;
                    count++;
                } else {
                    Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                }

                if (count == 15)
                    win();
                break;
            case R.id.number_15:
                number_15.setBackgroundColor(R.color.colorPrimaryDark);
                if (checked[14] == 0) {
                    checked[14] = 1;
                    count++;
                } else {
                    Toast.makeText(this, "Number is already checked", Toast.LENGTH_SHORT).show();
                }

                if (count == 15)
                    win();
                break;
        }
    }

    public void win() {
        Toast.makeText(this, "You WIN!", Toast.LENGTH_SHORT).show();
    }
}
