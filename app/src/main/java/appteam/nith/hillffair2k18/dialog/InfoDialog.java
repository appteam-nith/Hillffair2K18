package appteam.nith.hillffair2k18.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import appteam.nith.hillffair2k18.R;

/**
 * Created by naman on 20-09-2018.
 */

public class InfoDialog extends Dialog {

    String info;

    public InfoDialog(@NonNull Activity context, String info) {
        super(context);
        this.info = info;
        initUI();
    }

    public void initUI() {
        setContentView(R.layout.infodialog);
        setCancelable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.dimAmount = 0.3f;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setAttributes(lp);
        TextView textView = findViewById(R.id.dialog);
        textView.setText(info);
        setCanceledOnTouchOutside(true);
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }
}