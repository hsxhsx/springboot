package com.test.dbhappy.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Setter
@Getter
@Accessors(chain = true)
public class TokenResponse401 {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public Date timestamp;
    public int status;
    public String error;
    public String message;
    public String path;

}
