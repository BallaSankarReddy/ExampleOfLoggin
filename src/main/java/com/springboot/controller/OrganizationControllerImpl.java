package com.springboot.controller;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entity.Organization;
import com.springboot.exception.DataNotFoundException;
import com.springboot.service.OrganizationService;
@RequestMapping("/api/origination")
@RestController

public class OrganizationControllerImpl implements OrganizationController {
	
	 private Logger logger = LoggerFactory.getLogger(OrganizationControllerImpl.class);
	
	/*
	 * @Autowired private OrganizationRepo organizationService ;
	 * 
	 */

	 
	 @Autowired
	 OrganizationService organizationService;
	@Override
	public List<Organization> getOrganization() {
		List<Organization> findAll = organizationService.getOrganization();
		logger.info("Get All Organization details :{} ",findAll);
		
		return findAll;
	}

	@Override
	public Organization saveOrigination(Organization organization)  {
		
		Organization organizationById =  organizationService.saveOrganization(organization);

		logger.info("Saving Organization details :{} ",organizationById);
		return organizationById;
				//organizationService.saveOrigination(organization);
	}

	@Override
	public Organization getOrganizationById(Integer id)  {
		
		logger.info("Get  Organization details by Id :{} ",id);
	return organizationService.getOrganizationById(id);
	}

	@Override
	public Integer deleteOrganizationById(Integer id) {
		logger.info("Delete  Organization details by Id :{} ",id);
		return organizationService.deleteOrganizationById(id);
	}

}
