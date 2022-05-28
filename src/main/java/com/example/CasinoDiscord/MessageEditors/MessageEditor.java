package com.example.CasinoDiscord.MessageEditors;


import org.springframework.stereotype.Component;

@Component
public class MessageEditor {


    public String createClearNameId(String id){
        return id.replace("<@", "").replace("<@","").replace(">", "");
    }
}
