/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.SistemaConstrutora.DataAcess;


import br.edu.ifnmg.SistemaConstrutora.DomainModel.Cliente;
import br.edu.ifnmg.SistemaConstrutora.DomainModel.ClienteRepositorio;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author root
 */
@Stateless(name = "ClienteRepositorio")
public class ClienteDAO
        extends DaoGenerico<Cliente>
        implements ClienteRepositorio {

    public ClienteDAO() {
        super(Cliente.class);
    }

    @Override
    public List<Cliente> Buscar(Cliente obj) {
        // Corpo da consulta
        String consulta = "select cl from Cliente cl ";

        // A parte where da consulta
        String filtro = "";

        // Guarda a lista de parâmetros da query
        HashMap<String, Object> parametros = new HashMap<String, Object>();

        // Verifica campo por campo os valores que serão filtrados
        if (obj!=null) {
            if (obj.getCpfcnpj()!= null && obj.getCpfcnpj().toString().length() > 0) {
                filtro += " cl.cpfcnpj=:cpfcnpj ";
                parametros.put("cpfcnpj", obj.getCpfcnpj());
            }
            if (obj.getNome()!= null && obj.getNome().toString().length() > 0) {
                if (filtro.length() > 0) {
                    filtro += " and ";
                }
                filtro += " cl.nome=:nome ";
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
    public Cliente porNome (String nome){
        String consulta = "select cl from Cliente cl WHERE cl.nome=:nome";
         // Cria a consulta no JPA
        Query query = manager.createQuery(consulta);

        // Aplica os parâmetros da consulta
        query.setParameter("nome", nome);

        // Executa a consulta
        return (Cliente)query.getSingleResult();
    }   


}
