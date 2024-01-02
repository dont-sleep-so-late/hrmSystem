package com.elms.hrsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author 24660
 */
@Data
@RequiredArgsConstructor
public class ResultVo<T> {
    private String mess;
    private boolean isOk;
    private T data;
}
