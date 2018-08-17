package global.msnthrp.nyheter.utils

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by msnthrp on 22/01/18.
 */


fun <T> applySchedulersSingle() = { single: Single<T> ->
    single
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
