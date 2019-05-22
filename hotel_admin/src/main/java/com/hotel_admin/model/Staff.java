package com.hotel_admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private int id;
    @Column(name = "first_name")
    private String name;
    private String lastName;
    private String patronymic;
    private Date dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "id_position")
    private Position position;

    private Double salary;

}
