package com.mjc.school.repository.interfaces;

import java.util.List;

public interface Repository<T> {
    List<T> findAll();

    T findById(Long id);

    T create(T id);

    T update(T id);

    Boolean deleteById(Long id);

    Boolean isExistById(Long id);

}
