package utils;

import lombok.Getter;

@Getter
public enum RequestHeaders {
    X_CHALLENGER("X-CHALLENGER");

    private final String header;


    RequestHeaders(String header) {
        this.header = header;
    }

    public String getRequestHeader() {
        return this.header;
    }

}
