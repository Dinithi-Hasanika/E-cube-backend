package ecube.controller;

import ecube.Service.UserService;
import ecube.entity.User;
import ecube.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController
{
    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/users")
    List<User> getUsers(){
        return userService.getUsers();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/user")
    User createUser( @RequestBody User user ){
    user.setId( 0 );
    if(!StringUtils.stringNullorEmpty( user.getUserId() ) && !StringUtils.stringNullorEmpty( user.getEmail() ) && !StringUtils.stringNullorEmpty( user.getPassword() ) )
    {
        return userService.saveUser( user );
    }
    return null;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/user")
    void deleteUser(@RequestBody String userId){
       userService.deleteUser( userId );
    }
}
