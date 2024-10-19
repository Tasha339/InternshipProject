package com.tasha.readandchat;

import com.tasha.readandchat.entity.UserAuthority;
import com.tasha.readandchat.entity.UserAuthorityEnum;
import com.tasha.readandchat.repository.UserAuthorityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
@RequiredArgsConstructor
public class ReadandchatApplication implements CommandLineRunner {
	@Autowired
	private final UserAuthorityRepo userAuthorityRepo;

	public static void main(String[] args) {
		SpringApplication.run(ReadandchatApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		UserAuthority userA = UserAuthority.builder()
				.userAuthority(UserAuthorityEnum.ROLE_USER)
				.build();
		UserAuthority admin = UserAuthority.builder()
				.userAuthority(UserAuthorityEnum.ROLE_ADMIN)
				.build();
		userAuthorityRepo.save(userA);
		userAuthorityRepo.save(admin);
	}
}
