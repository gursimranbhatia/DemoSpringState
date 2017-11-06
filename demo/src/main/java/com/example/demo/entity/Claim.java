package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.enums.ClaimStates;


@Entity
@Table(name = "Claim")
public class Claim implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Seq;
	
	@Column
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private long claimId;

	@Column(nullable = false)
	private String documentControlNumber;

	@Column
	private long memberId;
	
	@Column
	private double claimAmount;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ClaimStates state;

	public Claim() {
		super();
	}
	
	public Claim(long claimId, String documentControlNumber,long memberId,double claimAmount) {
		super();
		this.claimId = claimId;
		this.documentControlNumber = documentControlNumber;
		this.state=ClaimStates.INITIAL; 
		this.memberId = memberId;
		this.claimAmount = claimAmount;
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

}
