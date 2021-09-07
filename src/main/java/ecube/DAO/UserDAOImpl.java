package ecube.DAO;

import ecube.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO
{

    @Autowired
    private SessionFactory sessionFactory;

    @Override public List<User> getUsers()
    {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query usersQuery = session.createQuery( "from User" );
        List<User> users = (List<User>) usersQuery.list();
        transaction.commit();
        return users;
    }

    @Override public User getUser()
    {
        return null;
    }

    @Override public User saveUser( User user )
    {
        return null;
    }

    @Override public void deleteUser( int id )
    {

    }

    @Override public User updateUser( User user )
    {
        return null;
    }
}
