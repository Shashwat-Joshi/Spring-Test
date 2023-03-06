package wissen.studentdomain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import wissen.studentdomain.models.User;
import wissen.studentdomain.repository.IUserRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    IUserRepository iUserRepository;

    /*
        ENDPOINT: {_BASEURL}/login
        REQUEST METHOD: POST
        login() is a function which will take User as @RequestBody
        and check if the creds are correct then return a relevant String response
    */
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        Optional<User> dbUser = iUserRepository.findById(user.getEmail());

        if(dbUser.isPresent()) {
            if(dbUser.get().getPassword().equals(user.getPassword())) {
                return "LOGGED IN SUCCESSFULLY";
            }
            return "INCORRECT CREDENTIALS";
        }

        return "USER NOT FOUND";
    }

    /*
    ENDPOINT: {_BASEURL}/register
    REQUEST METHOD: POST
    register() is a function which will take User as @RequestBody
    and create a user if it the email is now found in the database
*/
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        Optional<User> dbUser = iUserRepository.findById(user.getEmail());

        if(!dbUser.isPresent()) {
            iUserRepository.save(user);
            return "ACCOUNT CREATED SUCCESSFULLY";
        }

        return user.getEmail() + " ALREADY IN USE";
    }
}



















