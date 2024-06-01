package com.example.proiect;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DNACombination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String combination;

    public DNACombination() {}

    public DNACombination(String combination) {
        this.combination = combination;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }
}
