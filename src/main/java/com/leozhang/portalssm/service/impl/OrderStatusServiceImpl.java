package com.leozhang.portalssm.service.impl;


import com.leozhang.portalssm.mapper.OrderStatusMapper;
import com.leozhang.portalssm.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {
    @Autowired
    OrderStatusMapper orderStatusMapper;

    @Override
    public List selectListAll() {
        return orderStatusMapper.selectByExample(null);

    }
}
