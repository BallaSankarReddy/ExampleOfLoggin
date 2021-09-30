package com.springboot.service;

import java.util.List;

import com.springboot.entity.Organization;

public interface OrganizationService {
	
	public List<Organization> getOrganization();
	
	public Organization saveOrganization(Organization organization);
	
	public Organization getOrganizationById(Integer id);
	public Integer deleteOrganizationById(Integer id);

}
