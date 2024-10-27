package com.example.demo.vet;

import com.example.demo.vet.model.VetProfile;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vet")
public class SampleController {

    @GetMapping
    public String hello() {
        return "Hello, Swagger!";
    }

    @PostMapping
    public void createVetProfile( @RequestBody VetProfile vetProfile){
    }

    @PutMapping
    public VetProfile updateVetProfile(@RequestBody VetProfile vetProfile){
        return vetProfile;
    }

    @DeleteMapping
    public void deleteVetProfile(String id){

    }
}

