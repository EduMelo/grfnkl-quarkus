package dev.edumelo.grfnkl.config;

import javax.ws.rs.core.Response.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {
	int errorCode;
	Status errorStatus;
	String errorMessage;
}
