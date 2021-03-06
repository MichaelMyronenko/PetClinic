package com.dve.petclinic.security;

import com.dve.petclinic.entities.user.UserRepository;
import com.dve.petclinic.generalExceptions.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findCommonUserByUsername(username)
                .map(AuthenticatedUser::new)
                .orElseThrow(() -> new NotFoundException("NotFound.userDetailsService.loadUser",
                        "User not found with username ${username}, in UserPrincipalDetailsService.class",
                        new Object[]{username}));
    }
}
