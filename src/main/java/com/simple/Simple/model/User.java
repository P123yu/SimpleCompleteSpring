package com.simple.Simple.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="_user")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String city;

    private Double marks;

}
