package com.example.demo.dao;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;

import com.example.demo.entity.Claim;
import com.example.demo.enums.ClaimEvents;
import com.example.demo.enums.ClaimStates;
import com.example.demo.repository.IClaimRepository;
import com.example.demo.statemachine.ClaimStateMachineFactory;

public class ClaimDAO {

	  private static final Logger logger = LoggerFactory.getLogger(ClaimDAO.class);

	  public static final String CLAIM_STATE_MACHINE = "claimStateMachine";

	  private Claim claim;

	  public StateMachine<ClaimStates, ClaimEvents> stateMachine;


	  @Autowired
	  private IClaimRepository claimRepository;

	  public ClaimDAO(Claim claim, ClaimStateMachineFactory stateMachineFactory, IClaimRepository claimRepository) {
	    this.claim = claim;
	    this.claimRepository = claimRepository;
	    this.save();
	    this.stateMachine = stateMachineFactory.build(this);
	    this.stateMachine.start();
	  }

	  public ClaimDAO(Long Id, ClaimStateMachineFactory stateMachineFactory, IClaimRepository claimRepository ) {
	    this.claimRepository = claimRepository;
	    this.claim = getClaimById(Id);
	    this.stateMachine = stateMachineFactory.build(this);
	    logger.info("ClaimDAO Before Start ");
	    this.stateMachine.start();
	    logger.info("ClaimDAO Before Start ");
	  }

	  public String getClaimStateMachineId() {
	    return CLAIM_STATE_MACHINE + claim.getDocumentControlNumber();
	  }

	  public void sendEvent(Message<ClaimEvents> message) {
	    exceptionHandler(stateMachine.sendEvent(message));
	  }

	  public void sendEvent(ClaimEvents events) {
	 State<ClaimStates, ClaimEvents> state = stateMachine.getState();
	    exceptionHandler(stateMachine.sendEvent(events));
	  }

	  private void exceptionHandler(boolean succeed) {
	    if (!succeed) {
	      throw new RuntimeException("The event cannot be matched.");
	    } else if (stateMachine.hasStateMachineError()) {
	      RuntimeException exception = (RuntimeException) stateMachine.getExtendedState().getVariables().get("ERROR");

//	      if (exception instanceof ClaimDomainException) {
//	      }

	      throw exception;
	    }
	  }



public void onStateChanged() {
	    claim.setState(stateMachine.getState().getId());
	    save();
	  }

	  void onError(Exception exception) {
	    stateMachine.getExtendedState().getVariables().put("ERROR", exception);
	    stateMachine.setStateMachineError(exception);
	  }

	  public ClaimStates getOrderState() {
	    return claim.getState();
	  }

	  public Claim getClaim() {
	    return claim;
	  }

	  public Claim save() {
	    claimRepository.save(claim);
	    return claim;
	  }

	  private Claim getClaimById(Long Id) {
	    Claim claim = claimRepository.findByClaimId(Id);

	    if (Objects.nonNull(claim)) {
	     
	    }

	    return claim;
	  }
	
	
}
