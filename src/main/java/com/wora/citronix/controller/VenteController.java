package com.wora.citronix.controller;


import com.wora.citronix.DTO.Vente.CreateVenteDTO;
import com.wora.citronix.DTO.Vente.ResponseVenteDTO;
import com.wora.citronix.service.VenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Vente")
public class VenteController {

    @Autowired
    private VenteService venteService;

    @PostMapping
    public ResponseEntity<ResponseVenteDTO> createVente(@Valid @RequestBody CreateVenteDTO createVenteDTO){
        ResponseVenteDTO response = venteService.createVente(createVenteDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ResponseVenteDTO>> getAllVentes() {
        List<ResponseVenteDTO> response = venteService.getALlVentes();
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{venteId}")
    public ResponseEntity<ResponseVenteDTO> getVenteById(@PathVariable("venteId") Long id) {
        ResponseVenteDTO response = venteService.getVenteById(id);
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{venteId}")
    public ResponseEntity<?> deleteVente(@PathVariable("venteId") Long id) {
        if (venteService.deleteVente(id)){
            ResponseEntity.status(HttpStatus.OK).body("VEnte Deleted succefuly");
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Couldnt Delete the vente");
    }
}
