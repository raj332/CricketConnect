/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdiBeans;

import composite.Bid;
import entities.Playermaster;
import entities.Projectusers;
import entities.Teamownermaster;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import restClient.commonClient;
import restClient.organizerClient;
import restClient.ownerClient;

/**
 *
 * @author Bhatt Jaimin
 */
@Named(value = "ownercdi")
@SessionScoped
public class ownerCdi implements Serializable {
   Bid BidDeatils;
    int number;

    public int getNumber() {
        return number;
    }
    public void increment(){
        number++;
    }
    public Bid getBidDeatils() {
        return BidDeatils;
    }
      Response rs;
      organizerClient orclient = new organizerClient();
      ownerClient owclient = new ownerClient();
      commonClient cclient = new  commonClient();
      Teamownermaster owner = new Teamownermaster();
       String currentPassword;
       boolean msg;
   public boolean getMsg() {
        return msg;
    }

    public void setMsg(boolean msg) {
        this.msg = msg;
    }
//     public void checkUsernameAvailability() {
//        
//         if(p!=null){
//             this.msg = true;
//         }
//         else{
//             setMsg(false);
//         }
//     }
    
    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }
   
    GenericType<List<Teamownermaster>> glist = new GenericType<List<Teamownermaster>>(){};
    public ownerCdi() {
        BidDeatils = new Bid();
    }

    public Teamownermaster getOwner() {
        return owner;
    }

    public void setOwner(Teamownermaster owner) {
        this.owner = owner;
    }
    
     public List<Teamownermaster> displayOwners(){
        rs = orclient.getOwners(Response.class);
        List<Teamownermaster> ownlist = rs.readEntity(glist);
        return ownlist;
    }
     
      public String loadupdate(Teamownermaster p){
         owner = p;
         return "update.xhtml";
    }
    
    public String update(){
       // owner.setOwnerId(owner.getOwnerId().concat(":"+currentPassword));
        owner.setOwnerId(owner.getOwnerId());

         owclient.updateOwner(owner);
         owner = new Teamownermaster();
         System.gc();
         return "OwnerList.xhtml";
    }
    
    public String delete(String id){
        owclient.deleteOwner(id);
        return "OwnerList.xhtml";
    }
    public String insertOwner() throws IOException{
       
        owner.setOwnerId(owner.getOwnerId().concat(":"+currentPassword));
        owclient.addOwner(owner);
        owner= new Teamownermaster();
        System.gc();
        return "OwnerList.xhtml";
    }
    
     public void checkUsernameAvailability() {
         Projectusers p = cclient.getUser(Projectusers.class,owner.getOwnerId());
         if(p!=null){
             this.msg = true;
         }
         else{
             setMsg(false);
         }
     }
      public Bid updateBidDetails(){
         this.BidDeatils.setLogo("Photo.png");
         this.BidDeatils.setCurrentBid(3);
         this.BidDeatils.setTimer(120);
         return BidDeatils;
     }
     public void decrement(){
         int time=BidDeatils.getTimer();
         time=--time;
         this.BidDeatils.setTimer(time);
     }
}
