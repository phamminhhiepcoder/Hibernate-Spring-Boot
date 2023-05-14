package com.t3h.controller;

import com.t3h.entity.User;
import com.t3h.service.IUserService;
import jakarta.jws.WebParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {


    /*
        http
            get: su dung khi client muon lay du lieu tu server
            post: tao moi du lieu
            put: update
            delete: xoa
            option
     */

    private IUserService iUserService;

    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @GetMapping()
    public String getUsers(Model model){
        model.addAttribute("nameClass","ljava2209");
        List<User> users = iUserService.getAllUser();
        model.addAttribute("users",users);
        return "user";
    }
    @GetMapping("/form-create-user")
    public String getFormCreateUser(Model model) {
        User userObject = new User();
        model.addAttribute("user", userObject);
        return "user-create";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute(name = "user") User user, Model model) {
        iUserService.saveOrUpdate(user);
        return "redirect:/user";
    }
    @GetMapping("/form-update-user/{id}")
    public String findUserById(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("user", iUserService.findUserByID(id));
        return "user-update";
    }
    @PostMapping("/update/{id}")
    public String updateUser(@ModelAttribute(name = "user") User user) {
        iUserService.saveOrUpdate(user);
        return "redirect:/user";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") int id) {
        iUserService.delete(id);
        return "redirect:/user";
    }
}
