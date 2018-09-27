package appteam.nith.hillffair2k18.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.adapter.ContributorsAdaptor;
import appteam.nith.hillffair2k18.model.contributorsItem;

/**
 * Created by naman on 26-09-2018.
 */

public class ContributorsActivity extends AppCompatActivity {
    String BASE_URL = "https://github.com/";
    private List<contributorsItem> contributorsItems = new ArrayList<>();
    private RecyclerView recyclerView;
    private ContributorsAdaptor contributorsAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributors);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        contributorsAdaptor = new ContributorsAdaptor(contributorsItems, ContributorsActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(contributorsAdaptor);

        findViewById(R.id.backBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContributorsActivity.this, DashActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        initData();
    }

    private void initData() {
        //5th Year
        contributorsItems.add(new contributorsItem("Sahil Ramola", BASE_URL + "RamolaWeb.png", BASE_URL + "RamolaWeb"));


        contributorsItems.add(new contributorsItem("Sukhbir Singh", BASE_URL + "sukhbir-singh.png", BASE_URL + "sukhbir-singh"));
        contributorsItems.add(new contributorsItem("Jalaz Kumar", BASE_URL + "jaykay12.png", BASE_URL + "jaykay12"));
        contributorsItems.add(new contributorsItem("Narendra Dodwaria", BASE_URL + "narendra36.png", BASE_URL + "narendra36"));

        //4th Year
        contributorsItems.add(new contributorsItem("Aditya Arora", BASE_URL + "adi23arora.png", BASE_URL + "adi23arora"));
        contributorsItems.add(new contributorsItem("B Jatin Rao", BASE_URL + "Jatin0312.png", BASE_URL + "Jatin0312"));
        contributorsItems.add(new contributorsItem("Nitin Sharma", BASE_URL + "iamNitin16.png", BASE_URL + "iamNitin16"));
        contributorsItems.add(new contributorsItem("Suraj Negi", BASE_URL + "Akatsuki06.png", BASE_URL + "Akatsuki06"));

        //3rd Year
        contributorsItems.add(new contributorsItem("Bharat Shah", BASE_URL + "bharatshah1498.png", BASE_URL + "bharatshah1498"));
        contributorsItems.add(new contributorsItem("Kartik Saxena", BASE_URL + "SaxenaKartik.png", BASE_URL + "SaxenaKartik"));
        contributorsItems.add(new contributorsItem("Muskan Jhunjhunwalla", BASE_URL + "musukeshu.png", BASE_URL + "musukeshu"));
        contributorsItems.add(new contributorsItem("Anubhaw Bhalotia", BASE_URL + "anubhawbhalotia.png", BASE_URL + "anubhawbhalotia"));
        contributorsItems.add(new contributorsItem("Kaushal Kishore", BASE_URL + "kaushal16124.png", BASE_URL + "kaushal16124"));

        //2nd Year
        contributorsItems.add(new contributorsItem("Abhinav Lamba", BASE_URL + "Abhinavlamba.png", BASE_URL + "Abhinavlamba"));
        contributorsItems.add(new contributorsItem("Daniyaal Khan", BASE_URL + "drtweety.png", BASE_URL + "drtweety"));
        contributorsItems.add(new contributorsItem("Utkarsh Singh", BASE_URL + "utkarshsingh99.png", BASE_URL + "utkarshsingh99"));
        contributorsItems.add(new contributorsItem("Anshu Akansha", BASE_URL + "AnshuAkansha52227.png", BASE_URL + "AnshuAkansha"));
        contributorsItems.add(new contributorsItem("Alisha Mehta", BASE_URL + "Alisha1116.png", BASE_URL + "Alisha1116"));
        contributorsItems.add(new contributorsItem("Vishal Parmar", BASE_URL + "Vishal17599.png", BASE_URL + "Vishal17599"));
        contributorsItems.add(new contributorsItem("Naman Lalit", BASE_URL + "naman99lalit.png", BASE_URL + "naman99lalit"));
        contributorsItems.add(new contributorsItem("ThisIsNSH", BASE_URL + "ThisIsNSH.png", BASE_URL + "ThisIsNSH"));
        contributorsItems.add(new contributorsItem("Kumar Kartikay", BASE_URL + "Kartikay26.png", BASE_URL + "Kartikay26"));
        contributorsItems.add(new contributorsItem("Tanvi Mahajan", BASE_URL + "tanvi003.png", BASE_URL + "tanvi003"));
        contributorsItems.add(new contributorsItem("Akanksha Tiwari", BASE_URL + "aakankshaa23.png", BASE_URL + "aakankshaa23"));
        contributorsItems.add(new contributorsItem("Rohan Nawathe", BASE_URL + "rohannawathe.png", BASE_URL + "rohannawathe"));

        //1st Year
        contributorsItems.add(new contributorsItem("Pranjal Dureja", BASE_URL + "pranjaldureja0002.png", BASE_URL + "pranjaldureja0002"));
        contributorsItems.add(new contributorsItem("Vishal Shrivastava", BASE_URL + "vishalshrivastava16.png", BASE_URL + "vishalshrivastava16"));
        contributorsItems.add(new contributorsItem("Pawan Harariya", BASE_URL + "pawanharariya.png", BASE_URL + "pawanharariya"));
        contributorsItems.add(new contributorsItem("Sarthak", BASE_URL + "Sarthak3661.png", BASE_URL + "Sarthak3661"));
        contributorsItems.add(new contributorsItem("Shweta Gurnani", BASE_URL + "shwetagurnani.png", BASE_URL + "shwetagurnani"));
        contributorsItems.add(new contributorsItem("Riya Goyal", BASE_URL + "riyagoyall.png", BASE_URL + "riyagoyall"));

        contributorsItems.add(new contributorsItem("Abhiraj Singh Rathore", BASE_URL + "AbhirajSinghRathore.png", BASE_URL + "AbhirajSingh99"));
        contributorsItems.add(new contributorsItem("Chirag Thakur", BASE_URL + "chirag-thakur.png", BASE_URL + "chirag-thakur"));

    }
}
