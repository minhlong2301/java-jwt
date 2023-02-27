package vn.learning.jwt.repositoryy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.learning.jwt.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}
