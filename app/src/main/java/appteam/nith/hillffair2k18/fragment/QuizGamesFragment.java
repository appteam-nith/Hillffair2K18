package appteam.nith.hillffair2k18.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import appteam.nith.hillffair2k18.R;

/**
 * Coded by ThisIsNSH on Someday.
 */

public class QuizGamesFragment extends Fragment implements View.OnClickListener {

    private Activity activity;
    private TextView quiz, tambola, roulette;

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
                break;
            case R.id.tambola:
                break;
            case R.id.roulette:
                break;
        }

    }
}
