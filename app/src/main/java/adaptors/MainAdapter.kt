package adaptors

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ops.tizeti.letsbuildthatapp.HomeFeed
import com.ops.tizeti.letsbuildthatapp.R
import com.ops.tizeti.letsbuildthatapp.R.id.textView_video_title
import kotlinx.android.synthetic.main.video_row.view.*


class MainAdapter(val homeFeed : HomeFeed ): RecyclerView.Adapter<CustomViewHolder>(){

    override fun getItemCount(): Int {
    return homeFeed.videos.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        val layoutInflator = LayoutInflater.from(parent?.context)
        val cellForRow     = layoutInflator.inflate(R.layout.video_row,parent , false)
        return  CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {

        val videoArray = homeFeed.videos.get(position)
        holder?.view?.textView_video_title?.text = videoArray.name
    }
}

class CustomViewHolder(val view:View) : RecyclerView.ViewHolder(view){

}