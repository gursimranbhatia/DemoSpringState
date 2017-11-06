package com.example.demo.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ClaimDAO;
import com.example.demo.dao.ClaimDAOFactory;
import com.example.demo.dto.ClaimDTO;
import com.example.demo.entity.Claim;
import com.example.demo.enums.ClaimEvents;
import com.example.demo.exception.ClaimServiceException;
import com.example.demo.statemachine.ClaimStateMachineFactory;

@Service
public class ClaimService {

	@Autowired
	private ClaimDAOFactory claimDAOFactory;

	public ClaimDTO createClaim(ClaimDTO claimDTO) {
		Claim claim = new Claim(claimDTO.getClaimId(), claimDTO.getDocumentControlNumber(),claimDTO.getMemberId(),claimDTO.getClaimAmount());
		ClaimDAO claimDAO = claimDAOFactory.build(claim);
		claimDAO.sendEvent(ClaimEvents.START);
		claim = claimDAO.getClaim();
		return new ClaimDTO(claim.getClaimId(), claim.getDocumentControlNumber(), claim.getState(),claim.getMemberId(),claim.getClaimAmount());
	}

	public ClaimDTO reprocessClaim(Long claimId) {

	
		Message<ClaimEvents> message = MessageBuilder.withPayload(ClaimEvents.RESTART).setHeader("claimId", claimId)
			.build();

		final ClaimDAO claimDAO = claimDAOFactory.build(claimId);
		claimDAO.sendEvent(message);
		Claim claim = claimDAO.getClaim();
		return new ClaimDTO(claim.getClaimId(), claim.getDocumentControlNumber(), claim.getState(),claim.getMemberId(),claim.getClaimAmount());
	}
	
	

}
