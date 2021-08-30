package org.factoriaf5.app.configuration;

import org.factoriaf5.app.models.User;
import org.factoriaf5.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = new User("usuario_básico", "password", "ROLE_NONE");

        // En lugar de generar el mismo usuario siempre, podemos buscar el usuario en la base de datos:
        // User user = userRepository.findByUsername(username)
        //  .orElseThrow(() -> new UsernameNotFoundException("Unable to find user: " + username));


        return CustomUserDetails.buildFrom(user);
    }
}
