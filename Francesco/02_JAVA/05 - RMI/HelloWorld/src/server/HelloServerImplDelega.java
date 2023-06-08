package server;

import service.*;

public class HelloServerImplDelega implements Hello{
	
	public HelloServerImplDelega() {}
	public String sayHello() {
		System.out.println("sayHello() invoked on server");
		return "Hello, World!";
	}
}
