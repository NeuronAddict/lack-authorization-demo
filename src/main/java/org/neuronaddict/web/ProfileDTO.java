package org.neuronaddict.web;

import org.neuronaddict.data.Grade;

public record ProfileDTO(Long id, String name, String email, String address, String phone, boolean active,
                         String role, Grade grade, Double salary, String annualEvaluation) {
}
