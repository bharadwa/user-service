package org.design.userservicejuly2025.dtos.response;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.design.userservicejuly2025.models.Role;
import org.springframework.security.core.GrantedAuthority;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class CustomGrantedAuthority implements GrantedAuthority {

    private static final long serialVersionUID = -2208140597382385177L;

    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    public static CustomGrantedAuthority fromRole(Role role) {
       CustomGrantedAuthority grantedAuthority = new CustomGrantedAuthority();
       grantedAuthority.authority = role.getValue();
       return grantedAuthority;
    }
}
