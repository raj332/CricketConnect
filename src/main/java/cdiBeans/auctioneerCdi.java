/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdiBeans;

import Authentication.KeepRecord;
import composite.Sidebar;
import entities.Auctiondetailtb;
import entities.Playermaster;
import entities.Projectusers;
import entities.Teammaster;
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
import serverBeans.teamEJBLocal;

/**
 *
 * @author Bhatt Jaimin
 */
@Named(value = "auctioneercdi")
@SessionScoped
public class auctioneerCdi implements Serializable {
  @EJB auctioneerEJBLocal auctioneerejb;
    @EJB teamEJBLocal teamejb ;
    List<Teammaster> auctionTeamList ;
    List<Playermaster> auctionPlayerList;
    Teammaster currentTeam = new Teammaster();
     ArrayList<Sidebar> sidebarItems = new ArrayList<>();
    public ArrayList<Sidebar> getSidebarItems() {
        return sidebarItems;
    }
 
   
   
    public auctioneerCdi() {
         sidebarItems.add(new Sidebar("fsfs","Dashboard","home.jsf"));
      
        sidebarItems.add(new Sidebar("fsfs","My Profile","Profile.jsf"));
       
    }

    public List<Teammaster> getAuctionTeamList() {
        return auctionTeamList;
    }

    public void setAuctionTeamList(List<Teammaster> auctionTeamList) {
        this.auctionTeamList = auctionTeamList;
    }
  
    public List<Auctiondetailtb> showAuctionsByAuctioneer(){
        return auctioneerejb.getAuctionsByAuctioneer(KeepRecord.getUserid());
    }

    public Teammaster getCurrentTeam() {
        return currentTeam;
    }

    public void setCurrentTeam(Teammaster currentTeam) {
        this.currentTeam = currentTeam;
    }
    
    
     private int number = 120;

    public void decrement() {
        if(number>0){
            number--;
        }
        else{
            // save player to soldplayer tb
            number = 120;
        }
        
    }
    
    public String placeBidFromTeam(Teammaster team){
        currentTeam = team ;
        return "AuctionDashboard.jsf";
        
    }
    public int getNumber() {
        return number;
    }
    public String teamListForAuction(Auctiondetailtb auction){
       auctionTeamList= teamejb.getTeamsByTournamentId(auction.getTornamentId().getTournamentId());
       auctionPlayerList = auction.getPlayermasterList();
       if(!auctionTeamList.isEmpty() ){
           return "AuctionDashboard.jsf";
       }else{
           return "home.jsf";
       }
    }
}
