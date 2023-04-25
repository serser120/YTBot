package com.ytbot.website.controller.mvc;

import com.ytbot.website.constants.BotInviteLink;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Hidden
public class MainController {

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("link", BotInviteLink.link);
        return "index";
    }
}
