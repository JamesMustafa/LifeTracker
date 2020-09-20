package com.james.LifeTracker.service;

import com.james.LifeTracker.db.entity.Role;
import com.james.LifeTracker.db.entity.User;
import com.james.LifeTracker.db.repository.UserRepository;
import com.james.LifeTracker.dto.binding.auth.UserAddBindingModel;
import com.james.LifeTracker.dto.binding.auth.UserServiceModel;
import com.james.LifeTracker.error.EmailNotFoundException;
import com.james.LifeTracker.error.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void createAndLoginUser(UserAddBindingModel userModel) {
        User newUser = createUser(userModel);
        User user = loadUserByUsername(newUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user,
                userModel.getPassword(),
                user.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Transactional
    public User createUser(UserAddBindingModel userModel) {
        LOGGER.info("Creating a new user with e-mail: {}.", userModel.getEmail());
        User user = this.modelMapper.map(userModel, User.class);

        if (userModel.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        }

        Role customerRole = this.roleService.findRoleByName("ROLE_USER");
        user.setRoles(Set.of(customerRole));
        return userRepository.save(user);
    }

    @Transactional
    public void editUser(Long userId, UserServiceModel userModel){
        User user = this.userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with given id was not found!"));

        user.setName(userModel.getName());
        user.setSurname(userModel.getSurname());

        this.userRepository.save(user);
    }

    @Override
    public User loadUserByUsername(String username) throws EmailNotFoundException {
        return this.userRepository.findByEmail(username)
                .orElseThrow(() -> new EmailNotFoundException(username));
    }

    public User findUserById(Long userId){
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with given id was not found!"));
    }

    public boolean isUsernameAvailable(String username){
        return userRepository.findByEmail(username).isPresent();
    }
}
