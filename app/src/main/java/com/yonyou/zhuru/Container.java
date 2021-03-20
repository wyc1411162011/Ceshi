package com.yonyou.zhuru;


import java.lang.reflect.Field;

class Container {
    public static <T> T getBean(Class<T> clazz) {
        T obj = null;
        try {
            obj = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(Inject.class)) // 如果被@Inject注解了的话
                    continue;
                if (!field.isAccessible())
                    field.setAccessible(true);
                field.set(obj, field.getType().newInstance()); // 给它绑定一个对象
            }
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
}
 