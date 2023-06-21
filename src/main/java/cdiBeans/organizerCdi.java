/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdiBeans;

import Authentication.KeepRecord;
import composite.Sidebar;
import entities.Auctiondetailtb;
import entities.Auctioneermaster;
import entities.Organizermaster;
import entities.Playermaster;
import entities.Soldplayertb;
import entities.Teammaster;
import entities.Teamownermaster;
import entities.Tournamenttb;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import org.primefaces.model.file.UploadedFile;

import restClient.organizerClient;
import restClient.teamClient;
import restClient.tournamentClient;
import serverBeans.auctionDetailEJBLocal;
import serverBeans.auctioneerEJBLocal;
import serverBeans.soldPlayerEJB;
import serverBeans.soldPlayerEJBLocal;
import serverBeans.teamEJBLocal;
import serverBeans.tournamentEJBLocal;

/**
 *
 * @author Bhatt Jaimin
 */
@Named(value = "organizercdi")
@SessionScoped
public class organizerCdi implements Serializable {

   
    
     ArrayList<Sidebar> sidebarItems = new ArrayList<>();
    public ArrayList<Sidebar> getSidebarItems() {
        return sidebarItems;
    }
    //clients
    String SelectedAuctioneer ;

    public String getSelectedAuctioneer() {
        return SelectedAuctioneer;
    }

    public void setSelectedAuctioneer(String SelectedAuctioneer) {
        this.SelectedAuctioneer = SelectedAuctioneer;
    }
    organizerClient client;
    tournamentClient tclient;
    teamClient teamclient;
    Auctiondetailtb auctiondetail ;
    Teammaster team;
    Organizermaster organizer;
    Date Currentdate= new Date();
    Tournamenttb tournament;
    Tournamenttb tempTournament ;
    String Organizerid= KeepRecord.getUserid();
int tempteamid;
    String selectedOwnerId;
    Auctioneermaster auctioneer;
    String currentPassword ;
    Response rs;
    UploadedFile teamLogo;
    int selectedtournamentid ;
    @EJB tournamentEJBLocal tejb ;
    @EJB auctioneerEJBLocal auctioneerejb;
    @EJB auctionDetailEJBLocal aucDetailejb;
    @EJB teamEJBLocal teamejb;
    @EJB soldPlayerEJBLocal soldejb ;
    public Auctioneermaster getAuctioneer() {
        return auctioneer;
    }

    public void setAuctioneer(Auctioneermaster auctioneer) {
        this.auctioneer = auctioneer;
    }
    
    GenericType<List<Tournamenttb>> glist = new GenericType<List<Tournamenttb>>() {
    };
    GenericType<List<Teamownermaster>> oglist = new GenericType<List<Teamownermaster>>() {
    };
    GenericType<List<Teammaster>> tmglist = new GenericType<List<Teammaster>>() {
    };
    List<Teamownermaster> ownersList;
    List<Teammaster> teamList ;
      List<Tournamenttb> tournamnetlistForAuction;
    List<Auctiondetailtb> auctionDetailList ;
    public organizerCdi() {
        team = new Teammaster();
       
        client = new organizerClient();
        auctiondetail = new Auctiondetailtb();
        tclient = new tournamentClient();
        teamclient = new teamClient();
        organizer = new Organizermaster();
        auctioneer = new Auctioneermaster();
        tournament = new Tournamenttb();
        tempTournament = new Tournamenttb();
       
         sidebarItems.add(new Sidebar("fsfs","Dashboard","../organizer/home.jsf"));
        sidebarItems.add(new Sidebar("fsfs","Add New Tournamnets","../organizer/createTournament.jsf"));
          sidebarItems.add(new Sidebar("fsfs","View Tournamnets","../organizer/viewTournaments.jsf"));
         sidebarItems.add(new Sidebar("fsfs","Add Teams","../organizer/createTeam.jsf"));
         sidebarItems.add(new Sidebar("fsfs","Add Owners","../teamowner/insertOwner.jsf"));
         sidebarItems.add(new Sidebar("fsfs","Add Auctioneer","../organizer/createAuctioneer.jsf"));
         sidebarItems.add(new Sidebar("fsfs","Create new Auction","../organizer/createAuction.jsf"));
         sidebarItems.add(new Sidebar("fsfs","Manage Owners","../teamowner/OwnerList.jsf"));         
         //new jaimin
         tournamnetlistForAuction = new ArrayList<>();
         
    }

