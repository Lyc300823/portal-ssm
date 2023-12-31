package com.leozhang.portalssm.controller;

import com.leozhang.portalssm.entity.Equipment;
import com.leozhang.portalssm.entity.Room;
import com.leozhang.portalssm.entity.RoomArea;
import com.leozhang.portalssm.entity.User;
import com.leozhang.portalssm.service.EquipmentService;
import com.leozhang.portalssm.service.RoomAreaService;
import com.leozhang.portalssm.service.RoomService;
import com.leozhang.portalssm.service.UserService;
import com.leozhang.portalssm.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/room")
@Controller
public class RoomController {
    @Autowired
    RoomService roomService;
    @Autowired
    RoomAreaService roomAreaService;
@Autowired
    UserService userService;

    @RequestMapping("/list")
    @RequiresPermissions(value = {"permission:query"})
    public String roomList(Model model){
        List<RoomArea> list = roomAreaService.getAllRoomsArea();
        model.addAttribute("areaList",list);
        return "room/room/list";

    }

    @RequestMapping("/list/page")
    @ResponseBody
    public Result roomListPage(
            @RequestParam(value = "pno",defaultValue = "1")int pno,
            @RequestParam(value = "psize",defaultValue = "10")int psize,
            @RequestParam(value = "name",defaultValue = "")String name,
            @RequestParam(value = "areaId")Long areaId,
            @RequestParam(value = "phone",defaultValue = "")String phone,
            @RequestParam(value = "sortField",defaultValue = "")String sortField,
            @RequestParam(value = "sortType",defaultValue = "")String sortType
    ){
        return roomService.getListForPage(pno,psize,name,areaId,phone,sortField,sortType);
    }

    @RequestMapping("/bind/user/page")
    public String bindUserPage(Model model,Long id){
        List<User> list = userService.selectAllUser();
        Room room = roomService.selectRoomById(id);
        model.addAttribute("userList",list);
        model.addAttribute("formData",room);
        return "room/room/bind-user";
    }
    @RequestMapping("/edit")
    public String roomEdit(Room room){
        roomService.updateRoom(room);
        return "redirect:/room/list";
    }
    @RequiresPermissions(value = {"permission:query"})
    @RequestMapping("/equipment/list")
    public String roomEquipmentList(Long roomId,Model model){
        model.addAttribute("roomId",roomId);
        return "room/room/equipment-list";
    }
    @Autowired
    private EquipmentService equipmentService;
    @RequestMapping("/equipment/add/page")
    public String roomEquipmentAddPage(Long roomId,Model model){
        Room room = roomService.selectRoomById(roomId);
        List<Equipment> equipmentList = equipmentService.selectAll();
        model.addAttribute("formData",room);
        model.addAttribute("equipmentList",equipmentList);
        return "room/room/equipment-add";
    }
    @RequestMapping("/equipment/add")
    public String roomEquipmentAdd(Equipment equipment){
        equipmentService.updateEquipment(equipment);
        return "redirect:/room/equipment/list?roomId="+equipment.getRoomId();
    }
    @RequestMapping("/equipment/delete")
    public String roomEquipmentDelete(Long roomId,Long id){//分别是机房ID和设备本身id
        equipmentService.deleteEquipment(roomId,id);
        return "redirect:/room/equipment/list?roomId="+roomId;
    }
}
