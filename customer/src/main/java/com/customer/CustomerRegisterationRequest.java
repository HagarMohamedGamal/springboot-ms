package com.customer;

public record CustomerRegisterationRequest(String firstName,
                                           String lastName,
                                           String email) {
}
