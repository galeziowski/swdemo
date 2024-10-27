package com.example.demo.owner.service;

import com.example.demo.exceptions.AuthorizationError;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.owner.model.OwnerProfile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {


    public static final List<OwnerProfile> OWNER_PROFILES = List.of(
            OwnerProfile.builder().id("1").name("Łukasz").surname("Gałęziowski").build()
            , OwnerProfile.builder().id("2").name("Zyta").build());

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
