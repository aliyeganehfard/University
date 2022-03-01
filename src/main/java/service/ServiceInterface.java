package service;

import java.util.List;

public interface ServiceInterface<T> {
    void add(T t);
    void update(T t);
    void delete(String id);
    void findAll();
}
