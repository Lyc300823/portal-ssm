package com.leozhang.portalssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.leozhang.portalssm.entity.*;
import com.leozhang.portalssm.entity.vo.RoleMenuVO;
import com.leozhang.portalssm.service.*;
import com.leozhang.portalssm.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/role")
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private PermissionService permissionService;
    @Autowired
    RoomService roomService;

    @RequiresPermissions(value = {"permission:query"})
    @RequestMapping("/list")
    public String roleList(){
        return "system/role/list";
    }
    @RequiresPermissions(value = {"permission:query"})
    @RequestMapping("/list/page")
    @ResponseBody
    public Result getUserListForPage(
            @RequestParam(value = "pno",defaultValue = "1") int pno,
            @RequestParam(value = "psize",defaultValue = "10") int psize,
            @RequestParam(value = "sortField",defaultValue = "") String sortField,
            @RequestParam(value = "sortType",defaultValue = "") String sortType

    ){
        return roleService.getListForPage(pno,psize,sortField,sortType);
    }

    @RequestMapping("/add/page")
    public String roleAddPage(){
        return "system/role/add";
    }

    @RequestMapping("/edit/page")
    public String editAddPage(Long id, Model model){
        Role role = roleService.findRoleById(id);
        model.addAttribute("formData",role);
        return "system/role/edit";
    }

    @RequestMapping("/add")
    public String roleAdd(Role role){
        roleService.insertRole(role);
        return "redirect:/role/list";
    }

    @RequestMapping("/edit")
    public String roleEdit(Role role){
        roleService.editRole(role);
        return "redirect:/role/list";
    }

    @RequestMapping("/delete")
    public String roleDelete(Long id){
        roleService.deleteRoleByid(id);
        return "redirect:/role/list";
    }

    @RequestMapping("/bind/menu/page")
    public String bindMenuPage(Model model,Long id){
        Role role = roleService.findRoleById(id);
        List<Menu> menuList = menuService.selectMenuTree();

        model.addAttribute("formData",role);
        List<Menu> hasMenuList = menuService.selectMenuListByRoleId(id);
        model.addAttribute("menuList",JSONObject.toJSONString(menuList));
        model.addAttribute("hasMenuList",JSONObject.toJSONString(hasMenuList));
        return "system/role/bind-menu";
    }

    @RequestMapping("/bind/menu")
    public String bindMenu(RoleMenuVO roleMenuVO){
        System.out.println(roleMenuVO);
        roleService.insertRoleMenu(roleMenuVO);
        return "redirect:/role/list";
    }

    @RequestMapping("/bind/permission/page")
    public String bindPermissionPage(Long id,Model model){
        Role role = roleService.findRoleById(id);
        List<Permission> permissioinList = permissionService.selectPermissionAll();
        Long[] permissionIds = roleService.selectPermissionIdByRoleId(id);
        model.addAttribute("formData",role);
        model.addAttribute("permissionList",permissioinList);
        model.addAttribute("permissionIds",permissionIds);
        return "system/role/bind-permission";
    }

    @RequestMapping("/bind/permission")
    public String bindPermission(RoleMenuVO roleMenuVO){
        roleService.bindPermission(roleMenuVO);
        return "redirect:/role/list";
    }


}
