package com.plb.vinylmgt.repository;

import com.plb.vinylmgt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
