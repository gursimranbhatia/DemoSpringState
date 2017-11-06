package com.example.demo.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ClaimDTO;
import com.example.demo.entity.Claim;
import com.example.demo.exception.ClaimServiceException;
import com.example.demo.service.ClaimService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ClaimAPI {

	private static final Logger logger = LoggerFactory.getLogger(ClaimAPI.class);
	
    @Autowired
    private ClaimService claimService;

	
   
			
	@RequestMapping(value = "/claim", method = RequestMethod.POST, headers = "Accept=application/json")
   	@ApiOperation(value="getClaimData", nickname="claim", response=Claim.class)
   	@ApiResponses(value = {
   			@ApiResponse(code = 201, message = "Success", response = Claim.class),
   			@ApiResponse(code = 400, message = "Bad Request"),
   			@ApiResponse(code = 415, message = "Unsupported Media Type"),
   			@ApiResponse(code = 500, message = "Internal Server Error")
   	})

    public ResponseEntity<ClaimDTO> createNewClaim(@RequestBody ClaimDTO claimDTO) {
      claimDTO = claimService.createClaim(claimDTO);
      return new ResponseEntity<ClaimDTO>(claimDTO, HttpStatus.OK);
    }


    @RequestMapping(value = "/claim/{claimdId}/reprocess", method = RequestMethod.POST, headers = "Accept=application/json")
	@ApiResponses(value = {
   			@ApiResponse(code = 201, message = "Success", response = Claim.class),
   			@ApiResponse(code = 400, message = "Bad Request"),
   			@ApiResponse(code = 415, message = "Unsupported Media Type"),
   			@ApiResponse(code = 500, message = "Internal Server Error")
   	})
    public ResponseEntity<ClaimDTO>  ReprocessClaim(@PathVariable("claimdId") Long claimId) throws ClaimServiceException {
    	ClaimDTO claimDTO = claimService.reprocessClaim(claimId);
        return new ResponseEntity<ClaimDTO>(claimDTO, HttpStatus.OK);

    }

   
}
