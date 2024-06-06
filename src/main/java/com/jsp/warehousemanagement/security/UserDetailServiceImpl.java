package com.jsp.warehousemanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jsp.warehousemanagement.repository.AdminRepository;
@Service
public class UserDetailServiceImpl implements UserDetailsService {
	@Autowired
	private AdminRepository adminRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return adminRepo.findByEmail(username)
				.map(UserDetailImpl::new
						)//.map(admin->new UserDetailImpl(admin))
				.orElseThrow(()->new UsernameNotFoundException("Authentication failed"));
	}

}
