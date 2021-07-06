package roemah.kreator.moviecat.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import roemah.kreator.moviecat.R
import roemah.kreator.moviecat.data.MovieEntity
import roemah.kreator.moviecat.databinding.ItemsMovieBinding
import roemah.kreator.moviecat.ui.detail_catalog.DetailCatalogActivity
import roemah.kreator.moviecat.utils.Helper

class MovieAdapter :RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){
    private var listMovies = ArrayList<MovieEntity>()

    fun setMovies(movie:List<MovieEntity>?){
        if (movie == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMovieBinding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size

    class MovieViewHolder(private val binding: ItemsMovieBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(movie:MovieEntity){
            with(binding){
                tvMovieTitle.text = movie.title

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailCatalogActivity::class.java)
                    intent.putExtra(DetailCatalogActivity.EXTRA_ID, movie.id)
                    intent.putExtra(DetailCatalogActivity.EXTRA_TYPE, "movie")
                    itemView.context.startActivity(intent)
                }

                Glide.with(itemView.context)
                    .load(Helper.API_IMAGE_ENDPOINT + Helper.ENDPOINT_POSTER_SIZE_W185 +movie.poster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(imgPosterMovie)
            }
        }
    }
}