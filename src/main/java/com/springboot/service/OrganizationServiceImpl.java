package com.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entity.Organization;
import com.springboot.exception.BusinessValidationException;
import com.springboot.exception.DataNotFoundException;
import com.springboot.exception.ErrorMessage;
import com.springboot.repo.OrganizationRepo;
import com.springboot.utils.CustomExceptionStatuCode;

@Service
public class OrganizationServiceImpl implements OrganizationService {
	
	 private Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class);
	
	@Autowired
	private  OrganizationRepo organizationRepo ;

	@Override
	public List<Organization> getOrganization() {
		logger.info("Start OrganizationServiceImpl.getOrganization method");
		List<Organization> organizations = organizationRepo.findAll();
		if(organizations.isEmpty())
		{
			throw new DataNotFoundException(new ErrorMessage("Looks data is empty in system", CustomExceptionStatuCode.BAD_REQUEST));
		}
		logger.info("End OrganizationServiceImpl.getOrganization method");
		return organizations;
	}

	@Override
	public Organization saveOrganization(Organization organization) {
		logger.info("Start OrganizationServiceImpl.saveOrganization method");
		List<com.springboot.exception.Error> errors = new ArrayList<>();
		// Step:1 Given InputRequest Validation

		logger.info("Validating the incoming request fields.");
		this.requestValidation(organization, errors);
		logger.info("End OrganizationServiceImpl.saveOrganization method");
		return organizationRepo.save(organization);
	}

	private void requestValidation(Organization organization, List<com.springboot.exception.Error> errors) {
		if (!Optional.ofNullable(organization.getOrigName()).isPresent()) {

			errors.add(new com.springboot.exception.Error("Organization Name is Mandatory"));
		}
		if (!Optional.ofNullable(organization.getActiveStatus()).isPresent()) {

			errors.add(new com.springboot.exception.Error("Organization active status is Mandatory"));
		}
		

		if (!errors.isEmpty()) {
			ErrorMessage errorMessage = new ErrorMessage(errors);
			errorMessage.setErrorCode(CustomExceptionStatuCode.BAD_REQUEST);
			errorMessage.setDocumentation("Data NotFound Exception");
			throw new DataNotFoundException(errorMessage);
		}
	}

	@Override
	public Organization getOrganizationById(Integer id) {
		logger.info("Start OrganizationServiceImpl.getOrganizationById method: {}",id);
		Organization organization= organizationRepo.findAll().stream().filter(org -> org.getId()==id).findAny().get();
		if(!Optional.ofNullable(organization).isPresent()) {
			throw new BusinessValidationException(new ErrorMessage("Requested organization id not exist in system", "Business Validation Exception",CustomExceptionStatuCode.BAD_REQUEST));
		}else {
			logger.info("End OrganizationServiceImpl.getOrganizationById method.");	
			return organization;
		}
	}

	@Override
	public Integer deleteOrganizationById(Integer id) {
		logger.info("Start OrganizationServiceImpl.deleteOrganizationById method: {}",id);
		organizationRepo.deleteById(this.getOrganizationById(id).getId());
		logger.info("End OrganizationServiceImpl.deleteOrganizationById method.");
		return id;
	}

}
