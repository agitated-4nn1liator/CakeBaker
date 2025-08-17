package com.AliceAndHerBakery.CakeBaker.controllers;

import com.AliceAndHerBakery.CakeBaker.dtos.CakeDTO;
import com.AliceAndHerBakery.CakeBaker.exceptions.ResourceNotFound;
import com.AliceAndHerBakery.CakeBaker.services.CakeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/CakeBakerApi/v1/cakes")
public class CakeController {
    private final CakeService cakeService;

    public CakeController(CakeService cakeService) {
        this.cakeService = cakeService;
    }

//    GET /cakes -> Get all cakes
    @GetMapping
    public ResponseEntity<List<CakeDTO>> getAllCakes() {
        return ResponseEntity.ok(cakeService.getAllCakes());
    }

//    GET /cakes/{id} -> Get cake by id
    @GetMapping("/{id}")
    public ResponseEntity<CakeDTO> getCakeById(@PathVariable Long id) {
        Optional<CakeDTO> cakeDTO = cakeService.getCakeById(id);
        return cakeDTO
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFound("Cake with id: " + id + " not found"));
    }

//    POST /cakes -> Create Cake
    @PostMapping
    public ResponseEntity<CakeDTO> createCake(@RequestBody @Valid CakeDTO cakeDTO) {
        CakeDTO createdCake = cakeService.createCake(cakeDTO);
        return new ResponseEntity<>(createdCake, HttpStatus.CREATED);
    }

//    PUT /cakes/{id} -> Update cake
    @PutMapping("/{id}")
    public ResponseEntity<CakeDTO> updateCake(@PathVariable Long id, @RequestBody @Valid CakeDTO cakeDTO) {
        return ResponseEntity.ok(cakeService.updateCake(id, cakeDTO));
    }

//    DELETE /cake/{id} -> Delete cake
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCake(@PathVariable Long id) {
        if(cakeService.deleteCake(id)) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
