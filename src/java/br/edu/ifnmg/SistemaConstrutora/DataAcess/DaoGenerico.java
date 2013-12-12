/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.SistemaConstrutora.DataAcess;

import br.edu.ifnmg.SistemaConstrutora.DomainModel.Repositorio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Junior
 */
public abstract class DaoGenerico<T> implements Repositorio<T> {
    @PersistenceContext(name="SistemaConstrutoraPU")
    protected EntityManager manager;
    private Class tipo;
    public DaoGenerico (Class t) {
        tipo = t;
    }
    
   

 
    @Override
    public boolean Salvar(T obj) {
        try{
            //salva o objeto
            manager.merge(obj);
            return true;
        }catch (Exception ex){
        System.out.println(ex.getMessage());
        return false;
        }
    }
    
          
    
    @Override
    public T Abrir(Long id) {
        try {
            T obj = (T) manager.find(tipo, id);
            return obj;
            //abrir
        } catch (Exception ex) {
            return null;
        }
    }
    
    
    @Override
      public abstract List<T> Buscar(T obj);

    @Override
    public boolean Apagar(T obj) {
        try {
            manager.remove(manager.merge(obj));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
}
