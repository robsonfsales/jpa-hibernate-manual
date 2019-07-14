package com.demo.example.dao;

import java.util.List;
//import java.util.Optional;


public interface Dao<T> {

    //Optional<T> get(long id);

    T getById(long id);

    List<T> getAll();

    T save(T t);

    T update(T t);

    void delete(T t);
}
