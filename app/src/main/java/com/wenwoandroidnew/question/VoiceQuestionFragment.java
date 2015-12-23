package com.wenwoandroidnew.question;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wenwoandroidnew.R;
import com.wenwoandroidnew.question.voice.RecordActivity;
import com.wenwoandroidnew.question.voice.VoiceRecordPlayer;
import com.wenwoandroidnew.system.model.query.ModelQuestionRegisterQuery;
import com.wenwoandroidnew.system.util.AppSetting;
import com.wenwoandroidnew.system.util.UtilUi;

/**
 * A simple {@link Fragment} subclass.
 */
public class VoiceQuestionFragment extends Fragment {

    ActionBar actionBar;
    TextView mTextView;
    EditText editTitle;
    EditText editcontent;
    public static final int REQUEST_RECORED_ACTIVITY = 1;
    private String recordFileName = "";
    private boolean bPlay = false;
    ImageButton btnRecord;
    ImageButton btnPlay;
    Boolean loadVoice=false;

    private ModelQuestionRegisterQuery questionRegister;

    public VoiceQuestionFragment() {
        // Required empty public constructor
        this.setHasOptionsMenu(true);
    }

    Fragment f4;
    private static final String F4_TAG = "f4";
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_question_next :
                f4 = new QuestionRegisterFragment();
                InputMethodManager keyboard = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.hideSoftInputFromWindow(editcontent.getWindowToken(), 0);
                keyboard.hideSoftInputFromWindow(editTitle.getWindowToken(), 0);
                Bundle b = new Bundle();
                questionRegister = UtilUi.createQuestionRegister(view, AppSetting.QUESTION_TYPE_VOICE);
                if( questionRegister.isSuccess() == false){
                    Toast.makeText(getActivity(), questionRegister.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    return false;
                }

                if(loadVoice ==false){
                    Toast.makeText(getActivity(), "녹음을 하지않았습니다", Toast.LENGTH_SHORT).show();
                    return false;
                }

                questionRegister.setRecording(recordFileName);
                b.putSerializable("register", questionRegister);

                f4.setArguments(b);
                FragmentTransaction ft =getParentFragment().getParentFragment().getChildFragmentManager().beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.fragment_question_parent, f4, F4_TAG);
                ft.commit();
        }
        return super.onOptionsItemSelected(item);
    }


    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_voice_question, container, false);
        UISetting();
        ActionBarSetting();
        btnPlay = (ImageButton)view.findViewById(R.id.btn_listen_record);
        btnRecord = (ImageButton)view.findViewById(R.id.btn_record_again);
        editTitle = (EditText)view.findViewById(R.id.et_question_title);

        //음성 녹음
        final ImageButton btn =(ImageButton)view.findViewById(R.id.btn_start_record);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playStop();
                ((QuestionFragment) getParentFragment()).callMeActivity(new Intent(getActivity(), RecordActivity.class), REQUEST_RECORED_ACTIVITY);
                btn.setVisibility(View.GONE);
                btnPlay.setVisibility(View.VISIBLE);
                btnRecord.setVisibility(View.VISIBLE);
            }
        });

        // 재녹음
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playStop();
                ((QuestionFragment) getParentFragment()).callMeActivity(new Intent(getActivity(), RecordActivity.class), REQUEST_RECORED_ACTIVITY);
            }
        });


        //다시 듣기.
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( recordFileName == null || recordFileName.trim().length() == 0){
                    Toast.makeText( getActivity(), "녹음된 음성이 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 재생이 안되고 있을때
                if( bPlay == false){
                    playStart();
                }
                // 재생이 되고 있을때
                else{
                    playStop();
                }
            }
        });
        mTextView =(TextView) view.findViewById(R.id.text_input_counter);
        editcontent=(EditText)view.findViewById(R.id.et_question_content);
        editcontent.addTextChangedListener(mTextEditorWatcher);
        return view;
    }

    private VoiceRecordPlayer voiceRecordPlaye = new VoiceRecordPlayer();
    private void playStop(){
        voiceRecordPlaye.playStop();

//        ((ImageView)(view.findViewById(R.id.btn_listen_record))).setText("듣기");
        bPlay = false;
    }

    private void playStart(){
        if( voiceRecordPlaye.play( recordFileName) == false){
            Toast.makeText( getActivity(), "재생 실패", Toast.LENGTH_SHORT).show();
        }
//        ((ImageView)(view.findViewById(R.id.btn_listen_record))).setText("멈춤");
        bPlay = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        playStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        playStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        playStop();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == REQUEST_RECORED_ACTIVITY && resultCode == Activity.RESULT_OK){
            recordFileName = data.getStringExtra( RecordActivity.RESULT_RECORD_FILENAME);
            loadVoice =true;
        }
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

    private void UISetting(){
        ImageView img = (ImageView)view.findViewById(R.id.input_image1);
        img.setVisibility(View.GONE);
        img = (ImageView)view.findViewById(R.id.input_image2);
        img.setVisibility(View.GONE);
        img = (ImageView)view.findViewById(R.id.load_image);
        img.setVisibility(View.GONE);
    }

    private void ActionBarSetting(){
        actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        TextView textView = new TextView(getActivity());
        textView.setText("Voice Question");
        textView.setTextColor(getResources().getColor(R.color.colorWhite));
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(textView, new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
    }
}
