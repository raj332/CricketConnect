/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package serverBeans;

import entities.Organizermaster;
import entities.Projectgroups;
import entities.Projectusers;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 *
 * @author praj4
 */
@Stateless

           
public class organizerBeans implements organizerBeansLocal {
    
    @PersistenceContext(unitName = "jpapu")
    EntityManager em;

    public organizerBeans() {
    }
   
    @Override
    public boolean register(Organizermaster orgenizer) {
       try {
            String[] arrOfStr = orgenizer.getOrganizerId().split(":");
            orgenizer.setOrganizerId(arrOfStr[0]);
            
           em.persist(orgenizer);
            Projectusers u1= new Projectusers();
            Pbkdf2PasswordHashImpl pbk = new Pbkdf2PasswordHashImpl();
         String hash = pbk.generate(arrOfStr[1].toCharArray());
        u1.setPassword(hash);
        u1.setUserId(orgenizer.getOrganizerId());
        em.persist(u1);
       
        Projectgroups g1= new Projectgroups();
        g1.setGroupName("organizer");
        g1.setUserId(u1);
        em.persist(g1);
           return true;
       }catch(Exception ex){
           return false;
       }
    }

    @Override
    public Organizermaster getOrganizer(String id) {
        try{
            return em.find(Organizermaster.class, id);
        }catch(Exception ex){
            return null;
        }
    }
}
