package com.example.exceptions

import com.example.exceptions.ErrorCodes.MISSING_HEADER_PARAM

class MissingHeaderParamException : ServerException(MISSING_HEADER_PARAM)