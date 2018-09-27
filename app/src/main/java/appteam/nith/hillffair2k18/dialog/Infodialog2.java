package appteam.nith.hillffair2k18.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.adapter.SponsorAdapter;
import appteam.nith.hillffair2k18.model.Team;

/**
 * Code by ThisIsNSH on Someday.
 */

public class Infodialog2 extends Dialog {

    String info;
    TextView next, dialog;
    EditText editBet;
    int check = 1;
    private Activity activity;
    private RecyclerView recyclerView;
    private SponsorAdapter teamAdapter;
    private List<Team> teamList = new ArrayList<>();

    public Infodialog2(@NonNull Activity context) {
        super(context);
        activity = context;
        initUI();
    }

    public void initUI() {
        setContentView(R.layout.activity_infodialog2);
        setCancelable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.dimAmount = 0.3f;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setAttributes(lp);
        getData();
        setCanceledOnTouchOutside(true);
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        findViewById(R.id.cross).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void getData() {
        teamList.clear();
        recyclerView = findViewById(R.id.thirdRec);
        teamAdapter = new SponsorAdapter(teamList, activity);
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
        recyclerView.setAdapter(teamAdapter);
        AndroidNetworking.get(activity.getString(R.string.baseUrl) + "getsponsor")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // do anything with response
                        try {
                            int users = response.length();
                            for (int i = 0; i < users; i++) {
                                JSONObject json = response.getJSONObject(i);
                                String sponsorName = json.getString("name");
                                String info = json.getString("info");
//                                String  = json.getString("event_time");
                                teamList.add(new Team(sponsorName, "", info));
                            }
                            teamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
        teamAdapter.notifyDataSetChanged();

    }

}