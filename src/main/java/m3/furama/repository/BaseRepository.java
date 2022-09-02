package m3.furama.repository;

import m3.furama.util.ConstantUtil;
import m3.furama.util.Page;
import m3.furama.util.Pageable;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface BaseRepository<T> {
    List<T> findAll();
    Page<T> findAll(Pageable pageable);
    int save(T t);
    List<T> find(String q);

    default int delete(int id, String tableName){
        try (PreparedStatement st = Config.getConnection().prepareStatement(ConstantUtil.DELETE.replace("{table}", tableName))) {
            st.setInt(1, id);
            return st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
