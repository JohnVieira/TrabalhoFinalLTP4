/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.SistemaConstrutora.Presentation;

import br.edu.ifnmg.SistemaConstrutora.DomainModel.Funcionario;
import br.edu.ifnmg.SistemaConstrutora.DomainModel.FuncionarioRepositorio;
import br.edu.ifnmg.SistemaConstrutora.DomainModel.TipoFuncionario;
import br.edu.ifnmg.SistemaConstrutora.DomainModel.TipoFuncionarioRepositorio;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

/**
 *
 * @author Junior
 */
@Named(value = "funcionarioController")
@SessionScoped
public class FuncionarioController implements Serializable {

    Funcionario entidade;
    Funcionario filtro;
    List<Funcionario> listagem;
    List<TipoFuncionario> listagemTipos;
    @EJB
    FuncionarioRepositorio dao;
    @EJB
    TipoFuncionarioRepositorio daoTipo;

    /**
     * Creates a new instance of FuncionarioController
     */
    public FuncionarioController() {
        entidade = new Funcionario();
        filtro = new Funcionario();
    }
    public List<Funcionario> autoCompletar(String tmp){
        Funcionario f = new Funcionario();
        f.setNome(tmp);
        return dao.Buscar(f);
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
        return "EditarFuncionario.xhtml";
    }

    public String criar() {
        entidade = new Funcionario();
        return "EditarFuncionario.xhtml";
    }

    public String apagar() {
        dao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "FuncionarioLista.xhtml";
    }

    public String filtrar() {
        listagem = dao.Buscar(filtro);
        return "FuncionarioLista.xhtml";
    }

    public String voltar() {
        listagem = null;
        return "FuncionarioLista.xhtml";
    }

    public Funcionario getEntidade() {
        return entidade;
    }

    public void setEntidade(Funcionario entidade) {
        this.entidade = entidade;
    }
    
    public Funcionario getFiltro() {
        return filtro;
    }

    public void setFiltro(Funcionario filtro) {
        this.filtro = filtro;
    }

    public List<Funcionario> getListagem() {
        if (listagem == null) {
            Funcionario filtro = new Funcionario();
            listagem = dao.Buscar(null);
        }
        return listagem;
    }

    public void setListagem(List<Funcionario> listagem) {
        this.listagem = listagem;
    }

    public List<TipoFuncionario> getListagemTipos() {
        if (listagemTipos == null) {
            listagemTipos = daoTipo.Buscar(null);
        }
        return listagemTipos;
    }

    public void setListagemTipos(List<TipoFuncionario> listagemTipos) {

        this.listagemTipos = listagemTipos;
    }
}

