package com.wenwoandroidnew.discover;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.wenwoandroidnew.system.common.CallResult;
import com.wenwoandroidnew.system.module.ModuleFriend;
import com.wenwoandroidnew.system.util.AppSetting;

public class QRCodeActivity extends AppCompatActivity implements CallResult<Boolean> {

    private String qrUrl = "";

    private final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_qrcode);
        scanQR();
    }

    public void scanQR() {
        try {
            //start the scanning activity from the com.google.zxing.client.android.SCAN intent
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            showDialog(QRCodeActivity.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
        }
    }

    //alert dialog for downloadDialog
    private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title);
        downloadDialog.setMessage(message);
        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    act.startActivity(intent);
                } catch (ActivityNotFoundException anfe) {

                }
            }
        });
        downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return downloadDialog.show();
    }
    
    //on ActivityResult method
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");

                ModuleFriend.addFriend(this, contents);
                if( AppSetting.LOG_TYPE == true) {
                    Toast toast = Toast.makeText(this, "친구 Email:" + contents, Toast.LENGTH_LONG);
                    toast.show();
                }
                finish();
            }
        }
    }

    @Override
    public void callResult(Boolean aBoolean) {
        
        if( aBoolean == Boolean.TRUE){
            Toast.makeText(QRCodeActivity.this, "친구가 등록되었습니다.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(QRCodeActivity.this, "친구등록에 실패했습니다", Toast.LENGTH_SHORT).show();
        }
    }
}
