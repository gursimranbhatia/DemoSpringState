package com.example.demo.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.example.demo.enums.ClaimStates;

@XmlRootElement
public class ClaimDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private long claimId;

	private String documentControlNumber;


	
	private ClaimStates state;
	private long memberId;
	private double claimAmount ;

	public ClaimDTO() {
		super();
	}
	
	public ClaimDTO(long claimId, String documentControlNumber,  ClaimStates state,long memberId ,double claimAmount ) {
		super();
		this.claimId = claimId;
		this.documentControlNumber = documentControlNumber;
		this.state = state;
		this.memberId = memberId;
		this.claimAmount = claimAmount;
	}

	public ClaimDTO(long claimId, String documentControlNumber) {
		super();
		this.claimId = claimId;
		this.documentControlNumber = documentControlNumber;
	}

	public long getClaimId() {
		return claimId;
	}

	public void setClaimId(long claimId) {
		this.claimId = claimId;
	}

	public String getDocumentControlNumber() {
		return documentControlNumber;
	}

	public void setDocumentControlNumber(String documentControlNumber) {
		this.documentControlNumber = documentControlNumber;
	}


	public ClaimStates getState() {
		return state;
	}

	public void setState(ClaimStates state) {
		this.state = state;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public double getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
