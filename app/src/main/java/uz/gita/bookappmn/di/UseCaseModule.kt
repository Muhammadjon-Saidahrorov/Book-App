package uz.gita.bookappmn.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.bookappmn.domain.usecase.BookUseCase
import uz.gita.bookappmn.domain.usecase.ExploreUseCase
import uz.gita.bookappmn.domain.usecase.HomeUseCase
import uz.gita.bookappmn.domain.usecase.SaveUseCase
import uz.gita.bookappmn.domain.usecase.impl.BookUseCaseImpl
import uz.gita.bookappmn.domain.usecase.impl.ExploreUseCaseImpl
import uz.gita.bookappmn.domain.usecase.impl.HomeUseCaseImpl
import uz.gita.bookappmn.domain.usecase.impl.SaveUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bookUseCase(impl: BookUseCaseImpl): BookUseCase

    @Binds
    fun homeUseCase(impl: HomeUseCaseImpl): HomeUseCase

    @Binds
    fun saveUseCase(impl: SaveUseCaseImpl): SaveUseCase

    @Binds
    fun exploreUseCase(impl: ExploreUseCaseImpl): ExploreUseCase
}