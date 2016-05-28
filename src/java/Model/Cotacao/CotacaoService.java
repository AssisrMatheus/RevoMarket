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

    public ValidationResult validaCompraAcao(Acao acaoToAdd, Usuario usuario, Config configuracao) {
        ValidationResult result = new ValidationResult("Panel");

        if(acaoToAdd.getQuantidade()<1)
            result.addError("Você precisa comprar pelo menos 01 ação(Quantidade)");
        
        if (usuario.getPessoa().getConta().getCredito() <= this.getPrecoComTaxas(acaoToAdd.getSubtotal(), usuario, configuracao))
            result.addError("Você não possui crédito suficiente");
        
        return result;
    }

    public ValidationResult compraAcao(Acao acaoToAdd, Usuario usuario, Config configuracao) {
        ValidationResult result = this.validaCompraAcao(acaoToAdd, usuario, configuracao);

        if (result.isSucess()) {
            try {
                BaseDAO dao = new BaseDAO();

                dao.getEntityManager().find(Usuario.class, usuario.getId());
                dao.getEntityManager().getTransaction().begin();
                usuario = dao.getEntityManager().merge(usuario);

                //Adiciona a conta do usuario a acao
                acaoToAdd.setConta(usuario.getPessoa().getConta());
                //Adiciona a acao para o usuario(Se ele já tiver uma ação identica, só adiciona à quantidade)
                usuario.getPessoa().getConta().mergeAcoes(acaoToAdd);
                //Desconta o preço do seu crédito
                usuario.getPessoa().getConta().descontaCredito(this.getPrecoComTaxas(acaoToAdd.getSubtotal(), usuario, configuracao));
                //Sobe o limite de corretagem.
                usuario.getPessoa().getConta().addCorretagemTotalPaga(configuracao.getTaxaCorretagem());
                
                dao.getEntityManager().refresh(usuario);
                dao.getEntityManager().getTransaction().commit();
                dao.close();
            } catch (Exception ex) {
                result.addError("Não foi possível adicionar a ação à pessoa");
            }
        }

        return result;
    }

    public List<Acao> pesquisaCotacaoAcaoUnica(String termo) {
        return new AcaoJsonHelper().getAcoesFromJson("http://cotacao.davesmartins.com.br/webCotacao/?cod=" + termo);
    }
    
    public List<Acao> pesquisaAcaoWithList(List<Acao> acoes){
        String termo = "";
        for (Acao acao : acoes){
            termo += acao.getAcao() + ";";
        }
        return this.pesquisaCotacaoAcaoUnica(termo);
    }

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

    public UsuarioService getUsuarioService() {
        return UsuarioService;
    }

    public void setUsuarioService(UsuarioService UsuarioService) {
        this.UsuarioService = UsuarioService;
    }

}
