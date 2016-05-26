/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Cotacao;

import Model.DAO.BaseDAO;
import Model.User.Usuario;
import Util.ValidationResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.persistence.EntityManager;
import org.apache.tomcat.util.http.fileupload.IOUtils;

/**
 *
 * @author adowt
 */
public class CotacaoService {

    public CotacaoService() {
    }

    public ValidationResult validaCompraAcao(Acao acaoToAdd, Usuario usuario) {
        ValidationResult result = new ValidationResult("Panel");

        if (usuario.getPessoa().getConta().getCredito() <= acaoToAdd.getUlt_cotacao()) {
            result.addError("Você não possui crédito suficiente");
        }

        return result;
    }

    public ValidationResult addAcaoToUser(Acao acaoToAdd, Usuario usuario) {
        ValidationResult result = this.validaCompraAcao(acaoToAdd, usuario);

        if (result.isSucess()) {
            try {
                usuario.getPessoa().getConta().getAcoes().add(acaoToAdd);

                BaseDAO dao = new BaseDAO();

                dao.getEntityManager().find(Usuario.class, usuario.getId());

                dao.getEntityManager().getTransaction().begin();
                usuario = dao.getEntityManager().merge(usuario);
                dao.getEntityManager().refresh(usuario);
                dao.getEntityManager().getTransaction().commit();
                dao.close();
            } catch (Exception ex) {
                result.addError("Não foi possível adicionar a ação à pessoa");
            }
        }

        return result;
    }

    public List<Acao> pesquisaCotacaoAcao(String termo) {
        return getAcoesFromJson("http://cotacao.davesmartins.com.br/webCotacao/?cod=" + termo);
    }

    private List<Acao> getAcoesFromJson(String jsonServ) {
        boolean buscar = false;
        List<Acao> acoes = null;
        try {
            //Busca o json do server
            String json = this.readUrl(jsonServ);
            //Muda a virgula pro ponto
            json = json.replaceAll("(\\d),(\\d)", "\\1.\\2");

            //Preenche o OBJ com o json
            acoes = getAcoesFromJsonOnce(json);

            //CUIDADO COM ISTO AQUI
            //SE O SERVER NUNCA RESPONDER PODE GERAR DEADLOCK
            //SE A VARIACAO FOR REALMENTE 0 VAI GERAR DEADLOCK
//            for (Acao acao : acoes) {
//                if (acao.getVariacao() == 0.0) {
//                    buscar = true;
//                }
//            }
        } catch (Exception ex) {
            buscar = true;
        }
//
//        //Busca de novo recursivamente
//        if (buscar) {
//            return this.getAcoesFromJson(jsonServ);
//        } else {
        return acoes;
//        }

    }

    private List<Acao> getAcoesFromJsonOnce(String jsonString) {
        Gson gson = new Gson();

        //Fala que o tipo é uma lista
        Type tipoObj = new TypeToken<List<Acao>>() {
        }.getType();
        //Preenche o obj
        List<Acao> acoes = gson.fromJson(jsonString, tipoObj);

        return acoes;
    }

    private String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }

            return buffer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

}
