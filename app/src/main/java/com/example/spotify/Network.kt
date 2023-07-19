import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodAPI {
    @GET("getProduct")
    fun getResponse(
        @Query("barcode") response: String,
    ): Deferred<ServerResponse>
}

object NetworkManager {

    private val api = Retrofit.Builder()
        .baseUrl("")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(FoodAPI::class.java)

    fun getProduct(barcode: String): Deferred<ServerResponse> {
        return api.getResponse(barcode)
    }

}