/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package serverBeans;

import entities.Projectgroups;
import entities.Projectusers;
import entities.Teamownermaster;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;


@Stateless
public class teamOwnerEJB implements teamOwnerEJBLocal {
      @PersistenceContext(unitName = "jpapu")
  EntityManager em;
    @Override
    public boolean register(Teamownermaster owner) {
        try{
        String[] arrOfStr = owner.getOwnerId().split(":");
        owner.setOwnerId(arrOfStr[0]);
        
        em.persist(owner);
        
        Projectusers u1= new Projectusers();
         Pbkdf2PasswordHashImpl pbk = new Pbkdf2PasswordHashImpl();
         String hash = pbk.generate(arrOfStr[1].toCharArray());
        u1.setPassword(hash);
        u1.setUserId(owner.getOwnerId());
        em.persist(u1);
       
        Projectgroups g1= new Projectgroups();
        g1.setGroupName("owner");
        g1.setUserId(u1);
        em.persist(g1);
        return true;
    }catch(Exception ex){
        return false;
    }
    }

    @Override
    public List<Teamownermaster> getOwnerlist() {
     return em.createNamedQuery("Teamownermaster.findAll").getResultList();
    }

    @Override
    public Teamownermaster getOwner(String id) {
      try{
           Teamownermaster m1= new Teamownermaster();
        m1= em.find(Teamownermaster.class, m1);
        return m1;
      }catch (Exception e){
          return null;
      }
    }

    @Override
    public boolean editOwner(Teamownermaster owner) {
try{
       Teamownermaster t1= new Teamownermaster();
//        String[] arrOfStr = owner.getOwnerId().split(":",1);
//        owner.setOwnerId(arrOfStr[0]);
         t1=    em.find(Teamownermaster.class, owner.getOwnerId());
       t1.setMobileNumber(owner.getMobileNumber());
       t1.setName(owner.getName());
       em.persist(t1);
       
//        Projectusers u1= new Projectusers();
//        u1= em.find(Projectusers.class, owner.getOwnerId());
//        
//        u1.setPassword(arrOfStr[1]);
//      
//        em.persist(u1);
      
       
        return true;
    }catch(Exception ex){
        return false;
    }    }

    @Override
    public boolean deleteOwner(String id) {
        try{
            Teamownermaster owner = em.find(Teamownermaster.class, id);
            em.remove(owner);
            Projectusers p1= em.find(Projectusers.class, id);
            em.remove(p1);
            Projectgroups g1= (Projectgroups) em.createNamedQuery("Projectgroups.findByUserId").setParameter("userId", p1).getSingleResult();
            em.remove(g1);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
}
