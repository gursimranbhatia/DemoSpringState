package com.example.demo.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

import com.example.demo.enums.ClaimEvents;
import com.example.demo.enums.ClaimStates;

public class PendingAction implements Action<ClaimStates, ClaimEvents>{


	private static final Logger logger = LoggerFactory.getLogger(PendingAction.class);
	@Override
	public void execute(StateContext<ClaimStates, ClaimEvents> context) {
			logger.info("Calim Pending");
		//context.getStateMachine().sendEvent(ClaimEvents.START);
	
	
	}
}
