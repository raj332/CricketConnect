
package serverBeans;

import entities.Playermaster;
import entities.Projectgroups;
import entities.Projectusers;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 *
 * @author Bhatt Jaimin
 */
@Stateless
public class playerEJB implements playerEjbLocal {
  @PersistenceContext(unitName = "jpapu")
    EntityManager em;
    @Override
    public boolean register(Playermaster player) {
    try{
        String[] arrOfStr = player.getPlayerId().split(":");
        player.setPlayerId(arrOfStr[0]);
        
        em.persist(player);
        
        Projectusers u1= new Projectusers();
         Pbkdf2PasswordHashImpl pbk = new Pbkdf2PasswordHashImpl();
         String hash = pbk.generate(arrOfStr[1].toCharArray());
        u1.setPassword(hash);
        u1.setUserId(player.getPlayerId());
        em.persist(u1);
       
        Projectgroups g1= new Projectgroups();
        g1.setGroupName("player");
        g1.setUserId(u1);
        em.persist(g1);
        return true;
    }catch(Exception ex){
        return false;
    }
    }

    @Override
    public List<Playermaster> getAllPlayers() {
       return em.createNamedQuery("Playermaster.findAll").getResultList();
    }

    @Override
    public boolean update(Playermaster player) {
        try{
            em.merge(player);
            return true;
        }catch(Exception ex){
            return false;
        }
        
    }

    @Override
    public boolean delete(String id) {
        try{
            Playermaster player = getPlayerByID(id);
            em.remove(player);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    @Override
    public Playermaster getPlayerByID(String id){
      return  em.find(Playermaster.class, id);
    }

   
}
