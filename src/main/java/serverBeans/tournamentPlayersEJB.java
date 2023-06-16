/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package serverBeans;

import entities.Tournamentplayerslist;
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
            em.persist(data);
            return true;
        }catch(Exception ex){
            return false;
        }
   }
   
}
