/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel.User;

import Model.User.Pessoa;
import Model.User.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author adowt
 */
@ManagedBean(name = "cadastrar")
@RequestScoped
public class CadastrarManagedBean {
    
    private Pessoa Usuario;

    /**
     * Creates a new instance of CadastrarManagedBean
     */
    public CadastrarManagedBean() {
        Usuario = new Pessoa();
    }

    public Pessoa getUsuario() {
        return Usuario;
    }

    public void setUsuario(Pessoa Usuario) {
        this.Usuario = Usuario;
    }

    
}
