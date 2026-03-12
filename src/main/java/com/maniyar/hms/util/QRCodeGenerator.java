package com.maniyar.hms.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class QRCodeGenerator {

    public static byte[] generateQRCode(String text) throws WriterException, IOException {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 250, 250);

        BufferedImage image = new BufferedImage(250, 250, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < 250; x++) {
            for (int y = 0; y < 250; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF);
            }
        }

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", pngOutputStream);

        return pngOutputStream.toByteArray();
    }
}