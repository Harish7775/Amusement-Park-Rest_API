package com.EaseAmuse.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EaseAmuse.exceptions.CustomerException;
import com.EaseAmuse.models.Customer;
import com.EaseAmuse.payloads.CustomerInputDto;
import com.EaseAmuse.payloads.CustomerOutputDto;
import com.EaseAmuse.repositories.CustomerRepo;

@Service
public class CustomerServicesImpl implements CustomerServices{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Override
	public CustomerOutputDto registerCustomer(CustomerInputDto customerDTO) {
		
		Customer customer =	this.modelMapper.map(customerDTO, Customer.class);
		
		Customer savedCustomer =  this.customerRepo.save(customer);
		
		return this.modelMapper.map(savedCustomer, CustomerOutputDto.class);
	}

	@Override
	public CustomerOutputDto getCustomerById(Integer customerId) {
		
		Customer foundCustomer =  this.customerRepo.findById(customerId).orElseThrow(() -> new CustomerException("cutomer Not ound"));
		
		return this.modelMapper.map(foundCustomer, CustomerOutputDto.class);
		
	}

	

}
