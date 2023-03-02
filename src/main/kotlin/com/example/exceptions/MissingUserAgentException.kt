package com.example.exceptions

import com.example.exceptions.ErrorCodes.MISSING_USER_AGENT

class MissingUserAgentException : ServerException(MISSING_USER_AGENT)