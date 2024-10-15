package ressources;

import entities.Logement;
import filtres.Secured;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Path("logements")
public class LogementRessources {
    /*
    public static LogementBusiness logementMetier = new LogementBusiness();

@POST
@Consumes(MediaType.APPLICATION_JSON)
    public Response addLogement(Logement l) {
     if(logementMetier.addLogement(l))
         return  Response.status(Status.CREATED).build();
     return  Response.status(Status.NOT_FOUND).build();
    }

@Secured
@GET
@Produces(MediaType.APPLICATION_JSON)
    public Response getLogements() {
        List<Logement> liste=logementMetier.getLogements();
    if(liste.size()==0)
        return  Response.status(Status.NOT_FOUND).build();
    return  Response.status(Status.OK).entity(liste).build();


    }

/*@PUT
@Consumes(MediaType.APPLICATION_JSON)
    public Response updateLogement(Logement updatedLogement, int reference) {


        if (logementMetier.updateLogement(reference,updatedLogement)) {
            return Response.status(Status.OK).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

@DELETE
        public  Response deleteLogement(int reference){
           if(logementMetier.deleteLogement(reference))
                    return Response.status(Status.OK).build();


            return Response.status(Status.NOT_FOUND).build();

        }
*/

    public static LogementBusiness logb = new LogementBusiness();
@POST
@Consumes("application/xml")
//@Consumes("application/xml")
public Response ajouterLogement(Logement l){
    if (logb.addLogement(l)) {
        return Response.status(Response.Status.CREATED).build();
    }else {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
    @Secured
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //methode de type get avec identifiant /logements
    public Response listlogementsbydeleguation(@QueryParam(value = "delegation") String delegation,
                                               @QueryParam(value = "reference") String reference) {
        List list =new ArrayList<Logement>();
        if(delegation==null && reference==null) {
            list = logb.getLogements();
        }
        if(reference==null && delegation!=null) {
            list = logb.getLogementsByDeleguation(delegation);
        }
        if(delegation==null && reference!=null) {
            list = logb.getLogementsListeByref(Integer.parseInt(reference));
        }
        if (list.size()!=0)
            return Response.status(Response.Status.CREATED).entity(list).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Produces("application/xml")
    @Path("/{reference}")
    public Response deleteLogement(@PathParam(value = "reference") int reference) {
        if (logb.deleteLogement(reference)) {
            return Response.status(Response.Status.OK).entity("seccess").build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).entity("echec").build();
        }
    }

    @PUT
    @Consumes("application/xml")
    @Path("/{id}")
    public Response modifierLogement(@PathParam("id") int reference, Logement logement) {
        if (logb.updateLogement(reference, logement)) {
            return Response.status(Response.Status.OK).entity("seccess").build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).entity("echec").build();
        }
    }

}
