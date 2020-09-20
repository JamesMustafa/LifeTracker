package com.james.LifeTracker.service;

import com.james.LifeTracker.db.entity.Role;
import com.james.LifeTracker.db.repository.RoleRepository;
import com.james.LifeTracker.error.RoleNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findRoleByName(String name){
        return this.roleRepository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException("Role with given name was not found!"));


    }
}
