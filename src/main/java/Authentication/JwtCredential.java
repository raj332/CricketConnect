/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Authentication;

import java.io.Serializable;
import java.util.Set;
import javax.security.enterprise.credential.Credential;


public class JwtCredential implements Credential, Serializable {

    private final String principal;
    private final Set<String> authorities;

    public JwtCredential(String principal, Set<String> authorities) {
        this.principal = principal;
        this.authorities = authorities;
    }

    public String getPrincipal() {
        return principal;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

}
