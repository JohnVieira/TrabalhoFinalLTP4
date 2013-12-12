/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.SistemaConstrutora.Presentation;

import br.edu.ifnmg.SistemaConstrutora.DomainModel.Predio;
import br.edu.ifnmg.SistemaConstrutora.DomainModel.PredioRepositorio;
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
@Named(value = "predioController")
@SessionScoped
public class PredioController implements Serializable {

    Predio entidade;
    Predio filtro;
    List<Predio> listagem;
    @EJB
    PredioRepositorio prediodao;


    /**
     * Creates a new instance of FuncionarioController
     */
    public PredioController() {
        entidade = new Predio();
        filtro = new Predio();
    }
        public List<Predio> AutoCompletar(String tmp){
        Predio pe = new Predio();
        pe.getNome();
        return prediodao.Buscar(pe);
    }
        
    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }

    public void salvar() {
        prediodao.Salvar(entidade);
        listagem = null;
        exibirMensagem("Salvo com Sucesso!");

    }

    public String editar() {
        return "EditarPredio.xhtml";
    }

    public String criar() {
        entidade = new Predio();
        return "EditarPredio.xhtml";
    }

    public String apagar() {
        prediodao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "PredioLista.xhtml";
    }

    public String filtrar() {
        listagem = prediodao.Buscar(filtro);
        return "PredioLista.xhtml";
    }

    public String voltar() {
        listagem = null;
        return "PredioLista.xhtml";
    }

    public Predio getEntidade() {
        return entidade;
    }

    public void setEntidade(Predio entidade) {
        this.entidade = entidade;
    }

    public Predio getFiltro() {
        return filtro;
    }

    public void setFiltro(Predio filtro) {
        this.filtro = filtro;
    }

    public PredioRepositorio getPrediodao() {
        return prediodao;
    }

    public void setPrediodao(PredioRepositorio prediodao) {
        this.prediodao = prediodao;
    }

    
    public List<Predio> getListagem() {
        if (listagem == null) {
         Predio filtro = new Predio();
         listagem = prediodao.Buscar(null);
        }
        return listagem;
    
    }

    public void setListagem(List<Predio> listagem) {
        this.listagem = listagem;
    }  
    
    
}
