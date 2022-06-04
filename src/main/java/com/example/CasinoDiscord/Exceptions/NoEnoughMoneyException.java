package com.example.CasinoDiscord.Exceptions;

import com.example.CasinoDiscord.domains.Chanel;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;

import java.awt.*;

public class NoEnoughMoneyException extends RuntimeException{
    public NoEnoughMoneyException(MessageChannel channel) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.CYAN);
        builder.addField("Error Message", "Enable to make money transaction due to shortage of funds", false);
        channel.sendMessageEmbeds(builder.build()).queue();

    }
}
