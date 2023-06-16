/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package serverBeans;

import entities.Teamownermaster;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Bhatt Jaimin
 */
@Local
public interface teamOwnerEJBLocal {
     boolean register(Teamownermaster owner);
     public List<Teamownermaster> getOwnerlist();
     public Teamownermaster getOwner(String id);
     public boolean editOwner (Teamownermaster owner);
     public boolean deleteOwner(String id);
}
