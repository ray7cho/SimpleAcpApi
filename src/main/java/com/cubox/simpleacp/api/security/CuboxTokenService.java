package com.cubox.simpleacp.api.security;

import com.cubox.simpleacp.api.cmnuser.service.CmnUserTokenService;
import com.cubox.simpleacp.api.domain.entity.CmnUser;
import com.cubox.simpleacp.api.domain.entity.CmnUserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CuboxTokenService implements TokenService {

  @Autowired
  private CmnUserTokenService cmnUserTokenService;

  @Override
  public Token allocateToken(String s) {
    return null;
  }

  @Override
  public Token verifyToken(String s) {
    return null;
  }

  public CuboxToken issueToken(CmnUser cmnUser) {

    CmnUserToken cmnUserToken = cmnUserTokenService.issueToken(cmnUser);

    CuboxToken cuboxToken = new CuboxToken(cmnUser.getUserId(), cmnUserToken.getToken(), convertAuthorities(cmnUser));
    cuboxToken.setDetails(cmnUser);

    return cuboxToken;
  }

  public CuboxToken getTokenByTokenKey(String token) {

    Optional<CmnUserToken> oCmnUserToken = cmnUserTokenService.findOneByToken(token);

    if (oCmnUserToken.isEmpty()) {
      throw new BadCredentialsException("TokenService, Invalid token : " + token);
    }


    CmnUser cmnUser = oCmnUserToken.get().getCmnUser();

    CuboxToken cuboxToken = new CuboxToken(cmnUser.getUserId(), token, convertAuthorities(cmnUser));
    cuboxToken.setDetails(cmnUser);

    return cuboxToken;
  }

  private Collection<GrantedAuthority> convertAuthorities(CmnUser cmnUser) {
    Set<GrantedAuthority> authorities = new HashSet<> ();

    // TO-DO 사용자권한
    // 메뉴 접근이 동적이라 권한은 로그인 여부로만 사용한다.
//    for(Role role : cmnUser.getRoles())
//    {
//        authorities.add(new SimpleGrantedAuthority(role.getNm()));
//    }
    authorities.add(new SimpleGrantedAuthority(AuthoritiesConstants.ROLE_USER));

    return authorities;
  }
}
