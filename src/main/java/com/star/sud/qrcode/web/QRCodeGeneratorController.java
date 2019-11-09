/**
 * 
 */
package com.star.sud.qrcode.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.star.sud.qrcode.model.GenerateQrCode;
import com.star.sud.qrcode.service.IQRCodeGeneratorService;
import com.star.sud.qrcode.status.QRCodeStatus;
import com.star.sud.qrcode.status.QRCodeStatus.STATUS;

/**
 * @author Sudarshan
 *
 */
@Controller
public class QRCodeGeneratorController {

	@Autowired
	@Qualifier("qrCodeGeneratorService")
	IQRCodeGeneratorService codeGeneratorService;

	public static final String ROOT_URL = "/";
	public static final Logger log = Logger.getLogger(QRCodeGeneratorController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getLandingPage(Model model) {
		log.debug("getLandingPage");
		try {
			model.addAttribute("generateQrCode", new GenerateQrCode());

		} catch (Exception e) {
			log.error("getLandingPage", e);
		}
		return "home";
	}

	@RequestMapping(value = "/generateQrCode", method = RequestMethod.POST)
	public String generateQrCode(Model model, @ModelAttribute GenerateQrCode generateQrCode) {
		log.debug("generateQrCode");
		try {

			QRCodeStatus status = codeGeneratorService.generateQRCodeImage(generateQrCode.getContent());
			if (status != null && status.getStatus().equals(STATUS.SUCCESS)) {
				generateQrCode.setBase64Image(status.getResult());
				model.addAttribute("msgsuccess", "Successfully generated!!");
				model.addAttribute("isDisabled", Boolean.TRUE);
				model.addAttribute("isEnableQrImg", Boolean.TRUE);
			} else {
				model.addAttribute("msgdanger", "Failed to generate QR code!!");
				model.addAttribute("isDisabled", Boolean.FALSE);
			}

			model.addAttribute("generateQrCode", generateQrCode);

		} catch (Exception e) {
			log.error("generateQrCode", e);
		}

		return "home";
	}

}
