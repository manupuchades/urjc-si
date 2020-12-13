package es.urjc.si.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.si.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByNick(String nick);

}
