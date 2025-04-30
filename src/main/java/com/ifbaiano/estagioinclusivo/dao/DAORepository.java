package com.ifbaiano.estagioinclusivo.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAORepository<T, I> {

    public Optional<I> insert(T entity);
    public void update(T entity);
    public void delete(I id);
    public List<T> findAll();
    public Optional<T> findById(I id);


}
