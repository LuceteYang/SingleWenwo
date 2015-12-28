package com.wenwoandroidnew.answerer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wenwoandroidnew.R;
import com.wenwoandroidnew.question.QuestionFragment;

import java.util.ArrayList;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class AnswerActivity extends AppCompatActivity {
    TextView mTextView;
    EditText editcontent;
    EditText editTitle;
    ActionBar actionBar;
    Boolean loadimaged =false;
    public static final int REQUEST_PICTURE_ACTIVITY1 = 0;
    private List<String> imageList = null; // 선택 이미지 리스트

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // XML로 옵션메뉴 추가 하기
        getMenuInflater().inflate(R.menu.menu_question, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_question_next:
                AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(AnswerActivity.this);
                myAlertDialog.setTitle("질문등록");
                myAlertDialog.setMessage("질문등록 하시겠습니까?");
                myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                        Toast.makeText(AnswerActivity.this, "질문등록", Toast.LENGTH_SHORT).show();
                    }
                });
                myAlertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });
                myAlertDialog.show();
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer2);
        UISetting();
        ActionBarSetting();
        mTextView =(TextView)findViewById(R.id.text_input_counter);
        editcontent=(EditText)findViewById(R.id.et_question_content);
        editcontent.addTextChangedListener(mTextEditorWatcher);
        editTitle = (EditText)findViewById(R.id.et_question_title);
        ((ImageView)findViewById(R.id.load_image)).setOnClickListener(btnClick);
    }

    private final TextWatcher mTextEditorWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mTextView.setText(String.valueOf(s.length()));
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private ArrayList<String> mSelectPath;

    private View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                // 이미지 로드하기
                case R.id.load_image: {
                    showSelectImage();
                    break;
                }

            }
        }
    };
    int childRequestCode = 1;
    private void showSelectImage(){

        int selectedMode = MultiImageSelectorActivity.MODE_MULTI;

        Intent intent = new Intent(AnswerActivity.this, MultiImageSelectorActivity.class);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 2);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, selectedMode);
        if(mSelectPath != null && mSelectPath.size()>0){
            intent.putExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, mSelectPath);
        }
        startActivityForResult(intent, childRequestCode);
    }

    private void setImage(String ... imgs){

        imageList = new ArrayList<>();
        ((ImageView)findViewById(R.id.input_image1)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.input_image1)).setImageBitmap(BitmapFactory.decodeFile(imgs[0]));
        imageList.add( imgs[0]);
        if (imgs.length == 2){
            ((ImageView)findViewById(R.id.input_image2)).setVisibility(View.VISIBLE);
            ((ImageView)findViewById( R.id.input_image2)).setImageBitmap(BitmapFactory.decodeFile(imgs[1]));
            imageList.add(imgs[1]);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK) {
            loadimaged = true;
            mSelectPath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);

            if (mSelectPath.size() == 1) {
                setImage(mSelectPath.get(0));
            } else {
                setImage(mSelectPath.get(0), mSelectPath.get(1));
            }
        }
    }

    private void UISetting(){
        ImageView img = (ImageView)findViewById(R.id.input_image1);
        img.setVisibility(View.INVISIBLE);
        img = (ImageView)findViewById(R.id.input_image2);
        img.setVisibility(View.INVISIBLE);
        ImageButton btn = (ImageButton)findViewById(R.id.btn_record_again);
        btn.setVisibility(View.GONE);
        btn = (ImageButton)findViewById(R.id.btn_start_record);
        btn.setVisibility(View.GONE);
        btn = (ImageButton)findViewById(R.id.btn_listen_record);
        btn.setVisibility(View.GONE);
    }

    private void ActionBarSetting(){
        actionBar = getSupportActionBar();
        TextView textView = new TextView(getApplicationContext());
        textView.setText("Answer");
        textView.setTextColor(getResources().getColor(R.color.colorWhite));
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(textView, new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
    }
}
