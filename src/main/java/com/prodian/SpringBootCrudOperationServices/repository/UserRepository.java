package com.prodian.SpringBootCrudOperationServices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prodian.SpringBootCrudOperationServices.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}
