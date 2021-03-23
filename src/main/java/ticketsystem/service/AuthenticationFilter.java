package ticketsystem.service;

import ticketsystem.service.factory.RoleManagerFactory;
import ticketsystem.service.factory.UserManagerFactory;
import ticketsystem.service.logic.IRoleManager;
import ticketsystem.service.logic.IUserManager;
import ticketsystem.service.model.data.UserDTO;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.lang.reflect.Method;
import java.util.*;

public class AuthenticationFilter implements ContainerRequestFilter {
    UserDTO user;
    IUserManager userManager = UserManagerFactory.CreateUserManager(CustomApplicationConfig.DATA_ENVIRONMENT);
    IRoleManager roleManager = RoleManagerFactory.createRoleManger();

    /**
     - resourceInfo contains information about the requested operation (GET,
     PUT, POST …).
     - resourceInfo will be assigned/set automatically by the Jersey
     framework, you do not need to assign/set it.
     */
    @Context
    private ResourceInfo resourceInfo;

    // requestContext contains information about the HTTP request message
    @Override
    public void filter(ContainerRequestContext requestContext) {
        final String AUTHORIZATION_PROPERTY = "Authorization";
        final String AUTHENTICATION_SCHEME = "Bearer";

        //Get request headers
        final MultivaluedMap<String, String> headers = requestContext.getHeaders();

        //Fetch authorization header
        final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

        //If no authorization information present: abort with UNAUTHORIZED and stop
        if (authorization == null || authorization.isEmpty()) {
            Response response = Response.status(Response.Status.UNAUTHORIZED).
                    entity("Missing username and/or password.").build();
            requestContext.abortWith(response);
            return;
        }

        //Get encoded username and password
        final String encodedCredentials = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

        //Decode username and password into one string
        String credentials = new String(Base64.getDecoder().decode(encodedCredentials.getBytes()));

        //Split username and password tokens in credentials
        final StringTokenizer tokenizer = new StringTokenizer(credentials, ":");
        final String username = tokenizer.nextToken();
        final String password = tokenizer.nextToken();

        //Check if username and password are valid (e.g., database)
        //If not valid: abort with UNAUTHORIED and stop
        if (!isValidUser(username, password)) {
            Response response = Response.status(Response.Status.UNAUTHORIZED).
                    entity("Invalid username and/or password.").build();
            requestContext.abortWith(response);
            return;
        }

        /* Get information about the service method which is being called. This information includes the annotated/permitted roles. */
        Method method = resourceInfo.getResourceMethod();
        // if access is allowed for all -> do not check anything further : access is approved for all
        if (method.isAnnotationPresent(PermitAll.class)) {
            return;
        }

        // if access is denied for all: deny access
        if (method.isAnnotationPresent(DenyAll.class)) {
            Response response = Response.status(Response.Status.FORBIDDEN).build();
            requestContext.abortWith(response);
            return;
        }


        if (method.isAnnotationPresent(RolesAllowed.class)) {
            // get allowed roles for this method
            RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
            Set<String> rolesSet = new HashSet<>(Arrays.asList(rolesAnnotation.value()));

        /* isUserAllowed : implement this method to check if this user has any of
                           the roles in the rolesSet
           if not isUserAllowed abort the requestContext with FORBIDDEN response*/
          if (!isUserAllowed(rolesSet)) {
                Response response = Response.status(Response.Status.FORBIDDEN).build();
                requestContext.abortWith(response);
            }
        }



    }

    private boolean isUserAllowed(Set<String> rolesSet) {
        String role = roleManager.getRole(user.getRole());
        for (String r : rolesSet){
            if (role.equals(r)){
                return true;
            }
        }
        return false;
    }


    private boolean isValidUser(String username, String password) {
        user = new UserDTO(username, password, 0);
        user = userManager.login(user);
        return userManager.login(user) != null;
    }


}

