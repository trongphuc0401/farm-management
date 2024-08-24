package vn.edu.likelion.farm_management.service;

import java.util.List;
import java.util.Optional;

public interface BaseService <E, I, O, U> {
    Optional<O> create(I t);

    Optional<O> update(U u);

    Optional<O> updateInfo(String id, U u);

    List<O> saveAll(List<E> ts);

    void delete(String id);

    void deleteAll(List<String> listId);

    Optional<O> findById(String id);

    List<O> findAll();
}
