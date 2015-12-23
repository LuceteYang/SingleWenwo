package com.wenwoandroidnew.writer;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wenwoandroidnew.R;
import com.wenwoandroidnew.newsfeed.FeedAdapter;
import com.wenwoandroidnew.newsfeed.QuestionItem;
import com.wenwoandroidnew.newsfeed.answer.AnswerListFragment;
import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.model.query.ModelBestAnswerQuery;
import com.wenwoandroidnew.system.model.ModelQuestionList;
import com.wenwoandroidnew.system.model.query.ModelMagazineQuery;
import com.wenwoandroidnew.system.module.ModelPicture;
import com.wenwoandroidnew.system.module.ModuleAnswer;
import com.wenwoandroidnew.system.module.ModuleMagazineList;
import com.wenwoandroidnew.system.util.AppSetting;
import com.wenwoandroidnew.system.util.UtilUi;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WriterAnswerFragment extends Fragment implements CallResult<ModelQuestionList> {

    ListView listView;
    FeedAdapter mAdapter;
    private Dialog dialog;

    public WriterAnswerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_writer_answer, container, false);
        listView = (ListView)view.findViewById(R.id.writer_answer_listView);
        mAdapter = new FeedAdapter();
        listView.setAdapter(mAdapter);

        dialog = UtilUi.showWaitDialog(getContext(), "대표 답변 조회중..."); // 다이아로그 띄우기
        ModelBestAnswerQuery geoquery = new ModelBestAnswerQuery(AppGlobalSetting.WRITER_EMAIL);
        ModuleAnswer.bestAnswer(this, geoquery);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                QuestionItem item = mAdapter.getItem(position);
                AnswerListFragment dialog = new AnswerListFragment();
                Bundle b = new Bundle();
                b.putParcelable("question", item);
                dialog.setArguments(b);
                dialog.show(getActivity().getSupportFragmentManager(), "answer");
            }
        });

        return view;
    }

    @Override
    public void callResult(ModelQuestionList modelQuestionList) {

        for (int i = 0; i < modelQuestionList.getData().size(); i++) {
            ModelQuestionList.ModelData mq = modelQuestionList.getData().get(i);
            QuestionItem d = new QuestionItem(Parcel.obtain());
            Drawable questionIcon;
            d.questionCategory = mq.getCategory();//"Emergency";
            d.voteNumber = Integer.parseInt(mq.getSpentSeed());
            d.questionLocation = mq.getSi() + " " + mq.getDu() + " " + mq.getDong(); //"낙성대역 근처";
            d.questionPName = mq.getNickName();//"박상환";
            d.questionTitle = mq.getTitle();//"안녕하세요";
            d.questionAccept = Integer.toString(mq.getAnum().size());
            d.questionTime = mq.getDueTime();//"2015년 5월 8일";
            d.questionContext = mq.getText();//"낙성대역 근처 치킨집좀 알려주세용~~";
            d.status = Integer.toString(mq.getStatus());
            d.qnum = mq.getQnum();
            ModelPicture tempPicture = null;
            for (int j = 0; j < mq.getImage().size(); j++) {
                List<ModelPicture> imageList = new ArrayList<>();
                tempPicture = new ModelPicture(
                        mq.getImage().get(j).getOriginalPath(),
                        mq.getImage().get(j).getTh_path());
                imageList.add(tempPicture); // 썸네일 이미지로 리스트 보여주기
                if (j == 0) {
                    d.setQuestionImage1(imageList);
                } else {
                    d.setQuestionImage2(imageList);
                }
            }
            List<ModelPicture> ProfileimageList = new ArrayList<>();
            tempPicture = null;
            for (int j = 0; j < mq.getProfileImage().size(); j++) {
                tempPicture = new ModelPicture(
                        mq.getProfileImage().get(0).getOriginalPath(),
                        mq.getProfileImage().get(0).getTh_path());
                ProfileimageList.add(tempPicture); // 썸네일 이미지로 리스트 보여주기
            }
            if( ProfileimageList.size() >0){
                d.setProfileList(ProfileimageList );
            }

//            0:text, 1:image, 2:recording
            if (mq.getType() == 0) {
                questionIcon = getResources().getDrawable(R.drawable.blank);
            } else if (mq.getType() == 1) {
                questionIcon = getResources().getDrawable(R.drawable.myfeed_img);
            } else {
                questionIcon = getResources().getDrawable(R.drawable.myfeed_voice);
            }

            d.questionIcon = questionIcon;

            mAdapter.add(d);

        }
        UtilUi.hideWaitDialog(dialog);
    }
    }
