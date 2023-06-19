package cdiBeans;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

import Authentication.KeepRecord;
import entities.Projectgroups;
import entities.Projectusers;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import javax.ws.rs.core.Response;
import restClient.commonClient;

/**
 *
 * @author Bhatt Jaimin
 */
@Named(value = "commoncdi")
@RequestScoped
public class commonCdi implements Serializable {
    String Errorstatus ;
    public commonCdi() {
        Errorstatus = KeepRecord.getErrorStatus();
        
    }
       
       


    public String getErrorstatus() {
        return Errorstatus;
    }

    public void setErrorstatus(String Errorstatus) {
        this.Errorstatus = Errorstatus;
    }
    
   
    
}
