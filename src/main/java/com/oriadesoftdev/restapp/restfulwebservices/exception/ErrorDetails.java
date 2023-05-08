package com.oriadesoftdev.restapp.restfulwebservices.exception;

import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime timeStamp, String message, String details) {

}
