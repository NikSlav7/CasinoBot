package com.example.CasinoDiscord.domains;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class UserChanelId implements Serializable {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(referencedColumnName = "user_id", name = "user_id")
    })
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(referencedColumnName = "chanel_id", name = "chanel_id")
    })

    private Chanel chanel;

    public UserChanelId() {
    }

    public UserChanelId(User user, Chanel chanel) {
        this.user = user;
        this.chanel = chanel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Chanel getChanel() {
        return chanel;
    }

    public void setChanel(Chanel chanel) {
        this.chanel = chanel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserChanelId userId = (UserChanelId) o;
        return Objects.equals(user, userId.user) && Objects.equals(chanel, userId.chanel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, chanel);
    }
}
