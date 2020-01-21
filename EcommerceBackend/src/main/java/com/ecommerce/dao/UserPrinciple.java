package com.ecommerce.dao;


import com.ecommerce.model.Address;
import com.ecommerce.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
 
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


 
public class UserPrinciple implements UserDetails {
  private static final long serialVersionUID = 1L;
 
  private int userId;
 
  private String firstName;
  private String surname;
  private String supplyName;
 
    private String username;
 
    private String email;
    private String contactNumber;
    @JsonIgnore
    private String password;
 
    private Address address;
    private Collection<? extends GrantedAuthority> authorities;
 
    public UserPrinciple(int userId,String firstName, String surname, String supplyName, 
              String username, String email,String contactNumber, String password, Address address,
              Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.firstName = firstName;
        this.surname = surname;
        this.supplyName = supplyName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.authorities = authorities;
    }
 
    public static UserPrinciple build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());
 
        
    
        
        
        
        return new UserPrinciple(
                user.getUserId(),
                user.getFirstName(),
                user.getSurname(),
                user.getSupplyName(),
                user.getUsername(),
                user.getEmail(),
                user.getContactNumber(),
                user.getPassword(),
                user.getObjAddress(),
                authorities
        );
    }
 
  
 
    public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getUserId() {
		return userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSurname() {
		return surname;
	}

	public String getSupplyName() {
		return supplyName;
	}
	@Override
	public String getUsername() {
		return username;
	}
	 
	public String getEmail() {
		return email;
	}
	
	public String getContactNumber() {
		return contactNumber;
	}
	@Override
	public String getPassword() {
		return password;
	}
	
	public Address getAddress() {
		return address;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	

	@Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(userId, user.userId);
    }
}