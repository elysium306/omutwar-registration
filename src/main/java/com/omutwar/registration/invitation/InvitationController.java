package com.omutwar.registration.invitation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.omutwar.registration.domain.User;
import com.omutwar.registration.request.CompleteInviteRequest;
import com.omutwar.registration.request.CreateInviteRequest;
import com.omutwar.registration.request.CreateVendorInviteRequest;
import com.omutwar.registration.service.UserService;

@RestController
@RequestMapping("/api/invitations")
public class InvitationController {

	private final InvitationService invitationService;
	private final UserService userService;

	public InvitationController(InvitationService invitationService, UserService userService) {
		this.invitationService = invitationService;
		this.userService = userService;
	}

	@PostMapping("/partner")
	public Invitation createPartnerInvite(@RequestBody CreateInviteRequest req) {
		return invitationService.createPartnerInvitation(req.getEmail());
	}

	@PostMapping("/vendor")
	public Invitation createVendorInvite(@RequestBody CreateVendorInviteRequest req) {
		return invitationService.createVendorInvitation(req.getEmail(), req.getPartnerId());
	}

	@GetMapping("/validate")
	public Invitation validate(@RequestParam String token) {
		return invitationService.validateToken(token);
	}

	@PostMapping("/complete")
	public ResponseEntity<?> complete(@RequestBody CompleteInviteRequest req) {
		Invitation invitation = invitationService.validateToken(req.getToken());

		User user = userService.createFromInvitation(invitation, req);

		invitationService.markCompleted(invitation);

		return ResponseEntity.ok().build();
	}
}
