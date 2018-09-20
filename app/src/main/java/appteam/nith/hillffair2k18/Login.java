package appteam.nith.hillffair2k18;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

import appteam.nith.hillffair2k18.activity.MainActivity;

public class Login extends AppCompatActivity {
    TextView btnGenerateOTP, btnSignIn;
    String phoneNumber, otp;
    EditText etPhoneNumber, etOTP;
    RelativeLayout mobile,verify;
    FirebaseAuth auth;
    public static String phone;
    private String verificationCode;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        FirebaseApp.initializeApp(this);
        StartFirebaseLogin();
    }
    private void findViews() {
        btnGenerateOTP=findViewById(R.id.btn_generate_otp);
        btnSignIn=findViewById(R.id.btn_sign_in);
        etPhoneNumber=findViewById(R.id.et_phone_number);
        etOTP=findViewById(R.id.et_otp);
        mobile = findViewById(R.id.mobile);
        verify = findViewById(R.id.verify);
        setupdata();
    }

    public void setupdata()
    {
        phoneNumber = String.valueOf(etPhoneNumber.getText());
        btnGenerateOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumber = String.valueOf(etPhoneNumber.getText());
                phone = phoneNumber;
                if (phoneNumber.length()== 0)
                    Toast.makeText(Login.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
                else {
                    btnSignIn.setVisibility(View.VISIBLE);
                    btnGenerateOTP.setVisibility(View.GONE);
                    mobile.setVisibility(View.GONE);
                    verify.setVisibility(View.VISIBLE);
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
                otp=etOTP.getText().toString();
                if (otp.length() == 0)
                {
                    Toast.makeText(Login.this, "Enter OTP", Toast.LENGTH_SHORT).show();
                }
                else {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, otp);
                    SigninWithPhone(credential);
                }
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
                            startActivity(new Intent(Login.this,Housie.class));
                            finish();
                        } else {
                            Toast.makeText(Login.this,"Incorrect OTP",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void StartFirebaseLogin() {

        auth = FirebaseAuth.getInstance();
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(Login.this,"verification completed",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(Login.this,"verification failed",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                Toast.makeText(Login.this,"Code sent",Toast.LENGTH_SHORT).show();
            }
        };
    }
}
