package com.omutwar.registration.service;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserCreateRequest {

	@Email
	@NotBlank
	public String email;

	@NotBlank
	@Size(min = 1, max = 50)
	public String firstName;

	@NotBlank
	@Size(min = 1, max = 50)
	public String lastName;

	public String password;

}