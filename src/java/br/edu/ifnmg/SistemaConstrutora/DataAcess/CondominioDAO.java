/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.SistemaConstrutora.DataAcess;

import br.edu.ifnmg.SistemaConstrutora.DomainModel.Condominio;
import br.edu.ifnmg.SistemaConstrutora.DomainModel.CondominioRepositorio;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author root
 */
@Stateless(name = "CondominioRepositorio")
public class CondominioDAO
        extends DaoGenerico<Condominio>
        implements CondominioRepositorio {

    public CondominioDAO() {
        super(Condominio.class);
    }

    @Override
    public List<Condominio> Buscar(Condominio obj) {
        // Corpo da consulta
        String consulta = "select co from Condominio co ";

        // A parte where da consulta
        String filtro = "";

        // Guarda a lista de parâmetros da query
        HashMap<String, Object> parametros = new HashMap<String, Object>();

        // Verifica campo por campo os valores que serão filtrados
        if (obj!=null) {
            if (obj.getNumero()!= null && obj.getNumero().toString().length() > 0) {
                filtro += " co.numero=:numero ";
                parametros.put("numero", obj.getNumero());
            }
            if (obj.getNome()!= null && obj.getNome().toString().length() > 0) {
                if (filtro.length() > 0) {
                    filtro += " and ";
                }
                filtro += " co.nome=:nome ";
                parametros.put("nome", obj.getNome());
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
    public Condominio porNome (String nome){
        String consulta = "select co from Condominio co WHERE co.nome=:nome";
         // Cria a consulta no JPA
        Query query = manager.createQuery(consulta);

        // Aplica os parâmetros da consulta
        query.setParameter("nome", nome);

        // Executa a consulta
        return (Condominio)query.getSingleResult();
    }   


}
