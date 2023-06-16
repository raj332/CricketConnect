/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package composite;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Bhatt Jaimin
 */
@Named
@ViewScoped
public class Bid implements Serializable {

    String logo;
    double currentBid;
    private int timer =120;

    public int getTimer() {
        return timer;
    }

    

    public void decrement() {
        System.out.println("timer : "+timer);
            timer--;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public double getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(double currentBid) {
        this.currentBid = currentBid;
    }

    public Bid() {
        
    }

    public Bid(String logo, double currentbid,int timer) {
        this.logo = logo;
        this.currentBid = currentbid;
        this.timer = timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
}
