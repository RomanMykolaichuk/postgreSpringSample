/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.e4x.postgrespringsample;

/**
 *
 * @author User
 */
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller    // This means that this class is a Controller

public class TableOneController {

    private final TableOneRepository tableOneRepository;

    @Autowired
    public TableOneController(TableOneRepository tableOneRepository) {
        this.tableOneRepository = tableOneRepository;
    }

    @GetMapping("/signup")
    public String showSignUpForm(TableOne tableOne) {
        return "add-user";
    }

    @PostMapping("/add")
    public String addNewUser(@Valid TableOne tableOne, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        tableOneRepository.save(tableOne);
        model.addAttribute("users", tableOneRepository.findAll());
        return "index";
    }

    @GetMapping("")
    public String initUsers(Model model) {

        model.addAttribute("users", tableOneRepository.findAll());
        return "index";
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {

        model.addAttribute("users", tableOneRepository.findAll());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        TableOne tableOne = tableOneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("tableOne", tableOne);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id, @Valid TableOne tableOne,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            tableOne.setId(id);
            return "update-user";
        }

        tableOneRepository.save(tableOne);
        model.addAttribute("users", tableOneRepository.findAll());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        TableOne tableOne = tableOneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        tableOneRepository.delete(tableOne);
        model.addAttribute("users", tableOneRepository.findAll());
        return "index";
    }
}
