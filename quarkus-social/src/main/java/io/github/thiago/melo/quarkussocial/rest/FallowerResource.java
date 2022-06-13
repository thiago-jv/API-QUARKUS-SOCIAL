package io.github.thiago.melo.quarkussocial.rest;

import io.github.thiago.melo.quarkussocial.domain.model.Follower;
import io.github.thiago.melo.quarkussocial.repository.FollowerRepository;
import io.github.thiago.melo.quarkussocial.repository.UserRepository;
import io.github.thiago.melo.quarkussocial.rest.dto.FollowerPerUserResponse;
import io.github.thiago.melo.quarkussocial.rest.dto.FollowerResponse;
import io.github.thiago.melo.quarkussocial.rest.dto.FollowerRquest;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@Path("/users/{userId}/followers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FallowerResource {

    private FollowerRepository followerRepository;
    private UserRepository userRepository;

    @Inject
    public FallowerResource(FollowerRepository followerRepository, UserRepository userRepository) {
        this.followerRepository = followerRepository;
        this.userRepository = userRepository;
    }

    @PUT
    @Transactional
    public Response followUser(@PathParam("userId") Long userId, FollowerRquest followerRquest) {

        if(userId.equals(followerRquest.getFollowerId())){
            return Response.status(Response.Status.CONFLICT).entity("You can't your Self").build();
        }

        var user = userRepository.findById(userId);
        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        var follower =  userRepository.findById(followerRquest.getFollowerId());
        boolean follows = followerRepository.follows(follower, user);

        if(!follows){
            var entity = new Follower();
            entity.setUser(user);
            entity.setFollower(follower);

            followerRepository.persist(entity);
        }

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    public Response listFollowers(@PathParam("userId") Long userId){

        var user = userRepository.findById(userId);
        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

       var list = followerRepository.findByUser(userId);
        FollowerPerUserResponse responseObject = new FollowerPerUserResponse();
        responseObject.setFollowersCount(list.size());

        var followerList = list.stream().map(FollowerResponse::new).collect(Collectors.toList());

        responseObject.setContent(followerList);

        return Response.ok(responseObject).build();
    }

    @DELETE
    @Transactional
    public Response unfollowUser(@PathParam("userId") Long userId, @QueryParam("followerId") Long followerId){
        var user = userRepository.findById(userId);
        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        followerRepository.deleteByFollowerAndUser(followerId, userId);

        return Response.status(Response.Status.OK).entity(followerId).build();

    }
}
