package com.zazu.ecommerce.Service;

import com.ctc.wstx.util.StringUtil;
import com.zazu.ecommerce.Dto.Request.CustomerRequest;
import com.zazu.ecommerce.Dto.Response.CustomerResponse;
import com.zazu.ecommerce.Entity.Customer;
import com.zazu.ecommerce.Exception.CustomerNotFoundException;
import com.zazu.ecommerce.Mapper.CustomerMapper;
import com.zazu.ecommerce.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String createCustomer(CustomerRequest customerRequest) {
        var customer = customerRepository.save(customerMapper.toCustomer(customerRequest));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest customerRequest) {
        var customer = customerRepository.findById(customerRequest.id()).orElseThrow(() -> new CustomerNotFoundException(
                String.format("Cannot update customer:: No Customer found with the provided ID:: %s", customerRequest.id())
        ));
        mergeCustomer(customer, customerRequest);
        customerRepository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest customerRequest) {
        if (StringUtils.isNotBlank(customerRequest.firstName())) {
            customer.setFirstName(customerRequest.firstName());
        }
        if (StringUtils.isNotBlank(customerRequest.lastName())) {
            customer.setLastName(customerRequest.lastName());
        }
        if (StringUtils.isNotBlank(customerRequest.email())) {
            customer.setEmail(customerRequest.email());
        }
        if (customerRequest.address() != null) {
            customer.setAddress(customerRequest.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::fromCustomer).collect(Collectors.toList());
    }

    public Boolean existById(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId).map(customerMapper::fromCustomer).orElseThrow(()->new CustomerNotFoundException(
                String.format("No customer found with the provided ID :: %s ",customerId)
        ));
    }

    public void deleteById(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
