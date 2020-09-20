package com.james.LifeTracker;

import com.james.LifeTracker.db.entity.Role;
import com.james.LifeTracker.db.entity.User;
import com.james.LifeTracker.db.repository.RoleRepository;
import com.james.LifeTracker.db.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class LifeTrackerApplicationInit implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public LifeTrackerApplicationInit(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        if(userRepository.count() == 0){
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);

            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);

            //---- ADMIN ----
            User admin = new User();
            admin.setEmail("admin@example.com");
            admin.setName("admin");
            admin.setSurname("admin");
            admin.setPassword(passwordEncoder.encode("admin"));

            admin.setRoles(Set.of(adminRole, userRole));
            userRepository.save(admin);

            // ---- USER ------
            User customer = new User();
            customer.setEmail("djem_mustafa@abv.bg");
            customer.setName("Djem");
            customer.setSurname("Mustafa");
            customer.setPassword(passwordEncoder.encode("111114"));

            customer.setRoles(Set.of(userRole));
            userRepository.save(customer);
        }

    }
}
