package com.wenwoandroidnew.question;


import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
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
import com.wenwoandroidnew.system.model.query.ModelQuestionRegisterQuery;
import com.wenwoandroidnew.system.util.AppSetting;
import com.wenwoandroidnew.system.util.UtilUi;

import java.util.ArrayList;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class PictureQuestionFragment extends Fragment {

    public PictureQuestionFragment() {
        // Required empty public constructor
        this.setHasOptionsMenu(true);
    }
    TextView mTextView;
    EditText editcontent;
    EditText editTitle;
    ActionBar actionBar;
    Boolean loadimaged =false;

    private ModelQuestionRegisterQuery questionRegister;
    private List<String> imageList = null; // 선택 이미지 리스트

    public static String TAG = "";
    ImageView imageLoad;

    Fragment f4;
    private static final String F4_TAG = "f4";

    public static final int REQUEST_PICTURE_ACTIVITY = 0;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_question_next :
                f4 = new QuestionRegisterFragment();
                Bundle b = new Bundle();
                f4.setArguments(b);

                InputMethodManager keyboard = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.hideSoftInputFromWindow(editcontent.getWindowToken(), 0);
                keyboard.hideSoftInputFromWindow(editTitle.getWindowToken(), 0);
                questionRegister = UtilUi.createQuestionRegister(view, AppSetting.QUESTION_TYPE_PICTURE);
                if( questionRegister.isSuccess() == false){
                    Toast.makeText(getActivity(), questionRegister.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    return false;
                }
                if(loadimaged ==false){
                    Toast.makeText(getActivity(), "사진을 첨부하지않았습니다", Toast.LENGTH_SHORT).show();
                    return false;
                }

                questionRegister.setImage(imageList);
                b.putSerializable("register", questionRegister);

                FragmentTransaction ft =getParentFragment().getParentFragment().getChildFragmentManager().beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.fragment_question_parent, f4, F4_TAG);
                ft.commit();
        }
        return super.onOptionsItemSelected(item);
    }


    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_picture_question, container, false);
        TAG = getTag();
        UISetting();
        ActionBarSetting();
        mTextView =(TextView) view.findViewById(R.id.text_input_counter);
        editcontent=(EditText)view.findViewById(R.id.et_question_content);
        editcontent.addTextChangedListener(mTextEditorWatcher);
        editTitle = (EditText)view.findViewById(R.id.et_question_title);
        ((ImageView) view.findViewById( R.id.load_image)).setOnClickListener(btnClick);

        Bundle b = getArguments();
        if(b!=null){
            editTitle.setText(b.getString("title"));
            editcontent.setText(b.getString("context"));
//            editTitle.setText("질문수정테스트");
//            editcontent.setText("질문수정테스트");
        }

        return view;
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



    private void showSelectImage(){

        int selectedMode = MultiImageSelectorActivity.MODE_MULTI;

        Intent intent = new Intent( getContext(), MultiImageSelectorActivity.class);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 2);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, selectedMode);
        if(mSelectPath != null && mSelectPath.size()>0){
            intent.putExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, mSelectPath);
        }
        ((QuestionFragment)getParentFragment()).callMeActivity( intent , REQUEST_PICTURE_ACTIVITY);
    }

    private void setImage(String ... imgs){

        imageList = new ArrayList<>();
        ((ImageView) view.findViewById(R.id.input_image1)).setVisibility(View.VISIBLE);
        ((ImageView) view.findViewById(R.id.input_image1)).setImageBitmap(BitmapFactory.decodeFile(imgs[0]));
        imageList.add( imgs[0]);
        if( imgs.length == 2){
            ((ImageView)view.findViewById(R.id.input_image2)).setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById( R.id.input_image2)).setImageBitmap(BitmapFactory.decodeFile(imgs[1]));
            imageList.add( imgs[1]);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_PICTURE_ACTIVITY){
            if(resultCode == getActivity().RESULT_OK){
                loadimaged=true;
                mSelectPath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);

                if( mSelectPath.size() == 1){
                    setImage( mSelectPath.get(0));
                }
                else {
                    setImage( mSelectPath.get(0) , mSelectPath.get(1));
                }

            }
        }
    }

    //public static final int REQUEST_IMAGE = 2;
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

    private void UISetting(){
        ImageView img = (ImageView)view.findViewById(R.id.input_image1);
        img.setVisibility(View.INVISIBLE);
        img = (ImageView)view.findViewById(R.id.input_image2);
        img.setVisibility(View.INVISIBLE);
        ImageButton btn = (ImageButton) view.findViewById(R.id.btn_record_again);
        btn.setVisibility(View.GONE);
        btn = (ImageButton) view.findViewById(R.id.btn_start_record);
        btn.setVisibility(View.GONE);
        btn = (ImageButton) view.findViewById(R.id.btn_listen_record);
        btn.setVisibility(View.GONE);
    }

    private void ActionBarSetting(){
        actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        TextView textView = new TextView(getActivity());
        textView.setText("Image Question");
        textView.setTextColor(getResources().getColor(R.color.colorWhite));
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(textView, new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
    }

}
