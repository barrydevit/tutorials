package com.app.demoprojection.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Addresse {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String quartier;
    private String ville;

    public Addresse(String ville, String rue) {
        this.ville = ville;
        this.quartier = rue;
    }

    public Addresse() {
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String rue) {
        this.quartier = rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
