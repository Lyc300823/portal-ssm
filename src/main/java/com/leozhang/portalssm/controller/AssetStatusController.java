package com.leozhang.portalssm.controller;

import com.leozhang.portalssm.service.AssetStatusService;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/assets-status")
public class AssetStatusController {
    @Autowired
    AssetStatusService assetStatusService;
    @RequestMapping("/list")
    public String getList(){
        return "type/assets-status/list";
    }
    @RequestMapping("/list/page")
    @ResponseBody
    public Result getListPage(
            @RequestParam(value = "pno",defaultValue = "1") int pno,
            @RequestParam(value = "psize",defaultValue = "10") int psize,
            @RequestParam(value = "assetTypeName",defaultValue = "") String assetTypeName,
            @RequestParam(value = "sortField",defaultValue = "") String sortField,
            @RequestParam(value = "sortType",defaultValue = "") String sortType
    ){
        Result result = assetStatusService.getListForPage(pno,psize,assetTypeName,sortField,sortType);
        return result;
    }
}
