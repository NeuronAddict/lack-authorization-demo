package org.neuronaddict.web;

public record ProfileDTO(String name, String email, String address, String phone, boolean active, String role) {
}
