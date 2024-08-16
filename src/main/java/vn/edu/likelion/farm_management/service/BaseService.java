package vn.edu.likelion.farm_management.service;

import java.util.List;
import java.util.Optional;

public interface BaseService <E, I, O> {
    Optional<O> create(E t);

    Optional<O> update(I t);

    List<O> saveAll(List<E> ts);

    void delete(String id);

    void deleteAll(List<String> listId);

    Optional<O> findById(String id);

    List<O> findAll();
}
