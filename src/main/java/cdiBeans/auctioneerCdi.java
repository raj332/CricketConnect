/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdiBeans;

import Authentication.KeepRecord;
import composite.Sidebar;
import entities.Auctiondetailtb;
import entities.Projectusers;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.Response;
import restClient.commonClient;
import serverBeans.auctioneerEJBLocal;

/**
 *
 * @author Bhatt Jaimin
 */
@Named(value = "auctioneercdi")
@SessionScoped
public class auctioneerCdi implements Serializable {

    /**
     * Creates a new instance of auctioneerCdi
     */
     ArrayList<Sidebar> sidebarItems = new ArrayList<>();
    public ArrayList<Sidebar> getSidebarItems() {
        return sidebarItems;
    }
   @EJB auctioneerEJBLocal auctioneerejb;
    public auctioneerCdi() {
         sidebarItems.add(new Sidebar("fsfs","Dashboard","home.jsf"));
      
        sidebarItems.add(new Sidebar("fsfs","My Profile","Profile.jsf"));
       
    }
  
    public List<Auctiondetailtb> showAuctionsByAuctioneer(){
        return auctioneerejb.getAuctionsByAuctioneer(KeepRecord.getUserid());
    }
}
