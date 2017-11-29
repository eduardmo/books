package com.books.models.others;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.books.models.Employee;
import com.books.models.xmlprocessing.service.EmployeeService;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	EmployeeService emplS;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee empl = emplS.getEmployeeByUsername(username);
		if (empl != null) {
			String password = empl.getPassword();
			boolean enabled = true;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;
			Collection<GrantedAuthority> authority = new ArrayList<GrantedAuthority>();
			authority.add(new SimpleGrantedAuthority(empl.getEmployeeRole()));
			org.springframework.security.core.userdetails.User securityUser = new org.springframework.security.core.userdetails.User(
					username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authority);

			return securityUser;
		} else {
			throw new UsernameNotFoundException("User Not Found!");
		}
	}

}
