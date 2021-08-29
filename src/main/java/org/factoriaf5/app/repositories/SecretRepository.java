package org.factoriaf5.app.repositories;

import org.factoriaf5.app.models.Secret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretRepository extends JpaRepository<Secret,Long> {}
