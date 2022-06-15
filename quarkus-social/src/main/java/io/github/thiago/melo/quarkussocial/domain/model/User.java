package io.github.thiago.melo.quarkussocial.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@Entity
@Table(name = "USUARIO", schema = "public")
public class User extends PanacheEntity {

    @NotNull(message = "Informar o nome do usuário")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Informar a idade do usuário")
    @Column(name = "age")
    private Integer age;

}
