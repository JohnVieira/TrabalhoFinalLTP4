/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.SistemaConstrutora.DomainModel;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author root
 */
@Entity
public class Apartamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String numero;
    private Integer comodos;
    private Integer quarto;
    private Integer sala;
    private Integer cozinha;
    private Integer banheiro;    
    private Integer area;
    private Integer suite;
    private Integer garagem;
    private boolean ativo;
    
    @ManyToOne
    Predio predio;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getComodos() {
        return comodos;
    }

    public void setComodos(Integer comodos) {
        this.comodos = comodos;
    }

    public Integer getQuarto() {
        return quarto;
    }

    public void setQuarto(Integer quarto) {
        this.quarto = quarto;
    }

    public Integer getSala() {
        return sala;
    }

    public void setSala(Integer sala) {
        this.sala = sala;
    }

    public Integer getCozinha() {
        return cozinha;
    }

    public void setCozinha(Integer cozinha) {
        this.cozinha = cozinha;
    }

    public Integer getBanheiro() {
        return banheiro;
    }

    public void setBanheiro(Integer banheiro) {
        this.banheiro = banheiro;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getSuite() {
        return suite;
    }

    public void setSuite(Integer suite) {
        this.suite = suite;
    }

    public Integer getGaragem() {
        return garagem;
    }

    public void setGaragem(Integer garagem) {
        this.garagem = garagem;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Predio getPredio() {
        return predio;
    }

    public void setPredio(Predio predio) {
        this.predio = predio;
    }
    
    
@Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.predio);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Apartamento other = (Apartamento) obj;
        if (!Objects.equals(this.predio, other.predio)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return numero;
    } 
}
