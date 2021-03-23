package ticketsystem.service.controllers;

import ticketsystem.service.factory.TeamManagerFactory;
import ticketsystem.service.logic.ITeamManager;
import ticketsystem.service.model.data.TeamDTO;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/teams")
public class TeamController {

    @Context
    private UriInfo uriInfo;
    ITeamManager teamManager;

    public TeamController() {
        teamManager = TeamManagerFactory.CreateTeamManager();
    }

    @GET
    @Path("/hello")
    @DenyAll
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHello(){
        String message = "Yes yes teams page";
        return Response.ok(message).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user", "admin"})
    public Response GetTeams(@QueryParam("name") String name) {
        if (uriInfo.getQueryParameters().containsKey("name")) {
            TeamDTO team = teamManager.getTeamByName(name);
            if (team == null) {
                return Response.serverError().entity("Could not get team you requested.").build();
            } else {
                return Response.ok(team).build();
            }
        } else {
            List<TeamDTO> teams;
            teams = teamManager.getTeams();
            GenericEntity<List<TeamDTO>> entity = new GenericEntity<>(teams) {
            };
            return Response.ok(entity).build();
        }
    }

    @GET
    @Path("{id}")
    @RolesAllowed({"user", "admin"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeamByID(@PathParam("id") int id){
        TeamDTO team = teamManager.getTeamById(id);
        if (team == null){
            return Response.serverError().build();
        }
        return Response.ok(team).build();
    }

    @DELETE
    @Path("{id}")
    @RolesAllowed({"admin"})
    public Response deleteTeam(@PathParam("id") String teamID){
        if (!teamID.equals("")){
            int id = Integer.parseInt(teamID);
            teamManager.deleteTeam(id);
        }
        return Response.noContent().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    public void addTeam(TeamDTO team, @Suspended final AsyncResponse asyncResponse) {
        if (team != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (teamManager.createTeam(team)) {
                            asyncResponse.resume(Response.status(201).build());
                        } else {
                            String entity = "team with teamname: " + team.getName() + " already exist!";
                            asyncResponse.resume(Response.serverError().entity(entity).build());
                        }
                    } catch (final InterruptedException ex) {
                        asyncResponse.cancel();
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    public Response updateTeam(TeamDTO team){
            if (teamManager.updateTeam(team)){
            return Response.noContent().build();
        }
        else{
            return Response.serverError().entity("Team not found!").build();
        }
    }
}
