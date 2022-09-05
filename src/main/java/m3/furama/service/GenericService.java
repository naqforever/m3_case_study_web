package m3.furama.service;

import m3.furama.repository.GenericRepository;
import m3.furama.util.paging.Page;
import m3.furama.util.paging.Pageable;

import java.util.List;

public class GenericService {
    private GenericRepository genericRepository = new GenericRepository();
    private String entityName;

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public List<Object> findAll() {
        return genericRepository.findAll();
    }

    public Page<Object> findAll(Pageable pageable) {
        genericRepository.setEntityName(entityName);
        return genericRepository.findAll(pageable);
    }

    public int save(Object employee) {
        return 0;
    }

    public List<Object> find(String q) {
        return null;
    }

    public int delete(int id) {
        return 0;
    }
}
