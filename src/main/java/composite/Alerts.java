/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package composite;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Bhatt Jaimin
 */
public class Alerts {

    public Alerts() {
    }
    
    
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
      public void showInfo(String message) {
        addMessage(FacesMessage.SEVERITY_INFO, "Success", message);
    }
      public void showWarning(String message) {
        addMessage(FacesMessage.SEVERITY_WARN, "Warning", message);
    }
      public void showError(String message) {
        addMessage(FacesMessage.SEVERITY_FATAL, "Error", message);
    }
  
}
