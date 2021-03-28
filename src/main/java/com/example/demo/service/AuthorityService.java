package com.example.demo.service;

import com.example.demo.entity.Authority;
import com.example.demo.repository.AuthorityRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    Authority findById(long id) {
        return this.authorityRepository.
                findById(id).
                orElseThrow(() -> new NullPointerException("Authority does not exist!"));
    }
}
