package com.suzume.sipd.service.impl;

import com.suzume.sipd.constant.Constant;
import com.suzume.sipd.repository.UserRepository;
import com.suzume.sipd.service.AbstractMasterService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl extends AbstractMasterService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(notFoundException(Constant.USER));
    }

}
