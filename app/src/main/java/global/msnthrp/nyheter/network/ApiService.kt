package global.msnthrp.nyheter.network

import global.msnthrp.nyheter.network.model.BaseResponse
import io.reactivex.Single
import retrofit2.http.*

/**
 * Created by msnthrp on 22/01/18.
 */
interface ApiService {

    @FormUrlEncoded
    @POST("/post")
    fun post(@Field("message") message: String,
                    @Field("photo_id") photoId: Int): Single<BaseResponse<Int>>


    @FormUrlEncoded
    @POST("/upload")
    fun upload(@Field("photo") photo: String): Single<BaseResponse<Int>>

}