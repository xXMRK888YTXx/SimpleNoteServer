package com.example.exceptions

import com.example.exceptions.ErrorCodes.ACCOUNT_ALREADY_EXIST

class AccountAlreadyExistException : ServerException(ACCOUNT_ALREADY_EXIST)