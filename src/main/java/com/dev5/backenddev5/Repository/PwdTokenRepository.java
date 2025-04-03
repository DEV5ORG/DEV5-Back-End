package com.dev5.backenddev5.Repository;

import com.dev5.backenddev5.Model.PwdToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PwdTokenRepository extends JpaRepository<PwdToken, Integer> {
    Optional<PwdToken> findByToken(String token);
}