package com.example.explurerhub.Repository;

import com.example.explurerhub.Model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepo extends JpaRepository<Rating,Long> {

    Optional<Rating> findByUserId(Long id);

    @Query("SELECT AVG(ratingValue) FROM Rating")
    Double findAverageRatings();
}
