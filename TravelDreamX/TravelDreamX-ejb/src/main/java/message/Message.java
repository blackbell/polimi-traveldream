/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package message;

import org.springframework.stereotype.Component;

/**
 *
 * @author Dario
 */
@Component
public class Message {
    private String msg;

    public Message() {
        msg = "Spring is fun.";
    }
    
    

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
