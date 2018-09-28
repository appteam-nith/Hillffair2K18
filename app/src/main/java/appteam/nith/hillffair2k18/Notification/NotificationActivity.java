package appteam.nith.hillffair2k18.Notification;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.activity.DashActivity;
import appteam.nith.hillffair2k18.activity.ProfileMain;
import appteam.nith.hillffair2k18.adapter.Notification;
import appteam.nith.hillffair2k18.model.HomePostsModel;

public class    NotificationActivity extends AppCompatActivity {

    Notification notification;
    List<notifications> arrayList;
    Notification adapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    DbHelper dbHandler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        ImageView img = (ImageView) findViewById(R.id.backBtn);

       img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotificationActivity.this, DashActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        });
        progressBar = (ProgressBar) findViewById(R.id.category_progress);
        //   arrayList=dbHandler.gethomedata();
        arrayList=new ArrayList<>();

        Log.v("aa",""+arrayList);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView = (RecyclerView)findViewById(R.id.activity_notification_listview);
//        notification = new Notification(arrayList,NotificationActivity.this);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.setAdapter(notification);
        getData();

//        recyclerView.addOnItemTouchListener(new OnItemTouchListener(NotificationActivity.this, new OnItemTouchListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int db_position) {
//                //  int  position = db_position+1;
//                int n = arrayList.size();
//
//                HomePostsModel home_post2 = arrayList.get(db_position);
//                String id = home_post2.getNotification_id();
//                Intent expand = new Intent(getApplicationContext(), Notification2.class);
//                Log.d("afasdf", "intent_putextrats" + id + "g12112ddddd" + db_position);
//                expand.putExtra("id", id);
//                startActivity(expand);
//
//            }
//        }));


    }
    private void getData() {
        AndroidNetworking.get("https://onesignal.com/api/v1/notifications/")
                .addQueryParameter("app_id","a0f2f1ac-dabc-46fd-a756-aaf590c37a04")
                .addQueryParameter("offset","0")
                .addHeaders("Authorization","Basic ZDhiN2Q4NzUtZDU0Mi00MDQzLTllNDctYWE0MjEzNjQyNmYy")
                .build()
                .getAsObject(NotificationArrayModel.class, new ParsedRequestListener<NotificationArrayModel>() {
                    @Override
                    public void onResponse(NotificationArrayModel response) {
                        arrayList.clear();
                        Log.d("Response body " ,"Hello!"+response);
                        if ( response.getList().size() > 0)
                        {
                            Log.d("OnReponsefn","entering if condition");
                            arrayList = response.getList();
                            Log.d("Log" + "Response" ," inside the try catch..."+ arrayList.toString());
                            adapter = new Notification(arrayList, getApplicationContext());
                            recyclerView.setAdapter(adapter);


                        }
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(NotificationActivity.this, "Data not recieved", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
