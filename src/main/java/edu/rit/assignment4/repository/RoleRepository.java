package edu.rit.assignment4.repository;

import edu.rit.assignment4.bean.Role;
import edu.rit.assignment4.bean.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRolesById(Long roleId);
}
