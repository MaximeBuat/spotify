package com.example.spotify

import Album
import MyTrendingAlbumsAdapter
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var recyclerView: RecyclerView
private lateinit var albumArrayList: ArrayList<Album>


lateinit var imageId : Array<Uri>
lateinit var albumTitle : Array<String>
lateinit var news : Array<String>
lateinit var album : Album;
/**
 * A simple [Fragment] subclass.
 * Use the [RankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RankFragment : Fragment() {
    private val albumTrendingTrackAdapter = MyTrendingAlbumsAdapter(object : MyTrendingAlbumsAdapter.OnTrendingAlbumListClickListener {
        override fun onTrendingAlbumClicked(album: Album) {
            // TODO("Not yet implemented")
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Albums fragment", "onCreateView")
        return inflater.inflate(R.layout.fragment_rank, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = albumTrendingTrackAdapter

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = NetworkManager.getRanking("us", "itunes","albums").await()
                albumTrendingTrackAdapter.setData(response.trending)
            } catch (e: Exception) {
                // Gérer les erreurs ici, par exemple afficher un message d'erreur.
                Log.e("Erreur_API", "Erreur lors de la récupération des classements : ${e.message}")
            }
        }


    }
}