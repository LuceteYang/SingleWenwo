package com.wenwoandroidnew.question;


import android.content.Context;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class TextQuestionFragment extends Fragment {

    ActionBar actionBar;
    TextView mTextView;
    EditText editTitle;
    EditText editcontent;
    public TextQuestionFragment() {
        // Required empty public constructor
        this.setHasOptionsMenu(true);
    }

    private ModelQuestionRegisterQuery questionRegister;

    Fragment f4;
    private static final String F4_TAG = "f4";
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_question_next :
                f4 = new QuestionRegisterFragment();
                Bundle b = new Bundle();
                InputMethodManager keyboard = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.hideSoftInputFromWindow(editcontent.getWindowToken(), 0);
                keyboard.hideSoftInputFromWindow(editTitle.getWindowToken(), 0);

                questionRegister = UtilUi.createQuestionRegister( view, AppSetting.QUESTION_TYPE_TEXT);
                if( questionRegister.isSuccess() == false){
                    Toast.makeText( getActivity(), questionRegister.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    return false;
                }

                b.putSerializable("register", questionRegister);
                f4.setArguments(b);
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
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_text_question, container, false);
        ActionBarSetting();
        UISetting();
        mTextView =(TextView) view.findViewById(R.id.text_input_counter);
        editcontent=(EditText)view.findViewById(R.id.et_question_content);
        editcontent.addTextChangedListener(mTextEditorWatcher);
        editTitle = (EditText)view.findViewById(R.id.et_question_title);
        editTitle.setText("");
        editcontent.setText("");
        Bundle b = getArguments();
        if(b!=null){
            editTitle.setText("질문수정테스트");
            editcontent.setText("질문수정테스트");
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

    private void UISetting(){
        ImageButton btn = (ImageButton)view.findViewById(R.id.btn_record_again);
        btn.setVisibility(View.GONE);
        btn = (ImageButton)view.findViewById(R.id.btn_start_record);
        btn.setVisibility(View.GONE);
        btn = (ImageButton)view.findViewById(R.id.btn_listen_record);
        btn.setVisibility(View.GONE);
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
        textView.setText("Text Question");
        textView.setTextColor(getResources().getColor(R.color.colorWhite));
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(textView, new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
    }
}
