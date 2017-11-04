package org.world3.habit3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.world3.habit3.exception.ErrorCode;
import org.world3.habit3.exception.Habit3ValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
public class ValidationService {

    @Autowired
    ValidatorFactory validatorFactory;

    public <T> void validate(T target) {
        Set<ConstraintViolation<T>> violations = validatorFactory.getValidator().validate(target);

        StringBuilder errorMessage = new StringBuilder();
        if(!violations.isEmpty()){
            for(ConstraintViolation<T> error : violations){
                errorMessage.append(error.getPropertyPath() + ": " + error.getMessage() + "; ");
            }
            throw new Habit3ValidationException(ErrorCode.GenericValidationError, errorMessage.toString());
        }
    }

}
