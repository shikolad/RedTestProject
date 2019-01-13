package com.mstoyan.test.red.redtestproject.ui.search

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mstoyan.test.red.redtestproject.R
import com.mstoyan.test.red.redtestproject.core.models.VideoInfo
import com.mstoyan.test.red.redtestproject.core.ui.BaseFragmentInteractionListener
import com.mstoyan.test.red.redtestproject.core.ui.RecyclerViewItemClickListener
import com.mstoyan.test.red.redtestproject.injection.Injection
import com.mstoyan.test.red.redtestproject.ui.video.VideoFragment
import com.squareup.picasso.Picasso

class MainFragment : androidx.fragment.app.Fragment(), SearchContract.View {

    private val adapter = VideoAdapter()
    private var presenter: SearchPresenter? = null
    private var fragmentInteractionListener: BaseFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = Injection.provideDataRepository()
        presenter = SearchPresenter(repository)
        presenter!!.searchVideos(context!!, 0, "")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val result = inflater.inflate(R.layout.main_fragment, container, false)

        val searchInput = result.findViewById<AppCompatEditText>(R.id.searchInput)
        searchInput.addTextChangedListener(object : TextWatcher{

            val handler = Handler(Looper.getMainLooper())

            override fun afterTextChanged(s: Editable?) {
                handler.removeCallbacksAndMessages(null)
                handler.postDelayed({
                    presenter!!.searchVideos(context!!, 0, s.toString())
                }, 1000)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //do nothing
            }
        })

        val videoList = result.findViewById<RecyclerView>(R.id.searchResult)
        val layoutManager = GridLayoutManager(context, 2)
        adapter.setLayoutManager(layoutManager)
        adapter.setItemClickListener(object: RecyclerViewItemClickListener(videoList){
            override fun onItemClick(child: View, position: Int) {
                fragmentInteractionListener!!.showFragment(
                    VideoFragment::class.java,
                    VideoFragment.getBundle(adapter.getVideoInfo(position).embedUrl), true)
            }
        })
        videoList.layoutManager = layoutManager
        videoList.adapter = adapter


        return result
    }

    override fun onResume() {
        super.onResume()
        presenter!!.onViewActive(this)
    }

    override fun onPause() {
        super.onPause()
        presenter!!.onViewInactive()
    }

    override fun addVideos(videos: List<VideoInfo>, page: Int) {
        adapter.addVideos(videos, page == 0)
    }

    override fun failedLoadVideos(t: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentInteractionListener = context as BaseFragmentInteractionListener
    }

    override fun onDetach() {
        super.onDetach()
        fragmentInteractionListener = null
    }
}

class VideoHolder(itemView: View, clickListener: View.OnClickListener?) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun getInstance(parent: ViewGroup, clickListener: View.OnClickListener?): VideoHolder {
            return VideoHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_video_preview, parent, false),
                clickListener
            )
        }
    }

    private val durationView = itemView.findViewById<AppCompatTextView>(R.id.duration)!!
    private val viewsCountView = itemView.findViewById<AppCompatTextView>(R.id.views)!!
    private val ratingView = itemView.findViewById<AppCompatTextView>(R.id.rating)!!
    private val title = itemView.findViewById<AppCompatTextView>(R.id.title)!!
    private val thumb = itemView.findViewById<AppCompatImageView>(R.id.preview)!!

    init {
        itemView.setOnClickListener(clickListener)
    }

    fun bind(vi: VideoInfo){
        durationView.text = vi.duration
        viewsCountView.text = vi.viewsCount.toString()
        ratingView.text = vi.ratingRation.toString()
        title.text = vi.title
        Picasso.with(thumb.context).load(vi.thumbs["big"]!!.src[0]).into(thumb)
    }
}

class VideoAdapter : RecyclerView.Adapter<VideoHolder>() {

    private val videoInfoList = ArrayList<VideoInfo>()
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var itemClickListener: RecyclerViewItemClickListener? = null

    fun addVideos(videos: List<VideoInfo>, clear: Boolean){
        if (clear){
            videoInfoList.clear()
            videoInfoList.addAll(videos)
            notifyDataSetChanged()
            if (null != layoutManager)
                layoutManager!!.scrollToPosition(0)
        } else {
            val last = videoInfoList.size
            videoInfoList.addAll(videos)
            notifyItemInserted(last)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        return VideoHolder.getInstance(parent, itemClickListener)
    }

    override fun getItemCount(): Int {
        return videoInfoList.count()
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        holder.bind(videoInfoList[position])
    }

    override fun getItemId(position: Int): Long {
        return videoInfoList[position].id.toLong()
    }

    fun setLayoutManager(layoutManager: RecyclerView.LayoutManager){
        this.layoutManager = layoutManager
    }

    fun setItemClickListener(itemClickListener: RecyclerViewItemClickListener){
        this.itemClickListener = itemClickListener
    }

    fun getVideoInfo(position: Int): VideoInfo {
        return videoInfoList[position]
    }
}