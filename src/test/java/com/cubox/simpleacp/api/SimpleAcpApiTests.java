package com.cubox.simpleacp.api;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
class SimpleAcpApiTests {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void testPasswordEncoder() {

        String plane = "admin";

        String chyper1 = passwordEncoder.encode(plane);
        String chyper2 = passwordEncoder.encode(plane);

        System.out.println(chyper1);
        System.out.println(chyper2);

        Assert.assertFalse(chyper1.equals(chyper2));
        Assert.assertTrue(passwordEncoder.matches(plane, chyper1));
        Assert.assertTrue(passwordEncoder.matches(plane, chyper2));

    }

}
