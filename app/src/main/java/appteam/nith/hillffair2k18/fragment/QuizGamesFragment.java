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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.activity.Housie;
import appteam.nith.hillffair2k18.activity.Quiz;
import appteam.nith.hillffair2k18.activity.RouletteActivity;

/**
 * Coded by ThisIsNSH on Someday.
 */

public class QuizGamesFragment extends Fragment implements View.OnClickListener {

    ProgressBar loadwall;
    private Activity activity;
    private TextView quiz, tambola, roulette;
    private RelativeLayout rel1, rel2, rel3;
    private CardView playQuiz, playTambola, playRoulette;
    String user_id;
    String quizs,roullets,tambolas;
    int q=0,t=0,r=0;
    public QuizGamesFragment() {
    }

    public QuizGamesFragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidNetworking.initialize(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        final SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("number", Context.MODE_PRIVATE);
        user_id = sharedPreferences.getString("roll number","gsbs");
        getData();
//        System.out.print("jnnjn"+nowAsString);
        quiz = view.findViewById(R.id.quiz);
        roulette = view.findViewById(R.id.roulette);
        tambola = view.findViewById(R.id.tambola);
        rel1 = view.findViewById(R.id.rel1);
//        loadwall = view.findViewById(R.id.loadwall);
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
    public void quizstatus()
    {

    }
    public void roulletestatus()
    {
    }
    public void tambolastatus()
    {

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
//                quizstatus();
                AndroidNetworking.get("http://hillffair.tk/postgamestatus" +
                        "/" + user_id)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {

                            }

                            @Override
                            public void onError(ANError anError) {

                            }
                        });
                AndroidNetworking.get("http://hillffair.tk/getquizstatus/" + user_id)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try
                                {

//                            System.out.print("aaaaaaaaaaaaaaaaaaa" + user_id + "nnnnnnnnn   ");
//                            System.out.print("http://hillffair.tk/getquizstatus/" + user_id);
                                    quizs = response.getString("quizstatus");
                                    if (quizs.equals("null"))
                                        q = 1;
                                    else
                                        q = 0;
                                    if (q != 0)
                                    {
                                        startActivity(new Intent(activity, Quiz.class));
                                        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                    }
                                    else
                                    {
                                        Toast.makeText(activity, "You have played once\nPlay tomorrow", Toast.LENGTH_SHORT).show();
                                    }

                                } catch (JSONException e1) {
                                    e1.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(ANError anError) {


                            }

                        });
                AndroidNetworking.get("http://hillffair.tk/postquizstatus/" + user_id)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {

                            }

                            @Override
                            public void onError(ANError anError) {

                            }
                        });

                break;
            case R.id.play_tambola:
//                tambolastatus();
                AndroidNetworking.get("http://hillffair.tk/postgamestatus" +
                        "/" + user_id)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {

                            }

                            @Override
                            public void onError(ANError anError) {

                            }
                        });
                AndroidNetworking.get("http://hillffair.tk/gettambolastatus/" + user_id)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try
                                {
                                    tambolas = response.getString("tambolastatus");
                                    if (tambolas.equals("0"))
                                        t = 1;
                                    else
                                        t = 0;
                                    if (t != 0)
                                    {
                                        startActivity(new Intent(activity, Housie.class));
                                        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                    }
                                    else
                                    {
                                        Toast.makeText(activity, "You have played once\nPlay tomorrow", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e1) {
                                    e1.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(ANError anError) {


                            }

                        });
                AndroidNetworking.get("http://hillffair.tk/posttambolastatus/" + user_id)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {

                            }

                            @Override
                            public void onError(ANError anError) {

                            }
                        });
                break;
            case R.id.play_roulette:
//                roulletestatus();

                AndroidNetworking.get("http://hillffair.tk/postgamestatus" +
                        "/" + user_id)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {

                            }

                            @Override
                            public void onError(ANError anError) {

                            }
                        });

                startActivity(new Intent(activity, RouletteActivity.class));
                activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


                break;
        }
    }
}