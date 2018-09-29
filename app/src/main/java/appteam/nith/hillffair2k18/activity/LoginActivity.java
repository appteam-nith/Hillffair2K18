package appteam.nith.hillffair2k18.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import appteam.nith.hillffair2k18.R;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 138;

    private Button loginBtn;
    private ProgressBar progressBar;
    private List<AuthUI.IdpConfig> providers;
//    private SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);

        loginBtn = (Button) findViewById(R.id.login_btn);
        progressBar = (ProgressBar) findViewById(R.id.login_progress);

        providers = Arrays.asList(
                new AuthUI.IdpConfig.PhoneBuilder().build());

        final SharedPreferences sharedPreferences = getSharedPreferences("number", Context.MODE_PRIVATE);
        String Login = sharedPreferences.getString("Login", "gsbs");
        if (Login.equals("Complete")) {
            startActivity(new Intent(this, DashActivity.class));
            finish();
        }
    }

    public void onLoginBtnClick(View v) {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setTheme(R.style.LoginTheme)
                        .setLogo(R.drawable.hillffair_logo)
                        .build(),
                RC_SIGN_IN);
        progressBar.setVisibility(View.VISIBLE);
        loginBtn.setVisibility(View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Log.d("pno.: ", user.getPhoneNumber().substring(3));
                Log.d("uid: ", user.getUid());
                checkUser(user.getPhoneNumber().substring(3));

            } else {
                // Sign in failed, check response for error code
                progressBar.setVisibility(View.GONE);
                loginBtn.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Login Failed. Try Again!!", Toast.LENGTH_SHORT).show();
            }
        }

    }


    private void checkUser(final String phone_no) {

        AndroidNetworking.get(getResources().getString(R.string.baseUrl) + "checkuser/" + phone_no)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            Boolean userExists = response.getBoolean("exists");
                            Log.d("exists", userExists.toString());

                            if (userExists) {
                                JSONObject userData = response.getJSONObject("data");
                                String imageUrl =   userData.getString("image_url");
                                String id = userData.getString("id");
                                String referal = userData.getString("referal");
                                String name = userData.getString("name");

                                saveUserData( phone_no, imageUrl, id, name);
                                saveLoginStatus();

                                startActivity(new Intent(LoginActivity.this, DashActivity.class));
                                finish();
                            } else {
                                saveLoginStatus();
                                startActivity(new Intent(LoginActivity.this, Profile.class));
                                finish();
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        // Handle error
                    }
                });

    }

    private void saveUserData(String phone, String imageUrl, String id, String name){
        final SharedPreferences sharedPreferences = getSharedPreferences("number", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("name", name);
        editor.putString("roll number", id);
        editor.putString("Branch", "None");
        editor.putString("Phone", phone);
        editor.putString("Image", imageUrl);
        editor.commit();
    }

    private void saveLoginStatus(){
        final SharedPreferences sharedPreferences = getSharedPreferences("number", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("Login", "Complete");
        editor.commit();
    }

}