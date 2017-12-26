package com.hsd.devops.core.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Set;

import com.hsd.devops.core.support.*;
import com.hsd.devops.core.support.exception.ToolBoxException;


public class Convert {
	

	private Convert() {
		// 静态类不可实例化
	}


	public static Object parse(Class<?> clazz, Object value) {
		try {
			if (clazz.isAssignableFrom(String.class)) {
				// ----2016-12-19---zhuangqian----防止beetlsql对空字符串不检测导致无法入库的问题----
				if (StrKit.isBlank(String.valueOf(value)))
					return " ";
				else
					return String.valueOf(value);
			}
			return clazz.cast(value);
		} catch (ClassCastException e) {
			String valueStr = String.valueOf(value);

			Object result = parseBasic(clazz, valueStr);
			if (result != null) {
				return result;
			}

			if (Date.class.isAssignableFrom(clazz)) {
				// 判断标准日期
				// ----2016-11-24---zhuangqian----需要加toDate(),不然beetlsql转换date类型的时候会报错----
				return DateTimeKit.parse(valueStr).toDate();
			} else if (clazz == BigInteger.class) {
				// 数学计算数字
				return new BigInteger(valueStr);
			} else if (clazz == BigDecimal.class) {
				// 数学计算数字
				return new BigDecimal(valueStr);
			} else if (clazz == byte[].class) {
				// 流，由于有字符编码问题，在此使用系统默认
				return valueStr.getBytes();
			}
			// 未找到可转换的类型，返回原值
			return (StrKit.isBlank(valueStr)) ? null : value;
		}
	}


	public static Object parseBasic(Class<?> clazz, String valueStr) {
		if (null == clazz || null == valueStr) {
			return null;
		}

		if (StrKit.isBlank(valueStr)) return null;

		BasicType basicType = null;
		try {
			basicType = BasicType.valueOf(clazz.getSimpleName().toUpperCase());
		} catch (Exception e) {
			// 非基本类型数据
			return null;
		}

		switch (basicType) {
			case BYTE:
				if (clazz == byte.class) {
					return Byte.parseByte(valueStr);
				}
				return Byte.valueOf(valueStr);
			case SHORT:
				if (clazz == short.class) {
					return Short.parseShort(valueStr);
				}
				return Short.valueOf(valueStr);
			case INT:
				return Integer.parseInt(valueStr);
			case INTEGER:
				return Integer.valueOf(valueStr);
			case LONG:
				if (clazz == long.class) {
					return new BigDecimal(valueStr).longValue();
				}
				return Long.valueOf(valueStr);
			case DOUBLE:
				if (clazz == double.class) {
					return new BigDecimal(valueStr).doubleValue();
				}
			case FLOAT:
				if (clazz == float.class) {
					return Float.parseFloat(valueStr);
				}
				return Float.valueOf(valueStr);
			case BOOLEAN:
				if (clazz == boolean.class) {
					return Boolean.parseBoolean(valueStr);
				}
				return Boolean.valueOf(valueStr);
			case CHAR:
				return valueStr.charAt(0);
			case CHARACTER:
				return Character.valueOf(valueStr.charAt(0));
			default:
				return null;
		}
	}


	public static String toStr(Object value, String defaultValue) {
		if (null == value) {
			return defaultValue;
		}
		if (value instanceof String) {
			return (String) value;
		} else if (CollectionKit.isArray(value)) {
			return CollectionKit.toString(value);
		}
		return value.toString();
	}


	public static String toStr(Object value) {
		return toStr(value, null);
	}


	public static Character toChar(Object value, Character defaultValue) {
		if (null == value) {
			return defaultValue;
		}
		if (value instanceof Character) {
			return (Character) value;
		}

		final String valueStr = toStr(value, null);
		return StrKit.isEmpty(valueStr) ? defaultValue : valueStr.charAt(0);
	}


	public static Character toChar(Object value) {
		return toChar(value, null);
	}


	public static Byte toByte(Object value, Byte defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		if (value instanceof Byte) {
			return (Byte) value;
		}
		if (value instanceof Number) {
			return ((Number) value).byteValue();
		}
		final String valueStr = toStr(value, null);
		if (StrKit.isBlank(valueStr)) {
			return defaultValue;
		}
		try {
			return Byte.parseByte(valueStr);
		} catch (Exception e) {
			return defaultValue;
		}
	}


