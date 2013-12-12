/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.SistemaConstrutora.DomainModel;

import javax.ejb.Remote;

/**
 *
 * @author Anderson
 */
@Remote
public interface FormaDePagamentoRepositorio extends Repositorio<FormaDePagamento> {

    /**
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public boolean verificaESalva(FormaDePagamento obj) throws Exception;
    
}
