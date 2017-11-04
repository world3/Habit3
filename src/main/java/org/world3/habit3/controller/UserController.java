package org.world3.habit3.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.world3.habit3.entity.User;
import org.world3.habit3.service.UserService;

@RestController
@RequestMapping("/habit3")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(
            path="/register",
            method= RequestMethod.POST,
            consumes="application/json",
            produces = "application/json"
    )
    public void register(@RequestBody User user) {
        userService.create(user);
    }

}
