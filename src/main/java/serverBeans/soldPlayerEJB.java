/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package serverBeans;

import entities.Playermaster;
import entities.Soldplayertb;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Bhatt Jaimin
 */
@Stateless
public class soldPlayerEJB implements soldPlayerEJBLocal {
  @PersistenceContext(unitName = "jpapu")
    EntityManager em;
    @Override
    public List<Soldplayertb> getAllSoldPlayers() {
        List<Soldplayertb> soldPlayerList;
        try{
            soldPlayerList = em.createNamedQuery("Soldplayertb.findAll").getResultList();
            return soldPlayerList;
        }catch(Exception ex){
        return null;
        }
    }

    public Soldplayertb getSoldPlayerDetail(String pid){
       try{
            Soldplayertb s1= new Soldplayertb();
            s1 =  (Soldplayertb) em.createNamedQuery("Soldplayertb.findByPlayerId").setParameter("playerId", pid).getSingleResult();
            return s1;
        }catch(Exception ex){
        return null;
        } 
    }
    @Override
    public List<Soldplayertb> getSoldPlayersByTeam(int teamid) {
        List<Soldplayertb> soldPlayerList;
        try{
            soldPlayerList = em.createNamedQuery("Soldplayertb.findByTeamId").setParameter("teamId", teamid).getResultList();
            return soldPlayerList;
        }catch(Exception ex){
        return null;
        }
    }
    
    

 
}
