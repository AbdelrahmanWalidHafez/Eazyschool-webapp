package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class DashBoardController {
    @Autowired
     PersonRepository personRepository;
    @RequestMapping("/dashboard")
    public String displayDashBoard(Model model, Authentication authentication, HttpSession session){
        Person person=personRepository.readByEmail(authentication.getName());
        model.addAttribute("username",person.getName());
        model.addAttribute("roles",authentication.getAuthorities().toString());
        if (person.getEazyClass()!=null&&person.getEazyClass().getName()!=null){
            model.addAttribute("enrolledClass",person.getEazyClass().getName());
        }
        session.setAttribute("loggedInPerson",person);
        return "dashboard.html";
    }


}
