package br.com.kaindall.domain.exception;

public class UserException extends RuntimeException{
    private final Integer code;

    private UserException(Integer code, String message) {
        super(message);
        this.code = code;
    }
    public static UserException notFound() {
        return new UserException(1, "Usuário não encontrado");
    }

    public Integer getCode() {
        return code;
    }


}
