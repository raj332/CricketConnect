/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package serverBeans;

import entities.Auctiondetailtb;
import entities.Organizermaster;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Bhatt Jaimin
 */
@Stateless
public class auctionDetailEJB implements auctionDetailEJBLocal {
  @PersistenceContext(unitName = "jpapu")
    EntityManager em;
    @Override
    public boolean createAuction(Auctiondetailtb auction) {
        try{
            auction.setStatus("upcomming");
            em.persist(auction);
            return true;
        }catch(Exception ex){
            return false;
        }
    }

    @Override
    public boolean editAuction(Auctiondetailtb auction) {
        try{
            em.merge(auction);
            return true;
        }catch(Exception ex){
            return false;
        }
    }

    @Override
    public List<Auctiondetailtb> Auctionlist() {
       List<Auctiondetailtb> auctionlist;
       try{
            auctionlist = em.createNamedQuery("Auctiondetailtb.findAll").getResultList();
            return auctionlist;
       }catch(Exception ex){
        return null;   
       }
    }

    @Override
    public Auctiondetailtb getAuctionDetail(int id) {
        try{
            Auctiondetailtb auction;
            auction = em.find(Auctiondetailtb.class, id);
           
            return auction;
            
       }catch(Exception ex){
        return null;   
       }
    }

    @Override
    public List<Auctiondetailtb> getAuctionListByOrganizerid(String oid) {
        try{
            Organizermaster or1 =new Organizermaster();
            or1.setOrganizerId(oid);
            List<Auctiondetailtb>  l1=  em.createNamedQuery("Auctiondetailtb.findByOrganizerId").setParameter("organizerId", or1).getResultList();
          
            return l1;
        }catch(Exception ex){
        return null;   
       }
    }

   
}
