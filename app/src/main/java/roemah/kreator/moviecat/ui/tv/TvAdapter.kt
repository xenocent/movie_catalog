package roemah.kreator.moviecat.ui.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import roemah.kreator.moviecat.R
import roemah.kreator.moviecat.data.TvEntity
import roemah.kreator.moviecat.databinding.ItemsTvBinding
import roemah.kreator.moviecat.ui.detail_catalog.DetailCatalogActivity
import roemah.kreator.moviecat.utils.Helper

class TvAdapter: RecyclerView.Adapter<TvAdapter.TvViewHolder>(){
    private var listTv = ArrayList<TvEntity>()

    fun setTv(serie:List<TvEntity>?){
        if (serie == null) return
        this.listTv.clear()
        this.listTv.addAll(serie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val itemsTvBinding = ItemsTvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TvViewHolder(itemsTvBinding)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val movie = listTv[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listTv.size

    class TvViewHolder(private val binding: ItemsTvBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(serie: TvEntity){
            with(binding){
                tvTvTitle.text = serie.title

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailCatalogActivity::class.java)
                    intent.putExtra(DetailCatalogActivity.EXTRA_ID, serie.id)
                    intent.putExtra(DetailCatalogActivity.EXTRA_TYPE, "tv")
                    itemView.context.startActivity(intent)
                }

                Glide.with(itemView.context)
                    .load(Helper.API_IMAGE_ENDPOINT + Helper.ENDPOINT_POSTER_SIZE_W185 + serie.poster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(imgPosterTv)
            }
        }
    }
}