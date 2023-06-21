/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdiBeans;

import Authentication.KeepRecord;
import composite.Alerts;
import composite.Sidebar;
import entities.Auctiondetailtb;
import entities.Battingtypemaster;
import entities.Bowlingtypemaster;
import entities.Playermaster;
import entities.Soldplayertb;
import entities.Tournamentplayerslist;
import entities.Tournamenttb;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.primefaces.PrimeFaces;
import org.primefaces.model.file.UploadedFile;
import restClient.playerClient;
import restClient.tournamentClient;
import serverBeans.playerEjbLocal;
import serverBeans.soldPlayerEJBLocal;
import serverBeans.tournamentEJBLocal;
import serverBeans.tournamentPlayersEJBLocal;

/**
 *
 * @author praj4
 */
@Named(value = "playercdi")
@SessionScoped
public class playerCdi implements Serializable{

   Alerts alert;
    ArrayList<Sidebar> sidebarItems = new ArrayList<>();
    public ArrayList<Sidebar> getSidebarItems() {
        return sidebarItems;
    }
     //for check playerid avaible?
    boolean msg = false;
    Response rs;
    GenericType<List<Playermaster>> glist = new GenericType<List<Playermaster>>(){};
    GenericType<List<Battingtypemaster>> gbatlist = new GenericType<List<Battingtypemaster>>(){};
    GenericType<List<Bowlingtypemaster>> gballlist = new GenericType<List<Bowlingtypemaster>>(){};
    List<Battingtypemaster> battingTypesList =  new ArrayList<>();
    List<Soldplayertb> teamlist ;
    GenericType<List<Tournamenttb>> gtournametlist = new GenericType<List<Tournamenttb>>(){};
    List<Tournamenttb> tournamentList; 
    @EJB tournamentPlayersEJBLocal tplayerEjb ;
    @EJB playerEjbLocal playerejb ;
     @EJB tournamentEJBLocal tournamnetejb;
    @EJB soldPlayerEJBLocal soldejb;
    List<Auctiondetailtb> auctionList ;
    public List<Tournamenttb> getTournamnetList() {
        return tournamentList;
    }

    public void setTournamnetList(List<Tournamenttb> tournamenttList) {
        this.tournamentList = tournamenttList;
    }
    
    
    List<Bowlingtypemaster> bowlingTypesList;
    List<String> battingPosition = new ArrayList<>();

