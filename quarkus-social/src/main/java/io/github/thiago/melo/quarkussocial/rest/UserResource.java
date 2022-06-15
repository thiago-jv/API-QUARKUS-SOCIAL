package io.github.thiago.melo.quarkussocial.rest;

import io.github.thiago.melo.quarkussocial.domain.model.User;
import io.github.thiago.melo.quarkussocial.repository.UserRepository;
import io.github.thiago.melo.quarkussocial.rest.dto.CreateUserRequest;
import io.github.thiago.melo.quarkussocial.rest.dto.ResponseError;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "/users", description = "API cadastro de usuário")
public class UserResource {

    private static final Logger LOG = Logger.getLogger(UserResource.class);

    private UserRepository repository;
    private Validator validator;

    @Inject
    public UserResource(UserRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Operation(description = "API responsável por criar um usuário")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Retorno OK."),
            @APIResponse(responseCode = "404", description = "Recurso não encontrado")})
    @POST
    @Transactional
    public Response createUser(@Context UriInfo uriInfo, CreateUserRequest userRequest) {
        LOG.info("Criando usuário " +userRequest.getName());
        Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(userRequest);
        if (!violations.isEmpty()) {
            return ResponseError.createFromValidation(violations).withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);
        }

        User user = new User();
        user.setAge(userRequest.getAge());
        user.setName(userRequest.getName());
        repository.persist(user);

        return Response.status(
                Response.Status.CREATED.getStatusCode())
                .entity(user)
                .build();
    }

    @Operation(description = "API responsável por buscar todos os usuários")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Retorno OK"),
            @APIResponse(responseCode = "404", description = "Recurso não encontrado")})
    @GET
    public Response listAllUsers() {
        LOG.info("Lista todos os usuarios");
        Optional<User> query = repository.findAllUsers();
        return Response.ok(query).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteUser(@PathParam("id") Long id) {
        LOG.info("Deletando usuário por id " +id);
        User user = repository.findById(id);
        if (Objects.nonNull(user)) {
            repository.delete(user);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Operation(description = "API responsável por atualizar o usuário")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Retorno OK"),
            @APIResponse(responseCode = "404", description = "Recurso não encontrado")})
    @PUT
    @Path("{id}")
    @Transactional
    public Response updateUser(@PathParam("id") Long id, CreateUserRequest userData) {
        LOG.info("Atualizando usuário por id " +id);
        User user = repository.findById(id);

        if (Objects.nonNull(user)) {
            user.setName(userData.getName());
            user.setAge(userData.getAge());
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
