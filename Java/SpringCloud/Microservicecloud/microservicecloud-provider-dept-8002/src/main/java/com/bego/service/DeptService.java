package com.bego.service;

import com.bego.entity.Dept;

import java.util.List;

public interface DeptService {

    public boolean addDept(Dept dept);

    public Dept findById(Integer deptNo);

    public List<Dept> findAll();
}
