/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Cotacao;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author adowt
 */
@Entity
public class Acao {
    @Id
    @GeneratedValue
    private int Id;
    
    @ManyToOne
    @JoinColumn(name = "Acao_Id")
    private Conta Conta;
    
    private String acao;
    private double aber_cotacao, max_cotacao_dia, min_cotacao_dia, med_cotacao_dia, ult_cotacao, variacao;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Conta getConta() {
        return Conta;
    }

    public void setConta(Conta Conta) {
        this.Conta = Conta;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public double getAber_cotacao() {
        return aber_cotacao;
    }

    public void setAber_cotacao(double aber_cotacao) {
        this.aber_cotacao = aber_cotacao;
    }

    public double getMax_cotacao_dia() {
        return max_cotacao_dia;
    }

    public void setMax_cotacao_dia(double max_cotacao_dia) {
        this.max_cotacao_dia = max_cotacao_dia;
    }

    public double getMin_cotacao_dia() {
        return min_cotacao_dia;
    }

    public void setMin_cotacao_dia(double min_cotacao_dia) {
        this.min_cotacao_dia = min_cotacao_dia;
    }

    public double getMed_cotacao_dia() {
        return med_cotacao_dia;
    }

    public void setMed_cotacao_dia(double med_cotacao_dia) {
        this.med_cotacao_dia = med_cotacao_dia;
    }

    public double getUlt_cotacao() {
        return ult_cotacao;
    }

    public void setUlt_cotacao(double ult_cotacao) {
        this.ult_cotacao = ult_cotacao;
    }

    public double getVariacao() {
        return variacao;
    }

    public void setVariacao(double variacao) {
        this.variacao = variacao;
    }

    
    
}
