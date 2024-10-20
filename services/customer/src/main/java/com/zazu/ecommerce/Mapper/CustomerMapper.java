package com.zazu.ecommerce.Mapper;

import com.zazu.ecommerce.Dto.Request.CustomerRequest;
import com.zazu.ecommerce.Dto.Response.CustomerResponse;
import com.zazu.ecommerce.Entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest customerRequest) {
        if(customerRequest==null){
            return null;
        }
        return Customer.builder()
                .id(customerRequest.id())
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .address(customerRequest.address())
                .build();

    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
