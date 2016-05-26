/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Model.DAO.BaseDAO;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author adowt
 */
public class ConfigService {

    public Config prepareConfig() {
        BaseDAO dao = new BaseDAO();

        //Cria a query
        Query query = dao.getEntityManager().createQuery("SELECT c FROM Config c ");
        //Cria uma config padrao
        Config configPadrao = new Config(500.0, 20.0, 5000.0, 15.21, 0.5, 0.0275, 0.005);

        //Tenta buscar do banco
        try {
            return (Config) query.getSingleResult();
        } //Se não achou do banco
        catch (NoResultException ex) {
            //Cria no banco
            dao.getEntityManager().getTransaction().begin();
            dao.getEntityManager().persist(configPadrao);
            dao.getEntityManager().getTransaction().commit();

            //Tenta buscar
            return (Config) query.getSingleResult();
        } //Se deu um erro qualquer
        catch (Exception ex) {
            //Retorna a padrao
            return configPadrao;
        }
    }
}
