package appteam.nith.hillffair2k18.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import appteam.nith.hillffair2k18.R;


public class CautionDialog extends Dialog {

    TextView textView;

    public CautionDialog(@NonNull Activity context) {
        super(context);
        initUI();
    }

    public void initUI() {
        setContentView(R.layout.activity_caution_dialog);
        setCancelable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.dimAmount = 0.3f;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setAttributes(lp);
        setCanceledOnTouchOutside(true);
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        findViewById(R.id.proceed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}