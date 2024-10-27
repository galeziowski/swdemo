package com.example.demo.owner;

import com.example.demo.exceptions.AuthorizationError;
import com.example.demo.owner.model.OwnerProfile;
import com.example.demo.owner.service.OwnerService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public List<OwnerProfile> getOwners() {
        return ownerService.getOwners();
    }

    @GetMapping("/{id}")
    public OwnerProfile getOwnerById(@PathVariable String id,
                                     @Parameter(hidden = true) OwnerProfile loggedInProfile) {
        if (loggedInProfile.getPermissions().equals("ADMIN")) {
            return ownerService.getOwnerById(id);
        } else {
            throw new AuthorizationError("Nie masz uprawnien", "ktos");
        }
    }

    @GetMapping("/auth/{id}")
    public OwnerProfile getAuthOwnerById(@PathVariable String id) {
        return ownerService.getOwnerByIds(id);
    }

    @PostMapping
    public void createOwnerProfile(@RequestBody OwnerProfile ownerProfile){
    }

    @PutMapping
    public OwnerProfile updateOwnerProfile(@RequestBody OwnerProfile ownerProfile){
        return ownerProfile;
    }

    @DeleteMapping
    public void deleteOwnerProfile(String id){

    }
}

