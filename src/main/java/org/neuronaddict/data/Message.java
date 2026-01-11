package org.neuronaddict.data;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Message extends PanacheEntity {
    public String content;
    public LocalDateTime timestamp;

    @ManyToOne
    public Profile profile;

    public static List<Message> findByProfile(Profile profile) {
        return list("profile = ?1 order by timestamp desc", profile);
    }
}
