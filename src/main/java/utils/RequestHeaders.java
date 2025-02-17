package utils;

import lombok.Getter;

@Getter
public enum RequestHeaders {
    X_CHALLENGER("X-CHALLENGER"),
    ACCEPT("Accept");

    private final String header;
    @Getter
    private static final String XMLRequestFormat = "application/xml";
    @Getter
    private static final String JSONRequestFormat = "application/json";
    @Getter
    private static final String DEFAULTRequestFormat = "*/*";
    @Getter
    private static final String INVALIDRequestFormat = "application/gzip";


    RequestHeaders(String header) {
        this.header = header;
    }

    public String getRequestHeader() {
        return this.header;
    }

}
