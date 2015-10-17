package core;

import models.Friend;
import play.db.jpa.JPA;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

import static core.PlayPropertiesHelper.*;

/**
 * Created by nue on 6.10.2015.
 */
public class BackendDao {

    /**
     *
     * Why blocking ?
     *
     * with Play we can take
     *
     * -> JPA : Functional-JPA -> at the end also blocking, but lazy
     *      https://github.com/davidmoten/functional-jpa
     * -> reactive streams mongo db or something else -> for streams Play dont have Java API
     *      http://www.reactive-streams.org/
     * -> stream -> Observable (RxJava) -> Enumerators and Iteratees
     *      http://bryangilbert.com/blog/2013/10/22/rxPlay-making-iteratees-and-observables-play-nice/
     *      http://francois.farquet.com/Observables_for_Play.pdf
     */


    public Friend selectOneFriend(Long id) {
        Delay.mockCrashAndDelay(getSelectOneException(), getSelectOneDelay());
        EntityManager em = JPA.em("default");
        Query q = em.createQuery("select f from Friend f where f.id=?1", Friend.class);
        q.setParameter(1, id);

        // its really "awesome" for JPA hibernate that if i dont have result
        // it punch to my face one big exception"
        Friend result = null;
        try {
            result = (Friend) q.getSingleResult();
        } catch (NoResultException ex) {
            // nothing needed here
        }
        em.close();
        return result;
    }

    public List<Friend> selectAllFriends() {
        Delay.mockCrashAndDelay(getSelectAllException(), getSelectAllDelay());
        EntityManager em = JPA.em("default");
        List<Friend> result = em.createQuery("SELECT e FROM Friend e").getResultList();
        em.close();
        return result;
    }


    public boolean addOneFriend(Friend friend) {
        Delay.mockCrashAndDelay(getAddOneException(), getAddOneDelay());
        EntityManager em = JPA.em("default");
        em.getTransaction().begin();
        em.persist(friend);
        em.getTransaction().commit();
        em.close();

        return true; //TODO not so good
    }

    public boolean deleteOneFriend(long id) {
        EntityManager em = JPA.em("default");
        em.getTransaction().begin();
        Query q = em.createQuery("delete from Friend f where f.id=?1");
        q.setParameter(1, id);
        int changed = q.executeUpdate();
        em.getTransaction().commit();
        em.close();
        return changed > 0;
    }

}
