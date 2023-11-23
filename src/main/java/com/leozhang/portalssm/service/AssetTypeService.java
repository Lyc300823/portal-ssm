package com.leozhang.portalssm.service;

import com.leozhang.portalssm.util.Result;

public interface AssetTypeService {
    Result getListForPage(int pno,int psize,String assetName,String sortFiled, String sortType);
}
