package utils;

public enum Endpoints {
    CHALLENGER("/challenger"),
    CHALLENGES("/challenges"),
    TODOS("/todos");
    private final String endpoint;
    Endpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return this.endpoint;
    }
}
