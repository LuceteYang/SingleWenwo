package com.wenwoandroidnew.discover;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wenwoandroidnew.R;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.model.JsonNULL;
import com.wenwoandroidnew.system.model.ModelCurrency;
import com.wenwoandroidnew.system.module.ModuleCurrency;

public class CurrencyActivity extends AppCompatActivity implements CallResult<ModelCurrency> {
    private String countries[] =
            new String[] {"CNY", "KRW" ,"USD" };
    private int topState=0;
    private int bottomState=1;
    private int flags[] =
            new int[] {R.drawable.china, R.drawable.korea, R.drawable.american};
    private String Icon[] =
            new String[] {"¥","₩","$"};
    Spinner spinner, spinner2;
    RelativeLayout topLayout, bottomLayout;
    ArrayAdapter<String> mAdapter;
    TextView topIcon, bottomIcon, resultText;
    ImageView result;
    EditText et;
    final int sdk = android.os.Build.VERSION.SDK_INT;
    double cny, krw, usd=1.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        getSupportActionBar().setTitle("Exchange Rate");
        mAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.view_feedspinner_text);
        mAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        topLayout = (RelativeLayout)findViewById(R.id.top_layout);
        bottomLayout = (RelativeLayout)findViewById(R.id.bottom_layout);
        topIcon = (TextView)findViewById(R.id.tv_top_icon);
        bottomIcon =(TextView)findViewById(R.id.tv_bottom_icon);
        result = (ImageView)findViewById(R.id.result);
        resultText = (TextView)findViewById(R.id.textView17);
        et = (EditText)findViewById(R.id.editText3);
        JsonNULL model = new JsonNULL();
        ModuleCurrency.getCurrency(this, model);

        spinner.setAdapter(mAdapter);
        spinner2.setAdapter(mAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorPrimary));
                topIcon.setText(Icon[position]);
                et.setText("");
                resultText.setText("");
                topState = position;
                if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    topLayout.setBackgroundDrawable(getResources().getDrawable(flags[position]));
                } else {
                    topLayout.setBackground(getResources().getDrawable(flags[position]));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorPrimary));
                et.setText("");
                resultText.setText("");
                bottomIcon.setText(Icon[position]);
                bottomState = position;
                if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    bottomLayout.setBackgroundDrawable(getResources().getDrawable(flags[position]));
                } else {
                    bottomLayout.setBackground(getResources().getDrawable(flags[position]));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        });
        initData();
        spinner.setSelection(0);
        spinner2.setSelection(1);
        et.addTextChangedListener(CurrencyWatcher);

    }

    private final TextWatcher CurrencyWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void afterTextChanged(Editable s) {
            Log.d("dd",Boolean.toString(s.toString().equals("")));
            if(!s.toString().equals("")){
                if(topState==bottomState){
                    resultText.setText(s.toString());
                }else {
                    double newDouble=Double.parseDouble(s.toString());
                    double tempDouble = 0;
                    int tempInt;
                    if(topState==0){
                        if(bottomState==1){
                            tempDouble = newDouble/cny*100*krw;
                        }else if(bottomState==2){
                            tempDouble = newDouble/cny*100;
                            }
                    }else if(topState==1){
                        if(bottomState==0){
                            tempDouble = newDouble/krw*100*cny;
                        }else if(bottomState==2){
                            tempDouble = newDouble/krw*100;
                        }
                    }else{
                        if(bottomState==0){
                            tempDouble = newDouble*cny*100;
                        }else if(bottomState==1){
                            tempDouble = newDouble*krw*100;
                        }
                    }
                    tempInt = (int)tempDouble;
                    tempDouble = (double)tempInt/100;
                    resultText.setText(Double.toString(tempDouble));
                }
            }
        }
    };

    private void initData() {
        for(int i=0; i<countries.length;i++){
            mAdapter.add(countries[i]);
        }
    }

    @Override
    public void callResult(ModelCurrency modelCurrency) {
        ModelCurrency.RateInfo mq = modelCurrency.getRates();
        Log.d("dd",mq.toString());
        krw = mq.getKRW();
        cny = mq.getCNY();
    }
}

