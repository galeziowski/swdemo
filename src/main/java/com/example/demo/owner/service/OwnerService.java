package com.example.demo.owner.service;

import com.example.demo.exceptions.AuthorizationError;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.owner.model.OwnerProfile;
import com.example.demo.owner.model.PetProfile;
import com.example.demo.owner.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class OwnerService {

    @Autowired
    OwnerRepository ownerRepository;


    public static final Map<String, List<PetProfile>> pets = Map.of(
            "1", List.of(PetProfile.builder().name("Arya").build()),
            "2", List.of(PetProfile.builder().name("Erni").build()));

    public static final List<OwnerProfile> OWNER_PROFILES = List.of(
            OwnerProfile.builder().id("1").name("Łukasz").surname("Gałęziowski").pets(pets.get("2")).permissions("ADMIN").build(),
            OwnerProfile.builder().id("2").name("Zyta").pets(pets.get("1")).build());

    public List<OwnerProfile> getOwners() {
        return ownerRepository.findAll().stream().map(ownerEntity ->
                        OwnerProfile.builder().id(ownerEntity.getId().toString()).name(ownerEntity.getName()).permissions(ownerEntity.getRole()).build())
                .toList();
    }


    public OwnerProfile getOwnerById(String id) {
        return ownerRepository.findById(UUID.fromString(id)).map(ownerEntity ->
                        OwnerProfile.builder().id(ownerEntity.getId().toString()).name(ownerEntity.getName()).permissions(ownerEntity.getRole()).pets(ownerEntity.getPets().stream().map(pet -> PetProfile.builder().name(pet.getName()).build()).toList()).build())
                .orElseThrow(() -> new NotFoundException("Nie znalzlem uzytkownika"));
    }

    public OwnerProfile getOwnerByIds(String id) {
        throw new AuthorizationError("No nie bardzo", "Lukasz");

    }

}
