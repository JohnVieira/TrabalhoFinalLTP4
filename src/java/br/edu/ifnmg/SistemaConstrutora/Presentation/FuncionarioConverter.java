/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.SistemaConstrutora.Presentation;

import br.edu.ifnmg.SistemaConstrutora.DomainModel.Funcionario;
import br.edu.ifnmg.SistemaConstrutora.DomainModel.FuncionarioRepositorio;
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
@Named(value = "funcionarioConverter")
@SessionScoped
public class FuncionarioConverter implements Serializable, Converter {
    
    @EJB
    FuncionarioRepositorio daoFuncionario;

    /**
     * Creates a new instance of FuncionarioConverter
     */
    public FuncionarioConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().equals("")) {
            return null;
        } else {
            Long id = Long.parseLong(value);
            return daoFuncionario.Abrir(id);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
      if (value == null || value.toString().equals("")){
          return "";
      } else{
          Funcionario f = (Funcionario)value;
          return f.getId().toString();
      } 
    }
    
}
