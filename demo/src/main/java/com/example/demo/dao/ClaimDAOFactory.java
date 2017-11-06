package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Claim;
import com.example.demo.repository.IClaimRepository;
import com.example.demo.statemachine.ClaimStateMachineFactory;

@Component
public class ClaimDAOFactory {

	 @Autowired
	  private ClaimStateMachineFactory claimStateMachineFactory;

	  @Autowired
	  private IClaimRepository claimRepository;
	  
	 

	  public ClaimDAO build(Long claimId) {
	    return new ClaimDAO(claimId, claimStateMachineFactory, claimRepository);
	  }

	  public ClaimDAO build(Claim claim) {
	    return new ClaimDAO(claim, claimStateMachineFactory, claimRepository);
	  }
	  
	  public ClaimStateMachineFactory getStatefactory ()
	  {
		  return claimStateMachineFactory ;
		  
		  
	  }
	
}
