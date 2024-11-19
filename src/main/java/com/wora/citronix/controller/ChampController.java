package com.wora.citronix.controller;


import com.wora.citronix.DTO.Champ.CreateChampDTO;
import com.wora.citronix.DTO.Champ.ResponseChampDTO;
import com.wora.citronix.Entity.Champ;
import com.wora.citronix.annotation.Exist.Exist;
import com.wora.citronix.repository.ChampsRepository;
import com.wora.citronix.service.ChampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@RestController
@RequestMapping("/Champ")
public class ChampController {

    @Autowired
    private ChampService champService;

    @PostMapping
    public ResponseEntity<ResponseChampDTO> createChamp(@RequestBody CreateChampDTO createChampDTO){
        ResponseChampDTO response = champService.createChamp(createChampDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ResponseChampDTO>> getAllChamps(){
        List<ResponseChampDTO> response = champService.getAllChamps();
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @GetMapping("/{champId}")
    public ResponseEntity<ResponseChampDTO> getChamps(@PathVariable("champId") @Exist(entity = Champ.class, repository = ChampsRepository.class) Long id){
        ResponseChampDTO response = champService.getChampsById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @DeleteMapping("/{champId}")
    public ResponseEntity<?> deleteChamp(@PathVariable("champId") @Exist(entity = Champ.class, repository = ChampsRepository.class) Long id){
        if (champService.deleteChamps(id)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted sucefully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sorry Something went wrong");
    }

}
