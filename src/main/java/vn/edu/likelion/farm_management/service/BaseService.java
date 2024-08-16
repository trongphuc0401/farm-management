package vn.edu.likelion.farm_management.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BaseService <T> {
    Optional<T> save(T t);
    List<T> saveAll(List<T> ts);
    void delete(T t);
    void deleteAll(List<T> ts);
    Optional<T> findById(String id);
    List<T> getAll();
}
