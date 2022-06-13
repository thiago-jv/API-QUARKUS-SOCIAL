package io.github.thiago.melo.quarkussocial.domain.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "USUARIO", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

}
