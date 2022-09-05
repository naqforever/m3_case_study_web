package m3.furama.util;

import m3.furama.repository.Config;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CommonUtil {
    public static int toInt(String val) {
        try {
            return Integer.parseInt(val);
        } catch (Exception e) {
            return 0;
        }
    }

    public static Double toDouble(String val) {
        try {
            return Double.parseDouble(val);
        } catch (Exception e) {
            return 0.0;
        }
    }

    public static Class getClazz(String entityName) {
        String path = "m3.furama.model." + entityName.substring(0, 1).toUpperCase() + entityName.substring(1);
        try {
            return Class.forName(path);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Field> getAllFields(Class clazz) {
        if (clazz == null) {
            return Collections.emptyList();
        }

        List<Field> result = Arrays.stream(clazz.getDeclaredFields())
                .collect(Collectors.toList());

        return result;
    }

    public static Object getValueByField(Object o, String field) {
        Object result = null;

        try {
            Class<?> clazz = o.getClass();
            Field f = clazz.getDeclaredField(field);
            f.setAccessible(true);
            result = f.get(o);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String convertToSnakeCase(String val) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return val.replaceAll(regex, replacement).toLowerCase();
    }

    public static String convertToCamelCase(String str) {
        str = str.substring(0, 1).toUpperCase() + str.substring(1);
        StringBuilder builder = new StringBuilder(str);

        for (int i = 0; i < builder.length(); i++) {
            if (builder.charAt(i) == '_') {
                builder.deleteCharAt(i);
                builder.replace(i, i + 1, String.valueOf(Character.toUpperCase(builder.charAt(i))));
            }
        }

        return builder.toString();
    }
}
