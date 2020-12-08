package com.matafe;

import java.util.UUID;

/**
 * Error Response
 * 
 * @author ferram
 */
public class ErrorResponse {

	private String id;
	private String message;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorResponse [message=" + message + "]";
	}

	/**
	 * Person Builder
	 * 
	 * @author ferrazm
	 */
	public static final class Builder {

		private final ErrorResponse error;

		public Builder() {
			this.error = new ErrorResponse();
		}

		public Builder withMessge(final String message) {
			this.error.setMessage(message);
			return this;
		}

		public ErrorResponse build() {
			error.setId(UUID.randomUUID().toString());
			return this.error;
		}
	}

}
