package org.example.domain;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "orders")
public class Orders implements Serializable {

	@XmlElement(name = "order")
	public List<Order> orderList;

	public void setOrder(List<Order> order) {
		this.orderList = order;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	@Override
	public String toString() {
		return "Orders{" +
				"orderList=" + orderList +
				'}';
	}
}
