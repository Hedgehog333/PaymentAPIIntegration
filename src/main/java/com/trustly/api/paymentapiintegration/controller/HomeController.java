package com.trustly.api.paymentapiintegration.controller;

import com.trustly.api.paymentapiintegration.models.Attributes;
import com.trustly.api.paymentapiintegration.models.Deposit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("deposit", new Deposit());
        model.addAttribute("attributes", new Attributes());

        return "formpage";
    }
}