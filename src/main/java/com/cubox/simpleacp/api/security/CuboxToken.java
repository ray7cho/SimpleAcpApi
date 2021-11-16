package com.cubox.simpleacp.api.security;

import com.cubox.simpleacp.api.domain.entity.CmnUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

public class CuboxToken extends AbstractAuthenticationToken {

  private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

  private String principal;
  private String credentials;

  public CuboxToken(String principal, String credentials, boolean authenticated) {
    super(null);
    this.principal = principal;
    this.credentials = credentials;
    setAuthenticated(authenticated);
  }

  public CuboxToken(String principal, String credentials,
                          Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    this.principal = principal;
    this.credentials = credentials;
    super.setAuthenticated(true); // must use super, as we override
  }

  @Override
  public Object getCredentials() {
    return credentials;
  }

  @Override
  public Object getPrincipal() {
    return principal;
  }

  public CmnUser getCmnUser() {
    return (CmnUser) getDetails();
  }
}
