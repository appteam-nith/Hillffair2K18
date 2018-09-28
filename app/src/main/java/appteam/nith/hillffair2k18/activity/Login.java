package appteam.nith.hillffair2k18.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import appteam.nith.hillffair2k18.R;

public class Login extends AppCompatActivity {

    public static String phone;
    private TextView btnGenerateOTP, btnSignIn, skip;
    private String phoneNumber, otp;
    private EditText etPhoneNumber, etOTP;
    private FirebaseAuth auth;
    private LinearLayout linearLayout;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    private ImageView main;
    private String verificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT)
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        final SharedPreferences sharedPreferences = getSharedPreferences("number", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        String Login = sharedPreferences.getString("Login", "gsbs");
        if (!Login.equals("gsbs")) {
            finish();
            startActivity(new Intent(this, DashActivity.class));
        } else {
            setContentView(R.layout.activity_login);
            findViews();
            FirebaseApp.initializeApp(this);
            StartFirebaseLogin();
        }
    }

    private void findViews() {
        btnGenerateOTP = findViewById(R.id.btn_generate_otp);
        btnSignIn = findViewById(R.id.btn_sign_in);
        etPhoneNumber = findViewById(R.id.et_phone_number);
        linearLayout = findViewById(R.id.main1);
        main = findViewById(R.id.main);
        etOTP = findViewById(R.id.et_otp);
        skip = findViewById(R.id.skip);
        main.setVisibility(View.VISIBLE);
        main.setAlpha(0f);
        linearLayout.setVisibility(View.VISIBLE);
        linearLayout.setAlpha(0f);
        main.setTranslationY(70f);

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(main, "alpha", 0, 1);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(linearLayout, "alpha", 0, 1);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(main, "translationY", 70f, 0);
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(linearLayout, "translationY", 70f, 0);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator1.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator2.setInterpolator(new AnticipateOvershootInterpolator());
        objectAnimator3.setInterpolator(new AnticipateOvershootInterpolator());
        objectAnimator.setStartDelay(0);
        objectAnimator1.setStartDelay(350);
        objectAnimator2.setStartDelay(0);
        objectAnimator3.setStartDelay(350);
        objectAnimator.setDuration(1000);
        objectAnimator1.setDuration(1000);
        objectAnimator2.setDuration(1000);
        objectAnimator3.setDuration(1000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator, objectAnimator1, objectAnimator2);
        animatorSet.start();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                main.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        setupdata();
    }

    public void setupdata() {
        phone = "+91 ";
        phoneNumber = String.valueOf(etPhoneNumber.getText());
        btnGenerateOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etPhoneNumber.setVisibility(View.INVISIBLE);
                etOTP.setVisibility(View.VISIBLE);
                phoneNumber = String.valueOf(etPhoneNumber.getText());
                phone += phoneNumber;
                if (phoneNumber.length() == 0)
                    Toast.makeText(Login.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
                else {
                    phoneNumber = phone;
                    final SharedPreferences sharedPreferences = getSharedPreferences("number", Context.MODE_PRIVATE);
                    final SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("numberMobile", phone);
                    editor.commit();
                    btnSignIn.setVisibility(View.VISIBLE);
                    btnGenerateOTP.setVisibility(View.GONE);
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            phoneNumber,                     // Phone number to verify
                            60,                           // Timeout duration
                            TimeUnit.SECONDS,                // Unit of timeout
                            Login.this,        // Activity (for callback binding)
                            mCallback);// OnVerificationStateChangedCallbacks
                }
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp = etOTP.getText().toString();
                if (otp.length() == 0) {
                    Toast.makeText(Login.this, "Enter OTP", Toast.LENGTH_SHORT).show();
                } else {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, otp);
                    SigninWithPhone(credential);
                }
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, DashActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    private void SigninWithPhone(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(Login.this, Profile.class));
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                            finish();
                        } else {
                            Toast.makeText(Login.this, "Incorrect OTP", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void StartFirebaseLogin() {

        auth = FirebaseAuth.getInstance();
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(Login.this, "verification completed", Toast.LENGTH_SHORT).show();
                final SharedPreferences sharedPreferences = getSharedPreferences("number", Context.MODE_PRIVATE);
                final SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Login", "Complete");
                editor.commit();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(Login.this, "verification failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                Toast.makeText(Login.this, "Code sent", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
