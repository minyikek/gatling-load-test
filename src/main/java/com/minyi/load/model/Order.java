package com.minyi.load.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Order {

    private String orderId;
    private String ccyPair;
    private String dealtCcy;
    private String dealtSide;
    private BigDecimal dealtAmount;
    private BigDecimal traderRate;

}
