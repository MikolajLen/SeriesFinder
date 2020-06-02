package di

import com.example.seriesfinder.di.ActivitiesModule
import com.example.seriesfinder.di.FragmentsModule
import com.example.seriesfinder.di.SeriesFinderApplication
import com.example.seriesfinder.movie.MovieListFragmentTest
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, AndroidSupportInjectionModule::class,
        ActivitiesModule::class, FragmentsModule::class, TestModule::class]
)
interface TestMovieListFragmentComponent {

    fun inject(application: SeriesFinderApplication)
    fun inject(test: MovieListFragmentTest)
}
