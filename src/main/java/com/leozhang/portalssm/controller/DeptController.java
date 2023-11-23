package com.leozhang.portalssm.controller;

import com.leozhang.portalssm.entity.Dept;
import com.leozhang.portalssm.service.DeptService;
import com.leozhang.portalssm.util.IconList;
import com.leozhang.portalssm.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/dept")
@Controller
public class DeptController {
    //获取图标列表
    private List<String> iconList = IconList.getList();
    @Autowired
    DeptService deptService;
    @RequiresPermissions(value = {"permission:query"})
    @RequestMapping("/list")
    public String getList(@RequestParam(value = "pid",defaultValue = "-1")Long pid, ModelMap model){
        model.addAttribute("pid", pid);
        return "staff/dept/list";
    }

    @RequestMapping("/add/page")
    public String add(Long pid, ModelMap model){
        model.addAttribute("pid", pid);
        model.addAttribute("iconList",iconList);
        return "staff/dept/add";
    }

    @RequestMapping("/add")
    public String insert(Dept dept,Long pid){
        deptService.insert(dept);
        //确定当前添加部分所属的pid，添加完后直接跳到其所属的部门下的列表
        return "redirect:/dept/list?pid="+pid;
    }

    @RequestMapping("/edit/page")
    public String editPage(Long pid,Long id,ModelMap model){
        Dept dept = deptService.selectDeptById(id);
        model.addAttribute("pid",pid);
        model.addAttribute("dept",dept);
        model.addAttribute("iconList",iconList);
        return "staff/dept/edit";
    }
    @RequestMapping("/save")
    public String saveEdit(Dept dept,Long pid){
        deptService.update(dept);
        return "redirect:/dept/list?pid="+pid;
    }

    @RequestMapping("/delete")
    public String deleteDept(Long id,Long pid){
        deptService.deleteById(id);
        return "redirect:/dept/list?pid="+pid;
    }

    @RequestMapping("/list/pid")
    @ResponseBody
    public Result getDeptListByPid(Long pid){
        return deptService.getDeptListByPid(pid);
    }

    @RequestMapping("/list/page")
    @ResponseBody
    public Result getListFprPage(
            @RequestParam(value = "pno",defaultValue = "1")int pno,
            @RequestParam(value = "psize",defaultValue = "10")int psize,
            @RequestParam(value = "pid",defaultValue = "-1")Long pid,
            @RequestParam(value = "name",defaultValue = "")String name,
            @RequestParam(value = "sortField",defaultValue = "")String sortField,
            @RequestParam(value = "sortType",defaultValue = "")String sortType
    ){
        return deptService.getListForPage(pno,psize,pid,name,sortField,sortType);
    }
}
