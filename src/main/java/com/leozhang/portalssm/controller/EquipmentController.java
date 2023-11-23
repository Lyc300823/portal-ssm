package com.leozhang.portalssm.controller;

import com.leozhang.portalssm.entity.Equipment;
import com.leozhang.portalssm.entity.EquipmentBrand;
import com.leozhang.portalssm.entity.EquipmentStatus;
import com.leozhang.portalssm.service.EquipmentBrandService;
import com.leozhang.portalssm.service.EquipmentService;
import com.leozhang.portalssm.service.EquipmentStatusService;
import com.leozhang.portalssm.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/equipment")
@RequiresPermissions(value = {"Permission:query"})
public class EquipmentController {
    @Autowired
    EquipmentService equipmentService;
    @Autowired
    EquipmentBrandService equipmentBrandService;
    @Autowired
    EquipmentStatusService equipmentStatusService;

    @RequiresPermissions(value = {"Permission:query"})
    @RequestMapping("/list")
    public String list(Model model){
        List<EquipmentBrand> equipmentBrandList = equipmentBrandService.getEquipmentBrand();
        model.addAttribute("equipmentBrandList",equipmentBrandList);
        List<EquipmentStatus> equipmentStatusList = equipmentStatusService.getEquipmentStatus();
        model.addAttribute("equipmentStatusList",equipmentStatusList);
        return "room/equipment/list";
    }

    @RequestMapping("/list/page")
    @ResponseBody
    public Result page(
            @RequestParam(value = "pno",defaultValue = "1") int pno,
            @RequestParam(value = "psize",defaultValue = "10") int psize,
            @RequestParam(value = "name" , defaultValue = "") String name,
            @RequestParam(value = "brandId",required  = false) Long brandId,
            @RequestParam(value = "statusId",required  = false) Long statusId,
            @RequestParam(value = "sortField",defaultValue = "") String sortField,
            @RequestParam(value = "sortType",defaultValue = "") String sortType,
            @RequestParam(value = "roomId",required=false)Long roomId
    ){
        return equipmentService.selectListForPage(pno,psize,name,brandId,statusId,sortField,sortType,roomId);
    }

    @RequiresPermissions(value = {"permission:insert"})
    @RequestMapping("/add/page")
    public String addPage(Model model){
        List<EquipmentBrand> res = equipmentBrandService.getEquipmentBrand();
        model.addAttribute("equipmentBrandList",res);
        return "/room/equipment/add";
    }
    @RequiresPermissions(value = {"permission:insert"})
    @RequestMapping("/add")
    public String add(Equipment equipment){
        equipmentService.insertEquipment(equipment);
        return "redirect:/equipment/list";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "id") Long equipmentId){
        equipmentService.deleteEquipment(equipmentId);
        return "redirect:/equipment/list";
    }

    @RequestMapping("/set/status")
    public String setStatus(@RequestParam("id") Long equipmentId,Model model){
        Equipment equipment = equipmentService.getEquipmentById(equipmentId);
        List<EquipmentStatus> list = equipmentStatusService.getEquipmentStatus();
        model.addAttribute("formData",equipment);
        model.addAttribute("equipmentStatusList",list);
        return "/room/equipment/set-status";
    }

}