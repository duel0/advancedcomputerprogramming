package server;

import service.Hello;

// Implementazione per delega

public class HelloServerImplDelega implements Hello{
   
    public HelloServerImplDelega() {}

    public String sayHello() {
        System.out.println("sayHello() invoked on server...");
        return "Hello, world!";
    }
    
}
