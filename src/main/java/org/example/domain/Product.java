package org.example.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productType", propOrder = {
		"description",
		"gtin",
		"price",
		"supplier"
})
@XmlRootElement(name = "product")
public class Product implements Serializable {

	@XmlElement(name = "description")
	private String description;

	@XmlElement(name = "gtin")
	private String gtin;

	@XmlElement(name = "price")
	private Price price;

	@XmlElement(name = "supplier")
	private String supplier;

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public String getGtin() {
		return gtin;
	}

	public String getSupplier() {
		return supplier;
	}

	public String getDescription() {
		return description;
	}

	public Price getPrice() {
		return price;
	}
}
