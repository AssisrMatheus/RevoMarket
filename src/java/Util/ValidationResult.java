/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adowt
 */
public class ValidationResult {

    private boolean Sucess;
    private String Context;
    private List<ErrorMessage> ErrorMessages;

    public ValidationResult(boolean sucess, String errorContext, String errorMessage) {
        this.Sucess = sucess;
        this.Context = errorContext;
        this.ErrorMessages = new ArrayList<>();
        this.ErrorMessages.add(new ErrorMessage(Context, errorMessage));
    }

    public ValidationResult(boolean sucess, String errorContext, List<ErrorMessage> errorMessages) {
        this.Sucess = sucess;
        this.Context = errorContext;
        this.ErrorMessages = errorMessages;
    }

    public ValidationResult(boolean sucess, String errorContext) {
        this.Sucess = sucess;
        this.Context = errorContext;
        this.ErrorMessages = new ArrayList<>();
    }

    public ValidationResult(String errorContext) {
        this(true, errorContext);
    }
    
    
    public void addError(String errorMessage){
        this.Sucess = false;
        this.ErrorMessages.add(new ErrorMessage(Context, errorMessage));
    }
    
    public void addError(ErrorMessage errorMessage){
        this.Sucess = false;
        this.ErrorMessages.add(errorMessage);
    }

    public boolean isSucess() {
        return Sucess;
    }

    public String getContext() {
        return Context;
    }

    public void setContext(String Context) {
        this.Context = Context;
    }

    public List<ErrorMessage> getErrorMessages() {
        return ErrorMessages;
    }
    
}
