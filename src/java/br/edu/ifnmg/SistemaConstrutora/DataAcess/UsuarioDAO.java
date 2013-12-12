/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.SistemaConstrutora.DataAcess;

import br.edu.ifnmg.SistemaConstrutora.DomainModel.Usuario;
import br.edu.ifnmg.SistemaConstrutora.DomainModel.UsuariolRepositorio;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author emerson
 */
@Stateless(name = "UsuarioRepositorio")
public class UsuarioDAO extends DaoGenerico<Usuario> implements UsuariolRepositorio{
    
    public UsuarioDAO(){
        super(Usuario.class);
    }

    @Override
    public List<Usuario> Buscar(Usuario obj) {
        // Corpo da consulta
        String consulta = "select u from Usuario u";

        // A parte where da consulta
        String filtro = "";

        // Guarda a lista de parâmetros da query
        HashMap<String, Object> parametros = new HashMap<String, Object>();

        // Verifica campo por campo os valores que serão filtrados
        if (obj != null) {
            //Nome
            if (obj.getLogin()!= null && obj.getLogin().length() > 0) {
                filtro += " u.login=:login ";
                parametros.put("login", obj.getLogin());
            }
            //Id
            if (obj.getId() != null && obj.getId() > 0) {
                if (filtro.length() > 0) {
                    filtro = filtro + " and ";
                }
                filtro += " u.id=:id ";
                parametros.put("id", obj.getId());
            }

            // Se houver filtros, coloca o "where" na consulta
            if (filtro.length() > 0) {
                consulta = consulta + " where " + filtro;
            }
        }

        // Cria a consulta no JPA
        Query query = manager.createQuery(consulta);

        // Aplica os parâmetros da consulta
        for (String par : parametros.keySet()) {
            query.setParameter(par, parametros.get(par));
        }

        // Executa a consulta
        return query.getResultList();

    }
    
    @Override
     public Usuario porLogin(String login) throws Exception{
        String consulta = "select f from Usuario f where f.ativo = 1 and f.login=:login ";
                // Cria a consulta no JPA
        Query query = manager.createQuery(consulta);

        // Aplica os parâmetros da consulta
        query.setParameter("login", login);
        
        try{
        // Executa a consulta
            return (Usuario)query.getSingleResult();
        }catch(NoResultException ex){
            throw new Exception("Usuário não encontrado!");
            
        }
        


    }

    @Override
    public boolean Apagar(Usuario obj) {
       try {
            Query query = manager.createQuery("Update Usuario s set s.ativo = 0 WHERE s.id =:id");
            query.setParameter("id", obj.getId());
            query.executeUpdate();

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }
    
}
