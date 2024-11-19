package com.wora.citronix.service.impl;

import com.wora.citronix.Mapper.ChampMapper;
import com.wora.citronix.repository.ChampsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChampServiceImpl {

    @Autowired
    private ChampsRepository champsRepository;

    @Autowired
    private ChampMapper champMapper;



}
