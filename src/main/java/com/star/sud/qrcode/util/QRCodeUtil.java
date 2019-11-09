/**
 * 
 */
package com.star.sud.qrcode.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.star.sud.qrcode.constant.QRCodeConstants;

/**
 * @author Sudarshan
 *
 */
public class QRCodeUtil {

	/**
	 * @param qrCodeText
	 * @throws Exception
	 */
	public static String generateQrCode(String qrCodeText) throws Exception {

		Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
		hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

		hintMap.put(EncodeHintType.MARGIN, 1);
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, QRCodeConstants.QR_CODE_WIDTH,
				QRCodeConstants.QR_CODE_HEIGHT, hintMap);
		int CrunchifyWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < CrunchifyWidth; i++) {
			for (int j = 0; j < CrunchifyWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}

		// String filePath = getFilePath();
		String filePath = getTempFilePath();
		ImageIO.write(image, QRCodeConstants.QR_CODE_FILE_EXTENSION, new File(filePath));
		return filePath;
	}

	/**
	 * @param qrCodeText
	 * @throws Exception
	 */
	public static void generateBasicQrCode(String qrCodeText) throws Exception {

		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, QRCodeConstants.QR_CODE_WIDTH,
				QRCodeConstants.QR_CODE_HEIGHT);

		Path path = FileSystems.getDefault().getPath(getFilePath());
		MatrixToImageWriter.writeToPath(bitMatrix, QRCodeConstants.QR_CODE_FILE_EXTENSION, path);
	}

	// Helper Class
	//////////////////
	private static String getFilePath() throws Exception {
		String fileName = QRCodeConstants.QR_CODE_DEFAULT_FILE_NAME + "." + QRCodeConstants.QR_CODE_FILE_EXTENSION;
		String filePath = QRCodeConstants.QR_CODE_FILE_PATH + QRCodeConstants.QR_CODE_PATH_SEPERATOR + fileName;
		File file = new File(filePath);
		if (!file.exists())
			file.mkdirs();
		return filePath;
	}

	private static String getTempFilePath() throws Exception {
		File temp = File.createTempFile("QR_Code_generation", ".csv");
		String absolutePath = temp.getAbsolutePath();
		return absolutePath;

	}

}
