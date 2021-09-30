package com.springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.entity.Organization;

@Repository
public interface OrganizationRepo extends JpaRepository<Organization, Integer> {

}
