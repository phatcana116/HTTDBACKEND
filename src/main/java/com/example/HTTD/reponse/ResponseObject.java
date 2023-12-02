package com.example.HTTD.reponse;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseObject {
    private Number status;
    private String message;
    private boolean isSuccess;
    private Object data;
}
