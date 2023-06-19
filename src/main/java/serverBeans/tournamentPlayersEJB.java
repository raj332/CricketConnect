/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package serverBeans;

import entities.Auctiondetailtb;
import entities.Playermaster;
import entities.Tournamentplayerslist;
import entities.Tournamenttb;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Bhatt Jaimin
 */
@Stateless
public class tournamentPlayersEJB implements tournamentPlayersEJBLocal {
@PersistenceContext(unitName = "jpapu")
    EntityManager em;
    @Override
   public boolean enrollInAuction(Tournamentplayerslist data){
       try{
            Tournamenttb tournament = em.find(Tournamenttb.class, data.getTournamentId().getTournamentId());
            Auctiondetailtb auction = (Auctiondetailtb)em.createNamedQuery("Auctiondetailtb.findByTournamentId").setParameter("tournamentId", tournament).getSingleResult();
            Playermaster player = em.find(Playermaster.class, data.getPlayerId().getPlayerId());
            List <Auctiondetailtb> auctions = player.getAuctiondetailtbList();
            List<Tournamenttb> tournamnets = player.getTournamenttbList();
         
            auctions.add(auction);
            tournamnets.add(tournament);
         
            em.persist(data);
            em.merge(player);
            
           
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
   }
   
}
