package com.boha.myweb3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.boha.myweb3.util.E;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
public class Web3jSkunkApplication implements ApplicationListener<ApplicationReadyEvent> {

	private static final Logger logger = LoggerFactory.getLogger(Web3jSkunkApplication.class.getSimpleName());
	public static void main(String[] args) {
		logger.info(E.BLUE_DOT + E.BLUE_DOT+ "My Java ERC-20 application is starting .... " + E.BLUE_DOT+ E.BLUE_DOT+ E.BLUE_DOT+ E.BLUE_DOT);
		SpringApplication application = new SpringApplication(Web3jSkunkApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
		logger.info(E.BLUE_DOT + E.BLUE_DOT+ " \uD83C\uDF4E \uD83C\uDF4E  \uD83C\uDF4E  \uD83C\uDF4E " +
				"Web3jSkunkApplication: ERC-20/DeFi application HAS started!.... " + E.LEAF+ E.LEAF+ E.LEAF+ E.LEAF);
	}


	@Value("${nodeEndpoint}")
	private String nodeEndpoint;

	@Value("${fromAddress}")
	private String fromAddress;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		logger.info(E.BLUE_DOT + E.BLUE_DOT+ "onApplicationEvent: ...  ✳️  ✳️  ✳️  ✳️ WebApplicationType: "
				+ event.getSpringApplication().getWebApplicationType().name() + " " +E.DICE+ E.DICE);
		logger.info(E.BLUE_DOT + E.BLUE_DOT+ "onApplicationEvent: ... " +
				"\uD83D\uDD06 \uD83D\uDD06 \uD83D\uDD06 nodeEndpoint: "
				+ nodeEndpoint + " \uD83D\uDEBC \uD83D\uDEBC fromAddress: " + fromAddress + " \uD83D\uDEBC " + E.DICE+ E.DICE);
	}
}
