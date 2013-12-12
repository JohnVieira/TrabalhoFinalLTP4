/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.SistemaConstrutora.DataAcess;


import br.edu.ifnmg.SistemaConstrutora.DomainModel.Predio;
import br.edu.ifnmg.SistemaConstrutora.DomainModel.PredioRepositorio;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author root
 */
@Stateless(name = "PredioRepositorio")
public class PredioDao
        extends DaoGenerico<Predio>
        implements PredioRepositorio {

    public  PredioDao() {
        super(Predio.class);
    }
 @Override
    public List<Predio> Buscar(Predio obj) {
        // Corpo da consulta
        String consulta = "select pe from Predio pe ";

        // A parte where da consulta
        String filtro = "";

        // Guarda a lista de parâmetros da query
        HashMap<String, Object> parametros = new HashMap<String, Object>();

        // Verifica campo por campo os valores que serão filtrados
        if (obj!=null) {
            if (obj.getNumero()!= null && obj.getNumero().length() > 0) {
                filtro += " pe.numero =:numero ";
                parametros.put("numero", obj.getNumero());
            }

            if (obj.getNome()!= null && obj.getNome().length() > 0) {
                if (filtro.length() > 0) {
                    filtro += " and ";
                }
                filtro += " pe.nome,e=:nome ";
                parametros.put("nomw", obj.getNome());
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
    public Predio porNumero (String numero){
        String consulta = "select pe from Predio pe WHERE pe.numero=:numero";
         // Cria a consulta no JPA
        Query query = manager.createQuery(consulta);

        // Aplica os parâmetros da consulta
        query.setParameter("numero", numero);

        // Executa a consulta
        return (Predio)query.getSingleResult();
    }   
     
}
