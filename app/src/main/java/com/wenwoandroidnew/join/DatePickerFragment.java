package com.wenwoandroidnew.join;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.wenwoandroidnew.R;

import java.util.Calendar;

/**
 * Created by ModelLoginQuery on 2015-11-11.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            //Use the current date as the default11PNG date in the date picker
            final Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR,1991);
            c.set(Calendar.MONTH,0);
            c.set(Calendar.DAY_OF_MONTH,1);
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            //*********** Just uncomment any one below line to apply another Theme ************
//            return new DatePickerDialog(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_DARK,this, year, month, day);
            //return new DatePickerDialog(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, this, year, month, day);
            //return new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_DARK, this, year, month, day);
            return new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT, this, year, month, day);
            //return new DatePickerDialog(getActivity(), AlertDialog.THEME_TRADITIONAL, this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            //Do something with the date chosen by the user
            int montth =  month+1;
            Toast.makeText(getActivity(), year + "년" + montth + "월" + day + "일", Toast.LENGTH_SHORT).show();
            TextView tv_birthday_day = (TextView) getActivity().findViewById(R.id.tv_birthday_day);
            TextView tv_birthday_year = (TextView) getActivity().findViewById(R.id.tv_birthday_year);
            TextView tv_birthday_month = (TextView) getActivity().findViewById(R.id.tv_birthday_month);
            tv_birthday_day.setText(String.valueOf(day));
            tv_birthday_year.setText(String.valueOf(year));
            tv_birthday_month.setText(String.valueOf(month+1));
        }
}
