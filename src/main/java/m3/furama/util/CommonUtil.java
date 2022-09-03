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

    public static List findAll(String query, Class clazz) {
        List result = new ArrayList<>();

        try (Connection connection = Config.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Constructor<?> ctor = clazz.getConstructors()[0];
                List<Field> fields = getAllFields(clazz);
                Object[] tmp = new Object[fields.size()];
                for (int i = 0; i < fields.size(); i++) {
                    Class<?> fieldType = fields.get(i).getType();
                    switch (fieldType.getSimpleName()) {
                        case "int":
                            tmp[i] = rs.getInt(i + 1);
                            break;
                        case "String":
                            tmp[i] = rs.getString(i + 1);
                            break;
                        case "LocalDate":
                            Date date = rs.getDate(i + 1);
                            tmp[i] = new java.sql.Date(date.getTime()).toLocalDate();
                            break;
                        case "Double":
                            tmp[i] = rs.getDouble(i + 1);
                            break;
                    }
                }

                result.add(ctor.newInstance(tmp));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static List<Field> getAllFields(Class clazz) {
        if (clazz == null) {
            return Collections.emptyList();
        }

        List<Field> result = Arrays.stream(clazz.getDeclaredFields())
                .collect(Collectors.toList());

        return result;
    }
}
