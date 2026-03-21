package com.omutwar.registration.invitation;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    Optional<Invitation> findByToken(String token);

    Optional<Invitation> findByEmailAndRoleAndStatus(String email, String role, String status);
}
