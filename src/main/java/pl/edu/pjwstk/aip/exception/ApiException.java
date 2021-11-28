package pl.edu.pjwstk.aip.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ApiException extends Exception {
    private final String message;
    private final HttpStatus httpStatus;
}
