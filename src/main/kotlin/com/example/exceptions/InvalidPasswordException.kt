package com.example.exceptions

import com.example.exceptions.ErrorCodes.INVALID_PASSWORD

class InvalidPasswordException : ServerException(INVALID_PASSWORD)