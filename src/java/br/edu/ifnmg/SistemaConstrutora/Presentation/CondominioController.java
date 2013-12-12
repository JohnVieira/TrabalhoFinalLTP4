/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.SistemaConstrutora.Presentation;

import br.edu.ifnmg.SistemaConstrutora.DomainModel.Condominio;
import br.edu.ifnmg.SistemaConstrutora.DomainModel.CondominioRepositorio;
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
@Named(value = "CondominioController")
@SessionScoped
public class CondominioController implements Serializable {

    Condominio entidade;
    Condominio filtro;
    List<Condominio> listagem;
    @EJB
    CondominioRepositorio condominiodao;


    public CondominioController() {
        entidade = new Condominio();
        filtro = new Condominio();
    }

    public List<Condominio> autoCompletar(String tmp){
        Condominio co = new Condominio();
        co.setNome(tmp);
        return condominiodao.Buscar(co);
    }      
    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }

    public void salvar() {
        condominiodao.Salvar(entidade);
        listagem = null;
        exibirMensagem("Salvo com Sucesso!");

    }

    public String editar() {
        return "EditarCondominio.xhtml";
    }

    public String criar() {
        entidade = new Condominio();
        return "EditarCondominio.xhtml";
    }

    public String apagar() {
        condominiodao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "CondominioLista.xhtml";
    }

    public String filtrar() {
        listagem = condominiodao.Buscar(filtro);
        return "CondominioLista.xhtml";
    }

    public String voltar() {
        listagem = null;
        return "CondominioLista.xhtml";
    }

    public Condominio getEntidade() {
        return entidade;
    }

    public void setEntidade(Condominio entidade) {
        this.entidade = entidade;
    }

    public Condominio getFiltro() {
        return filtro;
    }

    public void setFiltro(Condominio filtro) {
        this.filtro = filtro;
    }

    public List<Condominio> getListagem() {
        if (listagem == null) {
         Condominio filtro = new Condominio();
         listagem = condominiodao.Buscar(null);
         }
        return listagem;
    }

    public void setListagem(List<Condominio> listagem) {
        this.listagem = listagem;
    }
    
}
