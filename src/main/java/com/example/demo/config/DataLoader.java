package com.example.demo.config;

import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadDefaultUser(
            UserAccountRepository repo,
            BCryptPasswordEncoder encoder
    ) {
        return args -> {

            // Create default user if not exists
            if (repo.findByEmail("admin@example.com").isEmpty()) {

                UserAccount user = new UserAccount();
                user.setEmail("admin@example.com");
                user.setPassword(encoder.encode("admin123"));
                user.setRole("ADMIN");

                repo.save(user);

                System.out.println("✅ Default user created: admin@example.com / admin123");
            }
        };
    }
}
