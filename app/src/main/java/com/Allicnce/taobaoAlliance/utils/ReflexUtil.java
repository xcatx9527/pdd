package com.Allicnce.taobaoAlliance.utils;

import android.text.TextUtils;
import com.chenyang.lloglib.LLog;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * author: cy
 * date: 2019/2/28 17:49
 */
public class ReflexUtil {

    /**
     * 通过反射获取属性值
     *
     * @param classObject
     * @param name        (field.toString)
     * @return
     */
    public static Object getField(Object classObject, String name) {
        Object fieldObj = null;
        if (!TextUtils.isEmpty(name)) {
            try {
                Field[] declaredFields = classObject.getClass().getDeclaredFields();
                for (int i = 0; i < declaredFields.length; i++) {
                    if (name.equals(declaredFields[i].toString())) {
                        LLog.i(" find fit field , current name -->> " + name);
                        declaredFields[i].setAccessible(true);
                        fieldObj = declaredFields[i].get(classObject);
                        break;
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return fieldObj;
            }
        }
        return fieldObj;
    }

    /**
     * 通过反射获取属性值
     *
     * @param name        (field.toString)
     * @return
     */
    public static Object getField(Class classz, String name) {
        Object fieldObj = null;
        if (!TextUtils.isEmpty(name)) {
            try {
                Field[] declaredFields = classz.getDeclaredFields();
                for (int i = 0; i < declaredFields.length; i++) {
                    if (declaredFields[i].getName().equals(name)) {
                        LLog.i(" find fit field , current name -->> " + name);
                        declaredFields[i].setAccessible(true);
                        fieldObj = declaredFields[i].get(null);
                        break;
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return fieldObj;
            }
        }
        return fieldObj;
    }

    /**
     * 通过反射设置属性值
     *
     * @param classObject
     * @param name        (field.toString)
     * @return
     */
    public static void setField(Object classObject, String name, Object value) {
        if (!TextUtils.isEmpty(name)) {
            try {
                Field[] declaredFields = classObject.getClass().getDeclaredFields();
                for (int i = 0; i < declaredFields.length; i++) {
                    if (name.equals(declaredFields[i].toString())) {
                        LLog.i(" find fit field , current name -->> " + name);
                        declaredFields[i].setAccessible(true);
                        declaredFields[i].set(classObject, value);
                        break;
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过反射调用方法
     *
     * @param classObject
     * @param name        (field.toString)
     * @param args
     * @return
     */
    public static Object callMethod(Object classObject, String name, Object... args) {
        Object resultObj = null;
        if (!TextUtils.isEmpty(name)) {
            try {
                Method[] methods = classObject.getClass().getDeclaredMethods();
                for (int i = 0; i < methods.length; i++) {
                    if (name.equals(methods[i].toString())) {
                        LLog.i(" find fit method , current name -->> " + name);
                        methods[i].setAccessible(true);
                        resultObj = methods[i].invoke(classObject, args);
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return resultObj;
            }
        }
        return resultObj;
    }

    /**
     * 通过反射调用方法
     *
     * @param clazz
     * @param name  (field.toString)
     * @param args
     * @return
     */
    public static Object callMethod(Class<?> clazz, String name, Object... args) {
        Object resultObj = null;
        if (!TextUtils.isEmpty(name)) {
            try {
                Method[] methods = clazz.getDeclaredMethods();
                for (int i = 0; i < methods.length; i++) {
                    if (name.equals(methods[i].toString())) {
                        LLog.i(" find fit method , current name -->> " + name);
                        methods[i].setAccessible(true);
                        resultObj = methods[i].invoke(null, args);
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return resultObj;
            }
        }
        return resultObj;
    }

    /**
     * 通过反射打印调用方法
     *
     * @param clazz
     * @return
     */
    public static void printOutFitDeclaredMethod(Class<?> clazz) {
        try {
            Method[] methods = clazz.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                LLog.e("method---    " + methods[i].toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 通过反射打印属性
     *
     * @param clazz
     * @return
     */
    public static void printOutFitDeclaredField(Class<?> clazz) {

        try {
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                LLog.e("field---   " + fields[i].toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Field findFieldByClassAndTypeAndName(Class<?> targetObject, Class<?> fieldType, String fieldName) {
        Class clazz = targetObject;
        do {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getType() == fieldType && field.getName().equals(fieldName)) {
                    field.setAccessible(true);
                    return field;
                }
            }
            clazz = clazz.getSuperclass();
        } while (clazz != null);
        throw new NoSuchFieldError("Field of type " + fieldType.getName() + " in class " + targetObject.getName());
    }


}
