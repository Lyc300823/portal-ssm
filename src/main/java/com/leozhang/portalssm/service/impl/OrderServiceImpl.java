package com.leozhang.portalssm.service.impl;

import com.leozhang.portalssm.mapper.OrderMapper;
import com.leozhang.portalssm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

}
