package com.suzume.sipd.repository;

import com.suzume.sipd.entity.MUser;

import java.util.Optional;

public interface UserRepository extends MasterRepository<MUser, Long> {

    Optional<MUser> findByEmail(String email);

}
