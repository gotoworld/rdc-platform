package com.hsd.devops.core.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import com.hsd.devops.core.support.StrKit;
//import com.smallchill.core.exception.ToolBoxException;

/**
 * 统一资源定位符相关工具类
 * 
 * @author xiaoleilu
 * 
 */
public class URLKit {
	
	/**
	 * 创建URL对象
	 * @param url URL
	 * @return URL对象
	 */
	public static URL url(String url) throws MalformedURLException {
			return new URL(url);
	}


	/**
	 * 获得URL
	 * 
	 * @param path 相对给定 class所在的路径
	 * @param clazz 指定class
	 * @return URL
	 */
	public static URL getURL(String path, Class<?> clazz) {
		return clazz.getResource(path);
	}

	/**
	 * 获得URL，常用于使用绝对路径时的情况
	 * 
	 * @param file URL对应的文件对象
	 * @return URL
	 * @exception  MalformedURLException
	 */
	public static URL getURL(File file) throws MalformedURLException {
			return file.toURI().toURL();

	}
	
	/**
	 * 获得URL，常用于使用绝对路径时的情况
	 * 
	 * @param files URL对应的文件对象
	 * @return URL
	 * @exception  MalformedURLException
	 */
	public static URL[] getURLs(File... files) throws MalformedURLException {
		final URL[] urls = new URL[files.length];
			for(int i = 0; i < files.length; i++){
				urls[i] = files[i].toURI().toURL();
			}

		
		return urls;
	}
	
	/**
	 * 格式化URL链接
	 * 
	 * @param url 需要格式化的URL
	 * @return 格式化后的URL，如果提供了null或者空串，返回null
	 */
	public static String formatUrl(String url) {
		if (StrKit.isBlank(url)){
			return null;
		}
		if (url.startsWith("http://") || url.startsWith("https://")){
			return url;
		}
		return "http://" + url;
	}

	/**
	 * 补全相对路径
	 * 
	 * @param baseUrl 基准URL
	 * @param relativePath 相对URL
	 * @return 相对路径
	 * @exception  MalformedURLException
	 */
	public static String complateUrl(String baseUrl, String relativePath) throws MalformedURLException {
		baseUrl = formatUrl(baseUrl);
		if (StrKit.isBlank(baseUrl)) {
			return null;
		}


			final URL absoluteUrl = new URL(baseUrl);
			final URL parseUrl = new URL(absoluteUrl, relativePath);
			return parseUrl.toString();

	}
	
	/**
	 * 编码URL
	 * @param url URL
	 * @param charset 编码
	 * @return 编码后的URL
//	 * @exception ToolBoxException UnsupportedEncodingException
	 */
	public static String encode(String url, String charset) throws UnsupportedEncodingException {
		if (StrKit.isBlank(url))
			return StrKit.EMPTY;

			return URLEncoder.encode(url, charset);

	}
	
	/**
	 * 解码URL
	 * @param url URL
	 * @param charset 编码
	 * @return 解码后的URL
//	 * @exception ToolBoxException UnsupportedEncodingException
	 */
	public static String decode(String url, String charset) throws UnsupportedEncodingException {
		if (StrKit.isBlank(url))
			return StrKit.EMPTY;

			return URLDecoder.decode(url, charset);

	}
	
	/**
	 * 获得path部分<br>
	 * URI -> http://www.aaa.bbb/search?scope=ccc&q=ddd
	 * PATH -> /search
	 * 
	 * @param uriStr URI路径
	 * @return path
//	 * @exception ToolBoxException URISyntaxException
	 */
	public static String getPath(String uriStr) throws URISyntaxException {
		URI uri = null;

			uri = new URI(uriStr);

		
		return uri == null ? null : uri.getPath();
	}
}
