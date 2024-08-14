package vn.edu.likelion.farm_management.service;

import java.util.Collection;
import java.util.List;

public interface BaseService <T,ID> {
    T save(T t);
    List<T> saveAll(List<T> ts);
    void delete(T t);
    void deleteAll(List<T> ts);
    T getById(ID id);
    Collection<T> getAll();
}
