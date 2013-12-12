/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.SistemaConstrutora.Presentation;

import br.edu.ifnmg.SistemaConstrutora.DomainModel.Fornecedor;
import br.edu.ifnmg.SistemaConstrutora.DomainModel.FornecedorRepositorio;
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
@Named(value = "fornecedorConverter")
@SessionScoped
public class FornecedorConverter implements Serializable, Converter {
    
    @EJB
    FornecedorRepositorio daoFornecedor;

    public FornecedorConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().equals("")) {
            return null;
        } else {
            Long id = Long.parseLong(value);
            return daoFornecedor.Abrir(id);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
      if (value == null || value.toString().equals("")){
          return "";
      } else{
          Fornecedor f = (Fornecedor)value;
          return f.getId().toString();
      } 
    }
    
}
