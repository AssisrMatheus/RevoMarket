/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author adowt
 */
@Entity
public class Usuario {
    @Id
    @GeneratedValue
    private int Id;
    
    private String Login, Senha;
    
    private boolean IsAdmin;
    
    @OneToOne
    private Pessoa Pessoa;

    public Usuario() {
        this.Pessoa = new Pessoa();
    }

    public Usuario(int Id, String Login, String Senha, boolean IsAdmin, Pessoa Pessoa) {
        this.Id = Id;
        this.Login = Login;
        this.Senha = Senha;
        this.IsAdmin = IsAdmin;
        this.Pessoa = Pessoa;
    }
    
    public Usuario(String Login, String Senha, boolean IsAdmin, Pessoa Pessoa) {
        this.Login = Login;
        this.Senha = Senha;
        this.IsAdmin = IsAdmin;
        this.Pessoa = Pessoa;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    public boolean isIsAdmin() {
        return IsAdmin;
    }

    public void setIsAdmin(boolean IsAdmin) {
        this.IsAdmin = IsAdmin;
    }

    public Pessoa getPessoa() {
        return Pessoa;
    }

    public void setPessoa(Pessoa Pessoa) {
        this.Pessoa = Pessoa;
    }

    
    
}
