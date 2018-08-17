package global.msnthrp.nyheter.dagger

import dagger.Component
import global.msnthrp.nyheter.MainActivity
import global.msnthrp.nyheter.dagger.module.ContextModule
import global.msnthrp.nyheter.dagger.module.NetworkModule
import javax.inject.Singleton

/**
 * Created by msnthrp on 22/01/18.
 */

@Singleton
@Component(modules = [ContextModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}