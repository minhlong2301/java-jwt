package vn.learning.jwt.repositoryy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.learning.jwt.model.User;

import java.util.Optional;
@Repository

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByTenDangNhap(String userName);

    Optional<User> findByUuid(String uuid);

    Boolean existsByTenDangNhap(String userName);
}
