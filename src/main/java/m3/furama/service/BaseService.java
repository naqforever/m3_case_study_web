package m3.furama.service;

import java.util.List;

public interface BaseService<T> {
    List<T> findAll();
    void save();
    List<T> find(String q);
}
