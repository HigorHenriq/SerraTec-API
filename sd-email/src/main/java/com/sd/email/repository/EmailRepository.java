package com.sd.email.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sd.email.domain.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long>{

}
