/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package serverBeans;

import entities.Teammaster;
import entities.Teamownermaster;
import entities.Tournamenttb;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.bootsfaces.render.A;

/**
 *
 * @author Bhatt Jaimin
 */
@Stateless
public class teamEJB implements teamEJBLocal {
  @PersistenceContext(unitName = "jpapu")
    EntityManager em ;
    @Override
    public boolean registerTeam(Teammaster team) {
        try{
            em.persist(team);
            return true;
        }catch(Exception ex){
            return false;
        }
    }

   

    @Override
    public List<Teammaster> getTeamsByTournament(int tournamnetid) {
       try{
          
           Tournamenttb t1 = new Tournamenttb();
           t1.setTournamentId(tournamnetid);
            List<Teammaster> l1=  em.createNamedQuery("Teammaster.findByTournament").setParameter("tournamentid", t1).getResultList();
            return l1;
        }catch(Exception ex){
            return null;
        }
    }

    @Override
    public Teammaster getTeamByid(int tournamnetid, int teamid) {
       try{
            return  em.find(Teammaster.class, teamid);
        }catch(Exception ex){
            return null;
        }
    }
@Override
    public boolean updateTeam(Teammaster t1){
        try{
       Teammaster t2=  em.find(Teammaster.class, t1.getTeamId());
        t2.setTeamLogo(t1.getTeamLogo());
        t2.setTeamName(t1.getTeamName());
        t2.setTotalPurse(t1.getTotalPurse());
        t2.setOwnerId(t1.getOwnerId());
        em.persist(t2);
        return true;
        }catch(Exception ex){
            return false;
        }
    }
    @Override
    public Teamownermaster getOwnerDetais(String ownerid, int tournamentid) {
        try{
            return  (Teamownermaster) em.createNamedQuery("Teammaster.findTeamOwner").setParameter("ownerId", ownerid).getSingleResult();
        }catch(Exception ex){
            return null;
        }
    }

    @Override
    public List<Teammaster> getTeamsByTournamentId(int tid) {
         try{
        Tournamenttb t1 = new Tournamenttb();
        t1.setTournamentId(tid);
         List<Teammaster> tlist= em.createNamedQuery("Teammaster.findByTournament").setParameter("tournamentid", t1).getResultList();
         return tlist;
           }catch(Exception ex){
            return null;
        }
    }


   
}
