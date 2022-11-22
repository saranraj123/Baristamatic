package com.order.coffee.transport;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class OrderResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String message;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String totalPrice;

	@JsonIgnore
	private boolean isInventoryAvailable;

	@JsonIgnore
	private HttpStatus httpStatus;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public boolean isInventoryAvailable() {
		return isInventoryAvailable;
	}

	public void setInventoryAvailable(boolean isInventoryAvailable) {
		this.isInventoryAvailable = isInventoryAvailable;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	@Override
	public String toString() {
		return "OrderResponse [message=" + message + ", totalPrice=" + totalPrice + ", isInventoryAvailable="
				+ isInventoryAvailable + ", httpStatus=" + httpStatus + "]";
	}

}
