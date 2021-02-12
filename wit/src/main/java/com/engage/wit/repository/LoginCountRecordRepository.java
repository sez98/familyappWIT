package com.engage.wit.repository;

import com.engage.wit.entity.LoginCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginCountRecordRepository extends JpaRepository<LoginCount, String> {
}
