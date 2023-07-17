## Tips and Tricks

### Python STOMP

* La ``Connection`` deve essere stabilita su host ``127.0.0.1`` e porto ``61613``

* Nel caso di traccia in cui il sistema da sviluppare include entità JMS e entità STOMP, utilizzare 
``auto_content_length=False`` per creare la ``Connection``

### Java JMS

Utilizzare le seguenti proprietà per JNDI:

```
"java.naming.factory.initial" -> "org.apache.activemq.jndi.ActiveMQInitialContextFactory"
"java.naming.provider.url" -> "tcp://127.0.0.1:61616"
```
