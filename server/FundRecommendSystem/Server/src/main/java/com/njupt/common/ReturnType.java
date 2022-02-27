package com.njupt.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReturnType {

    SUCCESS(0),
    ERROR(1);

    private final int type;
}