	public static Byte toByte(Object value) {
		return toByte(value, null);
	}


	public static Short toShort(Object value, Short defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		if (value instanceof Short) {
			return (Short) value;
		}
		if (value instanceof Number) {
			return ((Number) value).shortValue();
		}
		final String valueStr = toStr(value, null);
		if (StrKit.isBlank(valueStr)) {
			return defaultValue;
		}
		try {
			return Short.parseShort(valueStr.trim());
		} catch (Exception e) {
			return defaultValue;
		}
	}


	public static Short toShort(Object value) {
		return toShort(value, null);
	}


	public static Number toNumber(Object value, Number defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		if (value instanceof Number) {
			return (Number) value;
		}
		final String valueStr = toStr(value, null);
		if (StrKit.isBlank(valueStr)) {
			return defaultValue;
		}
		try {
			return NumberFormat.getInstance().parse(valueStr);
		} catch (Exception e) {
			return defaultValue;
		}
	}


	public static Number toNumber(Object value) {
		return toNumber(value, null);
	}


	public static Integer toInt(Object value, Integer defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		if (value instanceof Integer) {
			return (Integer) value;
		}
		if (value instanceof Number) {
			return ((Number) value).intValue();
		}
		final String valueStr = toStr(value, null);
		if (StrKit.isBlank(valueStr)) {
			return defaultValue;
		}
		try {
			return Integer.parseInt(valueStr.trim());
		} catch (Exception e) {
			return defaultValue;
		}
	}


	public static Integer toInt(Object value) {
		return toInt(value, null);
	}


	public static Integer[] toIntArray(boolean isIgnoreConvertError, Object... values) {
		if (CollectionKit.isEmpty(values)) {
			return new Integer[] {};
		}
		final Integer[] ints = new Integer[values.length];
		for (int i = 0; i < values.length; i++) {
			final Integer v = toInt(values[i], null);
			if (null == v && isIgnoreConvertError == false) {
				throw new ToolBoxException(StrKit.format("Convert [{}] to Integer error!", values[i]));
			}
			ints[i] = v;
		}
		return ints;
	}



	public static Integer[] toIntArray(String str) {
		return toIntArray(",", str);
	}


	public static Integer[] toIntArray(String split, String str) {
		if (StrKit.isEmpty(str)) {
			return new Integer[] {};
		}
		String[] arr = str.split(split);
		final Integer[] ints = new Integer[arr.length];
		for (int i = 0; i < arr.length; i++) {
			final Integer v = toInt(arr[i], 0);
			ints[i] = v;
		}
		return ints;
	}


	public static String[] toStrArray(String str) {
		return toStrArray("", str);
	}


	public static String[] toStrArray(String split, String str) {
		return str.split(split);
	}


	public static Long toLong(Object value, Long defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		if (value instanceof Long) {
			return (Long) value;
		}
		if (value instanceof Number) {
			return ((Number) value).longValue();
		}
		final String valueStr = toStr(value, null);
		if (StrKit.isBlank(valueStr)) {
			return defaultValue;
		}
		try {
			// 支持科学计数法
			return new BigDecimal(valueStr.trim()).longValue();
		} catch (Exception e) {
			return defaultValue;
		}
	}


	public static Long toLong(Object value) {
		return toLong(value, null);
	}


	public static Long[] toLongArray(boolean isIgnoreConvertError, Object... values) {
		if (CollectionKit.isEmpty(values)) {
			return new Long[] {};
		}
		final Long[] longs = new Long[values.length];
		for (int i = 0; i < values.length; i++) {
			final Long v = toLong(values[i], null);
			if (null == v && isIgnoreConvertError == false) {
				throw new ToolBoxException(StrKit.format("Convert [{}] to Long error!", values[i]));
			}
			longs[i] = v;
		}
		return longs;
	}


	public static Double toDouble(Object value, Double defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		if (value instanceof Double) {
			return (Double) value;
		}
		if (value instanceof Number) {
			return ((Number) value).doubleValue();
		}
		final String valueStr = toStr(value, null);
		if (StrKit.isBlank(valueStr)) {
			return defaultValue;
		}
		try {
			// 支持科学计数法
			return new BigDecimal(valueStr.trim()).doubleValue();
		} catch (Exception e) {
			return defaultValue;
		}
	}


