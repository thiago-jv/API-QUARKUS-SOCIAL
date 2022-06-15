package io.github.thiago.melo.quarkussocial.rest;

import io.github.thiago.melo.quarkussocial.domain.model.Post;
import io.github.thiago.melo.quarkussocial.domain.model.User;
import io.github.thiago.melo.quarkussocial.repository.FollowerRepository;
import io.github.thiago.melo.quarkussocial.repository.PostRepository;
import io.github.thiago.melo.quarkussocial.repository.UserRepository;
import io.github.thiago.melo.quarkussocial.rest.dto.CreatePostRequest;
import io.github.thiago.melo.quarkussocial.rest.dto.PostResponse;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Path("/users/{userId}/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {

    private static final Logger LOG = Logger.getLogger(PostResource.class);

    private UserRepository userRepository;
    private PostRepository postRepository;
    private FollowerRepository followerRepository;

    @Inject
    public PostResource(UserRepository userRepository,
                        PostRepository postRepository,
                        FollowerRepository followerRepository
    ) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.followerRepository = followerRepository;
    }

    @POST
    @Transactional
    public Response savePost(@PathParam("userId") Long userId, CreatePostRequest postRequest) {
        LOG.info("Criando post");
        User user = userRepository.findById(userId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Post post = new Post();
        post.setPost_text(postRequest.getPost_text());
        post.setUser(user);
        post.setDateTime(LocalDateTime.now());
        postRepository.persist(post);

        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public Response listPosts(@PathParam("userId") Long userId,
                              @HeaderParam("followerId") Long followerId) {
        LOG.info("Buscando post por id do usuário e seguidor");
        User user = userRepository.findById(userId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (followerId == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("You forgot the herader followerId").build();
        }

        User follower = userRepository.findById(followerId);

        if(follower == null){
            return Response.status(Response.Status.BAD_REQUEST).entity("Insxistent followerId").build();
        }

        boolean follows = followerRepository.follows(follower, user);
        if (!follows) {
            return Response.status(Response.Status.FORBIDDEN).entity("You can't see these posts").build();
        }

        PanacheQuery<Post> query = postRepository.find("user", Sort.by("dateTime", Sort.Direction.Descending), user);
        var list = query.list();

        var postRenponseList = list.stream()
                //.map(post -> PostResponse.fromEntity(post))
                .map(PostResponse::fromEntity)
                .collect(Collectors.toList());

        return Response.ok(postRenponseList).build();
    }
}
