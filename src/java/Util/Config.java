/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author adowt
 */
@Entity
public class Config {
    @Id
    @GeneratedValue
    private int Id;
    
    private double InvestimentoMinimo, TaxaCorretagem, LimiteCorretagem, PercentCorretagem, TaxaFixaCorretagem;

    public Config(double InvestimentoMinimo, double TaxaCorretagem, double LimiteCorretagem, double PercentCorretagem, double TaxaFixaCorretagem) {
        this.InvestimentoMinimo = InvestimentoMinimo;
        this.TaxaCorretagem = TaxaCorretagem;
        this.LimiteCorretagem = LimiteCorretagem;
        this.PercentCorretagem = PercentCorretagem;
        this.TaxaFixaCorretagem = TaxaFixaCorretagem;
    }

    public int getId() {
        return Id;
    }

    public double getInvestimentoMinimo() {
        return InvestimentoMinimo;
    }

    public void setInvestimentoMinimo(double InvestimentoMinimo) {
        this.InvestimentoMinimo = InvestimentoMinimo;
    }

    public double getTaxaCorretagem() {
        return TaxaCorretagem;
    }

    public void setTaxaCorretagem(double TaxaCorretagem) {
        this.TaxaCorretagem = TaxaCorretagem;
    }

    public double getLimiteCorretagem() {
        return LimiteCorretagem;
    }

    public void setLimiteCorretagem(double LimiteCorretagem) {
        this.LimiteCorretagem = LimiteCorretagem;
    }

    public double getPercentCorretagem() {
        return PercentCorretagem;
    }

    public void setPercentCorretagem(double PercentCorretagem) {
        this.PercentCorretagem = PercentCorretagem;
    }

    public double getTaxaFixaCorretagem() {
        return TaxaFixaCorretagem;
    }

    public void setTaxaFixaCorretagem(double TaxaFixaCorretagem) {
        this.TaxaFixaCorretagem = TaxaFixaCorretagem;
    }
    
    
}
