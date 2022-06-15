package io.github.thiago.melo.quarkussocial.repository;

import io.github.thiago.melo.quarkussocial.domain.model.Post;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class PostRepository implements PanacheRepository<Post> {

    @PersistenceContext
    private EntityManager em;

}
