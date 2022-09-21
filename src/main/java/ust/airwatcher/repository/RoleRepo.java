package ust.airwatcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ust.airwatcher.entity.Role;

import java.util.Optional;
@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {

    Optional<Role> findByRoleName(String name);
}
