package com.example.demo.statemachine;

import java.util.EnumSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.StaticListableBeanFactory;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccess;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;
import org.springframework.statemachine.*;

import com.example.demo.action.ProcessingAction;
import com.example.demo.action.RejectionAction;
import com.example.demo.dao.ClaimDAO;
import com.example.demo.entity.*;
import com.example.demo.enums.ClaimEvents;
import com.example.demo.enums.ClaimStates;
import com.example.demo.guard.BaseGuard;
import com.example.demo.guard.CompleteGuard;

@Component
public class ClaimStateMachineFactory {

	private static final Logger logger = LoggerFactory.getLogger(ClaimStateMachineFactory.class);

	
	public StateMachine<ClaimStates, ClaimEvents> build(ClaimDAO claimDAO) {
		StateMachineBuilder.Builder<ClaimStates, ClaimEvents> builder = StateMachineBuilder.builder();

		try {
			builder.configureConfiguration().withConfiguration().machineId(claimDAO.getClaimStateMachineId())
					.listener(listener(claimDAO)).beanFactory(new StaticListableBeanFactory());
			builder.configureStates().withStates().initial(ClaimStates.INITIAL)
					.states(EnumSet.allOf(ClaimStates.class)).junction(ClaimStates.PROCESSING).stateDo(ClaimStates.REJECTED ,notifyClaimRejectedAction(claimDAO));
			builder.configureTransitions().withExternal().source(ClaimStates.INITIAL).target(ClaimStates.PROCESSING)
				.event(ClaimEvents.START).action(notifyClaimProcessingAction(claimDAO))
				.and().withJunction().source(ClaimStates.PROCESSING).first(ClaimStates.REJECTED,  RejectedGuard(claimDAO)).then(ClaimStates.COMPLETED, CompleteGuard(claimDAO)).last(ClaimStates.PENDING)
			.and().withExternal().source(ClaimStates.PENDING).target(ClaimStates.REPROCESS)
			.event(ClaimEvents.RESTART).and().withExternal().source(ClaimStates.REPROCESS).target(ClaimStates.PROCESSING);
			      

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		final StateMachine<ClaimStates, ClaimEvents> stateMachine = builder.build();

		initialize(stateMachine, claimDAO);

		return stateMachine;
	}


	private Action<ClaimStates, ClaimEvents> notifyClaimRejectedAction(ClaimDAO claimDAO) {
		// TODO Auto-generated method stub
		logger.info("ClaimStateMachineFactory Rejection Action ...");
		return new RejectionAction();
	}

 
	private Action<ClaimStates, ClaimEvents>  notifyClaimProcessingAction(ClaimDAO claimDAO) {
		logger.info("ClaimStateMachineFactory Processing Action ...");
		return new ProcessingAction( );
		
    }

		
	

	private Action<ClaimStates, ClaimEvents> acceptClaim(ClaimDAO claimDAO) {
		// TODO Auto-generated method stub
		logger.info("ClaimStateMachineFactory acceptClaim ...");
		return null;
	}
	
	private Action<ClaimStates, ClaimEvents> reprocessClaim(ClaimDAO claimDAO) {
		// TODO Auto-generated method stub
		logger.info("ClaimStateMachineFactory reprocessClaim ...");
		return null;
	}

	private Action<ClaimStates, ClaimEvents> handleError(ClaimDAO claimDAO) {
		// TODO Auto-generated method stub
		logger.info("ClaimStateMachineFactory handleError ...");
		return null;
	}

	private void initialize(StateMachine<ClaimStates, ClaimEvents> stateMachine, ClaimDAO claimDAO) {
		StateMachineAccess<ClaimStates, ClaimEvents> region = stateMachine.getStateMachineAccessor().withRegion();
		final ClaimStates state = claimDAO.getOrderState();
		final String machinedId = claimDAO.getClaimStateMachineId();
		logger.info("ClaimStateMachineFactory before resetStateMachine ...");
		region.resetStateMachine(new DefaultStateMachineContext<>(state, null, null, null, null, machinedId));
		logger.info("ClaimStateMachineFactory after resetStateMachine ...");

	}

	public Guard<ClaimStates, ClaimEvents> CompleteGuard(ClaimDAO claimDAO) {
		logger.info("CompleteGuard ...");
	    return new CompleteGuard(claimDAO);
	}
	
	public Guard<ClaimStates, ClaimEvents> RejectedGuard(ClaimDAO claimDAO) {
		logger.info("RejectedGuard ...");
	    //return ctx -> false;
		return new BaseGuard(claimDAO);
	}
	StateMachineListenerAdapter<ClaimStates, ClaimEvents> listener(ClaimDAO claimDAO) {
		logger.info("ClaimStateMachineFactory inside listener(ClaimDAO) ...");
		return new StateMachineListenerAdapter<ClaimStates, ClaimEvents>() {
			@Override
			public void stateChanged(State<ClaimStates, ClaimEvents> from, State<ClaimStates, ClaimEvents> to) {
				super.stateChanged(from, to);
				claimDAO.onStateChanged() ;
			}

			@Override
			public void stateMachineError(StateMachine<ClaimStates, ClaimEvents> stateMachine, Exception exception) {

			}
		};
	}
}
