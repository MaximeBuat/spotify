import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Album(
    val idAlbum: String,
    val idArtist: String,
    val IntChartPlace: Int,
    val strAlbum: String,
    val strAlbumThumb: Uri,
    val strArtist: String,
)

data class AlbumList(
    val trending: List<Album>
)