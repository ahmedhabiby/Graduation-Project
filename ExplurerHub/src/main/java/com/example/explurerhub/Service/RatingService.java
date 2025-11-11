package com.example.explurerhub.Service;

import com.example.explurerhub.Model.Rating;

import java.util.List;

public interface RatingService {
    void saveRating( Long userID, Rating rating);
    Double getAvgRating();
    List<Rating> getAllRating();

}
