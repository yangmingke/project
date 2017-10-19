package com.flypaas.utils;

public class XSSUtils {
	public static String xssEncode(String s) {
		if (s == null || "".equals(s)) {
			return s;
		}
		StringBuilder sb = new StringBuilder(s.length() + 16);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '>':
				sb.append('》');// 全角大于号
				break;
			case '<':
				sb.append('《');// 全角小于号
				break;
			case '〉':
				sb.append('》');// 全角大于号
				break;
			case '〈':
				sb.append('《');// 全角小于号
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(xssEncode("<script"));
	}
}
