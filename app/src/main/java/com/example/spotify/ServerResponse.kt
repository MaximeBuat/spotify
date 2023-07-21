import android.net.Uri
import com.google.gson.annotations.SerializedName
import java.util.Locale

data class ServerResponse(
    @SerializedName("error")
    val error: String?,
    @SerializedName("response")
    val response: Response?
) {
    data class Response(
        @SerializedName("idAlbum")
        val idAlbum: String,
        @SerializedName("idArtist")
        val idArtist: String,
        @SerializedName("IntChartPlace")
        val IntChartPlace: Int,
        @SerializedName("strAlbum")
        val strAlbum: String,
        @SerializedName("strAlbumThumb")
        val strAlbumThumb: String,
        @SerializedName("strArtist")
        val strArtist: String,
    )

    fun toAlbum(): Album = response?.let { resp ->
        return Album(
            idAlbum = resp.idAlbum,
            idArtist = resp.idArtist,
            IntChartPlace = resp.IntChartPlace,
            strAlbum = resp.strAlbum,
            strAlbumThumb = resp.strAlbumThumb,
            strArtist = resp.strArtist
        )
    } ?: throw Exception("Unable to parse the product")
}