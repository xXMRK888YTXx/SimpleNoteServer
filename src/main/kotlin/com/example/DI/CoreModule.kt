package com.example.DI

import com.example.Database.Dao.AccountDao.AccountDao
import com.example.Database.Dao.AccountDao.AccountDaoImpl
import com.example.Database.Dao.NoteDao.NoteDao
import com.example.Database.Dao.NoteDao.NoteDaoImpl
import com.example.Database.Dao.TokenDao.TokenDao
import com.example.Database.Dao.TokenDao.TokenDaoImpl
import com.example.DatabaseFactory
import com.example.domain.AccountInfoProvider.AccountInfoProvider
import com.example.domain.AccountInfoProvider.AccountInfoProviderImpl
import com.example.domain.AuthManager.AuthManager
import com.example.domain.AuthManager.AuthManagerImpl
import com.example.domain.NoteRepository.NoteRepository
import com.example.domain.NoteRepository.NoteRepositoryImpl
import com.example.domain.SecureUtils.SecureUtils
import com.example.domain.SecureUtils.SecureUtilsImpl
import org.koin.core.scope.get
import org.koin.dsl.module
import org.ktorm.database.Database

val coreModule = module {
    single<Database> {
        DatabaseFactory.get()
    }

    factory<AccountDao> {
        AccountDaoImpl(get())
    }

    factory<TokenDao> {
        TokenDaoImpl(get())
    }

    factory<SecureUtils> {
        SecureUtilsImpl()
    }

    factory<AuthManager> {
        AuthManagerImpl(get(),get(),get())
    }

    factory<NoteDao> {
        NoteDaoImpl(get())
    }

    factory<AccountInfoProvider> {
        AccountInfoProviderImpl(get(),get())
    }

    factory<NoteRepository> {
        NoteRepositoryImpl(get(),get())
    }
}