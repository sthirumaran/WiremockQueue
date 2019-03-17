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
 *  Apache Qpid JMS 1.1 Solace AMQP Examples: QueueSender
 */

package Wiretest;

import org.apache.qpid.jms.JmsConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Queue;

/**
 * Sends a persistent message to a queue using Apache Qpid JMS 1.1 API over AMQP 1.0. Solace messaging is used as
 * the message broker.
 * 
 * The queue used for messages is created on the message broker.
 */
public class QueueProducer {

    final String QUEUE_NAME = "Q1/tutorial";

    public void run() throws Exception {
       // String solaceHost = args[0];
       // String solaceUsername = args[1];
       // String solacePassword = args[2];

       // System.out.printf("QueueProducer is connecting to Solace messaging at %s...%n", solaceHost);

        // Programmatically create the connection factory using default settings
      //  ConnectionFactory connectionFactory = new JmsConnectionFactory(solaceUsername, solacePassword,solaceHost);
        ConnectionFactory connectionFactory = new JmsConnectionFactory("solace-cloud-client", "k2i1vbacf4nhi00lapcc5taisj", "amqp://mr-red4o991.messaging.solace.cloud:20524"); 


        // Create connection to the Solace messaging
        Connection connection = connectionFactory.createConnection();

        // Create a non-transacted, auto ACK session.
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

      //  System.out.printf("Connected with username '%s'.%n", solaceUsername);

        // NOTE: this durable queue must already exist on the messaging, created by the administrator
        // or the QueueConsumer
        Queue queue = session.createQueue(QUEUE_NAME);

        // Create the message producer
        MessageProducer messageProducer = session.createProducer(null);

        // Create a text message.
        TextMessage message = session.createTextMessage("http://localhost:8080/api/mytest");
  

        System.out.printf("Sending message '%s' to queue '%s'...%n", message.getText(), queue.toString());

        // Send the message
        // NOTE: JMS Message Priority is not supported by the Solace Message Bus
        messageProducer.send(queue, message, DeliveryMode.PERSISTENT, Message.DEFAULT_PRIORITY,
                Message.DEFAULT_TIME_TO_LIVE);

        System.out.println("Producer1 Sent successfully. Exiting...");

        // Close everything in the order reversed from the opening order
        // NOTE: as the interfaces below extend AutoCloseable,
        // with them it's possible to use the "try-with-resources" Java statement
        // see details at https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
        messageProducer.close();
        session.close();
        connection.close();
    }

    public static void main(String[] args) throws Exception {
        //if (args.length < 3) {
          //  System.out.println("Usage: QueueProducer amqp://<msg_backbone_ip:amqp_port> <username> <password>");
           // System.exit(-1);
        //}
        //new QueueProducer().run(args);
    	new QueueProducer().run();
    }
}
