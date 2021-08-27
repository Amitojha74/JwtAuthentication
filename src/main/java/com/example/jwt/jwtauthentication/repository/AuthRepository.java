package com.example.jwt.jwtauthentication.repository;

import com.example.jwt.jwtauthentication.model.Logininfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends CrudRepository<Logininfo, Integer> {

    Logininfo findByUsername(String userName);
}
