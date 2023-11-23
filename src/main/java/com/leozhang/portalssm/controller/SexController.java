package com.leozhang.portalssm.controller;
import com.leozhang.portalssm.entity.Sex;
import com.leozhang.portalssm.service.SexService;
import com.leozhang.portalssm.util.Result;
import org.apache.maven.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/sex")
public class SexController {
    @Autowired
    SexService sexService;
    //跳转到查询页面
    @RequestMapping("/list")
    public String sexList(){

        return "type/sex/list";
    }
    //点击添加，跳转到添加页
    @RequestMapping("/add/page")
    public String addPage(){
        return "type/sex/add";
    }
    @RequestMapping("/add")
    public String add(Sex sex){
        sexService.add(sex);
        return "redirect:/sex/list";
    }
    //
    @RequestMapping("/edit")
    public String editPage(ModelMap model, Long id){
        Sex sex = sexService.queryById(id);
        model.addAttribute("formData",sex);
        return "type/sex/edit";
    }
    @RequestMapping("/save")
    public String save(Sex sex){
        sexService.save(sex);
        return "redirect:/sex/list";
    }
    @RequestMapping("/delete")
    public String deletePage(Long id){
        sexService.deleteById(id);
        return "redirect:/sex/list";
    }
    @RequestMapping("/list/page")
    @ResponseBody
    public Result sexListPage(
            @RequestParam(value = "pno",defaultValue = "1") int pno,
            @RequestParam(value = "psize",defaultValue = "10") int psize,
            @RequestParam(value = "sexName",defaultValue = "") String sexName,
            @RequestParam(value = "sortFiled",defaultValue = "") String sortField,
            @RequestParam(value = "sortType",defaultValue = "") String sortType
    ){
        Result result = sexService.getListForPage(pno, psize,sexName,sortField, sortType);
        return result;
    }



}