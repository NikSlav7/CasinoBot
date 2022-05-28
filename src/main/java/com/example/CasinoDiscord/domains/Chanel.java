package com.example.CasinoDiscord.domains;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "channels")
public class Chanel {


    @Id
    @Column(name = "id", updatable = false, nullable = false)
    String channelId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chanel", fetch = FetchType.EAGER)
    List<ChanelUser> chanelUsers;

    public Chanel(String channelId, List<ChanelUser> channelUser) {
        this.channelId = channelId;
        chanelUsers = channelUser;
    }

    public Chanel() {
        chanelUsers  = new ArrayList<ChanelUser>();
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public List<ChanelUser> getChannelUser() {
        return chanelUsers;
    }

    public void setChannelUser(List<ChanelUser> channelUser) {
        chanelUsers = channelUser;
    }
}