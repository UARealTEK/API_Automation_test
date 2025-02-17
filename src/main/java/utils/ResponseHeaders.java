package utils;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum ResponseHeaders {
    CHALLENGER("X-Challenger"),
    ALLOW("Allow");

    private final String header;
    private static final String[] expectedListOfOptions = {"OPTIONS","GET","POST","HEAD"};

    ResponseHeaders(String header) {
        this.header = header;
    }

    public static List<String> getListOfAllowedOptions() {
        return Arrays.stream(expectedListOfOptions).toList();
    }


}

