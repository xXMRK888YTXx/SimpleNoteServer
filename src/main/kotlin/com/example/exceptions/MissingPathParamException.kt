package com.example.exceptions

import com.example.exceptions.ErrorCodes.MISSING_PATH_PARAM

class MissingPathParamException : ServerException(MISSING_PATH_PARAM)