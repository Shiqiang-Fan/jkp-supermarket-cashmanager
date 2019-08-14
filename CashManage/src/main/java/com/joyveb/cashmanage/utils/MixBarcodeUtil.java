package com.joyveb.cashmanage.utils;

import java.util.Random;

/**
 * 混淆二维码
 * @author gj
 *
 */
public class MixBarcodeUtil {

	/**
	 * 混淆数组
	 */
	private static char[] mixArr = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
			'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
			'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
			'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
			'y', 'z' };

	private static Random rand = new Random();

	// 混淆值
	private static int mixValue = 4;

	/**
	 * 打散混淆数组
	 */
	private static void scatterMixArr() {
		int arrLen = mixArr.length;

		for (int i = 0; i < arrLen; i++) {
			int idx = Math.abs(rand.nextInt()) % arrLen;

			char tmpData = mixArr[idx];
			mixArr[idx] = mixArr[arrLen - 1];
			mixArr[arrLen - 1] = tmpData;
		}
	}

	/**
	 * 解密二维码
	 * 
	 * @param srcStr
	 *            加密的二维码
	 * @return 解密后的数据
	 */
	public static String decodeBarcode(String srcStr) {
		char[] tmpArr = srcStr.toCharArray();

		// 减权值
		for (int i = 0; i < tmpArr.length; i++) {
			tmpArr[i] = (char) (tmpArr[i] - mixValue);
		}

		int arr_len = tmpArr.length;
		int half_len = tmpArr.length / 2;

		// 置换回来
		for (int i = 0; i < half_len; i++) {
			char tmpData = tmpArr[i];

			tmpArr[i] = tmpArr[arr_len - i - 1];
			tmpArr[arr_len - i - 1] = tmpData;
		}

		// 结果数组
		char[] destArr = new char[half_len];

		for (int i = 0; i < destArr.length; i++) {
			destArr[i] = tmpArr[i * 2];
		}

		return new String(destArr);
	}

	/**
	 * 获得经过混淆后的数组数据
	 * 
	 * @param srcStr
	 *            需要加密的字符串
	 * @return 加入混淆数组后的数据
	 */
	private static char[] getBarArrByMix(String srcStr) {
		char[] tmpArr = srcStr.toCharArray();
		char[] destArr = new char[tmpArr.length * 2];

		int len = tmpArr.length;
		int mixArr_len = mixArr.length;

		// 数据之间用混淆数据分隔
		for (int i = 0; i < len; i++) {
			destArr[i * 2] = tmpArr[i];
			destArr[i * 2 + 1] = i < mixArr_len ? mixArr[i]
					: mixArr[mixArr_len - 1];
		}

		return destArr;
	}

	/**
	 * 通过混淆数组加密
	 * 
	 * @param srcStr
	 *            需要加密的二维码
	 * @return 加密后的数据
	 */
	private static String encodeBarcodeByMixArr(String srcStr) {
		char[] tmpArr = getBarArrByMix(srcStr);

		int arr_len = tmpArr.length;
		int half_len = tmpArr.length / 2;

		// 置换
		for (int i = 0; i < half_len; i++) {
			char tmpData = tmpArr[i];
			
			tmpArr[i] = tmpArr[arr_len - i - 1];
			tmpArr[arr_len - i - 1] = tmpData;
		}

		// 加权值
		for (int i = 0; i < tmpArr.length; i++) {
			tmpArr[i] = (char) (tmpArr[i] + mixValue);
		}

		return new String(tmpArr);
	}

	/**
	 * 加密二维码
	 * 
	 * @param srcStr
	 *            需要加密的二维码
	 * @return 加密后的数据
	 */
	public static String encodeBarcode(String srcStr) {
		// 混淆数组打散
		scatterMixArr();

		return encodeBarcodeByMixArr(srcStr);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String barcode = "G0005070050000727140ECPHNHLNLECGJCGNWJ007";

		String encodeBarcode = encodeBarcode(barcode);

		System.out.println(encodeBarcode);

		String decodeBarcode = decodeBarcode(encodeBarcode);

		System.out.println(decodeBarcode);

		if (decodeBarcode.equals(barcode)) {
			System.out.println("正确");
		} else {
			System.out.println("错误");
		}
	}

}