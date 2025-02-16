package org.test.crudimage.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CatImageNotFoundException extends RuntimeException{
    private final String msg;
}
