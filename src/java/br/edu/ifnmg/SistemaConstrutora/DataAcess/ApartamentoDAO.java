/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.SistemaConstrutora.DataAcess;

import br.edu.ifnmg.SistemaConstrutora.DomainModel.Apartamento;
import br.edu.ifnmg.SistemaConstrutora.DomainModel.ApartamentoRepositorio;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author root
 */
@Stateless(name = "ApartamentoRepositorio")
public class ApartamentoDAO
        extends DaoGenerico<Apartamento>
        implements ApartamentoRepositorio {

    public ApartamentoDAO() {
        super(Apartamento.class);
    }

    @Override
    public List<Apartamento> Buscar(Apartamento obj) {
        // Corpo da consulta
        String consulta = "select ap from Apartamento ap where ap.id > 0 ";

        // A parte where da consulta
        String filtro = "";

        // Guarda a lista de parâmetros da query
        HashMap<String, Object> parametros = new HashMap<String, Object>();

        // Verifica campo por campo os valores que serão filtrados
        if (obj!=null) {
            if (obj.getId()!= null && obj.getId().toString().length() > 0) {
                filtro += " ap.id=:id like '%"+obj.getId()+"%' ";
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
    public Apartamento porNumero (String numero){
        String consulta = "select ap from Apartamento fo where ap.numero=:numero";
                // Cria a consulta no JPA
        Query query = manager.createQuery(consulta);

        // Aplica os parâmetros da consulta
        query.setParameter("numero", numero);

        // Executa a consulta
        return (Apartamento)query.getSingleResult();
    }
}
