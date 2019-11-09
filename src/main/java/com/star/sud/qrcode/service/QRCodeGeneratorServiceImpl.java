/**
 * 
 */
package com.star.sud.qrcode.service;

import java.io.File;
import java.nio.file.Files;

import org.apache.commons.net.util.Base64;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.star.sud.qrcode.status.QRCodeStatus;
import com.star.sud.qrcode.status.QRCodeStatus.STATUS;
import com.star.sud.qrcode.util.QRCodeUtil;

/**
 * @author Sudarshan
 *
 */
@Service("qrCodeGeneratorService")
public class QRCodeGeneratorServiceImpl implements IQRCodeGeneratorService {

	public static Logger log = Logger.getLogger(QRCodeGeneratorServiceImpl.class);

	public QRCodeStatus generateQRCodeImage(String content) {
		log.debug("generateQRCodeImage");
		QRCodeStatus codeStatus = new QRCodeStatus();
		try {

			String filePath = QRCodeUtil.generateQrCode(content);
			if (null == filePath)
				throw new Exception("filePath is null or rmpty");

			byte[] bFile = Files.readAllBytes(new File(filePath).toPath());
			byte[] encodeBase64 = Base64.encodeBase64(bFile);
			codeStatus.setResult(new String(encodeBase64, "UTF-8"));

			codeStatus.setStatus(STATUS.SUCCESS);

		} catch (Exception e) {
			log.error("generateQRCodeImage", e);
			codeStatus.setStatus(STATUS.FAILED);
		}
		return codeStatus;
	}

}
