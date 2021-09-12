package ecube.Service;

import ecube.entity.User;

import java.util.List;

public interface UserService
{
    List<User> getUsers();

    User getUser();

    User saveUser(User user);

    void deleteUser(String userId);

    User updateUser(User user);
}
