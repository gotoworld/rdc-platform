package com.hsd.devops.core.support;


import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.hsd.devops.core.support.exception.ToolBoxException;


public class ClassKit {
	
	private ClassKit() {
		// 静态类不可实例化
	}
	
	
	public static boolean isNormalClass(Class<?> clazz) {
		return null != clazz && false == clazz.isInterface() && false == isAbstract(clazz) && false == clazz.isEnum() && false == clazz.isArray() && false == clazz.isAnnotation() && false == clazz
				.isSynthetic() && false == clazz.isPrimitive();
	}
	
	
	public static boolean isAbstract(Class<?> clazz) {
		return Modifier.isAbstract(clazz.getModifiers());
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(String clazz) {
		if (null == clazz)
			return null;
		try {
			return (T) Class.forName(clazz).newInstance();
		} catch (Exception e) {
			throw new ToolBoxException(StrKit.format("Instance class [{}] error!", clazz), e);
		}
	}

	
	public static <T> T newInstance(Class<T> clazz) {
		if (null == clazz)
			return null;
		try {
			return (T) clazz.newInstance();
		} catch (Exception e) {
			throw new ToolBoxException(StrKit.format("Instance class [{}] error!", clazz), e);
		}
	}

	
	public static <T> T newInstance(Class<T> clazz, Object... params) {
		if (null == clazz)
			return null;
		if (CollectionKit.isEmpty(params)) {
			return newInstance(clazz);
		}
		try {
			return clazz.getDeclaredConstructor(getClasses(params)).newInstance(params);
		} catch (Exception e) {
			throw new ToolBoxException(StrKit.format("Instance class [{}] error!", clazz), e);
		}
	}
	
	
	public static Class<?>[] getClasses(Object... objects){
		Class<?>[] classes = new Class<?>[objects.length];
		for (int i = 0; i < objects.length; i++) {
			classes[i] = objects[i].getClass();
		}
		return classes;
	}
	
	
	public static boolean isAssignable(Class<?> targetType, Class<?> sourceType) {
		if (null == targetType || null == sourceType) {
			return false;
		}

		// 对象类型
		if (targetType.isAssignableFrom(sourceType)) {
			return true;
		}

		// 基本类型
		if (targetType.isPrimitive()) {
			// 原始类型
			Class<?> resolvedPrimitive = BasicType.wrapperPrimitiveMap.get(sourceType);
			if (resolvedPrimitive != null && targetType.equals(resolvedPrimitive)) {
				return true;
			}
		} else {
			// 包装类型
			Class<?> resolvedWrapper = BasicType.primitiveWrapperMap.get(sourceType);
			if (resolvedWrapper != null && targetType.isAssignableFrom(resolvedWrapper)) {
				return true;
			}
		}
		return false;
	}
	
	
	public static Method setAccessible(Method method) {
		if (null != method && ClassKit.isNotPublic(method)) {
			method.setAccessible(true);
		}
		return method;
	}
	
	
	public static boolean isNotPublic(Class<?> clazz) {
		return false == isPublic(clazz);
	}

	
	public static boolean isNotPublic(Method method) {
		return false == isPublic(method);
	}
	
	
	public static boolean isPublic(Class<?> clazz) {
		if (null == clazz) {
			throw new NullPointerException("Class to provided is null.");
		}
		return Modifier.isPublic(clazz.getModifiers());
	}
	
	
	public static boolean isPublic(Method method) {
		if (null == method) {
			throw new NullPointerException("Method to provided is null.");
		}
		return isPublic(method.getDeclaringClass());
	}
}