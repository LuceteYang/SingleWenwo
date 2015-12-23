package com.wenwoandroidnew.answerer;

import android.app.Dialog;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.wenwoandroidnew.R;
import com.wenwoandroidnew.newsfeed.FeedAdapter;
import com.wenwoandroidnew.newsfeed.QuestionItem;
import com.wenwoandroidnew.newsfeed.answer.AnswerListFragment;

public class AnswerActivity extends AppCompatActivity {

    private PullToRefreshListView listView;
    private FeedAdapter mAdapter;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        listView = (PullToRefreshListView)findViewById(R.id.answer_listView);
        mAdapter = new FeedAdapter();
        listView.setAdapter(mAdapter);
        initData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                QuestionItem item = mAdapter.getItem(position - 1);
                if (item.getStatus().equals("0")) {
                    AnswererListFragment dialog = new AnswererListFragment();
                    Bundle b = new Bundle();
                    b.putParcelable("question", item);
                    dialog.setArguments(b);
                    dialog.show(getSupportFragmentManager(), "answer");
                } else if (item.getStatus().equals("1")) {
                    AnswererListFragment dialog = new AnswererListFragment();
                    Bundle b = new Bundle();
                    b.putParcelable("question", item);
                    dialog.setArguments(b);
                    dialog.show(getSupportFragmentManager(), "answer");
                } else if (item.getStatus().equals("2")) {
                    Toast.makeText(AnswerActivity.this, "질문수정....준비중입니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 5 ; i++) {
            QuestionItem d = new QuestionItem(Parcel.obtain());
            d.questionCategory="Emergency";
            d.voteNumber=3;
            d.questionLocation="낙성대역 근처";
            d.questionPName="Rooney";
            d.questionTitle="안녕하세요";
            d.questionAccept="채택완료된 답변이 1개 답변이 있습니다.";
            d.questionTime="2015년 5월 8일";
            d.questionContext="낙성대역 근처 치킨집좀 알려주세여~~";
            d.questionIcon = getResources().getDrawable(R.drawable.blank);
            d.status = Integer.toString(0);
            d.setIsAllfeed(false);
            mAdapter.add(d);
        }
    }
}
