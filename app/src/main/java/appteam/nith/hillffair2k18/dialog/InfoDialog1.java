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
import android.widget.Toast;

import appteam.nith.hillffair2k18.R;

import static appteam.nith.hillffair2k18.activity.RouletteActivity.arrayList;

/**
 * Code by ThisIsNSH on Someday.
 */

public class InfoDialog1 extends Dialog {

    String info;
    TextView next, dialog;
    EditText editBet;
    Activity context;
    int check = 1;

    public InfoDialog1(@NonNull Activity context) {
        super(context);
        this.context = context;
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
        next.setTextColor(Color.parseColor("#0000ff"));
        dialog = findViewById(R.id.dialog);
        editBet = findViewById(R.id.editBet);
        editBet.setText("");

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editBet.getText().toString().equals("")) {
                    Toast.makeText(context, "Enter some bet first", Toast.LENGTH_SHORT).show();
                } else {
                    if (Integer.parseInt(String.valueOf(editBet.getText())) > 36 || Integer.parseInt(String.valueOf(editBet.getText())) < 0) {
                        Toast.makeText(context, "Enter no. less than 37", Toast.LENGTH_SHORT).show();
                    } else if (!arrayList.contains(String.valueOf(editBet.getText()))) {
                        ++check;
                        arrayList.add(String.valueOf(editBet.getText()));
                    } else {
                        Toast.makeText(context, "Number already entered", Toast.LENGTH_SHORT).show();
                    }
                }

                if (check == 5) {
                    next.setText("Spin");
                    next.setTextColor(Color.parseColor("#FF1B5E20"));
                }
                if (check <= 5) {
                    dialog.setText("Make Bet No. " + check + " of 5");
                    editBet.setText("");
                } else {
                    dismiss();
                }
            }
        });

        getWindow().setGravity(Gravity.CENTER);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

}