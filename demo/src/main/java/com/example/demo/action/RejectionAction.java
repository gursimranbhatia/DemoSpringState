package com.example.demo.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

import com.example.demo.enums.ClaimEvents;
import com.example.demo.enums.ClaimStates;

public class RejectionAction implements Action<ClaimStates, ClaimEvents>{

	private static final Logger logger = LoggerFactory.getLogger(RejectionAction.class);
	@Override
	public void execute(StateContext<ClaimStates, ClaimEvents> context) {
		logger.info("Claim Rejected");
		
	}
}
