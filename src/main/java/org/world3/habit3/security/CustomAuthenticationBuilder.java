package org.world3.habit3.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.world3.habit3.entity.User;
import org.world3.habit3.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CustomAuthenticationBuilder implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userService.authenticateUser(name, password);

        if (user == null) {
            throw new BadCredentialsException("User not valid.");
        }

        Collection<? extends GrantedAuthority> authorities = convertRoles(user.getRoles());

        return new UsernamePasswordAuthenticationToken(user.getUserId(), password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private List<Role> convertRoles(List<String> roleNames) {
        List<Role> roles = new ArrayList<>(roleNames.size());

        for (String roleName : roleNames) {
            roles.add(new Role(roleName));
        }

        return roles;
    }

    static class Role implements GrantedAuthority {
        private String roleName;

        public Role(String roleName) {
            this.roleName = roleName;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        @Override
        public String getAuthority() {
            return this.roleName;
        }
    }
}
