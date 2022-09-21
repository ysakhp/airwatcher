package ust.airwatcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ust.airwatcher.entity.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    public Optional<User> findOneByUserName(String userName);
}
