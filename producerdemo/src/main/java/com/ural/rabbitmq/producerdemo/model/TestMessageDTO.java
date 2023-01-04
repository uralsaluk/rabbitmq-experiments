package com.ural.rabbitmq.producerdemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class TestMessageDTO {

    private String id;
    private DetailDTO detail;


}
