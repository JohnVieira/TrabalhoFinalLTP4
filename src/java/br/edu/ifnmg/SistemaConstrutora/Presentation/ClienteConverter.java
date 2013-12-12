/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.SistemaConstrutora.Presentation;


import br.edu.ifnmg.SistemaConstrutora.DomainModel.Cliente;
import br.edu.ifnmg.SistemaConstrutora.DomainModel.ClienteRepositorio;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author emerson
 */
@Named(value = "clienteConverter")
@SessionScoped
public class ClienteConverter implements Serializable, Converter{

    /**
     * Creates a new instance of ClienteConverter
     */
    @EJB
    ClienteRepositorio dao;
    
    public ClienteConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().equals("")) {
            return null;
        } else {
            Long id = Long.parseLong(value);
            return dao.Abrir(id);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.toString().equals("")){
            return "";
        } else{
            Cliente c = (Cliente)value;
            return c.getId().toString();
        } 
    }
    
}
