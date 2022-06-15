package com.example.CasinoDiscord.domains;


import org.springframework.jmx.export.annotation.ManagedNotification;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class RouletteId implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns(
            @JoinColumn(referencedColumnName = "user_id")
    )
    User user;

    String uuid;


    public RouletteId(User user, String uuid) {
        this.user = user;
        this.uuid = uuid;
    }

    public RouletteId() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}

