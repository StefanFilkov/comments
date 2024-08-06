package com.tinqinacademy.comments.core.errormapper;


import com.tinqinacademy.comments.api.errors.Errors;

public interface ErrorMapper {
     Errors mapErrors(Throwable throwable);
}
