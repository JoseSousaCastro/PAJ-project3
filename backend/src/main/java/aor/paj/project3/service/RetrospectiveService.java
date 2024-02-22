package aor.paj.project3.service;

import aor.paj.project3.bean.RetrospectiveBean;
import aor.paj.project3.bean.UserBean;
import aor.paj.project3.dto.RetrospectiveDto;
import aor.paj.project3.dto.CommentDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("/retrospective")
public class RetrospectiveService {

    @Inject
    RetrospectiveBean retrospectiveBean;
    @Inject
    UserBean userBean;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRetrospectives(@HeaderParam("username") String username, @HeaderParam("password") String password) {
        Response response;
        if (!userBean.isAuthenticated(username, password)) {
            response = Response.status(401).entity("Invalid credentials").build();
        } else {
            List<RetrospectiveDto> retrospectives = retrospectiveBean.getRetrospectives();
            response = Response.status(200).entity(retrospectives).build();
        }
        return response;
    }

    @GET
    @Path("/{id}/allComments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComments(@HeaderParam("username") String username, @HeaderParam("password") String password, @PathParam("id") String id) {
        Response response;
        if (!userBean.isAuthenticated(username, password)) {
            response = Response.status(401).entity("Invalid credentials").build();
        } else {
            List<CommentDto> comments = retrospectiveBean.getComments(id);
            if (comments == null) {
                response = Response.status(404).entity("Retrospective with this id not found").build();
            } else {
                response = Response.status(200).entity(comments).build();
            }
        }
        return response;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRetrospective(@HeaderParam("username") String username, @HeaderParam("password") String password, @PathParam("id") String id) {
        Response response;
        if (!userBean.isAuthenticated(username, password)) {
            response = Response.status(401).entity("Invalid credentials").build();
        } else {
            RetrospectiveDto retrospective = retrospectiveBean.getRetrospective(id);
            if (retrospective == null) {
                response = Response.status(404).entity("Retrospective with this id not found").build();
            } else {
                response = Response.status(200).entity(retrospective).build();
            }
        }
        return response;
    }

    @GET
    @Path("/{id}/comment/{id2}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComment(@HeaderParam("username") String username, @HeaderParam("password") String password, @PathParam("id") String id, @PathParam("id2") String id2) {
        Response response;
        if (!userBean.isAuthenticated(username, password)) {
            response = Response.status(401).entity("Invalid credentials").build();
        } else {
            if (id == null) {
                response = Response.status(400).entity("Invalid retrospective id").build();
            } else {
                CommentDto comment = retrospectiveBean.getComment(id, id2);
                if (comment == null) {
                    response = Response.status(404).entity("Comment with this id not found").build();
                } else {
                    response = Response.status(200).entity(comment).build();
                }
            }
        }
        return response;
    }


    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRetrospective(@HeaderParam("username") String username, @HeaderParam("password") String
            password, RetrospectiveDto temporaryRetrospective) {
        Response response;
        if (!userBean.isAuthenticated(username, password)) {
            response = Response.status(401).entity("Invalid credentials").build();
        } else {
            boolean added = retrospectiveBean.addRetrospective(temporaryRetrospective);
            if (!added) {
                response = Response.status(400).entity("Retrospective not created. Verify all fields").build();
            } else {
                response = Response.status(200).entity("Retrospective created successfuly").build();
            }
        }
        return response;
    }


    @POST
    @Path("/{id}/addComment")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newComment(@HeaderParam("username") String username, @HeaderParam("password") String
            password, @PathParam("id") String id, CommentDto temporaryComment) {
        Response response;
        if (!userBean.isAuthenticated(username, password)) {
            response = Response.status(401).entity("Invalid credentials").build();
        } else {
            boolean added = retrospectiveBean.addCommentToRetrospective(id, temporaryComment);
            if (!added) {
                response = Response.status(400).entity("Comment not created. Verify all fields").build();
            } else {
                response = Response.status(200).entity("Comment created successfuly").build();
            }
        }
        return response;
    }
}
