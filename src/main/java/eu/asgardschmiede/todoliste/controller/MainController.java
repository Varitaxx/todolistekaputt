package eu.asgardschmiede.todoliste.controller;

import eu.asgardschmiede.todoliste.model.Todoliste;
import eu.asgardschmiede.todoliste.model.User;
import eu.asgardschmiede.todoliste.model.UserDto;
import eu.asgardschmiede.todoliste.repository.TodolisteRepository;
import eu.asgardschmiede.todoliste.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller

public class MainController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TodolisteRepository todolisteRepository;

    @GetMapping("")
    public String index(Model model) {
        return "home";
    }

    @GetMapping({"login", "login/{sub}"})
    public String login(@PathVariable Optional<String> sub, Model model) {
        sub.ifPresent(s -> {
            model.addAttribute(s, true);
        });
        return "login";
    }
    @GetMapping("todoliste")
    public String todoliste( Model model){
        model.addAttribute("tasks", todolisteRepository.findAll());
        return "todoliste";
    }

    @GetMapping("register")
    public String register(UserDto userDto, Model model) {
        return "register";
    }

    @PostMapping("register")
    public String registerProcess(@Valid UserDto userDto, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "register";
        }
        userRepository.save(userDto.convert(passwordEncoder));
        return "redirect:/register/success";
    }

    @GetMapping("register/success")
    public String registerSuccess(UserDto userDto, Model model) {
        model.addAttribute("success", true);
        return "register";
    }

    @GetMapping("new") // th:object aus dem Formular ist todoliste gebunden
    public String newForm( Model model) {
        model.addAttribute("task", new Todoliste());
        return "todoliste-form";
    }

    @PostMapping("save")
    public String save(@Valid Todoliste task, BindingResult result, Model model) {

        // BindingResult enthält das Ergebnis der Validierung
        // todoliste ist mit den Daten aus dem Formular gefüllt
        if(result.hasErrors()) {
            return "todoliste-form";
        }

        todolisteRepository.save(task);
        return "redirect:/todoliste";
    }
}

