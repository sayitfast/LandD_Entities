package com.example.demo.repositories;

import com.example.demo.models.Certificate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends CrudRepository<Certificate, Long> {
}
