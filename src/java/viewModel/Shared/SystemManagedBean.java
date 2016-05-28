/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel.Shared;

import Model.DAO.BaseDAO;
import Model.User.Usuario;
import Model.User.UsuarioService;
import Util.Config;
import Util.ConfigService;
import Util.ErrorMessage;
import Util.ValidationResult;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author adowt
 */
@ManagedBean(name = "systemMB")
@SessionScoped
public class SystemManagedBean {
    
    private List<ValidationResult> Validacao;
    private Usuario UsuarioLogado;
    private Config Configuracao;    

    /**
     * Creates a new instance of SystemManagedBean
     */
    public SystemManagedBean() {
        Configuracao = new ConfigService().prepareConfig();
    }

    public void updateUsuario()
    {
        this.UsuarioLogado = new UsuarioService().getUpdateUsuario(UsuarioLogado);
    }
    
    public List<ValidationResult> getValidacao() {
        if(this.Validacao == null){
            Validacao = new ArrayList<>();
        }
        return Validacao;
    }

    public void setValidacao(List<ValidationResult> validacao) {
        for (ValidationResult validationResult : validacao) {
            this.mergeValidacao(validationResult);
        }
    }
    
    public void mergeValidacao(ValidationResult valResult){
        //Para cada result na validacao
        boolean temIgual = false;
        for (ValidationResult result : getValidacao()) {
            //Para os contextos iguais
            if(result.getContext().equals(valResult.getContext())){
                temIgual = true;
                for (ErrorMessage erro : valResult.getErrorMessages()) {
                    //Adiciona o erro
                    result.addError(erro);
                }
            }
        }
        
        //Se não achou nenhum igual
        if(!temIgual){
            Validacao.add(valResult);
        }
    }

    public Config getConfiguracao() {
        return Configuracao;
    }

    public void setConfiguracao(Config Configuracao) {
        this.Configuracao = Configuracao;
    }

    public Usuario getUsuarioLogado() {
        return UsuarioLogado;
    }

    public void setUsuarioLogado(Usuario UsuarioLogado) {
        this.UsuarioLogado = UsuarioLogado;
    }
}
