/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.SistemaConstrutora.Presentation;


import br.edu.ifnmg.SistemaConstrutora.DomainModel.Fornecedor;
import br.edu.ifnmg.SistemaConstrutora.DomainModel.FornecedorRepositorio;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Junior
 */
@Named(value = "fornecedorController")
@SessionScoped
public class FornecedorController implements Serializable {

    Fornecedor entidade;
    Fornecedor filtro;
    List<Fornecedor> listagem;
    @EJB
    FornecedorRepositorio dao;


    /**
     * Creates a new instance of FuncionarioController
     */
    public FornecedorController() {
        entidade = new Fornecedor();
        filtro = new Fornecedor();
    }

    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }

    public void salvar() {
        dao.Salvar(entidade);
        listagem = null;
        exibirMensagem("Salvo com Sucesso!");

    }

    public String editar() {
        return "EditarFornecedor.xhtml";
    }

    public String criar() {
        entidade = new Fornecedor();
        return "EditarFornecedor.xhtml";
    }

    public String apagar() {
        dao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "FornecedorLista.xhtml";
    }

    public String filtrar() {
        listagem = dao.Buscar(filtro);
        return "FornecedorLista.xhtml";
    }

    public String voltar() {
        listagem = null;
        return "FornecedorLista.xhtml";
    }

    public Fornecedor getEntidade() {
        return entidade;
    }

    public void setEntidade(Fornecedor entidade) {
        this.entidade = entidade;
    }

    public Fornecedor getFiltro() {
        return filtro;
    }

    public void setFiltro(Fornecedor filtro) {
        this.filtro = filtro;
    }

    public List<Fornecedor> getListagem() {
       if (listagem == null) {
         Fornecedor filtro = new Fornecedor();
         listagem = dao.Buscar(null);
        }
        return listagem;
    }

    public void setListagem(List<Fornecedor> listagem) {
        this.listagem = listagem;
    }
    
    
}
