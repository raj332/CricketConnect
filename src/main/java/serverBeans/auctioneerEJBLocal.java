/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package serverBeans;

import entities.Auctiondetailtb;
import entities.Auctioneermaster;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Bhatt Jaimin
 */
@Local
public interface auctioneerEJBLocal {
      boolean register(Auctioneermaster auctioneer,String password);
      boolean updateProfile(Auctioneermaster auctioneer);
      Auctioneermaster getAuctioneer(String auctioneerId);
     public List<Auctioneermaster> getAuctioneerList(String organizerId) ;
public List<Auctiondetailtb> getAuctionsByAuctioneer(String id);
}
