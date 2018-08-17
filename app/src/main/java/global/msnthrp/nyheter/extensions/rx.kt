package global.msnthrp.nyheter.extensions

import global.msnthrp.nyheter.network.model.BaseResponse
import global.msnthrp.nyheter.utils.applySchedulersSingle
import io.reactivex.Single

/**
 * Created by msnthrp on 22/01/18.
 */

fun <T> Single<BaseResponse<T>>.subscribeSmart(onSuccess: (T) -> Unit,
                                               onError: (String) -> Unit) {
    this.compose(applySchedulersSingle())
            .subscribe({ response ->
                if (response.result != null) {
                    onSuccess.invoke(response.result)
                } else if (response.errorCode != 0 && response.errorMessage != null) {
                    onError.invoke(response.errorMessage)
                }
            }, {
                onError.invoke(it.message ?: "Unknown error")
            })
}