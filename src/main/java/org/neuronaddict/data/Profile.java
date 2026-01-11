package org.neuronaddict.data;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import org.neuronaddict.web.Grade;

@Entity
public class Profile extends PanacheEntity {
    public String name;
    public String email;
    public String address;
    public String phone;
    public boolean active;
    public String role;
    public Grade grade;
    public Double salary;
    public String annualEvaluation;

    public static Profile findByName(String name) {
        return Profile.find("name", name).firstResult();
    }
}
