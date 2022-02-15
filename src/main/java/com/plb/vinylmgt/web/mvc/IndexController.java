package com.plb.vinylmgt.web.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

}
