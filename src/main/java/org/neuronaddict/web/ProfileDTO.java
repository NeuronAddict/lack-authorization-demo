package org.neuronaddict.web;

public record ProfileDTO(Long id, String name, String email, String address, String phone, boolean active, String role) {
}
