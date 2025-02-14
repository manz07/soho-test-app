package org.test.crudimage.apiresponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomApiResponse {

    private String status;
    private String message;
}
