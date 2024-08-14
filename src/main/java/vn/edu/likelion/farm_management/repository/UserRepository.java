package vn.edu.likelion.farm_management.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.likelion.farm_management.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {


}
