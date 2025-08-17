package com.Bakery.CakeBaker.services;

import com.Bakery.CakeBaker.dtos.CakeDTO;
import com.Bakery.CakeBaker.entities.CakeEntity;
import com.Bakery.CakeBaker.exceptions.ResourceNotFound;
import com.Bakery.CakeBaker.repositories.CakeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CakeService {
    private final CakeRepository cakeRepository;
    private final ModelMapper modelMapper;

    public CakeService(CakeRepository cakeRepository, ModelMapper modelMapper) {
        this.cakeRepository = cakeRepository;
        this.modelMapper = modelMapper;
    }

//    Check if cake exists
    public void doesCakeExist(Long id) {
        boolean exists = cakeRepository.existsById(id);
        if(!exists) {
            throw new ResourceNotFound("Cake with id: " + id + " not found");
        }
    }

//    GET /cakes -> Get all cakes
    public List<CakeDTO> getAllCakes() {
        List<CakeEntity> cakeEnities = cakeRepository.findAll();
        return cakeEnities
                .stream()
                .map(cakeEntity -> modelMapper.map(cakeEntity, CakeDTO.class))
                .collect(Collectors.toList());
    }

//    GET /cakes/{id} -> Get cake by id
    public Optional<CakeDTO> getCakeById(Long id) {
        return cakeRepository
                .findById(id)
                .map(cakeEntity -> modelMapper.map(cakeEntity, CakeDTO.class));
    }

//    POST /cakes -> Create Cake
    public CakeDTO createCake(CakeDTO cakeDTO) {
        CakeEntity savedCakeEntity = cakeRepository.save(modelMapper.map(cakeDTO, CakeEntity.class));
        return modelMapper.map(savedCakeEntity, CakeDTO.class);
    }

//    PUT /cakes/{id} -> Update cake
    public CakeDTO updateCake(Long id, CakeDTO cakeDTO) {
        doesCakeExist(id);

        CakeEntity cakeEntity = modelMapper.map(cakeDTO, CakeEntity.class);
        cakeEntity.setId(id);
        CakeEntity updatedCakeEntity = cakeRepository.save(cakeEntity);
        return modelMapper.map(updatedCakeEntity, CakeDTO.class);
    }

//    DELETE /cake/{id} -> Delete cake
    public boolean deleteCake(Long id) {
        doesCakeExist(id);
        cakeRepository.deleteById(id);
        return true;
    }
}
