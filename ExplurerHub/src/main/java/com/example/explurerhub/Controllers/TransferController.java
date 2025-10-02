package com.example.explurerhub.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransferController {
    @GetMapping("/about")
    public String showAbout(){
        return "about";
    }
    @GetMapping("/where-to-go")
    public String showWhereToGo(){
        return "where-to-go";
    }
    @GetMapping("/what-to-do")
    public String showWhatToDo(){
        return "what-to-do";
    }
    @GetMapping("/whats-on")
    public String showWhatsOn(){
        return "whats-on";
    }
    @GetMapping("/useful-info")
    public String showUsefulInfo(){
        return "useful-info";
    }
    @GetMapping("/cities")
    public String showCities(){
        return "cities";
    }
    @GetMapping("/nile")
    public String showNile(){
        return "nile";
    }
    @GetMapping("/red-sea")
    public String showRedSea(){
        return "red-sea";
    }
    @GetMapping("/med")
    public String showMed(){
        return "med";
    }
    @GetMapping("/deserts")
    public String showDeserts(){
        return "deserts";
    }
    @GetMapping("/sun-sea")
    public String showSunSea(){
        return "sun-and-sea";
    }
    @GetMapping("/archaeological-sites")
    public String showArchaeologicalSites(){
        return "archaeological-sites";
    }
    @GetMapping("/adventure-outdoor")
    public String showAdventureOutdoor(){
        return "adventure-outdoor";
    }
    @GetMapping("/arts-culture")
    public String showArtsCulture(){
        return "arts-culture";
    }
}
