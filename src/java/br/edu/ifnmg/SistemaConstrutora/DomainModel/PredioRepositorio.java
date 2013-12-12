/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.SistemaConstrutora.DomainModel;

import javax.ejb.Remote;

/**
 *
 * @author root
 */

@Remote
public interface PredioRepositorio 
    extends Repositorio<Predio>{
    
    public Predio porNumero(String numero);
}
