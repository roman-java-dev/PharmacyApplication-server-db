package com.example.pharmacy.config;

import communication.CustomerServiceThrift;
import communication.OrderServiceThrift;
import communication.ProductServiceThrift;
import communication.ShoppingCartServiceThrift;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThriftConfig {
    private final CustomerServiceThrift.Iface customerService;
	private final ProductServiceThrift.Iface productService;
	private final ShoppingCartServiceThrift.Iface shoppingCartService;
	private final OrderServiceThrift.Iface orderService;
	private final PharmacyProperties config;
	private final TMultiplexedProcessor processor;
	private final TServerSocket serverSocket;
	private final TServer server;

	public ThriftConfig(CustomerServiceThrift.Iface customerService, ProductServiceThrift.Iface productService,
						ShoppingCartServiceThrift.Iface shoppingCartService, OrderServiceThrift.Iface orderService,
						PharmacyProperties config) throws TTransportException {
		this.customerService = customerService;
		this.productService = productService;
		this.shoppingCartService = shoppingCartService;
		this.orderService = orderService;
		this.config = config;
		processor = new TMultiplexedProcessor();
		processor.registerProcessor(config.service().customer(), new communication.CustomerServiceThrift.Processor<>(customerService));
		processor.registerProcessor(config.service().product(), new communication.ProductServiceThrift.Processor<>(productService));
		processor.registerProcessor(config.service().shoppingCart(), new communication.ShoppingCartServiceThrift.Processor<>(shoppingCartService));
		processor.registerProcessor(config.service().order(), new communication.OrderServiceThrift.Processor<>(orderService));
		serverSocket = new TServerSocket(config.port());
		server = new TSimpleServer(new TServer.Args(serverSocket).processor(processor));
		System.out.println("Starting the Thrift Server...");
		server.serve();
	}
}
