package com.spaceyatech.berlin;
import com.spaceyatech.berlin.enums.RoleName;
import com.spaceyatech.berlin.models.Role;
import com.spaceyatech.berlin.services.usersevice.UserService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@SecurityScheme(
		name = "Bearer Authentication",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		scheme = "bearer"
)
public class BerlinspringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BerlinspringbootApplication.class, args);

	}

	//trigger saving of roles on startup
	@Bean
	public CommandLineRunner roleData(UserService userService) {
		return args -> {
			Role role;
			userService.saveRole(
					  role = Role.builder()
							  .name(RoleName.ROLE_USER)
							  .build()

			   );
			userService.saveRole(
					role = Role.builder()
							.name(RoleName.ROLE_ADMIN)
							.build()

			);
			userService.saveRole(
					role = Role.builder()
							.name(RoleName.ROLE_MODERATOR)
							.build()

			);

		};
	}

}
