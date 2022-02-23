package model.repository;

import java.sql.ResultSet;
import java.util.List;

public interface Repository<T> {
    void add(T t);
    void delete(Integer value);
    void update(T t);
    List<T> findAll();
    T getResultSet(ResultSet resultSet);
}
