/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Cotacao;

import Model.DAO.BaseDAO;
import Model.User.Usuario;
import Model.User.UsuarioService;
import Util.AcaoJsonHelper;
import Util.Config;
import Util.ValidationResult;
import java.util.List;

/**
 *
 * @author adowt
 */
public class CotacaoService {

    private UsuarioService UsuarioService;
    private Usuario UsuarioLogado;
    private Config Configuracao;

    public CotacaoService(Usuario usuarioLogado, Config configuracao) {
        this.UsuarioLogado = usuarioLogado;
        this.Configuracao = configuracao;
    }

    //Checa se o usuário pode comprar a ação
    public ValidationResult validaCompraAcao(Acao acaoToAdd) {
        ValidationResult result = new ValidationResult("Panel");

        if(acaoToAdd.getQuantidade()<1)
            result.addError("Você precisa comprar pelo menos 01 ação(Quantidade)");
        
        if (this.UsuarioLogado.getPessoa().getConta().getCredito() <= this.getPrecoComTaxas(acaoToAdd.getSubtotal(), this.UsuarioLogado, this.Configuracao))
            result.addError("Você não possui crédito suficiente");
        
        return result;
    }

    //Faz a compra e registra no banco de dados
    public ValidationResult compraAcao(Acao acaoToAdd) {
        ValidationResult result = this.validaCompraAcao(acaoToAdd);

        if (result.isSucess()) {
            try {
                BaseDAO dao = new BaseDAO();

                dao.getEntityManager().find(Usuario.class, this.UsuarioLogado.getId());
                dao.getEntityManager().getTransaction().begin();
                Usuario usuario = dao.getEntityManager().merge(this.UsuarioLogado);

                //Adiciona a conta do usuario a acao
                acaoToAdd.setConta(usuario.getPessoa().getConta());
                //Adiciona a acao para o usuario(Se ele já tiver uma ação identica, só adiciona à quantidade)
                usuario.getPessoa().getConta().mergeAcao(acaoToAdd);
                //Desconta o preço da ação do crédito
                usuario.getPessoa().getConta().descontaCredito(this.getPrecoComTaxas(acaoToAdd.getSubtotal(), usuario, this.Configuracao));
                //Sobe o limite de corretagem.
                usuario.getPessoa().getConta().addCorretagemTotalPaga(this.Configuracao.getTaxaCorretagem());
                
                dao.getEntityManager().refresh(usuario);
                dao.getEntityManager().getTransaction().commit();
                dao.close();
            } catch (Exception ex) {
                result.addError("Não foi possível adicionar a ação à pessoa");
            }
        }

        return result;
    }
    
    //Valida se pode vender a ação
    public ValidationResult validaVendaAcao(Acao acaoUsuario, double precoAtualizadoUnico, int quantidade)
    {
        ValidationResult result = new ValidationResult("Panel");

        if(quantidade > acaoUsuario.getQuantidade())
            result.addError("Você não pode vender mais que a quantidade que você tem dessa ação " + acaoUsuario.getAcao() +"(R$"+acaoUsuario.getUlt_cotacao()+")");
        
        return result;
    }
    
    public ValidationResult vendeAcao(Acao acaoUsuario, double precoAtualizadoUnico, int quantidade){
        ValidationResult result = this.validaVendaAcao(acaoUsuario, precoAtualizadoUnico, quantidade);

        if (result.isSucess()) {
            try {
                BaseDAO dao = new BaseDAO();

                dao.getEntityManager().find(Usuario.class, this.UsuarioLogado.getId());
                dao.getEntityManager().getTransaction().begin();
                Usuario usuario = dao.getEntityManager().merge(this.UsuarioLogado);

                //Adiciona a acao para o usuario(Se ele já tiver uma ação identica, só adiciona à quantidade)
                usuario.getPessoa().getConta().removeAcao(acaoUsuario, quantidade);
                //Acrescenta o preço da ação ao crédito
                usuario.getPessoa().getConta().addCredito(this.getPrecoComTaxas(precoAtualizadoUnico*quantidade, usuario, this.Configuracao));
                //Sobe o limite de corretagem.
                usuario.getPessoa().getConta().addCorretagemTotalPaga(this.Configuracao.getTaxaCorretagem());
                
                dao.getEntityManager().refresh(usuario);
                dao.getEntityManager().getTransaction().commit();
                dao.close();
            } catch (Exception ex) {
                result.addError("Não foi possível executar a transação");
            }
        }

        return result;
    }

    //Pesquisa a cotacao passando um unico termo
    public List<Acao> pesquisaCotacaoAcaoUnica(String termo) {
        return new AcaoJsonHelper().getAcoesFromJson("http://cotacao.davesmartins.com.br/webCotacao/?cod=" + termo);
    }
    
    //Pesquisa a contacao passando uma lista convertida em termo
    public List<Acao> pesquisaAcaoWithList(List<Acao> acoes){
        String termo = "";
        for (Acao acao : acoes){
            termo += acao.getAcao() + ";";
        }
        return this.pesquisaCotacaoAcaoUnica(termo);
    }

    //Calcula as taxas da cotação
    public double getPrecoComTaxas(double precoCotacao, Usuario usuario, Config configuracao) {
        //Se o total de corretagem pago deste usuario for menor que o limite, é uma taxa fixa
        if (usuario.getPessoa().getConta().getCorretagemTotalPaga() < configuracao.getLimiteCorretagem()) {
            precoCotacao += configuracao.getTaxaCorretagem();
        } else //Senão, adiciona a nova taxa fixa, e mais a porcentagem 
        {
            precoCotacao += (configuracao.getTaxaFixaCorretagem()) + (configuracao.getPercentCorretagem() * precoCotacao);
        }

        //Adiciona os percentuais de negociação e liquidação
        precoCotacao += (configuracao.getPercentTaxaNegociacao() + configuracao.getPercentTaxaLiquidacao()) * precoCotacao;

        return precoCotacao;
    }

    
    //Getter Setter
    public UsuarioService getUsuarioService() {
        return UsuarioService;
    }

    public void setUsuarioService(UsuarioService UsuarioService) {
        this.UsuarioService = UsuarioService;
    }

}
