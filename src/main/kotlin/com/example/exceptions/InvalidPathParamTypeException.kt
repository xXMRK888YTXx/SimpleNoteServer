package com.example.exceptions

import com.example.exceptions.ErrorCodes.INVALID_PATH_PARAM_TYPE

class InvalidPathParamTypeException : ServerException(INVALID_PATH_PARAM_TYPE)