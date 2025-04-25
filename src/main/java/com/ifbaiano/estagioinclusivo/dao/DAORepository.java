package com.ifbaiano.estagioinclusivo.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAORepository<T, I> {

    public void insert(T entity);
    public void update(T entity);
    public void delete(I id);
    public List<T> findAll();
    public T findById(I id);


}
