package com.example.Ems.validation;

import com.example.Ems.configuration.ObjectNotValidException;
import jakarta.persistence.Column;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectValidator <T>{
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
@Autowired
private final Validator validator = factory.getValidator();

    public void validate(T ObjectTOValidate){

        Set<ConstraintViolation<T>> violation =validator.validate(ObjectTOValidate);
        if(!violation.isEmpty()){
            var errorMessages=violation
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
            throw new ObjectNotValidException(errorMessages);
        }
    }
}
