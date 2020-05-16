package com.bloom.challenge.mathsservice.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="mathematician")
public class Mathematician {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="born_date")
    private LocalDate bornDate;

    @Column(name="died_date")
    private LocalDate diedDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBornDate() {
        return bornDate;
    }

    public void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
    }

    public LocalDate getDiedDate() {
        return diedDate;
    }

    public void setDiedDate(LocalDate diedDate) {
        this.diedDate = diedDate;
    }
}
