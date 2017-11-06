package com.example.demo.action;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.state.State;

import com.example.demo.enums.ClaimEvents;
import com.example.demo.enums.ClaimStates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessingAction implements Action<ClaimStates, ClaimEvents> {

	private static final Logger logger = LoggerFactory.getLogger(ProcessingAction.class);
	@Override
	public void execute(StateContext<ClaimStates, ClaimEvents> context) {
	 	logger.info("Processing Claim Application");
		 State<ClaimStates, ClaimEvents> state = context.getStateMachine().getState();
    	//context.getStateMachine().sendEvent(ClaimEvents.PEND);
	
	
	}

}
