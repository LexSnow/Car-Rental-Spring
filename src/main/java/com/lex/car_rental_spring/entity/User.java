package com.lex.car_rental_spring.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CarRentalUsers")
public class User {
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;
    @ToString.Include
    private String first_name;
    @ToString.Include
    private String last_name;
    @ToString.Include
    private String date_of_birth;
    private String id_card_nr;
}
