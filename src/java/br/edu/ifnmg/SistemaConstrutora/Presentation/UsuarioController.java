/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.SistemaConstrutora.Presentation;


import br.edu.ifnmg.SistemaConstrutora.DomainModel.Usuario;
import br.edu.ifnmg.SistemaConstrutora.DomainModel.UsuariolRepositorio;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author emerson
 */
@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable{

    /**
     * Creates a new instance of UsuarioController
     */
    @EJB
    UsuariolRepositorio dao;
    Usuario entidade;
    Usuario filtro;
    List<Usuario> listagem;
    
    public UsuarioController() {
        entidade = new Usuario();
        filtro = new Usuario();
       
    }
    
    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Aviso",msg));
    }
    
    public void salvar(){
        dao.Salvar(entidade);
        listagem = null;
        exibirMensagem("Salvo com sucesso!");
        entidade = new Usuario();
    }
    
    public String editar(){
        return "EditarUsuario.xhtml";
    }
    
    public String criar(){
        entidade = new Usuario();
        return "EditarUsuario.xhtml";
    }
    
    public String apagar(){
        dao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "UsuarioLista.xhtml";
    }
    
    public String filtrar() {
        listagem = dao.Buscar(filtro);
        return "UsuarioLista.xhtml";
    }

    public String voltar(){
        return "UsuarioLista.xhtml";
    }
    
    public UsuariolRepositorio getDao() {
        return dao;
    }

    public void setDao(UsuariolRepositorio dao) {
        this.dao = dao;
    }

    public Usuario getEntidade() {
        return entidade;
    }

    public void setEntidade(Usuario entidade) {
        this.entidade = entidade;
    }

    public Usuario getFiltro() {
        return filtro;
    }

    public void setFiltro(Usuario filtro) {
        this.filtro = filtro;
    }

    public List<Usuario> getListagem() {
        return listagem;
    }

    public void setListagem(List<Usuario> listagem) {
        this.listagem = listagem;
    }
    
    
}
