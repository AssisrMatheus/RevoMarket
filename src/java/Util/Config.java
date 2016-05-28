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
    
    private double InvestimentoMinimo, TaxaCorretagem, LimiteCorretagem, TaxaFixaCorretagem, PercentCorretagem, PercentTaxaNegociacao, PercentTaxaLiquidacao;
    private String cotacaoServiceUrl;

    public Config() {
    }

    public Config(double InvestimentoMinimo, double TaxaCorretagem, double LimiteCorretagem, double TaxaFixaCorretagem, double PercentCorretagem, double PercentTaxaNegociacao, double PercentTaxaLiquidacao) {
        this.InvestimentoMinimo = InvestimentoMinimo;
        this.TaxaCorretagem = TaxaCorretagem;
        this.LimiteCorretagem = LimiteCorretagem;
        this.TaxaFixaCorretagem = TaxaFixaCorretagem;
        this.PercentCorretagem = PercentCorretagem;
        this.PercentTaxaNegociacao = PercentTaxaNegociacao;
        this.PercentTaxaLiquidacao = PercentTaxaLiquidacao;
    }

    public Config(double InvestimentoMinimo, double TaxaCorretagem, double LimiteCorretagem, double TaxaFixaCorretagem, double PercentCorretagem, double PercentTaxaNegociacao, double PercentTaxaLiquidacao, String cotacaoServiceUrl) {
        this.InvestimentoMinimo = InvestimentoMinimo;
        this.TaxaCorretagem = TaxaCorretagem;
        this.LimiteCorretagem = LimiteCorretagem;
        this.TaxaFixaCorretagem = TaxaFixaCorretagem;
        this.PercentCorretagem = PercentCorretagem;
        this.PercentTaxaNegociacao = PercentTaxaNegociacao;
        this.PercentTaxaLiquidacao = PercentTaxaLiquidacao;
        this.cotacaoServiceUrl = cotacaoServiceUrl;
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

    public double getTaxaFixaCorretagem() {
        return TaxaFixaCorretagem;
    }

    public void setTaxaFixaCorretagem(double TaxaFixaCorretagem) {
        this.TaxaFixaCorretagem = TaxaFixaCorretagem;
    }

    public double getPercentCorretagem() {
        return PercentCorretagem;
    }

    public void setPercentCorretagem(double PercentCorretagem) {
        this.PercentCorretagem = PercentCorretagem;
    }

    public double getPercentTaxaNegociacao() {
        return PercentTaxaNegociacao;
    }

    public void setPercentTaxaNegociacao(double PercentTaxaNegociacao) {
        this.PercentTaxaNegociacao = PercentTaxaNegociacao;
    }

    public double getPercentTaxaLiquidacao() {
        return PercentTaxaLiquidacao;
    }

    public void setPercentTaxaLiquidacao(double PercentTaxaLiquidacao) {
        this.PercentTaxaLiquidacao = PercentTaxaLiquidacao;
    }

    public String getCotacaoServiceUrl() {
        return cotacaoServiceUrl;
    }

    public void setCotacaoServiceUrl(String cotacaoServiceUrl) {
        this.cotacaoServiceUrl = cotacaoServiceUrl;
    }

    
    
}
