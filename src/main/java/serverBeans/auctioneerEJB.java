/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package serverBeans;

import entities.Auctiondetailtb;
import entities.Auctioneermaster;
import entities.Projectgroups;
import entities.Projectusers;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 *
 * @author Bhatt Jaimin
 */
@Stateless
public class auctioneerEJB implements auctioneerEJBLocal {
  @PersistenceContext(unitName = "jpapu")
    EntityManager em;
    @Override
    public boolean register(Auctioneermaster auctioneer , String password) {
         
      try{
//        String[] arrOfStr = auctioneer.getAuctioneerId().split(":");
//        auctioneer.setAuctioneerId(arrOfStr[0]);
//        
        em.persist(auctioneer);
        
        Projectusers u1= new Projectusers();
      Pbkdf2PasswordHashImpl pbk = new Pbkdf2PasswordHashImpl();
         String hash = pbk.generate(password.toCharArray());
        u1.setPassword(hash);
        u1.setUserId(auctioneer.getAuctioneerId());
        em.persist(u1);
       
        Projectgroups g1= new Projectgroups();
        g1.setGroupName("auctioneer");
        g1.setUserId(u1);
        em.persist(g1);
        
        
        return true;
    }catch(Exception ex){
        return false;
    }
    }

    @Override
    public boolean updateProfile(Auctioneermaster auctioneer) {
       try{
        em.merge(auctioneer);
        return true;
    }catch(Exception ex){
        return false;
    }
    }

    @Override
    public Auctioneermaster getAuctioneer(String auctioneerId) {
         try{
             Auctioneermaster auctioneer;
             auctioneer = em.find(Auctioneermaster.class, auctioneerId);
             return auctioneer;
    }catch(Exception ex){
        return null;
    }
    }

     @Override
    public List<Auctioneermaster> getAuctioneerList(String organizerId) {
         try{
            
            return em.createNamedQuery("Auctioneermaster.findAll").getResultList();
             
    }catch(Exception ex){
        return null;
    }
    }

    @Override
    public List<Auctiondetailtb> getAuctionsByAuctioneer(String id) {
       Query query = em.createNativeQuery("SELECT * FROM `auctiondetailtb` WHERE auctioneerId=?",
                 Auctiondetailtb.class);
        query.setParameter(1, id);
        return query.getResultList();
    }
    
 
}
