/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Cotacao;

import Model.User.Pessoa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import oracle.jrockit.jfr.tools.ConCatRepository;

/**
 *
 * @author adowt
 */
@Entity
public class Conta {
    @Id
    @GeneratedValue
    private int Id;
    
    private double Credito, CorretagemTotalPaga;
    
//    @OneToOne(fetch = FetchType.LAZY)
//    private Pessoa Pessoa;
    
    @OneToMany(mappedBy = "Conta")
    private List<Acao> Acoes;

    public Conta() {
    }
    
    public Conta(double Credito) {
        this.Credito = Credito;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public double getCredito() {
        return Credito;
    }

    public void descontaCredito(double gasto) {
        this.Credito -= gasto;
    }

    public List<Acao> getAcoes() {
        if(this.Acoes == null)
            this.Acoes = new ArrayList<>();
        
        return Acoes;
    }

    public void setAcoes(List<Acao> Acoes) {
        this.Acoes = Acoes;
    }
    
    

//    public Pessoa getPessoa() {
//        return Pessoa;
//    }
//
//    public void setPessoa(Pessoa Pessoa) {
//        this.Pessoa = Pessoa;
//    }

    public double getCorretagemTotalPaga() {
        return CorretagemTotalPaga;
    }

    public void addCorretagemTotalPaga(double precoCorretagem) {
        this.CorretagemTotalPaga += precoCorretagem;
    }
    
    
}
