package global.msnthrp.nyheter

import android.app.Application
import global.msnthrp.nyheter.dagger.AppComponent
import global.msnthrp.nyheter.dagger.DaggerAppComponent
import global.msnthrp.nyheter.dagger.module.ContextModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .contextModule(ContextModule(this))
                .build()
    }

    companion object {
        lateinit var appComponent: AppComponent
        const val BASE_URL = "http://172.30.3.102:3301"
    }
}