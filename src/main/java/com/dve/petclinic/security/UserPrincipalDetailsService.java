package com.dve.petclinic.security;

import com.dve.petclinic.generalExceptions.InvalidCredentialsException;
import com.dve.petclinic.generalExceptions.NotFoundException;
import com.dve.petclinic.user.UserRepository;
import com.dve.petclinic.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserPrincipalDetailsService implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findCommonUserByUsername(username)
                .map(AuthenticatedUser::new)
                .orElseThrow(() -> new InvalidCredentialsException("Bad credentials"));
    }
}