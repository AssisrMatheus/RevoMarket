/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.User;

/**
 *
 * @author adowt
 */
public class Pessoa extends Usuario {
    private String Nome, Profissao, Endereco, Cidade, Estado;
    private int Cpf, Rg;

    public Pessoa(String Nome, String Profissao, String Endereco, String Cidade, String Estado, int Cpf, int Rg) {
        this.Nome = Nome;
        this.Profissao = Profissao;
        this.Endereco = Endereco;
        this.Cidade = Cidade;
        this.Estado = Estado;
        this.Cpf = Cpf;
        this.Rg = Rg;
    }

    public Pessoa() {
    }
    
    

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getProfissao() {
        return Profissao;
    }

    public void setProfissao(String Profissao) {
        this.Profissao = Profissao;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public int getCpf() {
        return Cpf;
    }

    public void setCpf(int Cpf) {
        this.Cpf = Cpf;
    }

    public int getRg() {
        return Rg;
    }

    public void setRg(int Rg) {
        this.Rg = Rg;
    }

    
    
    
    
}
