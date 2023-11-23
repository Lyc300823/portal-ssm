package com.leozhang.portalssm.service;

import com.leozhang.portalssm.entity.Dept;
import com.leozhang.portalssm.util.Result;

import java.util.List;

public interface DeptService {
    Result getListForPage(int pno, int psize, Long pid, String name, String sortField, String sortType);

    void insert(Dept dept);

    Dept selectDeptById(Long id);

    void update(Dept dept);

    void getDeleteIds(Long id, List<Long> idList, List<Dept> list);
    void deleteById(Long id);
    Result getDeptListByPid(Long pid);
}
