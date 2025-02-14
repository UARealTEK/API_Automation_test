package utils;

public enum ResponseHeaders {
    CHALLENGER("X-Challenger");

    private final String header;

    // Enum constructor to initialize the header
    ResponseHeaders(String header) {
        this.header = header;
    }

    // Corrected method to return the header of the enum instance it's called on
    public String getHeader() {
        return this.header;  // Return the header of the current enum instance
    }
}

