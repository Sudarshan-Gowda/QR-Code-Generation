/**
 * 
 */
package com.star.sud.qrcode.service;

import com.star.sud.qrcode.status.QRCodeStatus;

/**
 * @author Sudarshan
 *
 */
public interface IQRCodeGeneratorService {

	/**
	 * @param content
	 * @return
	 */
	QRCodeStatus generateQRCodeImage(String content);

}
