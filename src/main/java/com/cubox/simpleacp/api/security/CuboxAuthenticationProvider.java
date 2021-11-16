package com.cubox.simpleacp.api.security;

import com.cubox.simpleacp.api.cmnuser.service.CmnUserService;
import com.cubox.simpleacp.api.domain.entity.CmnUser;
import com.cubox.simpleacp.api.messages.MessageService;
import com.cubox.simpleacp.api.messages.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class CuboxAuthenticationProvider implements AuthenticationProvider {


    @Autowired
    private CmnUserService cmnUserService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userId = authentication.getName();
        String password = (String) authentication.getCredentials();

        Optional<CmnUser> oCmnUser = cmnUserService.findOneByUserId(userId);

        if ( oCmnUser.isEmpty() ) {
            throw new BadCredentialsException(messageService.getMessage(Messages.AUTH_USER_NOT_FOUND));
        }

        CmnUser cmnUser = oCmnUser.get();

        // 비밀번호 검증
        if (!passwordEncoder.matches(password, cmnUser.getUserPw())) {
            throw new BadCredentialsException(
                    messageService.getMessage(Messages.AUTH_PASSWORDS_DO_NOT_MATCH));
        }
        return new UsernamePasswordAuthenticationToken(cmnUser, password, convertAuthorities(cmnUser));
    }

    private Collection<GrantedAuthority> convertAuthorities(CmnUser cmnUser) {
        Set<GrantedAuthority> authorities = new HashSet<> ();

        // TO-DO 사용자권한
        // 메뉴 접근이 동적이라 권한은 로그인 여부로만 사용한다.
//        for(Role role : cmnUser.getRoles())
//        {
//            authorities.add(new SimpleGrantedAuthority(role.getNm()));
//        }
        authorities.add(new SimpleGrantedAuthority(AuthoritiesConstants.ROLE_USER));

        return authorities;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
