package com.example.test.utils;


import com.example.test.dto.ResultDto.ResultDto;
import org.springframework.http.HttpStatus;


public class ResultUtil {
    public static <T> ResultDto<T> success(T model ) {
        return new ResultDto<T>(
                true,
                HttpStatus.OK.value(),
                "success",
                model

        );
    }
    public static <T> ResultDto<T> successAndCunt(T model , T totalCunt ) {
        return new ResultDto<T>(
                true,
                HttpStatus.OK.value(),
                "success",
                model


        );
    }

    public static <T> ResultDto<T> badRequest() {
        return new ResultDto<T>(
                false,
                HttpStatus.BAD_REQUEST.value(),
                "bad Request",
                null
        );
    }

    public static <T> ResultDto<T> notFound() {
        return new ResultDto<T>(
                false,
                HttpStatus.NOT_FOUND .value(),
                "not found",
                null
        );
    }

    public static <T> ResultDto<T> error(String message) {
        return new ResultDto<>(
                false,
                HttpStatus.BAD_REQUEST.value(),
                message,
                null
        );
    }

    public static  ResultDto<Void> ResultDtoException(HttpStatus httpStatus, String message) {
        return new ResultDto<>(
                false,
                httpStatus.value(),
                message,
                null
        );
    }
}