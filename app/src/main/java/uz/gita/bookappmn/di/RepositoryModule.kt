package uz.gita.bookappmn.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.bookappmn.domain.repository.AppRepository
import uz.gita.bookappmn.domain.repository.impl.AppRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindAppRepository(impl: AppRepositoryImpl): AppRepository

}