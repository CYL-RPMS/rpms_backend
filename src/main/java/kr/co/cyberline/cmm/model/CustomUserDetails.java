package kr.co.cyberline.cmm.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends UserVO implements UserDetails {

    private static final long serialVersionUID = -4450269958885980297L;
    private String username;
    private String password;
    private String user_ci;
    private String author_id;
    List<GrantedAuthority> authorityList;
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    public CustomUserDetails(
    		String userName
    		, String password
    		, List<GrantedAuthority> authorityList
            , String author_id) {
    	this.username = userName;
    	this.password = password;
    	this.authorityList = authorityList;
    	this.author_id = author_id;
    }
    public CustomUserDetails(
              String userName
            , String password
            , String user_ci
            , List<GrantedAuthority> authorityList) {
        this.username = userName;
        this.password = password;
        this.user_ci = user_ci;
        this.authorityList = authorityList;
    }

    public CustomUserDetails(
              String userName
            , String password
            , List<GrantedAuthority> authorityList
            , boolean isAccountNonExpired
            , boolean isAccountNonLocked
            , boolean isCredentialsNonExpired
            , boolean isEnabled
    ) {
        this.username = userName;
        this.password = password;
        this.authorityList = authorityList;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getAuthor_id() {
        return author_id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getUser_ci() {
		return user_ci;
	}

	@Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}