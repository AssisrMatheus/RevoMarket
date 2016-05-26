/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel.User;

import Model.Cotacao.Acao;
import Model.Cotacao.CotacaoService;
import Util.ValidationResult;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import viewModel.Shared.SystemManagedBean;

/**
 *
 * @author adowt
 */
@ManagedBean(name = "panel")
@SessionScoped
public class PanelManagedBean {
    
    @ManagedProperty(value="#{systemMB}")
    private SystemManagedBean systemMB;

    private Acao AcaoPesquisada;
    private List<Acao> ResultadoPesquisa;

    private CotacaoService CotacaoService;
    
    /**
     * Creates a new instance of PanelManagedBean
     */
    public PanelManagedBean() {
        CotacaoService = new CotacaoService();
        AcaoPesquisada = new Acao();
    }
    
    public String actionPesquisaAcao(String termoAcao){
        ResultadoPesquisa = CotacaoService.pesquisaCotacaoAcao(termoAcao);
        return "panel";
    }
    
    public String compraAcao(Acao acao){
        ValidationResult result = this.CotacaoService.compraAcao(acao, systemMB.getUsuarioLogado(), systemMB.getConfiguracao());
        systemMB.mergeValidacao(result);
        return "panel";
    }

    public Acao getAcaoPesquisada() {
        return AcaoPesquisada;
    }

    public void setAcaoPesquisada(Acao AcaoPesquisada) {
        this.AcaoPesquisada = AcaoPesquisada;
    }

    public List<Acao> getResultadoPesquisa() {
        return ResultadoPesquisa;
    }

    public void setResultadoPesquisa(List<Acao> ResultadoPesquisa) {
        this.ResultadoPesquisa = ResultadoPesquisa;
    }

    public SystemManagedBean getSystemMB() {
        return systemMB;
    }

    public void setSystemMB(SystemManagedBean systemMB) {
        this.systemMB = systemMB;
    }

    public CotacaoService getCotacaoService() {
        return CotacaoService;
    }

    public void setCotacaoService(CotacaoService CotacaoService) {
        this.CotacaoService = CotacaoService;
    }
    
    public String tableColor(double numero){
        return (numero<0) ? "negative" : "positive";
    }
    
}
