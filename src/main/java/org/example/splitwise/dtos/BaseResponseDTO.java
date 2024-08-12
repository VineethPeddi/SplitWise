package org.example.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseResponseDTO {
    private long id;
    private ResponseStatus status;
    private String failureMessage;
}
