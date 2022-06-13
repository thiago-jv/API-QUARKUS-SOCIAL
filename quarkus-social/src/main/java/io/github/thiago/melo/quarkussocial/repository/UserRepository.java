package io.github.thiago.melo.quarkussocial.repository;

import io.github.thiago.melo.quarkussocial.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

}
