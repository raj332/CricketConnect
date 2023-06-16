/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Authentication;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

@DatabaseIdentityStoreDefinition(
 dataSourceLookup = "auctionJNDI",
        callerQuery = "select password from `projectusers` where userId = ?",
        groupsQuery = "select groupName from `projectgroups` where userId = ?",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        priority = 30)
@SessionScoped
public class IdentityStore implements Serializable{
  
}

