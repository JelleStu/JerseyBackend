package ticketsystem.service.controllers;



import ticketsystem.service.CustomApplicationConfig;
import ticketsystem.service.factory.UserManagerFactory;
import ticketsystem.service.logic.IUserManager;
import ticketsystem.service.model.data.UserDTO;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/user")
public class UserController {

    @Context
    private UriInfo uriInfo;
    IUserManager userManager;

    public UserController(){
        userManager = UserManagerFactory.CreateUserManager(CustomApplicationConfig.DATA_ENVIRONMENT);
    }

    @GET
    @Path("/signin")
    @RolesAllowed({"user", "admin"})
    public Response login(){
        return Response.ok().build();
    }

    @PermitAll
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(UserDTO user){
        if (userManager.register(user)){
            return Response.ok().build();
        }
        return Response.serverError().build();
    }

}
