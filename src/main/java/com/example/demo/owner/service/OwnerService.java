package com.example.demo.owner.service;

import com.example.demo.exceptions.AuthorizationError;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.owner.model.OwnerProfile;
import com.example.demo.owner.model.PetProfile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OwnerService {


    public static final Map<String, List<PetProfile>> pets = Map.of(
            "1", List.of(PetProfile.builder().race("dog").name("Arya").build()),
            "2", List.of(PetProfile.builder().race("tortoise").name("Enri").build()));

    public static final List<OwnerProfile> OWNER_PROFILES = List.of(
            OwnerProfile.builder().id("1").name("Łukasz").surname("Gałęziowski").pets(pets.get("2")).build(),
            OwnerProfile.builder().id("2").name("Zyta").pets(pets.get("1")).build());

    public List<OwnerProfile> getOwners(){
        return OWNER_PROFILES;
    }


    public OwnerProfile getOwnerById(String id) {
        return OWNER_PROFILES.stream().filter(ownerProfile -> ownerProfile.getId().equals(id)).findAny().orElseThrow(()-> new NotFoundException("Nie znalzlem uzytkownika"));
    }

    public OwnerProfile getOwnerByIds(String id) {
        throw new AuthorizationError("No nie bardzo", "Lukasz");

    }
}
