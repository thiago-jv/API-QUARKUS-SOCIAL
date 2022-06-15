package io.github.thiago.melo.quarkussocial.repository;

import io.github.thiago.melo.quarkussocial.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Optional<User> findAllUsers(){
       TypedQuery<User> query = (TypedQuery<User>) em.createQuery("from User");
       return query.getResultStream().findAny();
    }
}
