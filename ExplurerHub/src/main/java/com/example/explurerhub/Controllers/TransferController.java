package com.example.explurerhub.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransferController {
    @GetMapping("/about")
    public String showAbout() {
        return "about";
    }

    @GetMapping("/where-to-go")
    public String showWhereToGo() {
        return "where-to-go";
    }

    @GetMapping("/what-to-do")
    public String showWhatToDo() {
        return "what-to-do";
    }

    @GetMapping("/whats-on")
    public String showWhatsOn() {
        return "whats-on";
    }

    @GetMapping("/useful-info")
    public String showUsefulInfo() {
        return "useful-info";
    }

    @GetMapping("/cities")
    public String showCities() {
        return "cities";
    }

    @GetMapping("/nile")
    public String showNile() {
        return "nile";
    }

    @GetMapping("/red-sea")
    public String showRedSea() {
        return "red-sea";
    }

    @GetMapping("/med")
    public String showMed() {
        return "med";
    }

    @GetMapping("/deserts")
    public String showDeserts() {
        return "deserts";
    }

    @GetMapping("/sun-sea")
    public String showSunSea() {
        return "sun-and-sea";
    }

    @GetMapping("/abu-simble")
    public String showAbuSimble() {
        return "abu-simble";
    }
    @GetMapping("/old-alexandria")
    public String showOldAlexandria() {
        return "alexandria-old";
    }
    @GetMapping("/aswan-city")
    public String showAswanCity() {
        return "aswan-city";
    }
    @GetMapping("/aswan-food")
    public String showAswanFood() {
        return "aswan-food";
    }
    @GetMapping("/aswan-mosques")
    public String showAswanMosques() {
        return "aswan-mosques";
    }
    @GetMapping("/aswan-museums")
    public String showAswanMuseums() {
        return "aswan-museums";
    }
    @GetMapping("/old-aswan")
    public String showOldAswan() {
        return "aswan-old";
    }
    @GetMapping("/bahariya")
    public String showBahariya() {
        return "bahariya-city";
    }
    @GetMapping("/bahariya-hotels")
    public String showBahariyaHotels() {
        return "bahariya-hotels";
    }
    @GetMapping("/bahariya-transport")
    public String showBahariyaTransport() {
        return "bahariya-transport";
    }
    @GetMapping("/bibliotheca")
    public String showBibliotheca() {
        return "bibliotheca";
    }
    @GetMapping("/cairo-food")
    public String showCairoFood() {
        return "cairo-food";
    }

}

