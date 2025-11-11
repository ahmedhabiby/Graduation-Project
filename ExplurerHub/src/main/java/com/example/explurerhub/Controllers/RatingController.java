package com.example.explurerhub.Controllers;

import com.example.explurerhub.Model.Rating;
import com.example.explurerhub.Model.User;
import com.example.explurerhub.Service.RatingService;
import com.example.explurerhub.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RatingController {
    private RatingService ratingService;
    private UserService userService;
    @Autowired
    RatingController(RatingService ratingService){
        this.ratingService = ratingService;
    }
    @PostMapping("/save/rating")
    public String saveRating(@AuthenticationPrincipal UserDetails userDetails, Rating rating){
        User user = userService.findUserByUsername(userDetails.getUsername());

        ratingService.saveRating(user.getId(),rating);
        return "redirect:/rating";
    }
    @GetMapping("/show/rating")
    public String showRating(Model model) {
        model.addAttribute("ratings", ratingService.getAllRating());
        model.addAttribute("avgRating", ratingService.getAvgRating());
        return "rate";
    }

}
