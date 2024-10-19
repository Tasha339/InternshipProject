package com.tasha.readandchat.repository;

import com.tasha.readandchat.entity.UserAuthority;
import com.tasha.readandchat.entity.UserAuthorityEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthorityRepo extends JpaRepository<UserAuthority, Integer> {
    UserAuthority findByUserAuthority(UserAuthorityEnum userAuthority);
}
