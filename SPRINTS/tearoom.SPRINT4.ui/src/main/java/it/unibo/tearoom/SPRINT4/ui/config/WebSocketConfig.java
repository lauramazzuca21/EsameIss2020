package it.unibo.tearoom.SPRINT4.ui.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import it.unibo.tearoom.SPRINT4.ui.handler.CustomHandshakeHandler;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	public static final String brokerDestinationPrefixForBroker = "/topic";
	public static final String brokerDestinationPrefixForQueue = "/queue";
	public static final String userDestinationPrefixForQueue = "/user";
	public static final String applDestinationPrefix = "/app";
	public static final String stompEndpointPath = "/it-unibo-iss";

	public static final String topicForClientMain = "/queue/main";
	public static final String topicForClientInTearoom = "/queue/tearoom";
	public static final String topicForManager = "/topic/manager";

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker(brokerDestinationPrefixForBroker, brokerDestinationPrefixForQueue,
				userDestinationPrefixForQueue);
		config.setApplicationDestinationPrefixes(applDestinationPrefix);
		config.setUserDestinationPrefix(userDestinationPrefixForQueue);

	}

	// register the Endpoints to which Clients can connect to through STOMP
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint(stompEndpointPath).setAllowedOrigins("*").setHandshakeHandler(new CustomHandshakeHandler())
				.withSockJS();
		registry.addEndpoint("/it-unibo-iss/tearoom").setAllowedOrigins("*").withSockJS();
	}

}

/*
 * WebSocketConfig is annotated with @Configuration to indicate that it is a
 * Spring configuration class. It is also annotated
 * with @EnableWebSocketMessageBroker that enables WebSocket message handling,
 * backed by a message broker.
 * 
 * The configureMessageBroker() method implements the default method in
 * WebSocketMessageBrokerConfigurer to configure the message broker. It starts
 * by calling enableSimpleBroker() to enable a simple memory-based message
 * broker to carry the messages back to the client on destinations prefixed with
 * /topic. It also designates the /app prefix for messages that are bound for
 * methods annotated with @MessageMapping. This prefix will be used to define
 * all the message mappings. For example, /app/hello is the endpoint that the
 * RobotController.greeting() method is mapped to handle.
 * 
 * The registerStompEndpoints() method registers the /it-unibo-iss endpoint,
 * enabling SockJS fallback options so that alternate transports can be used if
 * WebSocket is not available. The SockJS client will attempt to connect to
 * /it-unibo-iss and use the best available transport (websocket, xhr-streaming,
 * xhr-polling, and so on).
 * 
 */