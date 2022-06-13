package io.github.thiago.melo.quarkussocial.rest.dto;

import io.github.thiago.melo.quarkussocial.domain.model.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponse {

    private String post_text;
    private LocalDateTime dateTime;

    public static PostResponse fromEntity(Post post){
        var reponse = new PostResponse();
        reponse.setPost_text(post.getPost_text());
        reponse.setDateTime(post.getDateTime());
        return reponse;
    }

}
