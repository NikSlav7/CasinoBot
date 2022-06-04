package com.example.CasinoDiscord;

import com.example.CasinoDiscord.MessageControllers.*;
import com.example.CasinoDiscord.repositories.ChanelUserRepo;
import com.example.CasinoDiscord.repositories.ChanelsRepo;
import com.example.CasinoDiscord.JDBCS.JDBCChannels;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
@SpringBootApplication
@EnableJpaRepositories
public class CasinoDiscordApplication {
	public static String MessageStartingKey = "$";
	public static ApplicationContext application;


	public static void main(String[] args) {
		application = SpringApplication.run(CasinoDiscordApplication.class); //creating application context
		System.out.println(application);
		JDBCChannels jdbcChannels = application.getBean(JDBCChannels.class);

		try {
			JDA jda = JDABuilder.createDefault("OTcyMzkwNDU1NzU1MDI2NTAy.GuCIeh.oKJEVWvVecoaXWcqrtKTyAbP7fNvVbLBHNDSJ0")
							.addEventListeners(new InfoEventManager(jdbcChannels, application.getBean(ChanelsRepo.class), application.getBean(ChanelUserRepo.class)))
					        .addEventListeners(new AddMoneyManager(jdbcChannels))
//					.addEventListeners(new CheckIfChannelExistsManager(jdbcChannels))
//					.addEventListeners(new CheckIfChannelUserExists(application.getBean(ChanelsRepo.class), jdbcChannels))
					.addEventListeners(new CreateChannelTableManager(jdbcChannels))
									.build();
			System.out.println(jda.getToken());
		} catch (LoginException e) {

		}

	}





}
