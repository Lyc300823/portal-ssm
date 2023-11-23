package com.leozhang.portalssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leozhang.portalssm.entity.Equipment;
import com.leozhang.portalssm.entity.EquipmentExample;
import com.leozhang.portalssm.mapper.EquipmentMapper;
import com.leozhang.portalssm.service.EquipmentService;
import com.leozhang.portalssm.util.ChangeChar;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    EquipmentMapper equipmentMapper;

    @Override
    public Result selectListForPage(int pno, int psize, String name, Long brandId, Long statusId, String sortField, String sortType, Long roomId) {
        Page<Equipment> page = PageHelper.startPage(pno,psize);
        EquipmentExample example = new EquipmentExample();
        EquipmentExample.Criteria c =  example.createCriteria();
        if(roomId!=null){
            c.andRoomIdEqualTo(roomId);
        }
        if(name.trim().length()>0){
            c.andNameLike("%"+name.trim()+"%");
        }
        if(brandId!=null){
            c.andBrandIdEqualTo(brandId);
        }
        if(statusId!=null){
            c.andStatusIdEqualTo(statusId);
        }
        if(sortField.trim().length()>0){
            example.setOrderByClause(ChangeChar.camelToUnderline(sortField,2) +" "+sortType);
        }
        List<Equipment> equipment = equipmentMapper.selectAllByExample(example);
        return Result.end(200,equipment,"查询成功",page.getTotal());
    }

    @Override
    public void insertEquipment(Equipment equipment) {
        equipment.setInsertTime(new Date());
        equipmentMapper.insert(equipment);
    }

    @Override
    public void deleteEquipment(Long equipmentId) {
        equipmentMapper.deleteByPrimaryKey(equipmentId);

    }

    @Override
    public void deleteEquipment(Long room_id, Long equipmentId) {
        //删除机房绑定设备，即将设备的room_id置为空
        Equipment equipment = equipmentMapper.selectByPrimaryKey(equipmentId);
        equipment.setRoomId(null);
        equipmentMapper.updateByPrimaryKey(equipment);
    }

    @Override
    public Equipment getEquipmentById(Long equipmentId) {
        EquipmentExample example = new EquipmentExample();
        EquipmentExample.Criteria c = example.createCriteria();
        c.andIdEqualTo(equipmentId);
        List<Equipment> res =  equipmentMapper.selectByExample(example);
        if(res.size()>0) {
            return res.get(0);
        }
        return null;
    }

    @Override
    public List<Equipment> selectAll() {
        //这⾥需要搜索没有被绑定机房的设备
        EquipmentExample equipmentExample = new EquipmentExample();
        EquipmentExample.Criteria criteria = equipmentExample.createCriteria();
        criteria.andRoomIdIsNull();
        return equipmentMapper.selectByExample(equipmentExample);
    }
    //绑定设备到机房
    @Override
    public void updateEquipment(Equipment equipment) {
        equipmentMapper.updateByPrimaryKeySelective(equipment);
    }
}
