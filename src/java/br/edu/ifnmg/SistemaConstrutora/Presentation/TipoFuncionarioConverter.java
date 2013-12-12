/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.SistemaConstrutora.Presentation;

import br.edu.ifnmg.SistemaConstrutora.DomainModel.TipoFuncionario;
import br.edu.ifnmg.SistemaConstrutora.DomainModel.TipoFuncionarioRepositorio;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author Junior
 */
@Named(value = "tipoFuncionarioConverter")
@SessionScoped
public class TipoFuncionarioConverter implements Serializable, Converter {

    @EJB
    TipoFuncionarioRepositorio dao;

    public TipoFuncionarioConverter() {
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
        if (value == null || value.toString().equals("")) {
            return "";
        } else {
            TipoFuncionario tf = (TipoFuncionario) value;
            return tf.getId().toString();
        }
    }
    
}
