/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.SistemaConstrutora.DataAcess;

import br.edu.ifnmg.SistemaConstrutora.DomainModel.ContratoConstrucao;
import br.edu.ifnmg.SistemaConstrutora.DomainModel.ContratoConstrucaoRepositorio;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author root
 */
@Stateless(name = "ContratoConstrucaoRepositorio")
public class ContratoConstrucaoDAO 
    extends DaoGenerico<ContratoConstrucao> 
    implements ContratoConstrucaoRepositorio{

    public ContratoConstrucaoDAO() {
        super(ContratoConstrucao.class);
    }

    @Override
    public List<ContratoConstrucao> Buscar(ContratoConstrucao obj) {
        // Corpo da consulta
        String consulta = "select cc from ContratoConstrucao cc where cc.id > 0";

        // A parte where da consulta
        String filtro = "";

        // Guarda a lista de parâmetros da query
        HashMap<String, Object> parametros = new HashMap<String, Object>();

        // Verifica campo por campo os valores que serão filtrados
        if (obj != null) {
            //Id
            if (obj.getId() != null && obj.getId() > 0) {
                filtro += " AND cc.id like '%"+obj.getId()+"%'";
            }
         
            // Se houver filtros, coloca o "where" na consulta
            if (filtro.length() > 0) {
                consulta = consulta + filtro;
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
    public boolean Apagar(ContratoConstrucao obj) {
       
        try{
       
            String consulta = "Update ContratoConstrucao cc set cc.ativo = 0 WHERE s.id ="+obj.getId();
            
             Query query = manager.createQuery(consulta);
             query.executeUpdate();
             
       
             return true;
        
            
        }catch(Exception ex){
           ex.printStackTrace();
       
            return false;
        }
    }
    
}
