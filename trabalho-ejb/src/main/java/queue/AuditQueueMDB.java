package queue;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import interfaces.AuditingLocal;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/dm110queue") })
public class AuditQueueMDB implements MessageListener {
	
	@EJB
	private AuditingLocal bean;
	
	@Override
	public void onMessage(Message message) {
		log.info("Running method onMessage: " + message);
		try {
			if (message instanceof TextMessage) {
				TextMessage txtMessage = (TextMessage) message;
				String text = txtMessage.getText();
				log.info("Message received from queue: " + text);
				String[] strings = text.split(":");
				bean.registerOperation(Integer.parseInt(strings[1]), strings[0], new Date());
			}
		} catch (JMSException e) {
			log.log(Level.SEVERE, "Error processing message: " + message, e);
		}
	}

	@Inject
	Logger log;
}
