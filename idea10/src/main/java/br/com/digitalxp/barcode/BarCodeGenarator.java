package br.com.digitalxp.barcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

public class BarCodeGenarator {

	public static void main(String[] args) throws Exception {
		Code39Bean bean = new Code39Bean();

		final int dpi = 150;

		// Configure the barcode generator
		bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); 
		bean.setWideFactor(3);
		bean.doQuietZone(false);

		// Open output file
		File outputFile = new File("C:\\Lixo\\barcode\\out.png");
		OutputStream out = new FileOutputStream(outputFile);

		try {

			// Set up the canvas provider for monochrome PNG output
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/x-png", dpi,
					BufferedImage.TYPE_BYTE_BINARY, false, 0);

			// Generate the barcode
			bean.generateBarcode(canvas, "Madeira 250Reais metro quadrado imagem X");

			// Signal end of generation
			canvas.finish();
		} finally {
			out.close();
		}
	}
}