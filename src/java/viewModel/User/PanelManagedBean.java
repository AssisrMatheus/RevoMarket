/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel.User;

import Model.Cotacao.Acao;
import Model.Cotacao.CotacaoService;
import Util.AcaoJsonHelper;
import Util.ValidationResult;
import java.util.List;
import javax.annotation.PostConstruct;
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
    private List<Acao> ResultadoPesquisa, AcoesUsuarioAtualizadas;

    private CotacaoService CotacaoService;
    
    /**
     * Creates a new instance of PanelManagedBean
     */
    public PanelManagedBean() {
    }
    
    @PostConstruct
    public void PreparePanel(){
        CotacaoService = new CotacaoService(systemMB.getUsuarioLogado(), systemMB.getConfiguracao());
        AcaoPesquisada = new Acao();
        this.updateAcoesUsuarioList();
    }
    
    //Ação do clique no botão de pesquisar ação
    public String actionPesquisaAcao(String termoAcao){
        ResultadoPesquisa = CotacaoService.pesquisaCotacaoAcaoUnica(termoAcao.toUpperCase());
        return "panel";
    }
    
    //Ação do botão de compra de ação no resultado da pesquisa
    public String compraAcao(Acao acao){
        ValidationResult result = this.CotacaoService.compraAcao(acao);
        systemMB.mergeValidacao(result);
        systemMB.updateUsuario();
        
//        this.AcaoPesquisada=null;
//        this.ResultadoPesquisa=null;
        
        return "panel";
    }
    
    //Ação do botão de compra de ação no resultado da pesquisa
    public String vendeAcao(Acao acao, double precoAtualizadoUnico, int quantidade){
        ValidationResult result = this.CotacaoService.vendeAcao(acao, precoAtualizadoUnico, quantidade);
        systemMB.mergeValidacao(result);
        systemMB.updateUsuario();
        
        return "panel";
    }
    
    //Pega o preço da ação com as taxas aplicadas. Baseadas no usuário e na configuração do sistema.
    public double getPrecoAcao(double cotacao){
        return this.CotacaoService.getPrecoComTaxas(cotacao, systemMB.getUsuarioLogado(), systemMB.getConfiguracao());
    }
    
    //Pega uma ação atualizada(Usada na tabela de mostrar ações para comparar com ações do usuário)
    public Acao getAcaoOnline(String termoAcao){
        if(this.AcoesUsuarioAtualizadas == null)
            this.updateAcoesUsuarioList();
        
        //Pega das acoes atualizadas do usuario, a acao encontrada com nome passado
        for (Acao acao : this.AcoesUsuarioAtualizadas) {
            if(acao.getAcao().toUpperCase().equals(termoAcao.toUpperCase())){
                return acao;
            }
        }
        
        return null;
    }
    
    //Atualiza a lista de ações do usuário com os valores novos(e não ao serem compradas)
    //e guarda na lista
    public List<Acao> updateAcoesUsuarioList(){
        //Pega a lista de acoes de um usuario, e busca as informações mais recentes de todas as ações
        //Indo somente uma vez no web service
        this.AcoesUsuarioAtualizadas = this.systemMB.getUsuarioLogado().getPessoa().getConta().getAcoes();
        List<Acao> acoes = this.CotacaoService.pesquisaAcaoWithList(this.AcoesUsuarioAtualizadas);
        this.AcoesUsuarioAtualizadas = acoes;
        return acoes;
    }
    
    //Retorna positivo ou negativo para pintar de verde/vermelho as colunas das tabelas e ícones
    public String tableColor(boolean cond){
        return (cond) ? "positive" : "negative";
    }
    
    //Método para encurtar a comparação de ações direto no html para retornar visualmente
    //se as ações estão mais caras/baratas usando o visual
    public boolean compareAcaoComTaxas(Acao acaoUm, Acao acaoDois){
        return (this.getPrecoAcao(acaoUm.getSubtotal())
                >
                this.getPrecoAcao(acaoDois.getUlt_cotacao()*acaoUm.getQuantidade()));
    }

    //Calcula o saldo total baseado no preço atualizado das ações do usuário logado
    //e descontando as taxas que ele teria que pagar na compra
    public double calculaSaldoTotal(){
        double saldo= this.systemMB.getUsuarioLogado().getPessoa().getConta().getCredito();
        //Para cada acao
        for (Acao acao : this.getAcoesUsuarioAtualizadas()) {
            //Saldo = (preco*quantitade) - ((1*taxas)-1) esse -1 normaliza
            saldo += acao.getSubtotal() - (this.getPrecoAcao(1)-1);
        }
        return saldo;
    }
    
    //Getters e setters
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

    public List<Acao> getAcoesUsuarioAtualizadas() {
        return AcoesUsuarioAtualizadas;
    }

    public void setAcoesUsuarioAtualizadas(List<Acao> AcoesUsuarioAtualizadas) {
        this.AcoesUsuarioAtualizadas = AcoesUsuarioAtualizadas;
    }
    
    
}
