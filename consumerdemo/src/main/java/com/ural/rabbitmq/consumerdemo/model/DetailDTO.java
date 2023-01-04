package com.ural.rabbitmq.consumerdemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class DetailDTO {

    private String message;
    private Integer code;
    private Boolean result;
}
