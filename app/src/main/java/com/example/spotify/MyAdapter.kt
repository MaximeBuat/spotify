import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spotify.R
import kotlin.math.log

class MyTrendingAlbumsAdapter(
    val callback: OnTrendingAlbumListClickListener
) : RecyclerView.Adapter<MyTrendingAlbumsAdapter.TrendingAlbumListViewHolder>() {

    private val data = mutableListOf<Album>()

    fun setData(trendingAlbum: List<Album>) {
        Log.d(TAG, "setData: sfknrdg,lksr")
        data.clear()
        data.addAll(trendingAlbum)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingAlbumListViewHolder {
        return TrendingAlbumListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }
    override fun onBindViewHolder(holder: TrendingAlbumListViewHolder, position: Int) {
        val trendingAlbum = data[position]
        holder.update(trendingAlbum)
        holder.artist.text = trendingAlbum.strArtist
        holder.album.text = trendingAlbum.strAlbum
        holder.itemView.setOnClickListener {
            callback.onTrendingAlbumClicked(trendingAlbum)
        }
    }

    class TrendingAlbumListViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val thumbnail: ImageView = v.findViewById(R.id.imageViewAlbum)
        val album: TextView = v.findViewById(R.id.textViewAlbumTitle)
        val artist: TextView = v.findViewById(R.id.textViewArtistName)

        fun update(trendingAlbum: Album) {
            Glide.with(itemView).load(trendingAlbum.strAlbumThumb).into(thumbnail)
            album.text = trendingAlbum.strAlbum
            artist.text = trendingAlbum.strArtist
        }

    }

    interface OnTrendingAlbumListClickListener {
        fun onTrendingAlbumClicked(album: Album)
    }
}