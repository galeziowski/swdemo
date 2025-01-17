package com.example.demo.owner.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Builder
public class Owner {

    @Id
    UUID id;

    String name;

    String role;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @Builder.Default
    List<Pet> pets = new ArrayList<>();


}
