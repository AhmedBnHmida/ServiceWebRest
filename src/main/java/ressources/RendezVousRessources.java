package ressources;

import entities.Logement;
import entities.RendezVous;
import metiers.LogementBusiness;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("RendezVous")
public class RendezVousRessources {
/*
    public static RendezVousBusiness rendezVousMetier = new RendezVousBusiness();


    public Response addrendezVous(RendezVous r) {
        if(rendezVousMetier.addRendezVous(r))
            return  Response.status(Response.Status.CREATED).build();
        return  Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    public Response getRendezVous(String refLogement) {
        List<RendezVous> liste=new ArrayList<RendezVous>();
        if(refLogement != null) {
            liste = rendezVousMetier.getListeRendezVousByLogementReference(Integer.parseInt(refLogement));

        } else {
            liste = rendezVousMetier.getListeRendezVous();
        }

        if(liste.size()==0)
            return  Response.status(Response.Status.NOT_FOUND).build();
        return  Response.status(Response.Status.OK).entity(liste).build();
    }


    public Response updateRdv(RendezVous updatedRendezVous, @PathParam("id") int id) {


        if (rendezVousMetier.updateRendezVous(id,updatedRendezVous)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    public  Response deleteRendezVous(int id){
        if(rendezVousMetier.deleteRendezVous(id))
            return Response.status(Response.Status.OK).build();


        return Response.status(Response.Status.NOT_FOUND).build();

    }

    public  Response getRendezVousbyId(int id){
        if(rendezVousMetier.getRendezVousById(id)!=null)
            return Response.status(Response.Status.OK).entity(rendezVousMetier.getRendezVousById(id)).build();


        return Response.status(Response.Status.NOT_FOUND).build();

    }
    */

    public static RendezVousBusiness logb= new RendezVousBusiness();

    @POST
    @Consumes("application/json")
    //@Consumes("application/xml")
    public Response ajouterRendezVous(RendezVous l){
        if (logb.addRendezVous(l)) {
            return Response.status(Response.Status.CREATED).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
/*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    //methode de type get avec identifiant /logements
    public Response listRendezVous(@QueryParam(value = "logement") String logement,
                                   @PathParam(value = "id") String id) {
        List list =new ArrayList<Logement>();
        if(logement==null && id==null) {
            list = logb.getListeRendezVous();
        }
        if(id==null && logement!=null) {

            list = logb.getListeRendezVousByLogementReference(Integer.parseInt(logement));
        }
        if(logement==null && id!=null) {
            list = logb.getRendezVousListeByref(Integer.parseInt(id));
            //list = logb.getListeRendezVousByLogementReference(Integer.parseInt(reference));
        }
        if (list.size()!=0)
            return Response.status(Response.Status.CREATED).entity(list).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
*/

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //@Path("/{ref}")
    //methode de type get avec identifiant /logements
    public Response listRendezVous(@QueryParam(value = "logement") String logement,
                                   @QueryParam(value = "ref") String id) {
        List list =new ArrayList<Logement>();
        if(logement==null && id==null) {
            list = logb.getListeRendezVous();
        }
        if(id==null && logement!=null) {

            list = logb.getListeRendezVousByLogementReference(Integer.parseInt(logement));
        }
        if(logement==null && id!=null) {
            list = logb.getRendezVousListeByref(Integer.parseInt(id));
            //list = logb.getListeRendezVousByLogementReference(Integer.parseInt(reference));
        }
        if (list.size()!=0){
            return Response.status(Response.Status.CREATED).entity(list).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @DELETE
    @Produces("application/xml")
    @Path("/{reference}")
    public Response deleteRendezVous(@PathParam(value = "reference") int reference) {
        if (logb.deleteRendezVous(reference)) {
            return Response.status(Response.Status.OK).entity("seccess").build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).entity("echec").build();
        }
    }

    @PUT
    @Consumes("application/json")
    @Path("/{id}")
    public Response modifierRendezVous(@PathParam("id") int reference, RendezVous rendezVous) {
        if (logb.updateRendezVous(reference, rendezVous)) {
            return Response.status(Response.Status.OK).entity("seccess").build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).entity("echec").build();
        }
    }
}