	public static Double toDouble(Object value) {
		return toDouble(value, null);
	}


	public static Double[] toDoubleArray(boolean isIgnoreConvertError, Object... values) {
		if (CollectionKit.isEmpty(values)) {
			return new Double[] {};
		}
		final Double[] doubles = new Double[values.length];
		for (int i = 0; i < values.length; i++) {
			final Double v = toDouble(values[i], null);
			if (null == v && isIgnoreConvertError == false) {
				throw new ToolBoxException(StrKit.format("Convert [{}] to Double error!", values[i]));
			}
			doubles[i] = v;
		}
		return doubles;
	}


	public static Float toFloat(Object value, Float defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		if (value instanceof Float) {
			return (Float) value;
		}
		if (value instanceof Number) {
			return ((Number) value).floatValue();
		}
		final String valueStr = toStr(value, null);
		if (StrKit.isBlank(valueStr)) {
			return defaultValue;
		}
		try {
			return Float.parseFloat(valueStr.trim());
		} catch (Exception e) {
			return defaultValue;
		}
	}


	public static Float toFloat(Object value) {
		return toFloat(value, null);
	}


	public static <T> Float[] toFloatArray(boolean isIgnoreConvertError, Object... values) {
		if (CollectionKit.isEmpty(values)) {
			return new Float[] {};
		}
		final Float[] floats = new Float[values.length];
		for (int i = 0; i < values.length; i++) {
			final Float v = toFloat(values[i], null);
			if (null == v && isIgnoreConvertError == false) {
				throw new ToolBoxException(StrKit.format("Convert [{}] to Float error!", values[i]));
			}
			floats[i] = v;
		}
		return floats;
	}


	public static Boolean toBool(Object value, Boolean defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		if (value instanceof Boolean) {
			return (Boolean) value;
		}
		String valueStr = toStr(value, null);
		if (StrKit.isBlank(valueStr)) {
			return defaultValue;
		}
		valueStr = valueStr.trim().toLowerCase();
		switch (valueStr) {
			case "true":
				return true;
			case "false":
				return false;
			case "yes":
				return true;
			case "ok":
				return true;
			case "no":
				return false;
			case "1":
				return true;
			case "0":
				return false;
			default:
				return defaultValue;
		}
	}


	public static Boolean toBool(Object value) {
		return toBool(value, null);
	}


	public static Boolean[] toBooleanArray(boolean isIgnoreConvertError, Object... values) {
		if (CollectionKit.isEmpty(values)) {
			return new Boolean[] {};
		}
		final Boolean[] bools = new Boolean[values.length];
		for (int i = 0; i < values.length; i++) {
			final Boolean v = toBool(values[i], null);
			if (null == v && isIgnoreConvertError == false) {
				throw new ToolBoxException(StrKit.format("Convert [{}] to Boolean error!", values[i]));
			}
			bools[i] = v;
		}
		return bools;
	}


	public static <E extends Enum<E>> E toEnum(Class<E> clazz, Object value, E defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		if (clazz.isAssignableFrom(value.getClass())) {
			@SuppressWarnings("unchecked")
			E myE = (E) value;
			return myE;
		}
		final String valueStr = toStr(value, null);
		if (StrKit.isBlank(valueStr)) {
			return defaultValue;
		}
		try {
			return Enum.valueOf(clazz, valueStr);
		} catch (Exception e) {
			return defaultValue;
		}
	}


	public static <E extends Enum<E>> E toEnum(Class<E> clazz, Object value) {
		return toEnum(clazz, value, null);
	}


	public static BigInteger toBigInteger(Object value, BigInteger defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		if (value instanceof BigInteger) {
			return (BigInteger) value;
		}
		if (value instanceof Long) {
			return BigInteger.valueOf((Long) value);
		}
		final String valueStr = toStr(value, null);
		if (StrKit.isBlank(valueStr)) {
			return defaultValue;
		}
		try {
			return new BigInteger(valueStr);
		} catch (Exception e) {
			return defaultValue;
		}
	}


	public static BigInteger toBigInteger(Object value) {
		return toBigInteger(value, null);
	}


