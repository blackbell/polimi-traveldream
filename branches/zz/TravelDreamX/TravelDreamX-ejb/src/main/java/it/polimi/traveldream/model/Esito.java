package it.polimi.traveldream.model;

public class Esito {
    private boolean result;
    private String message;
    private String newUrl;
    private Object returnedObj;
    
    public Esito() {
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNewUrl() {
        return newUrl;
    }

    public void setNewUrl(String newUrl) {
        this.newUrl = newUrl;
    }

    public Object getReturnedObj() {
        return returnedObj;
    }

    public void setReturnedObj(Object returnedObj) {
        this.returnedObj = returnedObj;
    }
    
    
    
}
