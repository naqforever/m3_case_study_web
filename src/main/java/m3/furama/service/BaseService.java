package m3.furama.service;

import java.util.List;

public interface BaseService<T> {
    List<T> findAll();
    int save(T t);
    List<T> find(String q);
    int delete(int id);
}
