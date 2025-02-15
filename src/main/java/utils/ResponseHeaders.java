package utils;

import lombok.Getter;

@Getter
public enum ResponseHeaders {
    CHALLENGER("X-Challenger");

    private final String header;

    ResponseHeaders(String header) {
        this.header = header;
    }

}

