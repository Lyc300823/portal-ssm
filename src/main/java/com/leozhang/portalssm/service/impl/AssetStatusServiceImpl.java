package com.leozhang.portalssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leozhang.portalssm.entity.AssetStatus;
import com.leozhang.portalssm.entity.AssetStatusExample;
import com.leozhang.portalssm.mapper.AssetStatusMapper;
import com.leozhang.portalssm.service.AssetStatusService;
import com.leozhang.portalssm.util.ChangeChar;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetStatusServiceImpl implements AssetStatusService {
    @Autowired
    AssetStatusMapper assetStatusMapper;
    @Override
    public Result getListForPage(int pno, int psize, String assetName, String sortFiled, String sortType) {
        Page page = PageHelper.startPage(pno,psize);
        AssetStatusExample assetStatusExample = new AssetStatusExample();
        AssetStatusExample.Criteria criteria = assetStatusExample.createCriteria();
        if(assetName.trim().length()>0){
            criteria.andAssetStatusNaEqualTo("%"+assetName+"%");
        }
        if(sortFiled.trim().length()>0){
            assetStatusExample.setOrderByClause(ChangeChar.camelToUnderline(sortFiled,2)+" "+sortType);
        }
        List<AssetStatus> list = assetStatusMapper.selectByExample(assetStatusExample);
        return Result.end(200,list,"查询成功",page.getTotal());
    }
}