    public Teammaster getTeam() {
        return team;
    }

    public List<Tournamenttb> getTournamnetlistForAuction() {
        return tournamnetlistForAuction;
    }

    public void setTournamnetlistForAuction(List<Tournamenttb> tournamnetlistForAuction) {
        this.tournamnetlistForAuction = tournamnetlistForAuction;
    }

    public Auctiondetailtb getAuctiondetail() {
        return auctiondetail;
    }

    public void setAuctiondetail(Auctiondetailtb auctiondetail) {
        this.auctiondetail = auctiondetail;
    }

    public Date getCurrentdate() {
        return Currentdate;
    }

    public void setCurrentdate(Date Currentdate) {
        this.Currentdate = Currentdate;
    }

    public void setTeam(Teammaster team) {
        this.team = team;
    }

    public int getTournamentid() {
        return selectedtournamentid;
    }

    public void setTournamentid(int tournamentid) {
        this.selectedtournamentid = tournamentid;
    }
  
    public List<Auctioneermaster> showAuctioneerList(){

        return auctioneerejb.getAuctioneerList(Organizerid);
    }

    public String getOrganizerid() {
        return Organizerid;
    }

    public UploadedFile getTeamLogo() {
        return teamLogo;
    }

    public void setTeamLogo(UploadedFile teamLogo) {
        this.teamLogo = teamLogo;
    }

    public void setOrganizerid(String organizerid) {
        this.Organizerid = organizerid;
    }

    public Tournamenttb getTournament() {
        return tournament;
    }

    public void setTournament(Tournamenttb tournament) {
        this.tournament = tournament;
    }

