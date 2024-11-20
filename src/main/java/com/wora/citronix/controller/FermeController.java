package com.wora.citronix.controller;


import com.wora.citronix.DTO.Ferme.CreateFermeDTO;
import com.wora.citronix.DTO.Ferme.ResponseFermeDTO;
import com.wora.citronix.DTO.Ferme.SearchFermeDTO;
import com.wora.citronix.Entity.Champ;
import com.wora.citronix.Entity.Ferme;
import com.wora.citronix.annotation.Exist.Exist;
import com.wora.citronix.repository.ChampsRepository;
import com.wora.citronix.repository.FermeRepository;
import com.wora.citronix.service.FermeService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.servlet.function.ServerResponse.ok;

@RestController
@RequestMapping("/Ferme")
public class FermeController {

    @Autowired
    private FermeService fermeService;


    @PostMapping
    public ResponseEntity<ResponseFermeDTO> createFerme(@RequestBody @Valid CreateFermeDTO createFermeDTO) {
        ResponseFermeDTO response = fermeService.createFerme(createFermeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ResponseFermeDTO>> getAllFermes() {
        List<ResponseFermeDTO> response = fermeService.getAllFermes();
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @GetMapping("/{fermeId}")
    public ResponseEntity<ResponseFermeDTO> getFermeById(@PathVariable("fermeId") @Exist(entity = Ferme.class, repository = FermeRepository.class) Long id){
        ResponseFermeDTO response = fermeService.getFermeById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

//    @GetMapping("/{name}/{}")
//    public ResponseEntity<List<ResponseFermeDTO>> getAllFermesByNameAndLocation(@RequestBody SearchFermeDTO searchFermeDTO) {
//        List<ResponseFermeDTO> response = fermeService.getAllFermesByNameAndLocalisation(searchFermeDTO);
//        return ResponseEntity.status(HttpStatus.FOUND).body(response);
//    }

    @DeleteMapping("/{fermeId}")
    public ResponseEntity<?> deleteFermeById(@PathVariable("fermeId") @Exist(entity = Ferme.class, repository = FermeRepository.class) Long id){
        if(fermeService.removeFerme(id)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted Succefully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something went wrong");
    }

}
