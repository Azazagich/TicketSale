package com.study.service.dto;

import com.study.domain.Economy;
import com.study.domain.Ticket;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.study.domain.Economy} entity.
 */
public class EconomyDTO implements Serializable {
    private int id;
    private String type;

    public EconomyDTO() { }

    public EconomyDTO(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public EconomyDTO id(int id){
        this.id = id;
        return this;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public EconomyDTO type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "EconomyDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof EconomyDTO)){
            return false;
        }
        return id == ((EconomyDTO)o).id;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