    public Organizermaster getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizermaster organizer) {
        this.organizer = organizer;
        this.Organizerid = organizer.getOrganizerId();
    }

    public List<Teamownermaster> getOwnersList() {
        return ownersList;
    }

    public void setOwnersList(List<Teamownermaster> ownersList) {
        this.ownersList = ownersList;
    }

    public String getSelectedOwnerId() {
        return selectedOwnerId;
    }

    public void setSelectedOwnerId(String selectedOwnerId) {
        this.selectedOwnerId = selectedOwnerId;
    }

    public Organizermaster getOrganizerDetails(String id) {

        organizer = client.getOrgenizer(Organizermaster.class, id);
        return organizer;
    }

    public List<Auctiondetailtb> getAuctionDetailList() {
        return auctionDetailList;
    }

    public void setAuctionDetailList(List<Auctiondetailtb> auctionDetailList) {
        this.auctionDetailList = auctionDetailList;
    }

    public int getSelectedtournamentid() {
        return selectedtournamentid;
    }

    public void setSelectedtournamentid(int selectedtournamentid) {
        this.selectedtournamentid = selectedtournamentid;
    }

    public List<Teammaster> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Teammaster> teamList) {
        this.teamList = teamList;
    }

    public Tournamenttb getTempTournament() {
        return tempTournament;
    }

    public void setTempTournament(Tournamenttb tempTournament) {
        this.tempTournament = tempTournament;
    }

    public String register() {
        try {
            organizer.setOrganizerId(organizer.getOrganizerId().concat(":"+currentPassword));
            client.OrgenizerRegistration(organizer);
            return "login.jsf";
        } catch (Exception ex) {
            return "error.jsf";
        }

    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String createTournament() {
        try {
            Organizermaster org = new Organizermaster();
            org.setOrganizerId(Organizerid);
            tournament.setOrganizerId(org);
            tclient.addTournament(tournament);
            tournament = new Tournamenttb();
            return "viewTournaments.jsf";
        } catch (Exception ex) {
            return "error.jsf";
        }
    }

    public String createAuctioneer(){
       
             auctioneerejb.register(auctioneer, currentPassword);
      
             return "home.jsf";
    }
    public List<Tournamenttb> fetchAllTournamentsByOrganizerid() {
       List<Tournamenttb> tournamnetlist  = tejb.getTournamentByOrganizer(KeepRecord.getUserid());
        return tournamnetlist;
    }

    public void fetchTournamentsForAuctionCreation(){
        tournamnetlistForAuction = tejb.getTournamentForAuction(KeepRecord.getUserid());
    }
    public String addNewTeam() {
        try {
            this.upload();
            Tournamenttb tr = new Tournamenttb();
            tr.setTournamentId(selectedtournamentid);
            team.setTournamentid(tr);
            Teamownermaster t1 = new Teamownermaster();
            t1.setOwnerId(selectedOwnerId);
            team.setOwnerId(t1);
            teamejb.registerTeam(team);
            team = new Teammaster();
            return "viewTournaments.jsf";
            
        } catch (Exception ex) {
            team = new Teammaster();
            return "error.jsf";
        }
    }

    public String getTeamsByTournament(int tid){
         try {
          rs= teamclient.getAllTeams(Response.class, String.valueOf(tid));
          teamList = rs.readEntity(tmglist);
            return "teamList.jsf";
        } catch (Exception ex) {
            return "error.jsf";
        }
        
    }
    public void upload() throws IOException {
        if (teamLogo != null) {
            FacesMessage message = new FacesMessage("Successful", teamLogo.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);

            //read input as stream of data from file
            InputStream inputStream = teamLogo.getInputStream();

            // create output stream of byte tyep
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            //read input stream and write into byte output stream 
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            // now insert into player.setPhoto()
            team.setTeamLogo(outputStream.toByteArray());

        }
    }
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
        addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void showInfo() {
        addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Message Content");
    }

    public String createAuction(){
        Tournamenttb t1= new Tournamenttb();
        t1.setTournamentId(selectedtournamentid);
        auctiondetail.setTornamentId(t1);
        Auctioneermaster au1 = new Auctioneermaster();
        au1.setAuctioneerId(SelectedAuctioneer);
        auctiondetail.setAuctioneerId(au1);
        if(aucDetailejb.createAuction(auctiondetail)){
            return "home.jsf";
        }else{
            return "createAuction.jsf";
        }
        
        
    }
    public void showAuctionsByOrganizer(){
        auctionDetailList= aucDetailejb.getAuctionListByOrganizerid(KeepRecord.getUserid());
    }
    
    public boolean matchDate(Date d1){
        
         return (d1.getDate() == Currentdate.getDate() && d1.getMonth()==Currentdate.getMonth() && d1.getYear()==Currentdate.getYear());
    }
    public String changeAuctionStatus(Auctiondetailtb a1){
        a1.setStatus("live");
        aucDetailejb.editAuction(a1);
        System.gc();
        showAuctionsByOrganizer();
        return "home.jsf";
      
    }
    public String showAuctionUpdate(Auctiondetailtb item){
        setAuctiondetail(item);
        return "updateAuction.jsf";
    
    }
    public String showLiveAuction(Auctiondetailtb item){
       
        return "AuctionDashboard.jsf";
    }
    public String updateAuction(){
        aucDetailejb.editAuction(auctiondetail);
        showAuctionsByOrganizer();
        System.gc();
        return "home.jsf";
    }
    public void onPageLoad()
    {
        Organizerid = KeepRecord.getUserid();
         rs = client.getOwners(Response.class);
        ownersList = (List<Teamownermaster>) rs.readEntity(oglist);
    }
   
public String deleteTournament(int id){
   tejb.removeTournament(id);
   System.gc();

    return "viewTournaments.jsf";
}
public String loadTournamentUpdateData(Tournamenttb t1){
    tempTournament = t1;
    return "updateTournament.jsf";
}
public String updateTournament(){
    tejb.editTournament(tempTournament);
    return "viewTournaments.jsf";
}
public String createWhatsappLink(int id){
  String link=  "https://wa.me/send?text=Register%20for%20Tournament%0ATournament%20ID%3A%20";
  link=link.concat(String.valueOf(id));
  return link;
}
public String loadplayersByTeam(int teamid){
tempteamid= teamid;
return "PlayerList.jsf";
}
public List<Soldplayertb> getPlayersByTeam(){
    return soldejb.getSoldPlayersByTeam(tempteamid);
}
}
