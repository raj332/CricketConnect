/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package serverBeans;

import entities.Soldplayertb;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Bhatt Jaimin
 */
@Local
public interface soldPlayerEJBLocal {
    List<Soldplayertb> getAllSoldPlayers();
    List<Soldplayertb> getSoldPlayersByTeam(int teamid);
    public boolean addSoldPlayer(Soldplayertb s1);
    boolean removeByTournamentId(int tournamentid);
    List<Soldplayertb> getSoldplayerTeams (String pid);
}
