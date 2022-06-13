package io.github.thiago.melo.quarkussocial.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "POSTS", schema = "public")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_text")
    private String post_text;

    @Column(name = "dateTime")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User user;

}
