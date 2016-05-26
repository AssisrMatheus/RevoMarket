/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel.User;

import Model.User.Pessoa;
import Model.User.Usuario;
import Model.User.UsuarioService;
import Util.ValidationResult;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import viewModel.Shared.SystemManagedBean;

/**
 *
 * @author adowt
 */
@ManagedBean(name = "cadastroUsuario")
@RequestScoped
public class CadastroUsuarioManagedBean {
    
    @ManagedProperty(value="#{systemMB}")
    private SystemManagedBean systemMB;
    
    private Usuario Usuario;

    /**
     * Creates a new instance of CadastroManagedBean
     */
    public CadastroUsuarioManagedBean() {
         Usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }
    
    public String actionCadastrarUsuario(){
        UsuarioService usuarioService = new UsuarioService();
        
        //Pega o resultado da validacao
        ValidationResult result = usuarioService.cadastraUsuario(this.getUsuario());
        
        //Se foi validado com sucesso
        if(result.isSucess()){
            return "/index";
        }
        else
        {
            //Senao, adiciona os erros a lista de erros
            getSystemMB().mergeValidacao(result);
            this.Usuario = new Usuario();
            return "pages/user/cadastrar";
        }
    }

    public SystemManagedBean getSystemMB() {
        return systemMB;
    }

    public void setSystemMB(SystemManagedBean systemMB) {
        this.systemMB = systemMB;
    }
    
    
    
}