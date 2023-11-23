package com.leozhang.portalssm.controller;

import com.leozhang.portalssm.entity.OrderStatus;
import com.leozhang.portalssm.service.OrderService;
import com.leozhang.portalssm.service.OrderStatusService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderStatusService orderStatusService;
    @RequiresPermissions(value = {"permission:query"})
    @RequestMapping("/problem/list/single")
    public String problemListSingle(Model model){
        List<OrderStatus> orderStatusList = orderStatusService.selectListAll();
        model.addAttribute("orderStatusList",orderStatusList);
        return "order/problem/single-list";
    }
}
