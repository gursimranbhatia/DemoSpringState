package com.example.demo.guard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

import com.example.demo.dao.ClaimDAO;
import com.example.demo.entity.Claim;
import com.example.demo.enums.ClaimEvents;
import com.example.demo.enums.ClaimStates;

public class CompleteGuard implements Guard<ClaimStates, ClaimEvents>  {
	private Claim claim ;
	
	private static final Logger logger = LoggerFactory.getLogger(CompleteGuard.class);
	


	public CompleteGuard(ClaimDAO claimDAO) {
		claim = claimDAO.getClaim();
		
	}




	@Override
	public boolean evaluate(StateContext<ClaimStates, ClaimEvents> context) {
		logger.info("CompleteGuard ProcessingAction ...");
		if(claim.getClaimAmount() < 1000 && claim.getMemberId() > 1000)
		{
		return true;
		}
		
		return false;
		
	}
}
