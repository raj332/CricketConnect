/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package serverBeans;

import entities.Organizermaster;
import entities.Tournamenttb;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Bhatt Jaimin
 */
@Stateless
public class tournamentEJB implements tournamentEJBLocal {
  @PersistenceContext(unitName = "jpapu")
    EntityManager em;
    @Override
    public boolean createTournamnet(Tournamenttb tournamnent) {
    try{
        em.persist(tournamnent);
        return true;
    }catch(Exception ex){
        return false;
    }
    }

    @Override
    public List<Tournamenttb> getAllTournament() {
    try{
        return (List<Tournamenttb>) em.createNamedQuery("Tournamenttb.findAll").getResultList();
    }catch(Exception ex){
     return null;   
    }
    }

    @Override
    public Tournamenttb getTournamentById(int id) {
     try{
        return em.find(Tournamenttb.class, id);
    }catch(Exception ex){
        return null;
    }
    }
    @Override
    public List<Tournamenttb> getTournamentByPlayersid(String id) {
        try {
            Query query = em.createNativeQuery(
                    "SELECT * FROM tournamenttb t WHERE t.tournamentId NOT IN (SELECT tp.tournamentId FROM tournamentplayerslist tp WHERE tp.playerId = ?)",
                    Tournamenttb.class
            );
          query.setParameter(1, id);
            List<Tournamenttb> tournaments = query.getResultList();
            return tournaments;
        } catch (Exception ex) {
            return null;
        }
    }
    @Override
    public List<Tournamenttb> getTournamentByOrganizer(String oid) {
        try{
            Organizermaster a1 = new Organizermaster();
            a1.setOrganizerId(oid);
            return em.createNamedQuery("Tournamenttb.findByOrganizerId").setParameter("organizerid",a1).getResultList();
            
        }catch(Exception ex){
        return null;
    }
    }

      
}
