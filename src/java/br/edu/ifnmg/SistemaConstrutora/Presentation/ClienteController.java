/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.SistemaConstrutora.Presentation;


import br.edu.ifnmg.SistemaConstrutora.DomainModel.Cliente;
import br.edu.ifnmg.SistemaConstrutora.DomainModel.ClienteRepositorio;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author root
 */
@Named(value = "ClienteController")
@SessionScoped
public class ClienteController implements Serializable {

    Cliente entidade;
    Cliente filtro;
    List<Cliente> listagem;
    @EJB
    ClienteRepositorio clientedao;


    public ClienteController() {
        entidade = new Cliente();
        filtro = new Cliente();
    }
    
    public List<Cliente> autoCompletar(String tmp){
        Cliente c = new Cliente();
        c.setNome(tmp);
        return clientedao.Buscar(c);
    }    
    

    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }

    public void salvar() {
        clientedao.Salvar(entidade);
        listagem = null;
        exibirMensagem("Salvo com Sucesso!");

    }

    public String editar() {
        return "EditarCliente.xhtml";
    }

    public String criar() {
        entidade = new Cliente();
        return "EditarCliente.xhtml";
    }

    public String apagar() {
        clientedao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "ClienteLista.xhtml";
    }

    public String filtrar() {
        listagem = clientedao.Buscar(filtro);
        return "ClienteLista.xhtml";
    }

    public String voltar() {
        listagem = null;
        return "ClienteLista.xhtml";
    }

    public Cliente getEntidade() {
        return entidade;
    }

    public void setEntidade(Cliente entidade) {
        this.entidade = entidade;
    }

    public Cliente getFiltro() {
        return filtro;
    }

    public void setFiltro(Cliente filtro) {
        this.filtro = filtro;
    }

    public List<Cliente> getListagem() {
         if (listagem == null) {
         Cliente filtro = new Cliente();
         listagem = clientedao.Buscar(null);
         }
        return listagem;
    }

    public void setListagem(List<Cliente> listagem) {
        this.listagem = listagem;
    }

    
}
