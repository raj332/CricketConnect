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
 * @author Bhatt Jaimin
 */
@Entity
@Table(name = "tournamentplayerslist", catalog = "auctiondb", schema = "")
@NamedQueries({
    @NamedQuery(name = "Tournamentplayerslist.findAll", query = "SELECT t FROM Tournamentplayerslist t"),
    @NamedQuery(name = "Tournamentplayerslist.findByPlayerId", query = "SELECT t FROM Tournamentplayerslist t WHERE t.playerId = :playerId"),
    @NamedQuery(name = "Tournamentplayerslist.findByPlayerStatus", query = "SELECT t FROM Tournamentplayerslist t WHERE t.playerStatus = :playerStatus"),
    @NamedQuery(name = "Tournamentplayerslist.findByIsApproved", query = "SELECT t FROM Tournamentplayerslist t WHERE t.isApproved = :isApproved"),
    @NamedQuery(name = "Tournamentplayerslist.findByTpid", query = "SELECT t FROM Tournamentplayerslist t WHERE t.tpid = :tpid")})
public class Tournamentplayerslist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "playerStatus")
    private String playerStatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isApproved")
    private boolean isApproved;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tpid")
    private Integer tpid;
    @JoinColumn(name = "tournamentId", referencedColumnName = "tournamentId")
    @ManyToOne(optional = false)
    private Tournamenttb tournamentId;
    @JoinColumn(name = "playerId", referencedColumnName = "playerId")
    @ManyToOne(optional = false)
    private Playermaster playerId;

    public Tournamentplayerslist() {
    }

    public Tournamentplayerslist(Integer tpid) {
        this.tpid = tpid;
    }

    public Tournamentplayerslist(Integer tpid, String playerStatus, boolean isApproved) {
        this.tpid = tpid;
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

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tpid != null ? tpid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tournamentplayerslist)) {
            return false;
        }
        Tournamentplayerslist other = (Tournamentplayerslist) object;
        if ((this.tpid == null && other.tpid != null) || (this.tpid != null && !this.tpid.equals(other.tpid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tournamentplayerslist[ tpid=" + tpid + " ]";
    }
    
}
