package com.example.mikisapp.model;

import java.util.SplittableRandom;

public class Chat {
    private String gonderici;
    private String alici;
    private String message;

    public Chat(String gonderici, String alici, String message) {
        this.gonderici = gonderici;
        this.alici = alici;
        this.message = message;
    }

    public Chat() {
    }

    public String getGonderici() {
        return gonderici;
    }

    public void setGonderici(String gonderici) {
        this.gonderici = gonderici;
    }

    public String getAlici() {
        return alici;
    }

    public void setAlici(String alici) {
        this.alici = alici;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
