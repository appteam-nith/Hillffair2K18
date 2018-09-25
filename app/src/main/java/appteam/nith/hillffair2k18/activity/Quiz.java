package appteam.nith.hillffair2k18.activity;
/**
 * Created by LENOVO on 24-09-2018.
 */
import android.view.View;
import appteam.nith.hillffair2k18.R;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;

        import appteam.nith.hillffair2k18.R;

public class Quiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }

    int QuestionNum = 1;

    public void optionAselected(View view){
        View ButtonB = this.findViewById(R.id.optionB_button);
        View ButtonC = this.findViewById(R.id.optionC_button);
        View ButtonD = this.findViewById(R.id.optionD_button);

        ButtonB.getBackground().setAlpha(0); //Setting alpha to half , alpha has values from 0 to 255
        ButtonC.getBackground().setAlpha(127);
        ButtonD.getBackground().setAlpha(127);

        sendOption('A');

        if(QuestionNum <= 10){
            QuestionNum += 1;
            nextQuestion();
        }
        else{
            endQuiz();
        }

    }

    public void optionBselected(View view){
        View ButtonA = this.findViewById(R.id.optionA_button);
        View ButtonC = this.findViewById(R.id.optionC_button);
        View ButtonD = this.findViewById(R.id.optionD_button);

        ButtonA.getBackground().setAlpha(127); //Setting alpha to half , alpha has values from 0 to 255
        ButtonC.getBackground().setAlpha(127);
        ButtonD.getBackground().setAlpha(127);

        if(QuestionNum <= 10){
            QuestionNum += 1;
            nextQuestion();
        }
        else{
            endQuiz();
        }
    }

    public void optionCselected(View view){
        View ButtonA = this.findViewById(R.id.optionA_button);
        View ButtonB = this.findViewById(R.id.optionB_button);
        View ButtonD = this.findViewById(R.id.optionD_button);

        ButtonA.getBackground().setAlpha(127); //Setting alpha to half , alpha has values from 0 to 255
        ButtonB.getBackground().setAlpha(127);
        ButtonD.getBackground().setAlpha(127);

        if(QuestionNum <= 10){
            QuestionNum += 1;
            nextQuestion();
        }
        else{
            endQuiz();
        }
    }

    public void optionDselected(View view){
        View ButtonA = this.findViewById(R.id.optionA_button);
        View ButtonB = this.findViewById(R.id.optionB_button);
        View ButtonC = this.findViewById(R.id.optionC_button);

        ButtonA.getBackground().setAlpha(127); //Setting alpha to half , alpha has values from 0 to 255
        ButtonB.getBackground().setAlpha(127);
        ButtonC.getBackground().setAlpha(127);

        if(QuestionNum <= 10){
            QuestionNum += 1;
            nextQuestion();
        }
        else{
            endQuiz();
        }
    }

    private void sendOption(char SelectedOption){
        //Code to send selected option to backend

    }

    private void nextQuestion(){
        //Code to change question on screen to another

    }

    private void endQuiz(){
        //Either intent to go to quiz summary page or directly go to Quiz Map
    }

}
