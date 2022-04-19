package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojos.ERole;
import com.app.pojos.Role;

@Repository

public interface IRoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByRoleName(ERole roleName);
}
