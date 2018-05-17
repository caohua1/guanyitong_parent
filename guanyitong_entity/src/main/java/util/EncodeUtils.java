package util;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class EncodeUtils extends StringEscapeUtils {
	
	private static final String DEFAULT_URL_ENCODING = "UTF-8";

	/**
	 * base64 encode
	 *
	 * @param input
	 * @return
     */
	public static String base64Encode(byte[] input) {
		return new String(Base64.encodeBase64(input));
	}

	/**
	 * base64 decode
	 *
	 * @param input
	 * @return
     */
	public static byte[] base64Decode(String input) {
		return Base64.decodeBase64(input);
	}
	
	/**
	 * url encode
	 */
	public static String urlEncode(String input) {
		return urlEncode(input, DEFAULT_URL_ENCODING);
	}
	
	/**
	 * url encode
	 */
	public static String urlEncode(String input, String encoding) {
		try {
			return URLEncoder.encode(input, encoding);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Unsupported Encoding Exception", e);
		}
	}
	
	/**
	 * url decode
	 */
	public static String urlDecode(String input) {
		return urlDecode(input, DEFAULT_URL_ENCODING);
	}

	/**
	 * url decode
	 *
	 * @param input
	 * @param encoding
     * @return
     */
	public static String urlDecode(String input, String encoding) {
		try {
			return URLDecoder.decode(input, encoding);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Unsupported Encoding Exception", e);
		}
	}


}
