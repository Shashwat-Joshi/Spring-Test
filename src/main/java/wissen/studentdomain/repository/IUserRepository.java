package wissen.studentdomain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wissen.studentdomain.models.User;

public interface IUserRepository extends JpaRepository<User, String> {
}
