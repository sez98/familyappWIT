package com.engage.wit.repository;

import com.engage.wit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRecordRepository extends JpaRepository<User, String> {

}
