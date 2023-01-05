package com.mjc.school.service.interfaces;

import java.util.List;

public interface Service<T1, T2> {

    public List<T2> readAll();

    public T2 readById(Long var1);

    public T2 create(T1 var1);

    public T2 update(T1 var1);

    public Boolean deleteById(Long var1);
}
