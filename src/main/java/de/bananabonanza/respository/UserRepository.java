package de.bananabonanza.respository;

import de.bananabonanza.entity.Address;
import de.bananabonanza.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
