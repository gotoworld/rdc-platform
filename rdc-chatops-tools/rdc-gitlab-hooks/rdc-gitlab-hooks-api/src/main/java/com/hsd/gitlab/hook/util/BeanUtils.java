package com.hsd.gitlab.hook.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Description
 *
 * @author uname.chen
 * @date 2017/11/29 0029
 */
public class BeanUtils {

    /**
     * @description 接收DTO Object对象，转换成Map类型
     * DTO里的属性名称是Map里的key
     * @param obj 需要转换的DTO对象
     * @return Map
     */
    @SuppressWarnings("unchecked")
    public static Map dtoToMap(Object obj){
        Map map = new HashMap();
        if(null != obj ){
            Class cla = obj.getClass();
            do{
                Field[] fields = cla.getDeclaredFields();
                for(Field field : fields ){
                    String name = field.getName();

                    if("serialVersionUID".equals(name)){
                        continue;
                    }

                    try {
                        Method method = cla.getMethod("get"+initCap(name), null);
                        map.put(underscoreName(name), method.invoke(obj, null));
                    } catch (Exception e) {
                        e.printStackTrace();
                        fields = null;
                    }
                }
                //获取父类属性
                cla = cla.getSuperclass();
            }while(cla != Object.class);
        }
        return map;
    }


    private static String initCap(String attr){
        return attr.substring(0, 1).toUpperCase()+attr.substring(1);
    }

    /**
     * 将驼峰式命名的字符串转换为下划线小写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。</br>
     * 例如：HelloWorld->hello_world
     * @param name 转换前的驼峰式命名的字符串
     * @return 转换后下划线小写方式命名的字符串
     */
    private static String underscoreName(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            // 将第一个字符处理成大写
            result.append(name.substring(0, 1).toLowerCase());
            // 循环处理其余字符
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("_");
                }
                // 其他字符直接转成小写
                result.append(s.toLowerCase());
            }
        }
        return result.toString();
    }
}
