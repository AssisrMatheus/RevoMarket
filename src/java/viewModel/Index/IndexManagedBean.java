/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel.Index;

import Model.Cotacao.CotacaoService;
import Model.User.Usuario;
import Model.User.UsuarioService;
import Util.ValidationResult;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import viewModel.Shared.SystemManagedBean;

/**
 *
 * @author adowt
 */
@ManagedBean(name = "index")
@RequestScoped
public class IndexManagedBean {

    @ManagedProperty(value = "#{systemMB}")
    private SystemManagedBean systemMB;

    private Usuario Usuario = new Usuario();

    private long QuantidadeUsuarios;

    /**
     * Creates a new instance of IndexManagedBean
     */
    public IndexManagedBean() {
        UsuarioService usuarioService = new UsuarioService();
        QuantidadeUsuarios = usuarioService.getTotalQuantUsuarios();

//        if (systemMB.getUsuarioLogado() != null) {
//            String url = "pages/user/panel";
//            FacesContext fc = FacesContext.getCurrentInstance();
//            ExternalContext ec = fc.getExternalContext();
//            try {
//                ec.redirect(url);
//            } catch (IOException ex) {
//                //Deu ruim
//            }
//        }
    }

    public String pageCadastrarUsuario() {
        return "pages/user/cadastrar";
    }

    public String pageUsuarioLogado() {
        return "pages/user/panel";
    }

    public String actionLoginUsuario() {
        UsuarioService usuarioService = new UsuarioService();

        //Verifica se o login é válido
        ValidationResult result = usuarioService.loginValido(this.Usuario);

        if (result.isSucess()) {
            systemMB.setUsuarioLogado(usuarioService.getUltimoUsuario());
            return pageUsuarioLogado();
        }

        systemMB.mergeValidacao(result);

        return "/index";
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.Usuario = usuario;
    }

    public long getQuantidadeUsuarios() {
        return QuantidadeUsuarios;
    }

    public void setQuantidadeUsuarios(int QuantidadeUsuarios) {
        this.QuantidadeUsuarios = QuantidadeUsuarios;
    }

    public SystemManagedBean getSystemMB() {
        return systemMB;
    }

    public void setSystemMB(SystemManagedBean systemMB) {
        this.systemMB = systemMB;
    }

}
