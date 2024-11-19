package com.wora.citronix.controller;


import com.wora.citronix.DTO.Ferme.CreateFermeDTO;
import com.wora.citronix.DTO.Ferme.ResponseFermeDTO;
import com.wora.citronix.Entity.Ferme;
import com.wora.citronix.service.FermeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.servlet.function.ServerResponse.ok;

@RestController
@RequestMapping("/Ferme")
public class FermeController {

    @Autowired
    private FermeService fermeService;

    @PostMapping
    public ResponseEntity<ResponseFermeDTO> createFerme(@RequestBody CreateFermeDTO createFermeDTO) {
        ResponseFermeDTO response = fermeService.createFerme(createFermeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
