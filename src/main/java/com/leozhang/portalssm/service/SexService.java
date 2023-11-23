package com.leozhang.portalssm.service;

import com.leozhang.portalssm.entity.Sex;
import com.leozhang.portalssm.util.Result;

public interface SexService {
    Result getListForPage(int pno, int psize, String sexName, String sortFiled, String sortType);

    void add(Sex sex);

    void save(Sex sex);

    Sex queryById(Long id);
    void deleteById(Long id);
}
