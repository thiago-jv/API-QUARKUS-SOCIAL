package io.github.thiago.melo.quarkussocial.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiledError {

    private String field;
    private String mesage;

    public FiledError(String field, String mesage) {
        this.field = field;
        this.mesage = mesage;
    }

}
