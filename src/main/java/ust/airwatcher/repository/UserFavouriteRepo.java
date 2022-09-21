package ust.airwatcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ust.airwatcher.entity.UserFavourite;

import java.util.Optional;

public interface UserFavouriteRepo  extends JpaRepository<UserFavourite,Integer> {
    public Optional<UserFavourite> findOneByCityAndUserId(String city,Integer userId);
}
