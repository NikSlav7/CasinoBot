package com.example.CasinoDiscord.messageSender;


import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.HashMap;
import java.util.Locale;

@Component
public class MessageSender {


    public static void rouletteGameNewBet(MessageChannel messageChannel, float amount, String bet, String name) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.CYAN);
        builder.setTitle("NEW BET");
        builder.addField("Bet summary", name + " has put " + amount +  " on " + bet.toUpperCase(Locale.ROOT), true);
        messageChannel.sendMessageEmbeds(builder.build()).queue();
    }

    public static void rouletteGameResults(MessageChannel messageChannel, HashMap<String, Float> winners, String textResult) {
        EmbedBuilder builder = new EmbedBuilder();
        StringBuilder names = new StringBuilder();
        StringBuilder money = new StringBuilder();
        for(String key : winners.keySet()) {
            names.append(key + "\n");
            money.append(winners.get(key) + "\n");
        }
        builder.setTitle("Roulette Results");
        builder.addField("Bet result", textResult, false);
        builder.addField("Names", names.toString(), true);
        builder.addField("Prizes", money.toString(), true);
        builder.setColor(Color.CYAN);

        messageChannel.sendMessageEmbeds(builder.build()).queue();

    }
}
