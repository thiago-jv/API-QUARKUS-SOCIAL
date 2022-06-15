package io.github.thiago.melo.quarkussocial.repository;

import io.github.thiago.melo.quarkussocial.domain.model.Follower;
import io.github.thiago.melo.quarkussocial.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@ApplicationScoped
public class FollowerRepository implements PanacheRepository<Follower> {

    @PersistenceContext
    private EntityManager em;

    public boolean follows(User follower, User user) {

        // Map<String, Object> params = new HashMap<>();
        // params.put("follower", follower);
        // params.put("user", user);

        var params = Parameters.with("follower", follower).and("user", user).map();

        PanacheQuery<Follower> query = find("follower =:follower and user =:user ", params);
        Optional<Follower> result = query.firstResultOptional();

        return result.isPresent();
    }

    public List<Follower> findByUser(Long userId){
      var query =  find("user.id", userId);
      return  query.list();
    }

    public void deleteByFollowerAndUser(Long followerId, Long userId) {
      var params = Parameters
              .with("userId", userId)
              .and("followerId", followerId)
                      .map();

        delete("follower.id =:followerId and user.id =:userId", params);
    }
}
