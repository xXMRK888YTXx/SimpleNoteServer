package com.example.exceptions

import com.example.exceptions.ErrorCodes.ACCOUNT_NOT_FOUND

class AccountNotFoundException : ServerException(ACCOUNT_NOT_FOUND)