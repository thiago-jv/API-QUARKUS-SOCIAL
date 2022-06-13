package io.github.thiago.melo.quarkussocial.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "FOLLOWER", schema = "public")
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;
}
