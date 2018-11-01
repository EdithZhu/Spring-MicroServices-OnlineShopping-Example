package com.vipul.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vipul.datamodel.Customer;
import com.vipul.dto.CustomerDTO;
import com.vipul.repository.CustomerRepository;

@Transactional
@Service
public class CustomerService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CustomerRepository customerRepository;

	public List<CustomerDTO> all() {
		return customerRepository.findAll().stream().map(c -> modelMapper.map(c, CustomerDTO.class))
				.collect(Collectors.toList());
	}

	public CustomerDTO save(CustomerDTO customerDTO) {
		Customer customer = customerRepository.save(modelMapper.map(customerDTO, Customer.class));
		return modelMapper.map(customer, CustomerDTO.class);
	}

	public CustomerDTO get(long customerId) {
		return modelMapper.map(customerRepository.findById(customerId), CustomerDTO.class);
	}

	public void delete(long customerId) {
		customerRepository.deleteById(customerId);
	}
}
