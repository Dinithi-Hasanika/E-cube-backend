package ecube.Service;

import ecube.DAO.UserDAO;
import ecube.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserDAO userDAO;


    @Override public List<User> getUsers()
    {
        return userDAO.getUsers();
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
