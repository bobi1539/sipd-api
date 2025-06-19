package com.suzume.sipd.constant.enums;

import com.suzume.sipd.constant.Constant;
import org.springframework.http.HttpStatus;

public enum GlobalMessage {

    SUCCESS(HttpStatus.OK, Constant.SUCCESS),

    PAGE_NUMBER_NOT_VALID(HttpStatus.BAD_REQUEST, Constant.PAGE_NUMBER_NOT_VALID),
    CANNOT_DELETE_THIS_DATA(HttpStatus.BAD_REQUEST, Constant.CANNOT_DELETE_THIS_DATA),
    WRONG_EMAIL_OR_PASSWORD(HttpStatus.BAD_REQUEST, Constant.WRONG_EMAIL_OR_PASSWORD),
    REFRESH_TOKEN_NOT_VALID(HttpStatus.BAD_REQUEST, Constant.REFRESH_TOKEN_NOT_VALID),
    FILE_NOT_VALID(HttpStatus.BAD_REQUEST, Constant.FILE_NOT_VALID),
    FILE_NOT_ALLOWED(HttpStatus.BAD_REQUEST, Constant.FILE_NOT_ALLOWED),
    FILE_DOESNT_EXIST(HttpStatus.BAD_REQUEST, Constant.FILE_DOESNT_EXIST),
    TRIP_PARTICIPANT_NOT_VALID(HttpStatus.BAD_REQUEST, Constant.TRIP_PARTICIPANT_NOT_VALID),
    TRIP_SEGMENT_NOT_VALID(HttpStatus.BAD_REQUEST, Constant.TRIP_SEGMENT_NOT_VALID),

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, Constant.UNAUTHORIZED),

    FORBIDDEN(HttpStatus.FORBIDDEN, Constant.FORBIDDEN),

    CANNOT_INSTANCE_FINAL_CLASS(HttpStatus.INTERNAL_SERVER_ERROR, Constant.CANNOT_INSTANCE_FINAL_CLASS),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, Constant.INTERNAL_SERVER_ERROR);

    public final HttpStatus httpStatus;
    public final String message;

    GlobalMessage(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
