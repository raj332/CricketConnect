/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdiBeans;

import Authentication.KeepRecord;
import composite.Sidebar;
import entities.Auctiondetailtb;
import entities.Playermaster;
import entities.Soldplayertb;
import entities.Teammaster;
import entities.Unsoldplayertb;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.PrimeFaces;
import serverBeans.auctionDetailEJBLocal;

import serverBeans.auctioneerEJBLocal;
import serverBeans.soldPlayerEJBLocal;
import serverBeans.teamEJBLocal;
import serverBeans.unsoldPlayerEJBLocal;

/**
 *
 * @author Bhatt Jaimin
 */
@Named(value = "auctioneercdi")
@SessionScoped
public class auctioneerCdi implements Serializable {
  @EJB auctioneerEJBLocal auctioneerejb;
    @EJB teamEJBLocal teamejb ;
    @EJB auctionDetailEJBLocal aucDetailejb;
    @EJB soldPlayerEJBLocal soldejb ;
    @EJB unsoldPlayerEJBLocal unsoldejb ;
    List<Teammaster> auctionTeamList ;
    List<Playermaster> auctionPlayerList;
    int currentBidAmount ;
    int countStopper = 0;
    int incrementAmount ;
    boolean disableSoldUnsold=false ;
    Teammaster currentTeam = new Teammaster();
    Playermaster currentPlayer = new Playermaster();
    Auctiondetailtb auction = new Auctiondetailtb();
     ArrayList<Sidebar> sidebarItems = new ArrayList<>();
    public ArrayList<Sidebar> getSidebarItems() {
        return sidebarItems;
    }
   
    public auctioneerCdi() {
         sidebarItems.add(new Sidebar("fsfs","Dashboard","home.jsf"));
      
        sidebarItems.add(new Sidebar("fsfs","My Profile","Profile.jsf"));
       
    }

    public boolean isDisableSoldUnsold() {
        return disableSoldUnsold;
    }

    public void setDisableSoldUnsold(boolean disableSoldUnsold) {
        this.disableSoldUnsold = disableSoldUnsold;
    }

    public int getCurrentBidAmount() {
        return currentBidAmount;
    }

    public void setCurrentBidAmount(int currentBidAmount) {
        this.currentBidAmount = currentBidAmount;
    }

    public int getIncrementAmount() {
        return incrementAmount;
    }

    public void setIncrementAmount(int incrementAmount) {
        this.incrementAmount = incrementAmount;
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

    public List<Playermaster> getAuctionPlayerList() {
        return auctionPlayerList;
    }

    public void setAuctionPlayerList(List<Playermaster> auctionPlayerList) {
        this.auctionPlayerList = auctionPlayerList;
    }

    public Playermaster getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Playermaster currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Teammaster getCurrentTeam() {
        return currentTeam;
    }

    public void setCurrentTeam(Teammaster currentTeam) {
        this.currentTeam = currentTeam;
    }
    
    
     private int number = 30;

    public void decrement() {
        
        if(number>0){
            number--;
        }
        else if( currentTeam.getTeamId()== null && countStopper==0){
            onPlayerUnsold();
          PrimeFaces.current().ajax().update("screenForm");
            
        }else if(countStopper==0){
            onPlayerSold();
            PrimeFaces.current().ajax().update("screenForm listForm");
            
        }
        
    }
    
    public String placeBidFromTeam(Teammaster team){
        currentBidAmount = currentBidAmount + incrementAmount ;
        currentTeam = team ;
        number =30 ;
        return "AuctionDashboard.jsf";
        
    }
    public int getNumber() {
        return number;
    }
    public String teamListForAuction(Auctiondetailtb auction){
        
       this.auction = auction;
       incrementAmount = auction.getBidIncrementAmount();
       currentBidAmount = (int) auction.getEachPlayerBasePrice();
       auctionTeamList= teamejb.getTeamsByTournamentId(auction.getTornamentId().getTournamentId());
       auctionPlayerList = auction.getPlayermasterList();
       currentPlayer= auctionPlayerList.get(0);
       auctionPlayerList.remove(0);
       if(!auctionTeamList.isEmpty() ){
           return "AuctionDashboard.jsf";
       }else{
           return "home.jsf";
       }
    }
    public void onNextPlayer(){
        if(auctionPlayerList.isEmpty()){
        auction.setStatus("finished");
        aucDetailejb.editAuction(auction);
        System.gc();
        }else{
        incrementAmount = auction.getBidIncrementAmount();
        currentBidAmount = (int) auction.getEachPlayerBasePrice();
        currentPlayer= auctionPlayerList.get(0);
        auctionPlayerList.remove(0);
        disableSoldUnsold =false;
        number = 30 ;
        currentTeam = new Teammaster();
        countStopper =0; 
        }
       
        
    }
    
   
    public void onPlayerSold(){
        countStopper++;
         number =0 ;
        disableSoldUnsold=true;
         Soldplayertb s1= new Soldplayertb();
        s1.setTeamId(currentTeam);
        s1.setPlayermaster(currentPlayer);
        s1.setDate(new Date());
        s1.setSoldAmount(currentBidAmount);
        s1.setPlayerId(currentPlayer.getPlayerId());
        soldejb.addSoldPlayer(s1);
        int remainpurse = (int) (currentTeam.getTotalPurse() - currentBidAmount) ;
        currentTeam.setTotalPurse(remainpurse);
        teamejb.updateTeam(currentTeam);
      
    }
    public void onPlayerUnsold(){
        countStopper++;
        number =0 ;
        disableSoldUnsold = true;
        Unsoldplayertb p1= new Unsoldplayertb();
        p1.setDate(new Date());
        p1.setPlayerId(currentPlayer.getPlayerId());
        p1.setPlayermaster(currentPlayer);
        p1.setTournamentId(auction.getTornamentId());
        unsoldejb.addPlayerToUnsoldList(p1);
        
      
        
    }
    public String handleAuctionLeave(){
        String status= auction.getStatus();
        if("finished".compareTo(status)==0 ){
            return "home.xhtml";
        }
        soldejb.removeByTournamentId(auction.getTornamentId().getTournamentId());
        unsoldejb.removeByTournamentId(auction.getTornamentId().getTournamentId());
        return "home.xhtml";
    }
      public String getImageDataUrl(byte[] imageData) {
        return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageData);
    }
    
}
