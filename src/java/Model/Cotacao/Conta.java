/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Cotacao;

import Model.User.Pessoa;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
    @Column(name = "CONTA_ID")
    private int Id;
    
    private double Credito, CorretagemTotalPaga;
    
//    @OneToOne(fetch = FetchType.LAZY)
//    private Pessoa Pessoa;
    
    @OneToMany(mappedBy = "Conta", cascade = CascadeType.PERSIST, targetEntity = Acao.class, fetch = FetchType.LAZY)
    private List<Acao> Acoes;

    public Conta() {
    }
    
    public void mergeAcao(Acao acaoToAdd) {
        boolean igual = false;
        //Para cada acao
        for (Acao acao : this.getAcoes()) {
            if(acao.getAcao().equals(acaoToAdd.getAcao()) &&
                    acao.getAber_cotacao() == acaoToAdd.getAber_cotacao() &&
                    acao.getMax_cotacao_dia() == acaoToAdd.getMax_cotacao_dia() &&
                    acao.getMed_cotacao_dia() == acaoToAdd.getMed_cotacao_dia() &&
                    acao.getMin_cotacao_dia() == acaoToAdd.getMin_cotacao_dia() &&
                    acao.getUlt_cotacao() == acaoToAdd.getUlt_cotacao() &&
                    acao.getVariacao() == acaoToAdd.getVariacao()){
                //Se tiver uma idêntica só aumenta a quantidade
                igual = true;
                acao.setQuantidade(acao.getQuantidade()+1);
            }
        }
        
        //Senão, adiciona a nova ação não idêntica(pode ser a mesma mas com valores diferentes)
        if(!igual)
            this.getAcoes().add(acaoToAdd);
    }
    
    public boolean removeAcao(Acao acaoToRemove, int quantidade) {
        //Para cada acao
        Iterator<Acao> itr = this.getAcoes().iterator();
        while(itr.hasNext()){
            Acao acaoRemov = itr.next();
            if(acaoRemov.getAcao().equals(acaoToRemove.getAcao()) &&
                    acaoRemov.getAber_cotacao() == acaoToRemove.getAber_cotacao() &&
                    acaoRemov.getMax_cotacao_dia() == acaoToRemove.getMax_cotacao_dia() &&
                    acaoRemov.getMed_cotacao_dia() == acaoToRemove.getMed_cotacao_dia() &&
                    acaoRemov.getMin_cotacao_dia() == acaoToRemove.getMin_cotacao_dia() &&
                    acaoRemov.getUlt_cotacao() == acaoToRemove.getUlt_cotacao() &&
                    acaoRemov.getVariacao() == acaoToRemove.getVariacao())
            {
            
                if((acaoToRemove.getQuantidade()-quantidade)<=0)
                {
                    itr.remove();
                    return true;
                }
                else
                {
                    itr.next().setQuantidade(acaoRemov.getQuantidade()-quantidade);
                    return true;
                }
                    
            }
        }
        return false;
    }
    
    //Pega a quantidade de ações de um usuário.
    //Nem sempre a quantidade da lista é a quantidade de ações
    //pois ações não idênticas são adicionadas separadamente
    //Então é preciso iterar
    public int quantAcoes(){
        int quant = 0;
        for (Acao acao : this.getAcoes()) {
            quant += acao.getQuantidade();
        }
        return quant;
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

    public void setCredito(double Credito) {
        this.Credito = Credito;
    }
    
    public void descontaCredito(double gasto) {
        this.Credito -= gasto;
    }
    
    public void addCredito(double credito) {
        this.Credito += credito;
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
