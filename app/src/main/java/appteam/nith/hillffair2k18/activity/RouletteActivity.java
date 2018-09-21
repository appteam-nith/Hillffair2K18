package appteam.nith.hillffair2k18.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.;
import java.util.Random;

import appteam.nith.hillffair2k18.R;

public class RouletteActivity extends AppCompatActivity {

    Button button;
    ImageView wheel;
    ImageView pointer;
    TextView textView;
    EditText entry;
    int degreeold;
    int degree;
    Random r;
    int num;
    int score=0;
    private static final float angle = 9.72f;

    public void MakeToast(String string) {

        Toast.makeText(RouletteActivity.this, string, Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette);


        button = (Button) findViewById(R.id.button);
        wheel = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
        entry = (EditText) findViewById(R.id.editText);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r = new Random();
                degreeold = degree % 360;
                degree = r.nextInt(3600) + 9000;
                RotateAnimation rotate = new RotateAnimation(degreeold, degree, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(3600);
                rotate.setFillAfter(true);
                rotate.setInterpolator(new DecelerateInterpolator());
                String empty = String.valueOf(entry.getText());

                if (empty.equals(""))
                    num = -1 ;
                else
                    num = Integer.parseInt(empty);

                if (String.valueOf(entry.getText()).isEmpty()) {
                    MakeToast("Enter The Number");
                } else if (num > 37) {
                    MakeToast("Inappropriate Choice");
                } else {

                    rotate.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            textView.setText("");

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                            textView.setText(currentNumber(360 - (degree % 360)));
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    wheel.startAnimation(rotate);

                    button.setVisibility(View.GONE);


                }
            }
        });


    }

    private String currentNumber(int degrees) {
        int num = Integer.parseInt(entry.getText().toString());

        String text = " ";

        if (degrees >= angle * 1 && degrees <= angle * 3 && num == 32) {

            text = "Congratulations you have won 25 points";
            score=score+25;

        }
        else if (degrees >= angle * 1 && degrees <= angle * 3 && num != 32) {
            text = "Sorry Better Luck Next Time";
        }else if(degrees>=angle*3 && degree<=angle*5 && num==15){
            text = "Congratulations you have won 25 points";
            score=score+25;
        }
        else if(degrees >= angle *3 && degrees <= angle *5 && num != 15) {
            text = "Sorry Better Luck Next Time";
        }else if(degrees >= angle *5 && degrees <= angle * 7 && num ==19) {
            text = "Congratulations you have won 25 points";
            score=score+25;
        }else if(degrees >= angle *5 && degrees <= angle *7 && num != 19) {
            text = "Sorry Better Luck Next Time";
        }else if(degrees >= angle *7 && degrees <= angle * 9 && num ==4) {
            text = "Congratulations you have won 25 points";
            score=score+25;
        }else if(degrees >= angle *7 && degrees <= angle *9 && num != 4) {
            text = "Sorry Better Luck Next Time";
        }else if(degrees >= angle *9 && degrees <= angle * 11 && num ==21) {


        }else if(degrees >= angle *9 && degrees <= angle *11 && num != 21) {
            text = "Sorry Better Luck Next Time";
        }else if(degrees >= angle *9 && degrees <= angle * 11 && num ==2) {
            text = "Congratulations you have won 25 points";
            score=score+25;
        }else if(degrees >= angle *9 && degrees <= angle *11 && num != 2) {
            text = "Sorry Better Luck Next Time";
        }else if(degrees >= angle *11 && degrees <= angle * 13 && num ==25) {
            text = "Congratulations you have won 25 points";
            score=score+25;
        }else if(degrees >= angle *11 && degrees <= angle *13 && num != 25) {
            text = "Sorry Better Luck Next Time";
        }else if(degrees >= angle *13 && degrees <= angle * 15 && num ==17) {
            text = "Congratulations you have won 25 points";
            score=score+25;
        }else if(degrees >= angle *13 && degrees <= angle *15 && num != 17) {
            text = "Sorry Better Luck Next Time";
        }else if(degrees >= angle *15 && degrees <= angle * 17 && num ==34) {
            text = "Congratulations you have won 25 points";
            score=score+25;
        }else if(degrees >= angle *15 && degrees <= angle *17 && num !=34) {
            text = "Sorry Better Luck Next Time";
        }else if(degrees >= angle *17 && degrees <= angle * 19 && num ==6) {
            text = "Congratulations you have won 25 points";
            score=score+25;
        }else if(degrees >= angle *17 && degrees <= angle *19 && num !=6) {
            text = "Sorry Better Luck Next Time";
        }else if(degrees >= angle*21 && degrees<= angle*23 && num==27){
            text= "Congratulations you have won 25 points";
            score=score+25;}
        else if(degrees >= angle*21 && degrees<= angle*23 && num!=27)

        {
            text= "Sorry Better Luck next Time"; }
        else if(degrees >= angle*23 && degrees<= angle*25 && num==13){  text= "Congratulations you have won 25 points";
            score=score+25;}
        else if(degrees >= angle*23 && degrees<= angle*25 && num!=13){  text= "Sorry Better Luck next Time";
        }
        else if(degrees >= angle*25 && degrees<= angle*27 && num==36){  text= "Congratulations you have won 25 points";
            score=score+25;}
        else if(degrees >= angle*25 && degrees<= angle*27 && num!=36){  text= "Sorry Better Luck next Time";}
        else if(degrees >= angle*27 && degrees<= angle*29 && num==11){  text= "Congratulations you have won 25 points";
            score=score+25;}
        else if(degrees >= angle*27 && degrees<= angle*29 && num!=11){  text= "Sorry Better Luck next Time";}
        else if(degrees >= angle*29 && degrees<= angle*31 && num==30){  text= "Congratulations you have won 25 points";
            score=score+25;}
        else if(degrees >= angle*29 && degrees<= angle*31 && num!=30){  text= "Sorry Better Luck next Time";}
        else if(degrees >= angle*31 && degrees<= angle*33 && num==8){  text= "Congratulations you have won 25 points";
            score=score+25;}
        else if(degrees >= angle*31 && degrees<= angle*33 && num!=8){  text= "Sorry Better Luck next Time";}
        else if(degrees >= angle*33 && degrees<= angle*35 && num==23){  text= "Congratulations you have won 25 points";
            score=score+25;}
        else if(degrees >= angle*33 && degrees<= angle*35 && num!=23){  text= "Sorry Better Luck next Time";}
        else if(degrees >= angle*35 && degrees<= angle*37 && num==10){  text= "Congratulations you have won 25 points";
            score=score+25;}
        else if(degrees >= angle*35 && degrees<= angle*37 && num!=10){  text= "Sorry Better Luck next Time";}
        else if(degrees >= angle*37 && degrees<= angle*39 && num==5){  text= "Congratulations you have won 25 points";
            score=score+25;}
        else if(degrees >= angle*37 && degrees<= angle*39 && num!=5){  text= "Sorry Better Luck next Time";}
        else if(degrees >= angle*39 && degrees<= angle*41 && num==24){  text= "Congratulations you have won 25 points";
            score=score+25;}
        else if(degrees >= angle*39 && degrees<= angle*41 && num!=24){  text= "Sorry Better Luck next Time";}
        else if(degrees >= angle*41 && degrees<= angle*43 && num==16){  text= "Congratulations you have won 25 points";
            score=score+25;}
        else if(degrees >= angle*41 && degrees<= angle*43 && num!=16){  text= "Sorry Better Luck next Time";}
        else if(degrees >= angle*43 && degrees<= angle*45 && num==33){  text= "Congratulations you have won 25 points";
            score=score+25;}
        else if(degrees >= angle*43 && degrees<= angle*45 && num!=33){  text= "Sorry Better Luck next Time";}
        else if(degrees >= angle*45 && degrees<= angle*47 && num==1){  text= "Congratulations you have won 25 points";
            score=score+25;}
        else if(degrees >= angle*45 && degrees<= angle*47 && num!=1){  text= "Sorry Better Luck next Time";}
        else if(degrees >= angle*47 && degrees<= angle*49 && num==20){  text= "Congratulations you have won 25 points";
            score=score+25;}
        else if(degrees >= angle*47 && degrees<= angle*49 && num!=20){  text= "Sorry Better Luck next Time";}
        else if(degrees >= angle*49 && degrees<= angle*51 && num==14){  text= "Congratulations you have won 25 points";
            score=score+25;}
        else if(degrees >= angle*49 && degrees<= angle*51 && num!=14){  text= "Sorry Better Luck next Time";}
        else if(degrees >= angle*51 && degrees<= angle*53 && num==31){  text= "Congratulations you have won 25 points";
            score=score+25;}
        else if(degrees >= angle*51 && degrees<= angle*53 && num!=31){  text= "Sorry Better Luck next Time";}
        else if(degrees >= angle*53 && degrees<= angle*55 && num==9){  text= "Congratulations you have won 25 points";
            score=score+25;}
        else if(degrees >= angle*53 && degrees<= angle*55 && num!=9){  text= "Sorry Better Luck next Time";}
        else if(degrees >= angle*55 && degrees<= angle*57 && num==22){  text= "Congratulations you have won 25 points";
            score=score+25;}
        else if(degrees >= angle*55 && degrees<= angle*57 && num!=22){  text= "Sorry Better Luck next Time";}
        else if(degrees >= angle*57 && degrees<= angle*59 && num==18){  text= "Congratulations you have won 25 points";
            score=score+25;}
        else if(degrees >= angle*57 && degrees<= angle*59 && num!=18){  text= "Sorry Better Luck next Time";}
        else if(degrees >= angle*59 && degrees<= angle*61 && num==29){  text= "Congratulations you have won 25 points";
            score=score+25;}
        else if(degrees >= angle*59 && degrees<= angle*61 && num!=29){  text= "Sorry Better Luck next Time";}
        else if(degrees >= angle*61 && degrees<= angle*63 && num==7){  text= "Congratulations you have won 25 points";
            score=score+25;}
        else if(degrees >= angle*61 && degrees<= angle*63 && num!=7){  text= "Sorry Better Luck next Time";}
        else if(degrees >= angle*63 && degrees<= angle*65 && num==28){  text= "Congratulations you have won 25 points";
            score=score+25;}
        else if(degrees >= angle*63 && degrees<= angle*65 && num!=28){  text= "Sorry Better Luck next Time";}
        else if(degrees >= angle*65 && degrees<= angle*67 && num==12){  text= "Congratulations you have won 25 points";
            score=score+25;}
        else if(degrees >= angle*65 && degrees<= angle*67 && num!=12){  text= "Sorry Better Luck next Time";}
        else if(degrees >= angle*67 && degrees<= angle*69 && num==35){  text= "Congratulations you have won 25 points";
            score=score+25;}
        else if(degrees >= angle*67 && degrees<= angle*69 && num!=35){  text= "Sorry Better Luck next Time";}
        else if(degrees >= angle*69 && degrees<= angle*71 && num==3){  text= "Congratulations you have won 25 points";
            score=score+25;}
        else if(degrees >= angle*69 && degrees<= angle*71 && num!=3){  text= "Sorry Better Luck next Time";}
        else if(degrees >= angle*71 && degrees<= angle*73 && num==26){  text= "Congratulations you have won 25 points";
            score=score+25;}
        else if(degrees >= angle*71 && degrees<= angle*73 && num!=26){  text= "Sorry Better Luck next Time";}
        else if ((degrees >= angle*73 && degrees <360 && num==0)||(degrees >=0 && degrees <(angle*1) &&num==0)){ text="Congratulations you have won 25 points";
            score=score+25;}
        else if ((degrees >= angle*73 && degrees <360 && num!=0)||(degrees >=0 && degrees <(angle*1) &&num!=0)){ text="Sorry Better Luck next Time";}



        return text;
    }

}