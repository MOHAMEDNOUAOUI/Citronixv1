package com.wora.citronix.controller;


import com.wora.citronix.DTO.Arbre.CreateArbreDTO;
import com.wora.citronix.DTO.Arbre.ResponseArbreDTO;
import com.wora.citronix.repository.ArbreRepository;
import com.wora.citronix.service.ArbreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Arbre")
public class ArbreController {

    @Autowired
    private ArbreService arbreService;

    @PostMapping
    public ResponseEntity<ResponseArbreDTO> createArbre(@RequestBody CreateArbreDTO createArbreDTO) {
        ResponseArbreDTO response = arbreService.createArbre(createArbreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ResponseArbreDTO>> getAllArbres() {
        List<ResponseArbreDTO> response = arbreService.getAllArbres();
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @GetMapping("/{arbreId}")
    public ResponseEntity<ResponseArbreDTO> getArbre(@PathVariable("arbreId") Long id){
        ResponseArbreDTO response = arbreService.getArbreById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @DeleteMapping("/{arbreId}")
    public ResponseEntity<?> deleteArbre(@PathVariable("arbreId") Long id){
        if (arbreService.deleteArbre(id)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted Succeffuly");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something went wrong");
    }
}
