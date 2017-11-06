package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Claim;

@Repository
public interface IClaimRepository extends CrudRepository<Claim, Long>{

	   public Claim findByClaimId(Long claimId);

	   public Claim save(Claim claim);
}
