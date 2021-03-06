package com.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookstore.entities.User;

@Repository
public interface ILoginRepository extends JpaRepository<User, Integer> {
	User findByEmailAndPassword(String email, String pass);
}	