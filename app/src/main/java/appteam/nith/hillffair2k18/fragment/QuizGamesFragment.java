package appteam.nith.hillffair2k18.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.activity.Housie;
import appteam.nith.hillffair2k18.activity.Quiz;
import appteam.nith.hillffair2k18.activity.RouletteActivity;

/**
 * Coded by ThisIsNSH on Someday.
 */

public class QuizGamesFragment extends Fragment implements View.OnClickListener {

    private Activity activity;
    private TextView quiz, tambola, roulette;
    private RelativeLayout rel1, rel2, rel3;
    private CardView playQuiz, playTambola, playRoulette;

    public QuizGamesFragment() {
    }

    public QuizGamesFragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        getData();
//        System.out.print("jnnjn"+nowAsString);
        quiz = view.findViewById(R.id.quiz);
        roulette = view.findViewById(R.id.roulette);
        tambola = view.findViewById(R.id.tambola);
        rel1 = view.findViewById(R.id.rel1);
        rel2 = view.findViewById(R.id.rel2);
        rel3 = view.findViewById(R.id.rel3);

        playQuiz = view.findViewById(R.id.play_quiz);
        playRoulette = view.findViewById(R.id.play_roulette);
        playTambola = view.findViewById(R.id.play_tambola);

        playTambola.setOnClickListener(this);
        playRoulette.setOnClickListener(this);
        playQuiz.setOnClickListener(this);
        quiz.setOnClickListener(this);
        roulette.setOnClickListener(this);
        tambola.setOnClickListener(this);
        Log.e("GamesFragment", "onCreateView: ");

        return view;
    }

    public void getData() {

    }

//    public void QUIZ()
//    {
//        Date now = new Date();
//        String nowAsString = new SimpleDateFormat("yyyy-MM-dd").format(now);
//        String[] date = nowAsString.split("-");
//        SharedPreferences prefs = this.getActivity().getSharedPreferences("number", Context.MODE_PRIVATE);
//        String check = prefs.getString("QuizFirstTime", "gsbs");
//        SharedPreferences.Editor editor = prefs.edit();
//        if (!check.equals("gsbs"))
//        {
//            int start = Integer.parseInt(check);
//            int today = Integer.parseInt(date[2]);
//            if ((start - today)==0)
//            {
//                playQuiz.setClickable(false);
//            }
//            else
//            {
//                editor.putString("QuizFirstTime",date[2]);
//                editor.commit();
//            }
//        }
//        else
//        {
//            editor.putString("QuizFirstTime",date[2]);
//            editor.commit();
//        }
//    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.quiz:
                rel1.setVisibility(View.VISIBLE);
                rel2.setVisibility(View.GONE);
                rel3.setVisibility(View.GONE);
                quiz.setTextColor(getResources().getColor(R.color.black));
                roulette.setTextColor(getResources().getColor(R.color.hint));
                tambola.setTextColor(getResources().getColor(R.color.hint));
//                QUIZ();
                break;
            case R.id.tambola:
                rel1.setVisibility(View.GONE);
                rel2.setVisibility(View.GONE);
                rel3.setVisibility(View.VISIBLE);
                tambola.setTextColor(getResources().getColor(R.color.black));
                quiz.setTextColor(getResources().getColor(R.color.hint));
                roulette.setTextColor(getResources().getColor(R.color.hint));
//                TAMBOLA();
                break;
            case R.id.roulette:
                rel1.setVisibility(View.GONE);
                rel2.setVisibility(View.VISIBLE);
                rel3.setVisibility(View.GONE);
                roulette.setTextColor(getResources().getColor(R.color.black));
                quiz.setTextColor(getResources().getColor(R.color.hint));
                tambola.setTextColor(getResources().getColor(R.color.hint));
//                ROULLETTE();
                break;
            case R.id.play_quiz:
                startActivity(new Intent(activity, Quiz.class));
                activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            case R.id.play_tambola:
                startActivity(new Intent(activity, Housie.class));
                activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            case R.id.play_roulette:
                startActivity(new Intent(activity, RouletteActivity.class));
                activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
        }
    }
}