/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
package it.polimi.traveldream.model;

public class Esito {
    public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    public static final String USER_NOT_AUTHORIZED = "USER_NOT_AUTHORIZED";
    public static final String USER_LOGIN_SUCCESS = "USER_LOGIN_SUCCESS";
    public static final String USER_ALREADY_EXISTNG = "USER_ALREADY_EXISTING";
    public static final String USER_SIGNIN_SUCCESSFUL = "USER_SIGNIN_SUCCESSFUL";
    public static final String EXCEPTION_RAISED = "EXCEPTION_RAISED";
    public static final String USER_LOGOUT_SUCCESS = "USER_LOGOUT_SUCCESS";
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

    @Override
    public String toString() {
        return "{'result': '" + result + "', 'message':'" + message +  "', 'returnedObj':'" + returnedObj +  "'}";
    }
    
    
    
}
