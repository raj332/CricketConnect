/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author praj4
 */
@Entity
@Table(name = "tournamentplayerslist")
@NamedQueries({
    @NamedQuery(name = "Tournamentplayerslist.findAll", query = "SELECT t FROM Tournamentplayerslist t"),
    @NamedQuery(name = "Tournamentplayerslist.findByPlayerId", query = "SELECT t FROM Tournamentplayerslist t WHERE t.playerId = :playerId"),
    @NamedQuery(name = "Tournamentplayerslist.findByPlayerStatus", query = "SELECT t FROM Tournamentplayerslist t WHERE t.playerStatus = :playerStatus"),
    @NamedQuery(name = "Tournamentplayerslist.findByIsApproved", query = "SELECT t FROM Tournamentplayerslist t WHERE t.isApproved = :isApproved")})
public class Tournamentplayerslist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tpid")
    private Integer tpid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "playerStatus")
    private String playerStatus;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "isApproved")
    private boolean isApproved;
    @JoinColumn(name = "tournamentId", referencedColumnName = "tournamentId")
    @ManyToOne(optional = false)
    private Tournamenttb tournamentId;
    @JoinColumn(name = "playerId", referencedColumnName = "playerId")
    @OneToOne(optional = false)
    private Playermaster playerId;

    public Tournamentplayerslist() {
    }

    

    public Tournamentplayerslist(String playerId, String playerStatus, boolean isApproved) {
       
        this.playerStatus = playerStatus;
        this.isApproved = isApproved;
    }

    



    public String getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(String playerStatus) {
        this.playerStatus = playerStatus;
    }

    public boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public Tournamenttb getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Tournamenttb tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Playermaster getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Playermaster playerId) {
        this.playerId = playerId;
    }

  

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(int tpid) {
        this.tpid = tpid;
    }

 

  

  
    
}
