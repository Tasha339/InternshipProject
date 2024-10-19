package com.tasha.readandchat.repository;

import com.tasha.readandchat.entity.ProfilePic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilePicRepository extends JpaRepository<ProfilePic, Integer> {
}
