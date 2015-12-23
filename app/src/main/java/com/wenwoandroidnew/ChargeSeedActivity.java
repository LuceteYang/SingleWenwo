package com.wenwoandroidnew;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ChargeSeedActivity extends AppCompatActivity {
    private RadioGroup rg;
    private RadioButton radioSelectButton;
    private Button btnDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_seed);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        // Set up the login form.
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        rg = (RadioGroup) findViewById(R.id.shop_seed_group);
        final RadioButton btn = (RadioButton) findViewById(R.id.radioButton);
        final RadioButton btn1 = (RadioButton) findViewById(R.id.radioButton1);
        final RadioButton btn2 = (RadioButton) findViewById(R.id.radioButton2);
        final RadioButton btn3 = (RadioButton) findViewById(R.id.radioButton3);
        final RadioButton btn4 = (RadioButton) findViewById(R.id.radioButton4);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton) {
                    btn.setBackgroundColor(Color.GRAY);
                    btn1.setBackgroundColor(Color.WHITE);
                    btn2.setBackgroundColor(Color.WHITE);
                    btn3.setBackgroundColor(Color.WHITE);
                    btn4.setBackgroundColor(Color.WHITE);
                }
                if (checkedId == R.id.radioButton1) {
                    btn.setBackgroundColor(Color.WHITE);
                    btn1.setBackgroundColor(Color.GRAY);
                    btn2.setBackgroundColor(Color.WHITE);
                    btn3.setBackgroundColor(Color.WHITE);
                    btn4.setBackgroundColor(Color.WHITE);
                }
                if (checkedId == R.id.radioButton2) {
                    btn.setBackgroundColor(Color.WHITE);
                    btn1.setBackgroundColor(Color.WHITE);
                    btn2.setBackgroundColor(Color.GRAY);
                    btn3.setBackgroundColor(Color.WHITE);
                    btn4.setBackgroundColor(Color.WHITE);
                }
                if (checkedId == R.id.radioButton3) {
                    btn.setBackgroundColor(Color.WHITE);
                    btn1.setBackgroundColor(Color.WHITE);
                    btn2.setBackgroundColor(Color.WHITE);
                    btn3.setBackgroundColor(Color.GRAY);
                    btn4.setBackgroundColor(Color.WHITE);
                }
                if (checkedId == R.id.radioButton4) {
                    btn.setBackgroundColor(Color.WHITE);
                    btn1.setBackgroundColor(Color.WHITE);
                    btn2.setBackgroundColor(Color.WHITE);
                    btn3.setBackgroundColor(Color.WHITE);
                    btn4.setBackgroundColor(Color.GRAY);
                }
            }
        });
        addListenerOnButton();
    }
    public void addListenerOnButton() {

        Button btn5 = (Button) findViewById(R.id.btn_seed_select);
        btn5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = rg.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioSelectButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(ChargeSeedActivity.this,radioSelectButton.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChargeSeedActivity.this,AlipayActivity.class);
                startActivity(intent);
            }

        });
    }


}