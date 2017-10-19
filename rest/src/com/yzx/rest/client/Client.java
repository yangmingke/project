package com.yzx.rest.client;

import javax.xml.namespace.QName;

import org.apache.cxf.aegis.databinding.AegisDatabinding;
import org.apache.cxf.frontend.ClientProxyFactoryBean;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
        String serviceURL;
        if (args != null && args.length > 0 && !"".equals(args[0])) {
            serviceURL = args[0];
        } else {
            serviceURL = "http://localhost:9000/Hello";
        }
        factory.setServiceName(new QName("http://server.hw.demo/", "HelloWorldService"));
        factory.setAddress(serviceURL);
        factory.setWsdlURL(serviceURL + "?wsdl");
        factory.getServiceFactory().setDataBinding(new AegisDatabinding());
//        HelloWorld client = factory.create(HelloWorld.class);
        System.out.println("Invoke sayHi()....");
//        System.out.println(client.sayHi(System.getProperty("user.name")));
        System.exit(0);
	}

}
