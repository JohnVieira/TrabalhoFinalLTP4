/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.SistemaConstrutora.Presentation;
;
import br.edu.ifnmg.SistemaConstrutora.DomainModel.Predio;
import br.edu.ifnmg.SistemaConstrutora.DomainModel.PredioRepositorio;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author root
 */
@Named(value = "predioConverter")
@SessionScoped
public class PredioConverter implements Serializable, Converter{

    /**
     * Creates a new instance of ClienteConverter
     */
    @EJB
    PredioRepositorio dao;
    
    public PredioConverter() {
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
            Predio ap = (Predio)value;
            return ap.getId().toString();
        } 
    }
    
    
}
