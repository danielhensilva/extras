package com.oreilly.restclient.json;

public class JokeResponse {

    private String status;

    private JokeResponseValue value;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public JokeResponseValue getValue() {
        return value;
    }

    public void setValue(JokeResponseValue value) {
        this.value = value;
    }

}
