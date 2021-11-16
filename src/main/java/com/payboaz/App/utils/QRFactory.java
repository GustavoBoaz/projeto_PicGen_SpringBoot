package com.payboaz.App.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Service;

import net.glxn.qrgen.javase.QRCode;

/**
 * Class responsible for business rules for QRCode.
 * 
 * @author BOAZ
 * @since 1.0
 *
 */
@Service
public class QRFactory {

	/**
	 * Static method responsible for generating QRCode image.
	 * 
	 * @param barcodeText
	 * @return BufferedImage
	 * @throws Exception
	 * @author Boaz
	 * @since 1.0
	 * 
	 */
	public static BufferedImage generatorQRCode(String barcodeText) throws Exception {
		ByteArrayOutputStream stream = QRCode.from(barcodeText).withSize(250, 250).stream();
		ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());

		return ImageIO.read(bis);
	}

	/**
	 * Method responsible for converting PNG to String Base64 format.
	 * 
	 * @param image
	 * @return
	 * @throws IOException
	 */
	public static String conversorPNGToString(BufferedImage image) throws IOException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(image, "PNG", out);
		byte[] bytes = out.toByteArray();

		return Base64.encodeBase64String(bytes);
	}

	/**
	 * Method responsible for informing spring that it is possible to return image
	 * in requests to the application controller.
	 * 
	 * @return BufferedImageHttpMessageConverter
	 */
	@Bean
	public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
		return new BufferedImageHttpMessageConverter();
	}
}
