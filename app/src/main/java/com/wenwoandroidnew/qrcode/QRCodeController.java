package com.wenwoandroidnew.qrcode;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.wenwoandroidnew.system.util.UtilCommon;

/**
 * Created by SeungJin on 2015-11-24.
 */
public class QRCodeController {

    public Bitmap createQRCode( String str) throws WriterException {

        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = writer.encode(
                str, BarcodeFormat.QR_CODE, 400, 400
        );
        return UtilCommon.qrcodeMatrixToBitmap(matrix);
    }
}
