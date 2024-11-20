package com.wora.citronix.controller;

import com.wora.citronix.DTO.Recolt.CreateRecoltDTO;
import com.wora.citronix.DTO.Recolt.ResponseRecoltDTO;
import com.wora.citronix.Entity.Recolte;
import com.wora.citronix.annotation.Exist.Exist;
import com.wora.citronix.repository.RecolteRepository;
import com.wora.citronix.service.RecolteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Recolt")
public class RecoltController {

    @Autowired
    private RecolteService recolteService;

    @PostMapping
    public ResponseEntity<ResponseRecoltDTO> createRecolt(@RequestBody @Valid CreateRecoltDTO createRecoltDTO){
        ResponseRecoltDTO response = recolteService.createRecolt(createRecoltDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ResponseRecoltDTO>> getRecolts() {
        List<ResponseRecoltDTO> response = recolteService.getAllRecolts();
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @GetMapping("/{recoltId}")
    public ResponseEntity<ResponseRecoltDTO> getRecolt(@PathVariable("recoltId") @Exist(entity = Recolte.class, repository = RecolteRepository.class) Long id){
        ResponseRecoltDTO response = recolteService.findRecoltById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @DeleteMapping("/{recoltId}")
    public ResponseEntity<?> deleteRecolt(@PathVariable("recoltId") @Exist(entity = Recolte.class, repository = RecolteRepository.class) Long id){
        if (recolteService.deleteRecolt(id)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted succefully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something went wrong");
    }
}
