package com.bego.dao;

import com.bego.entity.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptDao {

    public boolean addDept(Dept dept);

    public Dept findById(Integer deptNo);

    public List<Dept> findAll();
}
