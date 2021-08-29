package org.factoriaf5.app.controllers;

import org.factoriaf5.app.models.Secret;
import org.factoriaf5.app.repositories.SecretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SecretController {


    private SecretRepository secretRepository;

    @Autowired
    public SecretController(SecretRepository secretRepository) {
        this.secretRepository = secretRepository;
    }

    @GetMapping("/secrets")
    public List<Secret> allSecrets() {
        return secretRepository.findAll();
    }
}
