package appteam.nith.hillffair2k18.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.activity.RouletteActivity;

/**
 * Code by ThisIsNSH on Someday.
 */

public class InfoDialog1 extends Dialog {

    String info;
    TextView next, dialog;
    EditText editBet;
    int check = 1;

    public InfoDialog1(@NonNull Activity context) {
        super(context);
        initUI();
    }

    public void initUI() {
        setContentView(R.layout.infodialog1);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.dimAmount = 0.3f;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setAttributes(lp);

        next = findViewById(R.id.next);
        dialog = findViewById(R.id.dialog);
        editBet = findViewById(R.id.editBet);
        editBet.setText("");

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++check;
                RouletteActivity.arrayList.add(String.valueOf(editBet.getText()));
                if (check == 5) {
                    next.setText("Spin");
                }
                if (check <=5 ) {
                    dialog.setText("Make Bet No. " + check);
                    editBet.setText("");
                } else {
                    dismiss();
                }
            }
        });

        setCanceledOnTouchOutside(true);
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

}