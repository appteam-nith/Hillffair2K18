package appteam.nith.hillffair2k18.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.activity.Housie;
import appteam.nith.hillffair2k18.activity.MainActivity;

/**
 * Coded by ThisIsNSH on Someday.
 */

public class QuizGamesFragment extends Fragment implements View.OnClickListener {

    private Activity activity;
    private TextView quiz, tambola, roulette;
    private RelativeLayout rel1,rel2,rel3;

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
        quiz = view.findViewById(R.id.quiz);
        roulette = view.findViewById(R.id.roulette);
        tambola = view.findViewById(R.id.tambola);
        rel1=view.findViewById(R.id.rel1);
        rel2=view.findViewById(R.id.rel2);
        rel3=view.findViewById(R.id.rel3);
        quiz.setOnClickListener(this);
        roulette.setOnClickListener(this);
        tambola.setOnClickListener(this);
        Log.e("GamesFragment", "onCreateView: ");
        return view;
    }

    public void getData() {

    }
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
                break;
            case R.id.tambola:
                rel1.setVisibility(View.GONE);
                rel2.setVisibility(View.GONE);
                rel3.setVisibility(View.VISIBLE);
                tambola.setTextColor(getResources().getColor(R.color.black));
                quiz.setTextColor(getResources().getColor(R.color.hint));
                roulette.setTextColor(getResources().getColor(R.color.hint));
                break;
            case R.id.roulette:
                rel1.setVisibility(View.GONE);
                rel2.setVisibility(View.VISIBLE);
                rel3.setVisibility(View.GONE);
                roulette.setTextColor(getResources().getColor(R.color.black));
                quiz.setTextColor(getResources().getColor(R.color.hint));
                tambola.setTextColor(getResources().getColor(R.color.hint));
                break;
        }
    }
}