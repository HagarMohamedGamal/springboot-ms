package com.customer;

import com.customer.rabbitmq.Sender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate, Sender sender) {
    public void registerCustomer(CustomerRegisterationRequest customerRegisterationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegisterationRequest.firstName())
                .lastName(customerRegisterationRequest.lastName())
                .email(customerRegisterationRequest.email())
                .build();
        // todo: check if email is valid
        // todo: check if email is not taken
        customerRepository.saveAndFlush(customer);
        // todo: check if fraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/fraud/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );
        if (fraudCheckResponse != null && fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
        sender.send("customer added successfully");

    }
}
