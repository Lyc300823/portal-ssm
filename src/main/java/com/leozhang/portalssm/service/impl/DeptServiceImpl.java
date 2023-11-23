package com.leozhang.portalssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leozhang.portalssm.entity.Dept;
import com.leozhang.portalssm.entity.DeptExample;
import com.leozhang.portalssm.mapper.DeptMapper;
import com.leozhang.portalssm.service.DeptService;
import com.leozhang.portalssm.util.ChangeChar;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    DeptMapper deptMapper;
    @Override
    public Result getListForPage(int pno, int psize,Long pid, String name, String sortField, String sortType) {
        Page<Dept> page = PageHelper.startPage(pno,psize);
        DeptExample deptExample = new DeptExample();
        DeptExample.Criteria criteria = deptExample.createCriteria();
        criteria.andPidEqualTo(pid);
        if(name.trim().length()>0){
            criteria.andNameEqualTo("%"+name.trim()+"%");
        }
        if(sortField.trim().length()>0){
            deptExample.setOrderByClause(ChangeChar.camelToUnderline(sortField,2)+" "+sortType);
        }
        List<Dept> depts = deptMapper.selectByExample(deptExample);
        return Result.end(200,depts,"查询成功",page.getTotal());
    }

    @Override
    public void insert(Dept dept) {
        deptMapper.insert(dept);
    }

    @Override
    public Dept selectDeptById(Long id) {
        return deptMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Dept dept) {
        deptMapper.updateByPrimaryKeySelective(dept);
    }

    @Override
    public void getDeleteIds(Long id, List<Long> idList, List<Dept> list) {
        if(idList.size() == 0) {
            idList.add(id);
        }
        //循环遍历所有部门列表
        list.stream().forEach(dept -> {
            //由于要删除某id部门下的所有附属部门，则如果碰到有部门的pid为目标id，则要将该部门也放到需要删除的列表
            if (dept.getPid() == id){
                //把当前遍历的部门id加到需要删除的id列表内
                idList.add(dept.getId());
                //把当前部门id放到待删除名单后，还要判断当前部门下有没有附属部门，如果有，同样需要删除
                if(dept.getIsLeaf()==0)//不是叶子部门时，通过递归遍历，继续添加id
                getDeleteIds(dept.getId(),idList,list);
            }
            //如果当前部门id不是需要删除的部门的附属部门，则跳过，继续遍历其他dept，由于使用了forEach，所以不需要考虑前中后序遍历
        });
    }

    @Override
    public void deleteById(Long id) {
        //首先将查到的需要删除的id读到list，然后遍历list，调用删除sql
        List<Dept> list = deptMapper.selectByExample(new DeptExample());
        List<Long> idList = new ArrayList<>();
        getDeleteIds(id,idList,list);
        System.out.println(idList);
        //调用完getDeleteIds函数后，需要删除的Id都存放在idList，因此只需遍历idList，然后对IdList进行遍历删除
        idList.forEach(deleteId->{
            deptMapper.deleteByPrimaryKey(deleteId);
        });
    }

    @Override
    public Result getDeptListByPid(Long pid) {
        DeptExample deptExample = new DeptExample();
        DeptExample.Criteria criteria = deptExample.createCriteria();
        criteria.andPidEqualTo(pid);
        List<Dept> list = deptMapper.selectByExample(deptExample);
        return Result.end(200,list,"查询成功",list.size());
    }

}