    Playermaster player;
    playerClient pclient;
    tournamentClient tclient;
    String currentPassword;
    

    
    
    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }
    
    private UploadedFile file;
    private String dropZoneText = "Drop zone p:inputTextarea demo.";

    public boolean getMsg() {
        return msg;
    }

    public void setMsg(boolean msg) {
        this.msg = msg;
    }
     public void checkUsernameAvailability() {
         Playermaster p = pclient.getPlayer(Playermaster.class,player.getPlayerId());
         if(p!=null){
             this.msg = true;
         }
         else{
             setMsg(false);
         }
     }
    
        
      public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
     public String getDropZoneText() {
        return dropZoneText;
    }

    public void setDropZoneText(String dropZoneText) {
        this.dropZoneText = dropZoneText;
    }

    public List<String> getBattingPosition() {
        return battingPosition;
    }

 
    public List<Battingtypemaster> getBattingTypesList() {
        return battingTypesList;
    }

    public void setBattingTypesList(List<Battingtypemaster> battingTypesList) {
        this.battingTypesList = battingTypesList;
    }


    public List<Bowlingtypemaster> getBowlingTypesList() {
        return bowlingTypesList;
    }

    public void setBowlingTypesList(List<Bowlingtypemaster> bowlingTypesList) {
        this.bowlingTypesList = bowlingTypesList;
    }

 
    public Playermaster getPlayer() {
        return player;
    }

    public void setPlayer(Playermaster player) {
        this.player = player;
    }
    public playerCdi() {
        pclient = new playerClient();
        tclient = new tournamentClient();
        player = new Playermaster();
        
        rs = pclient.getBattingTypes(Response.class);
        battingTypesList = (List<Battingtypemaster>) rs.readEntity(gbatlist);
        
        rs = pclient.getBowlingTypes(Response.class);
        bowlingTypesList = rs.readEntity(gballlist);
        
        battingPosition.add("Top-Order");
        battingPosition.add("Middel-Order");
        battingPosition.add("Finisher");
        auctionList = new ArrayList<>();     
        getTournamnetForPlayer();
       
        sidebarItems.add(new Sidebar("fsfs","Dashboard","home.jsf"));
        sidebarItems.add(new Sidebar("fsfs","My Auctions","MyAuction.jsf"));
        sidebarItems.add(new Sidebar("fsfs","My Profile","Profile.jsf"));
         sidebarItems.add(new Sidebar("fsfs","Search","searchTournament.jsf"));
         
         //18/6 night
         
         searchedTournament = new Tournamenttb();
        alert = new Alerts();
        
    }
    public void getTournamnetForPlayer(){
         rs = tclient.getTournamentsByPlayerID(Response.class, KeepRecord.getUserid());
        tournamentList = rs.readEntity(gtournametlist);
    }
    //convert image from byte[] to string to display image comming  from database 
    public String getImageDataUrl(byte[] imageData) {
        return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageData);
    }
    
    public List<Playermaster> displayPlayers(){
        rs = pclient.getPlayers(Response.class);
        List<Playermaster> playerlist = rs.readEntity(glist);
        return playerlist;
    }
    public void upload() throws IOException {
        if (file != null) {
        
            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            InputStream inputStream = file.getInputStream();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            player.setPhoto(outputStream.toByteArray());
            file = null; 
        }
    }
    public String inroll(int i){
        System.out.println("Tournamentid: "+i);
        Tournamentplayerslist t = new Tournamentplayerslist();
        t.setPlayerStatus("fresh");
        Playermaster p1= new Playermaster();
        p1.setPlayerId(KeepRecord.getUserid());
        Tournamenttb trn =  new Tournamenttb();
        trn.setTournamentId(i);
        
        t.setTournamentId(trn);
    
        t.setIsApproved(true);
        t.setPlayerId(p1);
        if(tplayerEjb.enrollInAuction(t)){
            getTournamnetForPlayer();
        }
        msg = true;
        return "MyAuction.jsf";
       
    }
    public String insertPlayer() throws IOException{
        upload();
        player.setPlayerId(player.getPlayerId().concat(":"+currentPassword));
        playerejb.register(player);
        player = new Playermaster();
        this.currentPassword ="";
         alert.showInfo("Registartion Successfull");
         
            PrimeFaces.current().executeScript("setTimeout(function(){ window.location.href = 'login.jsf'; }, 2000);");
            return null;
        
    }
    
    public String loadupdate(Playermaster p){
         player = p;
         return "update.jsf";
    }
    
    public String update(){
         pclient.updtaePlayer(player);
         player = new Playermaster();
         System.gc();
         return "PlayerList.jsf";
    }
    
    public String delete(String id){
       playerejb.delete(id);
        return "PlayerList.jsf";
    }
    
    public String showPlayerbyID(){
//        player = pclient.getPlayer(Playermaster.class, KeepRecord.getUserid());
        player=playerejb.getPlayerByID(KeepRecord.getUserid());
        return "../player/Profile.jsf";
    }
    
    private boolean inputDisabled = true;
    private String btnValue = "Update Details";
 

    // Constructor and other methods...

    public void toggle(Playermaster player) throws IOException {
        inputDisabled = !inputDisabled;
        if(btnValue.equals("submit")){
           //  upload();
            Update(player);
        }
        btnValue = inputDisabled ? "Update Details" : "submit";
        
        
    }

    public boolean isInputDisabled() {
        return inputDisabled;
    }

    public void setInputDisabled(boolean inputDisabled) {
        this.inputDisabled = inputDisabled;
    }

   

    public String getBtnValue() {
        return btnValue;
    }
    public String Update(Playermaster player) throws IOException{
       
        playerejb.update(player);
        player =new Playermaster();
        return showPlayerbyID();
    }
    public List<Auctiondetailtb> showAuctionsByplayer(){
        return playerejb.getAuctionDetailsForPlayer(KeepRecord.getUserid());
    }

    public List<Auctiondetailtb> getAuctionList() {
        auctionList = showAuctionsByplayer();
        for(Auctiondetailtb ad : auctionList ){
            System.out.println("Tournament name: " + ad.getTornamentId().getTournamentName());
        }
        return auctionList;
    }

    public void setAuctionList(List<Auctiondetailtb> auctionList) {
        this.auctionList = auctionList;
    }
    int searchvalue;

    public int getSearchvalue() {
        return searchvalue;
    }

    public void setSearchvalue(int searchvalue) {
        this.searchvalue = searchvalue;
    }
    
    Tournamenttb searchedTournament;

    public Tournamenttb getSearchedTournament() {
        return searchedTournament;
    }

    public void setSearchedTournament(Tournamenttb searchedTournament) {
        this.searchedTournament = searchedTournament;
    }
    public Tournamenttb searchMYTournamnet(){
     
        searchedTournament = tournamnetejb.getTournamentById(searchvalue);
        Playermaster player = playerejb.getPlayerByID(KeepRecord.getUserid());
        msg = searchedTournament.getPlayermasterList().contains(player);
        return searchedTournament;
       
     
    }
 
     public List<Soldplayertb> showTeamsByPlayer(){
           return soldejb.getSoldplayerTeams(KeepRecord.getUserid());  
    }
}
