package ru.kpfu.itis.dao.base;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, K> {
    Optional<T> findById(K id);
    List<T> findAll();
    T save(T item);
    void update(T item);
    void delete(K id);
}