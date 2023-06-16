/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package restServices;

import entities.Teammaster;
import entities.Teamownermaster;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import serverBeans.teamOwnerEJBLocal;


@Path("owner")
@RequestScoped
public class OwnerRest {

  @EJB teamOwnerEJBLocal ownerejb;
   
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOwner(Teamownermaster u1){
        try{
            if(ownerejb.register(u1)){
                return Response.ok().status(200).build();
            }else{
                return Response.ok().status(404).build();
            }   
             
        }catch(Exception ex){
             return Response.ok().status(404).build();
        }
       
    }

    @GET
     @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOwner(@PathParam("id") String oid ) {
        try{
           Teamownermaster org = ownerejb.getOwner(oid);
           return Response.status(200).entity(org).build();
           
        }catch(Exception ex){
            return Response.status(404).build();
        }
    }
   
    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOwner(Teamownermaster owner) {
        try{
            ownerejb.editOwner(owner);
            return Response.status(200).build();
            
        }catch(Exception ex){
            return Response.status(404).build();
        }
        
    }
    @DELETE
    @Path("delete/{id}")
    public Response deleteOwner(@PathParam("id") String ownerid) {
      try{
          if(ownerejb.deleteOwner(ownerid)){
             return Response.status(200).build();
          }else{
            return Response.status(404).build();
          }
      }catch(Exception ex){
            return Response.status(404).build();
        }
    }
}
