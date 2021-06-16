/**
 *
 * @author Vrion
 */

import java.io.BufferedReader;
import java.io.IOException;

public class LineCounter {
	public static int getNumberOfLines(BufferedReader bR) throws IOException {
		int count = 0;
		boolean komentiFilloj = false;
		String line = null;

		while ((line = bR.readLine()) != null) {
			line = line.trim();
			if (line.equals("") || (line.isEmpty()) || line.startsWith("//")) {
				continue;
			}
			if (komentiFilloj) {
				if (komentiPerfundoj(line)) {
					line = line.substring(line.indexOf("*/") + 2).trim();
					komentiFilloj = false;
					if (line.equals("") || line.isEmpty() || line.startsWith("//")) {
						continue;
					}
				} else
					continue;
			}
			if (eshteRreshtKod(line)) {
				count++;
			}
			if (komentiFilloj(line)) {
				komentiFilloj = true;
			}
		}
		return count;
	}
	
	private static boolean komentiFilloj(String line) {
		// Nese rreshti eshte /* */, metoda kthen false
		// Nese rreshti eshte /* */ /*, metoda kthen true
		int index = line.indexOf("/*");
		if (index < 0) {
			return false;
		}
		int indexIThonjezave = line.indexOf("\"");
		if (indexIThonjezave != -1 && indexIThonjezave < index) {
			while (indexIThonjezave > -1) {
				line = line.substring(indexIThonjezave + 1);
				int quoteEndIndex = line.indexOf("\"");
				line = line.substring(quoteEndIndex + 1);
				indexIThonjezave = line.indexOf("\"");
			}
			return komentiFilloj(line);
		}
		return !komentiPerfundoj(line.substring(index + 2));
	}
        
	private static boolean komentiPerfundoj(String line) {
		// Nese rreshti eshte */ /* , metoda kthen false
		// Nese rreshti eshte */ /* */ metoda kthen true
		int index = line.indexOf("*/");
		if (index < 0) {
			return false;
		} else {
			String subString = line.substring(index + 2).trim();
			if ("".equals(subString) || subString.startsWith("//")) {
				return true;
			}
			if(komentiFilloj(subString))
			{
				return false;
			}
			else
			{
				return true;
			}
		}
	}
        
	private static boolean eshteRreshtKod(String line) {
		boolean eshteRreshtKod = false;
		line = line.trim();
		if ("".equals(line) || line.startsWith("//")) {
			return eshteRreshtKod;
		}
		if (line.length() == 1) {
			return true;
		}
		int index = line.indexOf("/*");
		if (index != 0) {
			return true;
		} else {
			while (line.length() > 0) {
				line = line.substring(index + 2);
				int pozicioniPerfundimitKoment = line.indexOf("*/");
				if (pozicioniPerfundimitKoment < 0) {
					return false;
				}
				if (pozicioniPerfundimitKoment == line.length() - 2) {
					return false;
				} else {
					String subString = line.substring(pozicioniPerfundimitKoment + 2)
							.trim();
					if ("".equals(subString) || subString.indexOf("//") == 0) {
						return false;
					} else {
						if (subString.startsWith("/*")) {
							line = subString;
							continue;
						}
						return true;
					}
				}

			}
		}
		return eshteRreshtKod;
	}
}