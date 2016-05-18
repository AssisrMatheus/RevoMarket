/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel.Index;

import Model.User.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author adowt
 */
@ManagedBean(name = "index")
@RequestScoped
public class IndexManagedBean {

    private Usuario Usuario = new Usuario();
    
    /**
     * Creates a new instance of IndexManagedBean
     */
    public IndexManagedBean() {
    }
    
    public String pageCadastrarUsuario(){
        return "pages/user/cadastrar";
    }
    
    public String actionLoginUsuario(){
        if(this.getUsuario().getLogin().equals("")){
            
        }
        
        
        return "pages/user/cadastrar";
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.Usuario = usuario;
    }
    
}
