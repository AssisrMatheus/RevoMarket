/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author adowt
 */
public class BaseDAO {
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("RevoMarketPU");
    private EntityManager em = emf.createEntityManager();

    public EntityManager getEntityManager() {
        return em;
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    public void close(){
        this.emf.close();
    }
    
}
