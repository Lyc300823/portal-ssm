package com.leozhang.portalssm.service;

import com.leozhang.portalssm.entity.Equipment;
import com.leozhang.portalssm.util.Result;

import java.util.List;

public interface EquipmentService {
    Result selectListForPage(int pno, int psize, String name, Long brandId, Long statusId, String sortField, String sortType, Long roomId);
    void insertEquipment(Equipment equipment);
    void deleteEquipment(Long equipmentId);
    void deleteEquipment(Long equipmentId, Long id);
    Equipment getEquipmentById(Long equipmentId);

    List<Equipment> selectAll();

    void updateEquipment(Equipment equipment);
}
