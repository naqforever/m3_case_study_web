package m3.furama.util;

import m3.furama.util.annotation.IsAssociate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class Query {
    public static String entityName;

    public static String findAll() {
        String tmp = "";

        try {
            List<Field> fields = CommonUtil.getAllFields(CommonUtil.getClazz(entityName));
            for (Field f : fields) {
                Annotation annotation = f.getAnnotation(IsAssociate.class);
                if (annotation instanceof IsAssociate) {
                    IsAssociate isAssociate = (IsAssociate) annotation;
                    String a = entityName + "." + CommonUtil.convertToSnakeCase(f.getName());
                    String b = isAssociate.table() + ".id";
                    tmp += String.format(" join %s on %s = %s", isAssociate.table(), a, b);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return String.format("select * from %s %s order by %s.id desc", entityName, tmp, entityName);
    }

    public static String findAllWithPaging() {
        return findAll() + " limit ?lim offset ?off";
    }

    public static String insert() {
        try {
            List<Field> fields = CommonUtil.getAllFields(CommonUtil.getClazz(entityName));
            String key = fields.stream().map(e -> e.getName()).skip(1).collect(Collectors.joining(","));
            String value = fields.stream().map(e -> "?").skip(1).collect(Collectors.joining(","));
            return String.format("insert into %s (%s) values (%s)", entityName, key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String update() {
        try {
            List<Field> fields = CommonUtil.getAllFields(CommonUtil.getClazz(entityName));
            String tmp = fields.stream().map(e -> e.getName() + "=?").skip(1).collect(Collectors.joining(","));
            return String.format("update %s set %s where id = ?", entityName, tmp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String delete() {
        return "delete from " + entityName + " where id = ?";
    }
}
