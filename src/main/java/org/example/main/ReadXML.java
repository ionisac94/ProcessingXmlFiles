package org.example.main;

import org.example.domain.Order;
import org.example.domain.Orders;
import org.example.domain.Price;
import org.example.domain.Product;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReadXML {
	public static void main(String[] args) {
//		readFromXml();
		writeToXml();
	}

	private static void writeToXml() {
		File file = new File("C:\\Users\\Elitebook\\Desktop\\pss\\orders.xml");
		try {
			Marshaller jaxbMarshaller = null;
			JAXBContext jaxbContext = JAXBContext.newInstance(Orders.class);
			jaxbMarshaller = jaxbContext.createMarshaller();

			Order order1 = new Order();
			order1.setId("2343");
			order1.setCreated(new Date());

			List<Product> products1 = new ArrayList();

			Product product1 = new Product();
			product1.setDescription("Sony 54.6 (Diag) Xbr Hx929 Internet Tv");
			product1.setGtin("00027242816657");
			product1.setSupplier("Sony");
			Price price1 = new Price();
			price1.setValue("555.0");
			price1.setCurrency("EUR");
			product1.setPrice(price1);
			products1.add(product1);

			Product product2 = new Product();
			product2.setDescription("Apple iPad 2 with Wi-Fi 16GB - iOS 5 - Black");
			product2.setGtin("00885909464517");
			product2.setSupplier("Apple");
			Price price2 = new Price();
			price2.setValue("555.0");
			price2.setCurrency("USD");
			product2.setPrice(price2);
			products1.add(product2);

			order1.setProduct(products1);

			// Order2

			Order order2 = new Order();
			order2.setId("2344");
			order2.setCreated(new Date());

			List<Product> products2 = new ArrayList();

			Product product3 = new Product();
			product3.setDescription("Apple iPad 2 with Wi-Fi 16GB - iOS 5 - Black");
			product3.setGtin("00885909464517");
			product3.setSupplier("Apple");
			Price price3 = new Price();
			price3.setValue("555.0");
			price3.setCurrency("USD");
			product3.setPrice(price3);
			products2.add(product3);

			order2.setProduct(products2);

			Orders orders = new Orders();
			List<Order> listOrder1 = new ArrayList<>();
			listOrder1.add(order1);
			listOrder1.add(order2);

			orders.setOrder(listOrder1);

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(orders, System.out);
			jaxbMarshaller.marshal(orders, file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	private static void readFromXml() {
		try {
			File file = new File("C:\\Users\\Elitebook\\Desktop\\pss\\orders.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Orders.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Orders orders = (Orders) jaxbUnmarshaller.unmarshal(file);
			System.out.println(orders);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
