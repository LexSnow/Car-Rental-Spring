package com.lex.car_rental_spring.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CarRentalUsers")
@ToString
public class User {
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;
    private String first_name;
    private String last_name;
    private String date_of_birth;
    @ToString.Exclude
    private String id_card_nr;
}
