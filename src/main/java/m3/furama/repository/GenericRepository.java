package m3.furama.repository;

import m3.furama.util.CommonUtil;
import m3.furama.util.ConstantUtil;
import m3.furama.util.Query;
import m3.furama.util.paging.Page;
import m3.furama.util.paging.PageHelper;
import m3.furama.util.paging.Pageable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static m3.furama.util.Query.entityName;

public class GenericRepository {
    public void setEntityName(String entityName) {
        Query.entityName = entityName;
    }

    public List<Object> findAll() {
        return findAll(Query.findAll(), CommonUtil.getClazz(entityName));
    }

    public Page<Object> findAll(Pageable pageable) {
        int offset = (pageable.getPageNum() - 1) * pageable.getPageSize();
        String query = Query.findAllWithPaging().replace("?lim", String.valueOf(pageable.getPageSize()));
        List list = findAll((query.replace("?off", String.valueOf(offset))), CommonUtil.getClazz(entityName));
        PageHelper<Object> helper = new PageHelper<>();
        return helper.listToPage(list, findAll().size(), pageable);
    }

//    public int save(Employee employee) {
//        return 0;
//    }

    public int delete(int id) {
        try (PreparedStatement st = Config.getConnection().prepareStatement(Query.delete())) {
            st.setInt(1, id);
            return st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private List findAll(String query, Class clazz) {
        List result = new ArrayList<>();

        try (Connection connection = Config.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Constructor<?> ctor = clazz.getConstructors()[0];
                List<Field> fields = CommonUtil.getAllFields(clazz);
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
}
