/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.SistemaConstrutora.Presentation;

import br.edu.ifnmg.SistemaConstrutora.DomainModel.FormaDePagamento;
import br.edu.ifnmg.SistemaConstrutora.DomainModel.FormaDePagamentoRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Anderson
 */
@Named(value = "formaDePagementoConverter")
@SessionScoped
public class FormaDePagementoConverter implements Serializable,Converter {

    /**
     * Creates a new instance of FormaDePagementoConverter
     */
    public FormaDePagementoConverter() {
    }
     @EJB
     FormaDePagamentoRepositorio dao;
    
    

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
            FormaDePagamento c = (FormaDePagamento)value;
            return c.getId().toString();
        } 
    }
}
