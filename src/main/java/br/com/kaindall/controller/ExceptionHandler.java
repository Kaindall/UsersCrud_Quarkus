package br.com.kaindall.controller;

import br.com.kaindall.domain.exception.UserException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Map;

@Provider
public class ExceptionHandler implements ExceptionMapper<UserException> {
    @Override
    public Response toResponse(UserException exception) {
        Map<String, Object> response = Map.of(
                "código", exception.getCode(),
                "mensagem", exception.getMessage());
        return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                .entity(response)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
