package com.example.explurerhub.Implementations;

import com.example.explurerhub.Model.Rating;
import com.example.explurerhub.Model.User;
import com.example.explurerhub.Repository.RatingRepo;
import com.example.explurerhub.Repository.UserRepo;
import com.example.explurerhub.Service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private RatingRepo ratingRepo;
    private UserRepo userRepo;

    @Autowired
    RatingServiceImpl(RatingRepo ratingRepo){
        this.ratingRepo = ratingRepo;
        this.userRepo = userRepo;
    }
    @Override
    public void saveRating(Long userID, Rating rating) {
        User user=userRepo.findById(userID).orElseThrow();
        rating.setUser(user);
        ratingRepo.save(rating);

    }

    @Override
    public Double getAvgRating() {return ratingRepo.findAverageRatings();
    }

    @Override
    public List<Rating> getAllRating() {
        return ratingRepo.findAll();
    }
}
