package ticketsystem.service.controllers;

import ticketsystem.service.CustomApplicationConfig;
import ticketsystem.service.factory.MatchManagerFactory;
import ticketsystem.service.logic.IMatchManager;
import ticketsystem.service.model.view.MatchView;
import ticketsystem.service.repository.DataStore;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/match")
public class MatchController {

    IMatchManager matchManager;

    public MatchController() {
        matchManager = MatchManagerFactory.createMatchManager(CustomApplicationConfig.DATA_ENVIRONMENT);
    }

    @Context
    private UriInfo uriInfo;

    @PermitAll
    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello() {
        return Response.ok("hello hello").build();
    }

    //This will return matches. If the queryparams are not filled in all matches will be returned.
    @GET
    @RolesAllowed({"user", "admin"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMatches(@QueryParam("hometeam") String hometeam, @QueryParam("awayteam") String awayteam, @QueryParam("team") String teamid) {
        List<MatchView> matches;
        if (uriInfo.getQueryParameters().containsKey("hometeam")) {
            matches = matchManager.getMatchesByHomeTeam(hometeam);
            GenericEntity<List<MatchView>> entity = new GenericEntity<>(matches) {};
            return Response.ok(entity).build();
        } else if (uriInfo.getQueryParameters().containsKey("awayteam")){
            matches = matchManager.getMatchesByAwayTeam(awayteam);
            GenericEntity<List<MatchView>> entity = new GenericEntity<>(matches){};
            return Response.ok(entity).build();
        }else if (uriInfo.getQueryParameters().containsKey("team")) {
            int id = Integer.parseInt(teamid);
            matches = matchManager.getMatchesByTeamId(id);
            GenericEntity<List<MatchView>> entity = new GenericEntity<>(matches) {};
            return Response.ok(entity).build();
        }
        else {
            matches = matchManager.getMatches();
            GenericEntity<List<MatchView>> entity = new GenericEntity<>(matches) {};
            return Response.ok(entity).build();
        }
    }
}
