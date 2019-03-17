/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/**
 *  Apache Qpid JMS 1.1 Solace AMQP Examples: QueueConsumer
 */

package Wiretest;

import org.apache.qpid.jms.JmsConnectionFactory;

import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;

import java.util.concurrent.CountDownLatch;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Queue;

/**
 * Receives a persistent message from a queue using Apache Qpid JMS 1.1 API over AMQP. Solace messaging is used as the message
 * broker.
 * 
 * The queue used for messages is created on the message broker.
 */
public class QueueConsumer {

    final String QUEUE_NAME = "Q1/tutorial";

    // Latch used for synchronizing between threads
    final CountDownLatch latch = new CountDownLatch(1);

    public void run(String... args) throws Exception {
//        String solaceHost = args[0];
//        String solaceUsername = args[1];
//        String solacePassword = args[2];
     //   System.out.printf("QueueConsumer is connecting to Solace messaging at %s...%n", solaceHost);

        // Programmatically create the connection factory using default settings
       // ConnectionFactory connectionFactory = new JmsConnectionFactory(solaceUsername, solacePassword, solaceHost);
    	ConnectionFactory connectionFactory = new JmsConnectionFactory("solace-cloud-client", "k2i1vbacf4nhi00lapcc5taisj", "amqp://mr-red4o991.messaging.solace.cloud:20524"); 
        //JMSContext context = connectionFactory.createContext(); 
        
        // Create connection to the Solace messaging
        Connection connection = connectionFactory.createConnection();

        // Create a non-transacted, client ACK session.
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

     //   System.out.printf("Connected with username '%s'.%n", solaceUsername);

        // Create the queue programmatically and the corresponding messaging resource
        Queue queue = session.createQueue(QUEUE_NAME);

        // From the session, create a consumer for the destination.
        MessageConsumer messageConsumer = session.createConsumer(queue);

        // Use the anonymous inner class for receiving messages asynchronously
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    if (message instanceof TextMessage) {
                        System.out.printf("C1 TextMessage received: '%s'%n", ((TextMessage) message).getText());
                    } else {
                        System.out.println("C1 Message received.");
                    }
                    System.out.printf("Message Content:%n%s%n", message.toString());
                    
                    
                    String URL = ((TextMessage) message).getText();
                	
                	Response response = given().with().get(URL);
                	
                	String S1 = response.getBody().asString();;
                	
                	System.out.println("body is " +S1);
                	
                    
                    
                    System.out.println("C1 Calling Producer2:");
                    try {
						QueueProducer2.run(S1);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    // ACK the received message manually because of the set Session.CLIENT_ACKNOWLEDGE above
                    message.acknowledge();

                   // latch.countDown(); // unblock the main thread
                } catch (JMSException ex) {
                    System.out.println("Error processing incoming message.");
                    ex.printStackTrace();
                }
            }
        });

        // Start receiving messages
        connection.start();
        System.out.println(" C1 Awaiting message...");
        // the main thread blocks at the next statement until a message received
        latch.await();

        // Close everything in the order reversed from the opening order
        // NOTE: as the interfaces below extend AutoCloseable,
        // with them it's possible to use the "try-with-resources" Java statement
        // see details at https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
        messageConsumer.close();
        session.close();
        connection.close();
    }

    public static void main(String[] args) throws Exception {
//        if (args.length < 3) {
//            System.out.println("Usage: QueueConsumer amqp://<msg_backbone_ip:amqp_port> <username> <password>");
//            System.exit(-1);
//        }
//        new QueueConsumer().run(args);
    	new QueueConsumer().run();
    	
    }

}
