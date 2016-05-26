/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel.User;

import Model.User.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author adowt
 */
@ManagedBean(name = "user")
@RequestScoped
public class UsuarioManagedBean {

    private Usuario Usuario;
    
    /**
     * Creates a new instance of UserManagedBean
     */
    public UsuarioManagedBean() {
        
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }
    
}
