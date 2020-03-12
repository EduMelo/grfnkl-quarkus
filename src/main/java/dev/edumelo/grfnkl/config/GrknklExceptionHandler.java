package dev.edumelo.grfnkl.config;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GrknklExceptionHandler implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		ErrorResponse response = new ErrorResponse(Status.INTERNAL_SERVER_ERROR.getStatusCode(), Status.INTERNAL_SERVER_ERROR, exception.getMessage());
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
	}

}
