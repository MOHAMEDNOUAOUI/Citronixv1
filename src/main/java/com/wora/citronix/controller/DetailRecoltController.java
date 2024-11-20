package com.wora.citronix.controller;

import com.wora.citronix.DTO.DetailRecolt.CreateDetailRecoltDTO;
import com.wora.citronix.DTO.DetailRecolt.ResponseDetailRecoltDTO;
import com.wora.citronix.DTO.Recolt.ResponseRecoltDTO;
import com.wora.citronix.service.DetailRecoltService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Harvest")
public class DetailRecoltController {

    @Autowired
    private DetailRecoltService detailRecoltService;

    @PostMapping
    public ResponseEntity<ResponseDetailRecoltDTO> createDetails(@Valid @RequestBody CreateDetailRecoltDTO createDetailRecoltDTO){
        ResponseDetailRecoltDTO response = detailRecoltService.Harvest(createDetailRecoltDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ResponseDetailRecoltDTO>> getAllHervests(){
        List<ResponseDetailRecoltDTO> response = detailRecoltService.findHarvests();
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }
}
