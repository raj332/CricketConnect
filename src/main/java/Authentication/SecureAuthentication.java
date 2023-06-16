/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Authentication;



import static Authentication.JwtConstants.BEARER;
import static Authentication.JwtConstants.ROLE_AUCTIONEER;
import static Authentication.JwtConstants.ROLE_ORGANIZER;
import static Authentication.JwtConstants.ROLE_PLAYER;
import static Authentication.JwtConstants.ROLE_TEAMOWNER;
import static Authentication.JwtConstants.TOKEN;
import io.jsonwebtoken.ExpiredJwtException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.CredentialValidationResult.Status;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Named
@RequestScoped
public class SecureAuthentication implements HttpAuthenticationMechanism {

    @Inject
    IdentityStoreHandler handler;
    CredentialValidationResult result;

    @Inject
    TokenProvider tokenProvider;
    String token;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext context) throws AuthenticationException {
        try {
            if (request.getRequestURI().contains("logout")) {
                request.logout();
                KeepRecord.reset();
                request.getSession().removeAttribute(TOKEN);
                clearTokenFromCookie(request, response);
                response.sendRedirect("/ps8/login.jsf");
                return context.doNothing();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (KeepRecord.getToken() != null) {
            token = KeepRecord.getToken();
        }

        try {
            if (token == null && request.getParameter("userId") != null) {
                String userId = request.getParameter("userId");
                String password = request.getParameter("password");

                Credential credential = new UsernamePasswordCredential(userId, new Password(password));
                result = handler.validate(credential);

                if (result.getStatus() == Status.VALID) {
                    KeepRecord.setErrorStatus(null);
                    AuthenticationStatus status = createToken(result, context);

                    status = context.notifyContainerAboutLogin(result);

                    KeepRecord.setPrincipal(result.getCallerPrincipal());
                    KeepRecord.setRoles(result.getCallerGroups());
                    KeepRecord.setCredential(credential);
                    KeepRecord.setUserid(userId);
                    
                    if (result.getCallerGroups().contains(ROLE_PLAYER)) {
                        response.sendRedirect("/ps8/player/home.jsf");
                    }
                    if(result.getCallerGroups().contains(ROLE_AUCTIONEER)){
                        response.sendRedirect("/ps8/auctioneer/home.jsf");
                    }
                    if (result.getCallerGroups().contains(ROLE_ORGANIZER)) {
                        response.sendRedirect("/ps8/organizer/home.jsf");
                    }
                    if (result.getCallerGroups().contains(ROLE_TEAMOWNER)) {
                        response.sendRedirect("/ps8/teamowner/home.jsf");
                    }
                    return status;
                } else {
                    KeepRecord.setErrorStatus("Either userid or password is wrong!");
                    response.sendRedirect("/ps8/login.jsf");
                    return context.doNothing();
                }
            }
            if (request.getSession().getAttribute(TOKEN) != null && ((request.getRequestURI().contains("Login")) || request.getRequestURI().equals("/TheLandmarkTour/"))) {
                if (KeepRecord.getRoles().contains(ROLE_PLAYER)) {
                    response.sendRedirect("/ps8/player/home.jsf");
                }
                if(KeepRecord.getRoles().contains(ROLE_AUCTIONEER)){
                        response.sendRedirect("/ps8/auctioneer/home.jsf");
                    }
                if (KeepRecord.getRoles().contains(ROLE_ORGANIZER)) {
                    response.sendRedirect("/ps8/organizer/home.jsf");
                }
                if (KeepRecord.getRoles().contains(ROLE_TEAMOWNER)) {
                    response.sendRedirect("/ps8/teamowner/home.jsf");
                }
            }

        } catch (Exception ex) {
            System.out.println("Exception occured  " + ex.getMessage());
            ex.printStackTrace();
        }

        if (KeepRecord.getToken() != null) {
            context.notifyContainerAboutLogin(KeepRecord.getPrincipal(), KeepRecord.getRoles());
        }

        if (token != null) {
            return validateToken(token, context);
        } else if (context.isProtected()) {
            return context.responseUnauthorized();
        }
        return context.doNothing();
    }

    private AuthenticationStatus validateToken(String token, HttpMessageContext context) {
        try {
            if (tokenProvider.validateToken(token)) {
                JwtCredential credential = tokenProvider.getCredential(token);
                return context.notifyContainerAboutLogin(credential.getPrincipal(), credential.getAuthorities());
            }
            return context.responseUnauthorized();
        } catch (ExpiredJwtException ex) {
            System.out.println("Token expired exception occured!");
            ex.printStackTrace();
            return context.responseUnauthorized();
        }
    }

    private AuthenticationStatus createToken(CredentialValidationResult result, HttpMessageContext context) {
        String jwt = tokenProvider.createToken(result.getCallerPrincipal().getName(), result.getCallerGroups(), false);
        KeepRecord.setToken(jwt);
        Cookie cookie = new Cookie(TOKEN, BEARER + jwt);
        cookie.setPath("/ps8");
        context.getResponse().addCookie(cookie);
        context.getRequest().getSession().setAttribute(TOKEN, BEARER + jwt);
        return context.notifyContainerAboutLogin(result.getCallerPrincipal(), result.getCallerGroups());
    }

    private String extractToken(HttpMessageContext context) {
        return context.getRequest().getSession().getAttribute(TOKEN).toString();
    }

    public void clearTokenFromCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(TOKEN)) {
                    c.setValue("");
                    c.setMaxAge(0);
                    c.setPath("/TheLandmarkTour");
                    response.addCookie(c);
                    return;
                }
            }
        }
        return;
    }

    public boolean isCookieExist(HttpMessageContext context) {
        Cookie[] cookies = context.getRequest().getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(TOKEN)) {
                    return true;
                }
            }
        }
        return false;
    }
}