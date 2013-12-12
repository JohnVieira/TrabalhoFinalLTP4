/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.SistemaConstrutora.DomainModel;

import javax.ejb.Remote;

/**
 *
 * @author emerson
 */
@Remote
public interface UsuariolRepositorio extends Repositorio<Usuario>{

    /**
     *
     * @param login
     * @return
     * @throws Exception
     */
    public Usuario porLogin(String login) throws Exception;
    
}
