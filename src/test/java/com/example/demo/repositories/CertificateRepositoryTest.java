package com.example.demo.repositories;

import com.example.demo.models.Certificate;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest(properties = {
        "spring.test.database.replace=NONE"
})
@ExtendWith(SpringExtension.class)
public class CertificateRepositoryTest {

    @Autowired
    CertificateRepository certificateRepository;

    @Test
    public void add_certificate()
    {
        Certificate cert1 = new Certificate();
        cert1 = certificateRepository.save(cert1);
        assertNotNull(cert1.getId());
    }

    @Test
    public void delete_certificate()
    {
        Certificate cert1 = new Certificate();
        cert1 = certificateRepository.save(cert1);
        certificateRepository.deleteById(cert1.getId());
        Optional<Certificate> opc1 = certificateRepository.findById(cert1.getId());
        assertFalse(opc1.isPresent());
    }

}