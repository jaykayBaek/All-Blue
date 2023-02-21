package com.spring.green2209s_08.web.controller.wysiwyg;

import com.spring.green2209s_08.web.controller.StatusResponse;
import lombok.Getter;

@Getter
public class FileStatusResponse extends StatusResponse {
    private final String SAVED_FILENAME;

    public FileStatusResponse(String CODE, String MESSAGE, String SUCCESS, String SAVED_FILENAME) {
        super(CODE, MESSAGE, SUCCESS);
        this.SAVED_FILENAME = SAVED_FILENAME;
    }
}
