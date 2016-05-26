/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Model.Cotacao.Acao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;

/**
 *
 * @author adowt
 */
public class AcaoJsonHelper {

    public List<Acao> getAcoesFromJson(String jsonServ) {
        boolean buscar = false;
        List<Acao> acoes = null;
        try {
            //Busca o json do server
            String json = this.readUrl(jsonServ);
            //Muda a virgula pro ponto
            json = json.replaceAll("(\\d),(\\d)", "\\1.\\2");

            //Preenche o OBJ com o json
            acoes = getAcoesFromJsonString(json);

            //Limpa as ações que o servidor trouxe não preenchidas
            for (Acao acao : acoes) {
                if (acao.getVariacao() == 0.0 && acao.getAber_cotacao() == 0.0 && acao.getMax_cotacao_dia() == 0.0 && acao.getMed_cotacao_dia() == 0.0 && acao.getMin_cotacao_dia() == 0.0 && acao.getUlt_cotacao() == 0.0) {
                    acoes.remove(acao);
                    //CUIDADO COM ISTO AQUI
                    //SE O SERVER NUNCA RESPONDER PODE GERAR DEADLOCK
                    //SE A VARIACAO FOR REALMENTE 0 VAI GERAR DEADLOCK
                    //SE A ACAO NAO EXISTIR GERA DEADLOCK
                    buscar = true;
                }
            }
        } catch (Exception ex) {
            buscar = true;
        }

        //Busca de novo recursivamente se uma ação nao foi encontrada
        if (buscar) {
            return this.getAcoesFromJson(jsonServ);
        } else {
            return acoes;
        }
    }

    private List<Acao> getAcoesFromJsonString(String jsonString) {
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
            StringBuilder buffer = new StringBuilder();
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
