/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.SistemaConstrutora.DomainModel;

import javax.ejb.Remote;

/**
 *
 * @author Junior
 */
@Remote
public interface FornecedorRepositorio 
    extends Repositorio<Fornecedor>{
    
    public Fornecedor porCnpj(String cnpj);
    
}
