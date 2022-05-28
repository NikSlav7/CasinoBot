package com.example.CasinoDiscord;

import com.example.CasinoDiscord.MessageControllers.AddMoneyManager;
import com.example.CasinoDiscord.MessageControllers.CheckIfChannelExistsManager;
import com.example.CasinoDiscord.MessageControllers.CheckIfChannelUserExists;
import com.example.CasinoDiscord.MessageControllers.InfoEventManager;
import com.example.CasinoDiscord.repositories.ChanelUserRepo;
import com.example.CasinoDiscord.repositories.ChanelsRepo;
import com.example.CasinoDiscord.JDBCS.JDBCChannels;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
@SpringBootApplication
public class CasinoDiscordApplication {
	public static String MessageStartingKey = "$";
	public static ApplicationContext application;


	public static void main(String[] args) {
		application = SpringApplication.run(CasinoDiscordApplication.class); //creating application context
		System.out.println(application);

		try {
			JDA jda = JDABuilder.createDefault("OTcyMzkwNDU1NzU1MDI2NTAy.GuCIeh.oKJEVWvVecoaXWcqrtKTyAbP7fNvVbLBHNDSJ0")
							.addEventListeners(new InfoEventManager(application.getBean(JDBCChannels.class), application.getBean(ChanelsRepo.class), application.getBean(ChanelUserRepo.class)))
					        .addEventListeners(new AddMoneyManager(application.getBean(JDBCChannels.class)))
					.addEventListeners(new CheckIfChannelExistsManager(application.getBean(JDBCChannels.class)))
					.addEventListeners(new CheckIfChannelUserExists(application.getBean(ChanelsRepo.class), application.getBean(JDBCChannels.class)))
									.build();
			System.out.println(jda.getToken());
		} catch (LoginException e) {

		}

	}





}