	public static BigDecimal toBigDecimal(Object value, BigDecimal defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		if (value instanceof BigDecimal) {
			return (BigDecimal) value;
		}
		if (value instanceof Long) {
			return new BigDecimal((Long) value);
		}
		if (value instanceof Double) {
			return new BigDecimal((Double) value);
		}
		if (value instanceof Integer) {
			return new BigDecimal((Integer) value);
		}
		final String valueStr = toStr(value, null);
		if (StrKit.isBlank(valueStr)) {
			return defaultValue;
		}
		try {
			return new BigDecimal(valueStr);
		} catch (Exception e) {
			return defaultValue;
		}
	}


	public static BigDecimal toBigDecimal(Object value) {
		return toBigDecimal(value, null);
	}

	// ----------------------------------------------------------------------- 全角半角转换

	public static String toSBC(String input) {
		return toSBC(input, null);
	}


	public static String toSBC(String input, Set<Character> notConvertSet) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (null != notConvertSet && notConvertSet.contains(c[i])) {
				// 跳过不替换的字符
				continue;
			}

			if (c[i] == ' ') {
				c[i] = '\u3000';
			} else if (c[i] < '\177') {
				c[i] = (char) (c[i] + 65248);

			}
		}
		return new String(c);
	}


	public static String toDBC(String input) {
		return toDBC(input, null);
	}


	public static String toDBC(String text, Set<Character> notConvertSet) {
		char c[] = text.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (null != notConvertSet && notConvertSet.contains(c[i])) {
				// 跳过不替换的字符
				continue;
			}

			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);
			}
		}
		String returnString = new String(c);

		return returnString;
	}

	// --------------------------------------------------------------------- hex

	public static String toHex(String str) {
		return HexKit.encodeHexStr(str.getBytes());
	}


	public static String toHex(byte[] bytes) {
		return HexKit.encodeHexStr(bytes);
	}


	public static byte[] hexToBytes(String src) {
		return HexKit.decodeHex(src.toCharArray());
	}


	public static String hexStrToStr(String hexStr, Charset charset) {
		return HexKit.decodeHexStr(hexStr, charset);
	}


	public static String strToUnicode(String strText) throws Exception {
		char c;
		StringBuilder str = new StringBuilder();
		int intAsc;
		String strHex;
		for (int i = 0; i < strText.length(); i++) {
			c = strText.charAt(i);
			intAsc = (int) c;
			strHex = Integer.toHexString(intAsc);
			if (intAsc > 128)
				str.append("\\u" + strHex);
			else // 低位在前面补00
				str.append("\\u00" + strHex);
		}
		return str.toString();
	}


	public static String unicodeToStr(String hex) {
		int t = hex.length() / 6;
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < t; i++) {
			String s = hex.substring(i * 6, (i + 1) * 6);
			// 高位需要补上00再转
			String s1 = s.substring(2, 4) + "00";
			// 低位直接转
			String s2 = s.substring(4);
			// 将16进制的string转为int
			int n = Integer.valueOf(s1, 16) + Integer.valueOf(s2, 16);
			// 将int转换为字符
			char[] chars = Character.toChars(n);
			str.append(new String(chars));
		}
		return str.toString();
	}


	public static String convertCharset(String str, String sourceCharset, String destCharset) {
		if (StrKit.hasBlank(str, sourceCharset, destCharset)) {
			return str;
		}

		try {
			return new String(str.getBytes(sourceCharset), destCharset);
		} catch (UnsupportedEncodingException e) {
			return str;
		}
	}


	public static String digitUppercase(double n) {
		String fraction[] = { "角", "分" };
		String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		String unit[][] = { { "元", "万", "亿" }, { "", "拾", "佰", "仟" } };

		String head = n < 0 ? "负" : "";
		n = Math.abs(n);

		String s = "";
		for (int i = 0; i < fraction.length; i++) {
			s += (digit[(int) (Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");
		}
		if (s.length() < 1) {
			s = "整";
		}
		int integerPart = (int) Math.floor(n);

		for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
			String p = "";
			for (int j = 0; j < unit[1].length && n > 0; j++) {
				p = digit[integerPart % 10] + unit[1][j] + p;
				integerPart = integerPart / 10;
			}
			s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
		}
		return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
	}
	
}
