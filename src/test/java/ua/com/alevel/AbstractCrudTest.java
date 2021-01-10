package ua.com.alevel;

import ua.com.alevel.entity.AbstractEntity;

public interface AbstractCrudTest<E extends AbstractEntity> {

    void create();
    void read();
    void update();
    void delete();
}
