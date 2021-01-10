package ua.com.alevel.db;

import ua.com.alevel.entity.AbstractEntity;

import java.util.List;

public interface AbstractDB<E extends AbstractEntity> {

    void create(E e);
    List<E> read(String fieldName, Object value);
    List<E> readAll();
    void update(E e);
    void delete(Integer id);
}
