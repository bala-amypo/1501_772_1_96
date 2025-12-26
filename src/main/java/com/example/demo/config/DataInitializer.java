package com.example.demo.config;

import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsers(
            UserAccountRepository repo,
            BCryptPasswordEncoder encoder) {

        return args -> {

            if (repo.findByEmail("admin@example.com").isEmpty()) {

                UserAccount user = new UserAccount();
                user.setEmail("admin@example.com");
                user.setPassword(encoder.encode("admin123"));
                user.setRole("ADMIN");

                repo.save(user);

                System.out.println("✅ Default admin user created");
            }
        };
    }
}
