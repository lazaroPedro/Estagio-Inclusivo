package com.ifbaiano.estagioinclusivo.dao;

import java.util.List;

public interface DAORepository<T, I> {

    public void insert(T entity);
    public void update(T entity);
    public void delete(T entity);
    public List<T> findAll();
    public T findById(I id);


}
