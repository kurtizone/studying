package org.xerces.parsing.example.model;

public class Car {
	
	private Integer id;
	private String manufacturer;
	private String model;
	private Integer weight;
	private Integer maxSpeed;
	private String color;
	private Integer price;
	
	public Car() {}

	public Car(Integer id, String manufacturer, String model, Integer weight,
			Integer maxSpeed, String color, Integer price) {
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.weight = weight;
		this.maxSpeed = maxSpeed;
		this.color = color;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(Integer maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Car info:\n");
		sb.append("Manufacturer: " + getManufacturer() + "\n");
		sb.append("Model: " + getModel() + "\n");
		sb.append("Weight: " + getWeight() + "\n");
		sb.append("Max speed: " + getMaxSpeed() + "\n");
		sb.append("Color: " + getColor() + "\n");
		sb.append("Price: " + getPrice() + "\n");
		
		return sb.toString(); 
	}

	
	
	
}
