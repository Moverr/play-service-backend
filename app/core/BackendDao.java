package core;

import models.Friend;
import play.db.jpa.JPA;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by nue on 6.10.2015.
 */
public class BackendDao {


    public Friend selectOneFriend(Long id, boolean exception, long delay) {
        Delay.mockCrashAndDelay(exception, delay);
        EntityManager em = JPA.em("default");
        Query q = em.createQuery("select f from Friend f where f.id=?1", Friend.class);
        q.setParameter(1, id);
        em.close();
        return (Friend) q.getSingleResult();
    }

    public List<Friend> selectAllFriends(boolean exception, long delay) {
        Delay.mockCrashAndDelay(exception, delay);
        EntityManager em = JPA.em("default");
        List<Friend> result = em.createQuery("SELECT e FROM Friend e").getResultList();
        em.close();
        return result;
    }


    public boolean addOneFriend(Friend friend, boolean exception, long delay) {
        Delay.mockCrashAndDelay(exception, delay);
        EntityManager em = JPA.em("default");
        em.getTransaction().begin();
        em.persist(friend);
        em.getTransaction().commit();
        em.close();

        return true; //TODO not so good
    }

    public boolean deleteOneFriend(long id, boolean exception, long delay) {
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
