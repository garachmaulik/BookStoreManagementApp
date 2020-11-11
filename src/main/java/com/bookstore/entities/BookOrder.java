package com.bookstore.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BOOKORDER")
public class BookOrder {
	
	public BookOrder() {
		super();
	}

	public BookOrder(Customer customer, LocalDate orderDate, double orderTotal, String status,
			Address shippingAddress, String paymentMethod, String recipientName, String recipientPhone) {
		super();
		this.customer = customer;
		this.orderDate = orderDate;
		this.orderTotal = orderTotal;
		this.status = status;
		ShippingAddress = shippingAddress;
		this.paymentMethod = paymentMethod;
		this.recipientName = recipientName;
		this.recipientPhone = recipientPhone;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "Order_Id")
	private int orderId;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;

	@Column(name = "Order_Date")
	private LocalDate orderDate;
	
	@Column(name = "Order_Total")
	private double orderTotal;
	
	@Column(name = "Status", length = 15)
	private String status;
	
	@OneToOne
	@JoinColumn(name = "ADDRESS_ID")
	private Address ShippingAddress;
	
	@Column(name = "Payment_Method", length = 15)
	private String paymentMethod;
	
	@Column(name = "Recipient_Name", length = 20)
	private String recipientName;
	
	@Column(name = "Recipient_Phone", length = 15)
	private String recipientPhone;
	
	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public LocalDate getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	
	public double getOrderTotal() {
		return orderTotal;
	}
	
	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Address getShippingAddress() {
		return ShippingAddress;
	}
	
	public void setShippingAddress(Address shippingAddress) {
		ShippingAddress = shippingAddress;
	}
	
	public String getPaymentMethod() {
		return paymentMethod;
	}
	
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public String getRecipientName() {
		return recipientName;
	}
	
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	
	public String getRecipientPhone() {
		return recipientPhone;
	}
	
	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}

	@Override
	public String toString() {
		return "BookOrder [orderId=" + orderId + ", customer=" + customer + ", orderDate=" + orderDate + ", orderTotal="
				+ orderTotal + ", status=" + status + ", ShippingAddress=" + ShippingAddress + ", paymentMethod="
				+ paymentMethod + ", recipientName=" + recipientName + ", recipientPhone=" + recipientPhone + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ShippingAddress == null) ? 0 : ShippingAddress.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + orderId;
		long temp;
		temp = Double.doubleToLongBits(orderTotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((paymentMethod == null) ? 0 : paymentMethod.hashCode());
		result = prime * result + ((recipientName == null) ? 0 : recipientName.hashCode());
		result = prime * result + ((recipientPhone == null) ? 0 : recipientPhone.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookOrder other = (BookOrder) obj;
		if (ShippingAddress == null) {
			if (other.ShippingAddress != null)
				return false;
		} else if (!ShippingAddress.equals(other.ShippingAddress))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderId != other.orderId)
			return false;
		if (Double.doubleToLongBits(orderTotal) != Double.doubleToLongBits(other.orderTotal))
			return false;
		if (paymentMethod == null) {
			if (other.paymentMethod != null)
				return false;
		} else if (!paymentMethod.equals(other.paymentMethod))
			return false;
		if (recipientName == null) {
			if (other.recipientName != null)
				return false;
		} else if (!recipientName.equals(other.recipientName))
			return false;
		if (recipientPhone == null) {
			if (other.recipientPhone != null)
				return false;
		} else if (!recipientPhone.equals(other.recipientPhone))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
	
	
}
