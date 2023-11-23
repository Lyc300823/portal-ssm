package com.leozhang.portalssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leozhang.portalssm.entity.AssetType;
import com.leozhang.portalssm.entity.AssetTypeExample;
import com.leozhang.portalssm.mapper.AssetTypeMapper;
import com.leozhang.portalssm.service.AssetTypeService;
import com.leozhang.portalssm.util.ChangeChar;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetTypeServiceImpl implements AssetTypeService {
    @Autowired
    AssetTypeMapper assetTypeMapper;
    @Override
    public Result getListForPage(int pno, int psize, String assetTypeNam, String sortFiled, String sortType) {
        Page page = PageHelper.startPage(pno,psize);
        AssetTypeExample assetTypeExample = new AssetTypeExample();
        AssetTypeExample.Criteria criteria = assetTypeExample.createCriteria();
        if(assetTypeNam.trim().length()>0){
            criteria.andAssetTypeNamEqualTo("%"+assetTypeNam+"%");
        }
        if(sortFiled.trim().length()>0){
            assetTypeExample.setOrderByClause(ChangeChar.camelToUnderline(sortFiled,2)+" "+sortType);
        }
        List<AssetType> list = assetTypeMapper.selectByExample(assetTypeExample);


        return Result.end(200,list,"查询成功",page.getTotal());
    }
}
