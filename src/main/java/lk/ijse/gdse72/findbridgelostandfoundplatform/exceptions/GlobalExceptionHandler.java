package lk.ijse.gdse72.findbridgelostandfoundplatform.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import lk.ijse.gdse72.findbridgelostandfoundplatform.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse usernameNotFoundException(UsernameNotFoundException ex) {
        return new ApiResponse(
                404,
                "User Not Found",
                ex.getMessage()
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse badCredentialsException(BadCredentialsException e) {
        return new ApiResponse(
                401,
                "Unauthorized",
                "Invalid Username or Password"
        );
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse expiredJwtException(ExpiredJwtException e) {
        return new ApiResponse(
                401,
                "Unauthorized",
                "Expired JWT Token"
        );
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse runtimeException(RuntimeException e) {
        return new ApiResponse(
                500,
                "Internal Server Error",
                e.getMessage()
        );
    }
}
