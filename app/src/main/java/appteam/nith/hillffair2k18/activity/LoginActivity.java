package appteam.nith.hillffair2k18.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.annotations.SerializedName;

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

//        sharedPref = new SharedPref(this);

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
                Log.d("pno.: ", user.getPhoneNumber());
                Log.d("uid: ", user.getUid());
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, DashActivity.class));
//                saveUserLoginData(user.getPhoneNumber(), user.getUid());
            } else {
                // Sign in failed, check response for error code
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        }

    }

//
//    private void saveUserLoginData(final String phone_no, String fb_id) {
//
//        Call<FirebaseLoginActivity.UserSentResponse> userSentResponseCall = Util.getRetrofitService().sendUserLoginData(phone_no, fb_id);
//        userSentResponseCall.enqueue(new Callback<FirebaseLoginActivity.UserSentResponse>() {
//            @Override
//            public void onResponse(Call<FirebaseLoginActivity.UserSentResponse> call, Response<FirebaseLoginActivity.UserSentResponse> response) {
//                FirebaseLoginActivity.UserSentResponse userSentResponse = response.body();
//                if (userSentResponse != null && response.isSuccess()) {
//                    Log.v("ID", userSentResponse.getUserId());
//                    sharedPref.setLoginStatus(true);
//                    sharedPref.setSkipStatus(false);// as user has login succesfully and we make sure  that screen does not come again
//                    sharedPref.setUserId(userSentResponse.getUserId());
//                    sharedPref.setUserPhone(phone_no);
//
//                    if (userSentResponse.getBranch().isEmpty() || userSentResponse.getYear().isEmpty() || userSentResponse.getEmail().isEmpty() ||
//                            userSentResponse.getName().isEmpty() || userSentResponse.getRoll_no().isEmpty()) {
//                        sharedPref.setProfileStatus(false);
//                        Log.d("no data", "hello");
//                    }
//                    else {
//
//                        sharedPref.setUserBranch(userSentResponse.getBranch());
//                        sharedPref.setUserYearPos(userSentResponse.getYear());
//                        // sharePref.setUserYearText(userSentResponse.getYearText());
//                        sharedPref.setUserEmail(userSentResponse.getEmail());
//                        sharedPref.setUserName(userSentResponse.getName());
//                        sharedPref.setUserRollno(userSentResponse.getRoll_no());
//                        sharedPref.setProfileStatus(true);
//                        Log.d("aa", userSentResponse.getBranch());
//                        Log.d("bb", userSentResponse.getRoll_no());
//                        Log.d("cc", userSentResponse.getName());
//                        Log.d("dd", userSentResponse.getEmail());
//                    }
//
//                    progressBar.setVisibility(View.GONE);
//
//                    Intent intent = new Intent(FirebaseLoginActivity.this, HomescreenNew.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
//                    finish();
//                } else{
//                    Toast.makeText(FirebaseLoginActivity.this, "Check Internet connection", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//
//            @Override
//            public void onFailure(Call<FirebaseLoginActivity.UserSentResponse> call, Throwable t) {
//                Toast.makeText(FirebaseLoginActivity.this, "Check Internet Connection", Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }


    public class UserSentResponse {
        @SerializedName("msg")
        private String message;

        @SerializedName("student_id")
        private String userId;

        @SerializedName("name")
        private String name;

        @SerializedName("roll_no")
        private String roll_no;

        @SerializedName("year")
        private String year;

        @SerializedName("branch")
        private String branch;

        @SerializedName("email")
        private String email;

        public UserSentResponse(String message, String userId, String name, String roll_no, String year, String branch, String email) {
            this.message = message;
            this.userId = userId;
            this.name = name;
            this.roll_no = roll_no;
            this.year = year;
            this.branch = branch;
            this.email = email;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRoll_no() {
            return roll_no;
        }

        public void setRoll_no(String roll_no) {
            this.roll_no = roll_no;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getBranch() {
            return branch;
        }

        public void setBranch(String branch) {
            this.branch = branch;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }


}