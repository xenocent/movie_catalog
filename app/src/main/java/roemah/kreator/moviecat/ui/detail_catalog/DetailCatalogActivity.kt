package roemah.kreator.moviecat.ui.detail_catalog

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import roemah.kreator.moviecat.R
import roemah.kreator.moviecat.data.MovieEntity
import roemah.kreator.moviecat.data.TvEntity
import roemah.kreator.moviecat.databinding.ActivityDetailCatalogBinding
import roemah.kreator.moviecat.databinding.ContentDetailCatalogBinding
import roemah.kreator.moviecat.utils.Helper
import roemah.kreator.moviecat.viewmodel.CatalogViewModelFactory

class DetailCatalogActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var detailCatalogBinding: ContentDetailCatalogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = CatalogViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this,factory)[DetailCatalogViewModel::class.java]

        val activityDetailCatalogBinding = ActivityDetailCatalogBinding.inflate(layoutInflater)
        detailCatalogBinding = activityDetailCatalogBinding.detailCatalog

        setContentView(activityDetailCatalogBinding.root)

        setSupportActionBar(activityDetailCatalogBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        if (extras != null) {
            val itemId = extras.getInt(EXTRA_ID).toString()
            val type = extras.getString(EXTRA_TYPE)
            Log.d("ini",itemId+" and "+type)
            if (itemId != null) {

                detailCatalogBinding.progressBar.visibility = View.VISIBLE
                viewModel.setSelectedItem(itemId)
                if(type == "movie"){
                    viewModel.getMovie().observe(this, { mov ->
                        detailCatalogBinding.progressBar.visibility = View.GONE
                        populateMovie(mov)
                    })
                }else if(type == "tv"){
                    viewModel.getTv().observe(this, { tv ->
                        detailCatalogBinding.progressBar.visibility = View.GONE
                        populateTv(tv)
                    })
                }
            }
        }
    }

    private fun populateMovie(data:MovieEntity){
        detailCatalogBinding.textTitle.text = data.title
        detailCatalogBinding.textOverview.text = data.overview

        Glide.with(this)
            .load(Helper.API_IMAGE_ENDPOINT + Helper.ENDPOINT_POSTER_SIZE_W780 +data.image)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
            .into(detailCatalogBinding.imagePoster)
    }

    private fun populateTv(data:TvEntity){
        detailCatalogBinding.textTitle.text = data.title
        detailCatalogBinding.textOverview.text = data.overview

        Glide.with(this)
            .load(Helper.API_IMAGE_ENDPOINT + Helper.ENDPOINT_POSTER_SIZE_W780 +data.image)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
            .into(detailCatalogBinding.imagePoster)
    }
}