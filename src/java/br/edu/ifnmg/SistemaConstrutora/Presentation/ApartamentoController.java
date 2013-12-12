/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.SistemaConstrutora.Presentation;

import br.edu.ifnmg.SistemaConstrutora.DomainModel.Apartamento;
import br.edu.ifnmg.SistemaConstrutora.DomainModel.ApartamentoRepositorio;
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
@Named(value = "ApartamentoController")
@SessionScoped
public class ApartamentoController implements Serializable {

    Apartamento entidade;
    Apartamento filtro;
    List<Apartamento> listagem;
    @EJB
    ApartamentoRepositorio apartamentodao;


    public ApartamentoController() {
        entidade = new Apartamento();
        filtro = new Apartamento();
    }
    

    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }

    public void salvar() {
//        adicionaFuncionario();
        
        if(apartamentodao.Salvar(entidade)){
            listagem = null;
            exibirMensagem("Salvo com Sucesso!");
            entidade = new Apartamento();
        }else{
                exibirMensagem("Erro ao Salvar!");
        }
        

    }

    public String editar() {
        return "EditarApartamento.xhtml";
    }

    public String criar() {
        entidade = new Apartamento();
        return "EditarApartamento.xhtml";
    }

    public String apagar() {
        apartamentodao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "ApartamentoLista.xhtml";
    }

    public String filtrar() {
        listagem = apartamentodao.Buscar(filtro);
        return "ApartamentoLista.xhtml";
    }

    public String voltar() {
        return "ApartamentoLista.xhtml";
    }
    
     public List<Apartamento>listarTodos(){
         listagem = apartamentodao.Buscar(filtro);
         return listagem;
     }

    public Apartamento getEntidade() {
        return entidade;
    }

    public void setEntidade(Apartamento entidade) {
        this.entidade = entidade;
    }

    public Apartamento getFiltro() {
        return filtro;
    }

    public void setFiltro(Apartamento filtro) {
        this.filtro = filtro;
    }

    public List<Apartamento> getListagem() {
        return listagem;
    }

    public void setListagem(List<Apartamento> listagem) {
        this.listagem = listagem;
    }

    public ApartamentoRepositorio getApartamentodao() {
        return apartamentodao;
    }

    public void setApartamentodao(ApartamentoRepositorio apartamentodao) {
        this.apartamentodao = apartamentodao;
    }
    
    
}
