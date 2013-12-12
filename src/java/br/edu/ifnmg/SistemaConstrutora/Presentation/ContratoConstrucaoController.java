/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.SistemaConstrutora.Presentation;

import br.edu.ifnmg.SistemaConstrutora.DomainModel.ContratoConstrucao;
import br.edu.ifnmg.SistemaConstrutora.DomainModel.ContratoConstrucaoRepositorio;
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
@Named(value = "contratoConstrucaoController")
@SessionScoped
public class ContratoConstrucaoController implements Serializable{

    /**
     * Creates a new instance of AluguelImovelController
     */
    @EJB
    ContratoConstrucaoRepositorio dao;
    ContratoConstrucao entidade;
    ContratoConstrucao filtro;
    List<ContratoConstrucao> listagem;
    
    public ContratoConstrucaoController() {
        entidade = new ContratoConstrucao();
        filtro = new ContratoConstrucao();
    }
    
    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Aviso",msg));
    }

    public void salvar() {
//        adicionaFuncionario();
        
        if(dao.Salvar(entidade)){
            listagem = null;
            exibirMensagem("Salvo com Sucesso!");
            entidade = new ContratoConstrucao();
        }else{
                exibirMensagem("Erro ao Salvar!");
        }
        

    }

//    public void adicionaFuncionario(){
//         AutenticacaoController a = new AutenticacaoController();
//                 
//         entidade.setFuncionario(a.pegarDaSessao().getFuncionario());
//         
//     
//     }
    public String editar() {
        return "EditarContratoConstrucao.xhtml";
    }

    public String criar() {
        entidade = new ContratoConstrucao();
        return "EditarContratoConstrucao.xhtml";
    }

    public String apagar() {
        dao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "ContratoConstrucaoLista.xhtml";
    }

    public String voltar() {
        listagem = null;
        return "ContratoConstrucaoLista.xhtml";
    }
    
     public String filtrar() {
        listagem = dao.Buscar(filtro);
        return "ContratoConstrucaoLista.xhtml";
    }
     
     public List<ContratoConstrucao>listarTodos(){
         listagem = dao.Buscar(filtro);
         return listagem;
     }

    public ContratoConstrucaoRepositorio getDao() {
        return dao;
    }

    public void setDao(ContratoConstrucaoRepositorio dao) {
        this.dao = dao;
    }

    public ContratoConstrucao getEntidade() {
        return entidade;
    }

    public void setEntidade(ContratoConstrucao entidade) {
        this.entidade = entidade;
    }

    public ContratoConstrucao getFiltro() {
        return filtro;
    }

    public void setFiltro(ContratoConstrucao filtro) {
        this.filtro = filtro;
    }

    public List<ContratoConstrucao> getListagem() {
        return listagem;
    }

    public void setListagem(List<ContratoConstrucao> listagem) {
        this.listagem = listagem;
    }
    
}
