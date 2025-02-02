package br.com.kaindall.controller;

import br.com.kaindall.domain.entity.UserEntity;
import br.com.kaindall.domain.service.UserService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {this.userService = userService;}

    @GET
    @Path("/liveness")
    public Response healthCheck() {
        String response = "Alive!";
        return Response.ok(response).build();
    }

    @GET
    public Response findAll(@QueryParam("page") @DefaultValue("0") Integer currentPage,
                            @QueryParam("page-size") @DefaultValue("10") Integer pageSize) {
        List<UserEntity> users = userService.findAll(currentPage, pageSize);

        return Response.ok(users).build();
    }

    @POST
    public Response createUser(UserEntity user) {
        return Response.status(Response.Status.CREATED.getStatusCode())
                .entity(userService.createUser(user))
                .build();
    }

    @GET
    @Path("/{id}")
    public Response findUser(@PathParam("id") UUID userId) {
        return Response.ok(userService.findById(userId)).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") UUID userId,
                               UserEntity user) {
        return Response.ok(userService.updateUser(userId, user)).build();
    }



    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") UUID userId) {
        userService.deleteUser(userId);
        return Response.noContent().build();
    }
}
