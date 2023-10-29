package com.jeffrey.chatgpt.domain.billing;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Jeffrey You
 * @description Billing
 */
@Data
public class LineItem {
    private String name;
    private BigDecimal cost;
}
