package com.example.spotify

import AlbumList
import ServerResponse
import android.content.ContentValues.TAG
import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface AudioAPI {
    @GET("trending.php")
    fun getResponse(
        @Query("country") response: String,
        @Query("type") response2: String,
        @Query("format") response3: String,
    ): Deferred<AlbumList>
}

object NetworkManager {

    private val api = Retrofit.Builder()
        .baseUrl("https://theaudiodb.com/api/v1/json/523532/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(AudioAPI::class.java)

    fun getRanking(country: String, type: String, format: String): Deferred<AlbumList> {
        Log.d(TAG, "getRanking: prpijogs")
        return api.getResponse(country, type, format)
    }

}