package com.wenwoandroidnew.join;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import com.wenwoandroidnew.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JoinChoiceActivity extends AppCompatActivity {
    @Bind(R.id.img_answer)
    ImageView imgview_answer;

    @Bind(R.id.img_quest)
    ImageView imgview_quest;

    @Bind(R.id.iv_join_choice_close)
    ImageView imgclose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_choice);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        ButterKnife.bind(this);
        imgview_answer.setImageDrawable(getResources().getDrawable(R.drawable.img_answer_join_choice));
        imgview_quest.setImageDrawable(getResources().getDrawable(R.drawable.img_question_join_choice));
    }
    //답변자 유아이추가로인해 기능잠시 막음
//    @OnClick(R.id.img_answer) void onAnswerJoin( ){
//        Intent i = new Intent( JoinChoiceActivity.this, CertificationEmailActivity.class);
//        i.putExtra("mantype","aemail");
//        startActivity(i);
//    }
//

    @OnClick(R.id.img_quest) void onQuestJoin( ){
        Intent i = new Intent( JoinChoiceActivity.this, CertificationEmailActivity.class);
        i.putExtra("mantype","qemail");
        startActivity(i);
    }

    @OnClick(R.id.iv_join_choice_close) void onClose(){
        finish();
    }

}
