package com.hsd.devops.core.support;

import java.beans.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import com.hsd.devops.core.support.exception.ToolBoxException;


public class BeanKit {
	
	
	public static boolean isBean(Class<?> clazz){
		if(ClassKit.isNormalClass(clazz)){
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				if(method.getParameterTypes().length == 1 && method.getName().startsWith("set")){
					//检测包含标准的setXXX方法即视为标准的JavaBean
					return true;
				}
			}
		}
		return false;
	}

	public static PropertyEditor findEditor(Class<?> type){
		return PropertyEditorManager.findEditor(type);
	}

	
	public static PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz) throws IntrospectionException {
		return Introspector.getBeanInfo(clazz).getPropertyDescriptors();
	}

	
	public static Map<String, PropertyDescriptor> getFieldNamePropertyDescriptorMap(Class<?> clazz) throws IntrospectionException{
		final PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(clazz);
		Map<String, PropertyDescriptor> map = new HashMap<>();
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			map.put(propertyDescriptor.getName(), propertyDescriptor);
		}
		return map;
	}

	
	public static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, final String fieldName) throws IntrospectionException {
		PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(clazz);
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			if (ObjectKit.equals(fieldName, propertyDescriptor.getName())) {
				return propertyDescriptor;
			}
		}
		return null;
	}

	
	public static <T> T mapToBean(Map<?, ?> map, Class<T> beanClass) {
		return fillBeanWithMap(map, ClassKit.newInstance(beanClass));
	}

	
	public static <T> T mapToBeanIgnoreCase(Map<?, ?> map, Class<T> beanClass) {
		return fillBeanWithMapIgnoreCase(map, ClassKit.newInstance(beanClass));
	}

	
	public static <T> T fillBeanWithMap(final Map<?, ?> map, T bean) {
		return fillBean(bean, new ValueProvider(){
			@Override
			public Object value(String name) {
				return map.get(name);
			}
		});
	}

	
	public static <T> T fillBeanWithMap(Map<?, ?> map, T bean, boolean isToCamelCase) {
		if(isToCamelCase){
			final Map<Object, Object> map2 = new HashMap<Object, Object>();
			for (Entry<?, ?> entry : map.entrySet()) {
				final Object key = entry.getKey();
				if (null != key && key instanceof String) {
					final String keyStr = (String) key;
					map2.put(StrKit.toCamelCase(keyStr), entry.getValue());
				} else {
					map2.put(key, entry.getValue());
				}
			}
			return fillBeanWithMap(map2, bean);
		}

		return fillBeanWithMap(map, bean);
	}

	
	public static <T> T fillBeanWithMapIgnoreCase(Map<?, ?> map, T bean) {
		final Map<Object, Object> map2 = new HashMap<Object, Object>();
		for (Entry<?, ?> entry : map.entrySet()) {
			final Object key = entry.getKey();
			if (key instanceof String) {
				final String keyStr = (String) key;
				map2.put(keyStr.toLowerCase(), entry.getValue());
			} else {
				map2.put(key, entry.getValue());
			}
		}

		return fillBean(bean, new ValueProvider(){
			@Override
			public Object value(String name) {
				return map2.get(name.toLowerCase());
			}
		});
	}

	
	public static <T> T requestParamToBean(javax.servlet.ServletRequest request, Class<T> beanClass) {
		return fillBeanWithRequestParam(request, ClassKit.newInstance(beanClass));
	}

	
	public static <T> T fillBeanWithRequestParam(final javax.servlet.ServletRequest request, T bean) {
		final String beanName = StrKit.lowerFirst(bean.getClass().getSimpleName());
		return fillBean(bean, new ValueProvider(){
			@Override
			public Object value(String name) {
				String value = request.getParameter(name);
				if (StrKit.isEmpty(value)) {
					// 使用类名前缀尝试查找值
					value = request.getParameter(beanName + StrKit.DOT + name);
					if (StrKit.isEmpty(value)) {
						// 此处取得的值为空时跳过，包括null和""
						value = null;
					}
				}
				return value;
			}
		});
	}

	
	public static <T> T toBean(Class<T> beanClass, ValueProvider valueProvider) {
		return fillBean(ClassKit.newInstance(beanClass), valueProvider);
	}

	
	public static <T> T fillBean(T bean, ValueProvider valueProvider) {
		if (null == valueProvider) {
			return bean;
		}

		Class<?> beanClass = bean.getClass();
		try {
			PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(beanClass);
			String propertyName;
			Object value;
			for (PropertyDescriptor property : propertyDescriptors) {
				propertyName = property.getName();
				value = valueProvider.value(propertyName);
				if (null == value) {
					continue;
				}
				try {
//					property.getWriteMethod().invoke(bean, Convert.parse(property.getPropertyType(), value));
					// TODO sign by ford
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			throw new ToolBoxException(e);
		}
		return bean;
	}

	
	public static <T> Map<String, Object> beanToMap(T bean) {
		return beanToMap(bean, false);
	}

	
	public static <T> Map<String, Object> beanToMap(T bean, boolean isToUnderlineCase) {

		if (bean == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			final PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(bean.getClass());
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				// 过滤class属性
				if (false == key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(bean);
					if (null != value) {
						map.put(isToUnderlineCase ? StrKit.toUnderlineCase(key) : key, value);
					}
				}
			}
		} catch (Exception e) {
			throw new ToolBoxException(e);
		}
		return map;
	}

	
	public static void copyProperties(Object source, Object target) {
		copyProperties(source, target, CopyOptions.create());
	}

	
	public static void copyProperties(Object source, Object target, String... ignoreProperties) {
		copyProperties(source, target, CopyOptions.create().setIgnoreProperties(ignoreProperties));
	}

	
	public static void copyProperties(Object source, Object target, CopyOptions copyOptions) {
		if(null == copyOptions){
			copyOptions = new CopyOptions();
		}

		Class<?> actualEditable = target.getClass();
		if (copyOptions.editable != null) {
			//检查限制类是否为target的父类或接口
			if (!copyOptions.editable.isInstance(target)) {
				throw new IllegalArgumentException(StrKit.format("Target class [{}] not assignable to Editable class [{}]", target.getClass().getName(), copyOptions.editable.getName()));
			}
			actualEditable = copyOptions.editable;
		}
		PropertyDescriptor[] targetPds = null;
		Map<String, PropertyDescriptor> sourcePdMap;
		try {
			sourcePdMap = getFieldNamePropertyDescriptorMap(source.getClass());
			targetPds = getPropertyDescriptors(actualEditable);
		} catch (IntrospectionException e) {
			throw new ToolBoxException(e);
		}

		HashSet<String> ignoreSet = copyOptions.ignoreProperties != null ? CollectionKit.newHashSet(copyOptions.ignoreProperties) : null;
		for (PropertyDescriptor targetPd : targetPds) {
			Method writeMethod = targetPd.getWriteMethod();
			if (writeMethod != null && (ignoreSet == null || false == ignoreSet.contains(targetPd.getName()))) {
				PropertyDescriptor sourcePd = sourcePdMap.get(targetPd.getName());
				if (sourcePd != null) {
					Method readMethod = sourcePd.getReadMethod();
					// 源对象字段的getter方法返回值必须可转换为目标对象setter方法的第一个参数
					if (readMethod != null && ClassKit.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
						try {
							Object value = ClassKit.setAccessible(readMethod).invoke(source);
							if(null != value || false == copyOptions.isIgnoreNullValue){
								ClassKit.setAccessible(writeMethod).invoke(target, value);
							}
						} catch (Throwable ex) {
							throw new ToolBoxException(ex, "Copy property [{}] to [{}] error: {}", sourcePd.getName(), targetPd.getName(), ex.getMessage());
						}
					}
				}
			}
		}
	}

	
	public static interface ValueProvider {
		
		public Object value(String name);
	}
	
	
	public static class CopyOptions {
		
		private Class<?> editable;
		
		private boolean isIgnoreNullValue;
		
		private String[] ignoreProperties;
		
		
		public static CopyOptions create(){
			return new CopyOptions();
		}
		
		
		public static CopyOptions create(Class<?> editable, boolean isIgnoreNullValue, String... ignoreProperties){
			return new CopyOptions(editable, isIgnoreNullValue, ignoreProperties);
		}
		
		
		public CopyOptions() {
		}
		
		
		public CopyOptions(Class<?> editable, boolean isIgnoreNullValue, String... ignoreProperties) {
			this.editable = editable;
			this.isIgnoreNullValue = isIgnoreNullValue;
			this.ignoreProperties = ignoreProperties;
		}

		
		public CopyOptions setEditable(Class<?> editable){
			this.editable = editable;
			return this;
		}
		
		
		public CopyOptions setIgnoreNullValue(boolean isIgnoreNullVall){
			this.isIgnoreNullValue = isIgnoreNullVall;
			return this;
		}
		
		
		public CopyOptions setIgnoreProperties(String... ignoreProperties){
			this.ignoreProperties = ignoreProperties;
			return this;
		}
	}
}
