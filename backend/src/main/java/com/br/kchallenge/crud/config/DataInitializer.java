package com.br.kchallenge.crud.config;

import com.br.kchallenge.crud.enums.RolesEnum;
import com.br.kchallenge.crud.model.User;
import com.br.kchallenge.crud.repository.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner dataInitializerScript(IUserRepository userRepository) {
        return args -> {
            // Check if the database is empty
            if (userRepository.count() == 0) {
                for (int i = 1; i <= 10; i++) {
                    User user = new User();
                    user.setName("User " + i);
                    user.setEmail("user" + i + "@example.com");
                    user.setPassword("password" + i); // Consider hashing the password before saving
                    userRepository.save(user);
                }

                User admin = new User();
                admin.setName("Admin User");
                admin.setEmail("admin@mail.com");
                admin.setPassword("admin123"); // Consider hashing the password before saving
                admin.setRole(RolesEnum.ADMIN);
                userRepository.save(admin);

                System.out.println("10 usuários foram inseridos automaticamente.");
            } else {
                System.out.println("A base de dados já contém usuários, não foram inseridos novos usuários.");
            }
        };

    }

}