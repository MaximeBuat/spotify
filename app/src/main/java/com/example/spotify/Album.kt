import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Album(
    val idAlbum: String,
    val idArtist: String,
    val IntChartPlace: Int,
    val strAlbum: String,
    val strAlbumThumb: String,
    val strArtist: String,
)

data class AlbumList(
    val trending: List<Album>
)