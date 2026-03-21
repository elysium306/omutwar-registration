package com.omutwar.registration.invitation;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class InvitationService {

	private final InvitationRepository invitationRepository;

	public InvitationService(InvitationRepository invitationRepository) {
		this.invitationRepository = invitationRepository;
	}

	public Invitation createPartnerInvitation(String email) {
		Invitation invitation = new Invitation();
		invitation.setEmail(email);
		invitation.setRole("PARTNER");
		invitation.setStatus("PENDING");
		invitation.setToken(UUID.randomUUID().toString());
		invitation.setExpiresAt(LocalDateTime.now().plusDays(7));

		return invitationRepository.save(invitation);
	}

	public Invitation createVendorInvitation(String email, Long partnerId) {
		Invitation invitation = new Invitation();
		invitation.setEmail(email);
		invitation.setRole("VENDOR");
		invitation.setPartnerId(partnerId);
		invitation.setStatus("PENDING");
		invitation.setToken(UUID.randomUUID().toString());
		invitation.setExpiresAt(LocalDateTime.now().plusDays(7));

		return invitationRepository.save(invitation);
	}

	public Invitation validateToken(String token) {
		Invitation invitation = invitationRepository.findByToken(token)
				.orElseThrow(() -> new IllegalArgumentException("Invalid invitation token"));

		if (!"PENDING".equals(invitation.getStatus()) || invitation.getExpiresAt().isBefore(LocalDateTime.now())) {
			throw new IllegalStateException("Invitation expired or already used");
		}

		return invitation;
	}

	public void markCompleted(Invitation invitation) {
		invitation.setStatus("COMPLETED");
		invitationRepository.save(invitation);
	}
}
