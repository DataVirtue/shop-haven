package com.example.shophaven.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "seller")
@Builder
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    @Column(unique = true,nullable = false)
    String emailId;

    @Column(unique = true,nullable = false)
    String panNo;

    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL)
    List<Product> products = new ArrayList<>();

}
