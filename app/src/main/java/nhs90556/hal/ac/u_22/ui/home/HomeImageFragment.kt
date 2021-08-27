package nhs90556.hal.ac.u_22.ui.home


import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home_image.*
import nhs90556.hal.ac.u_22.R
import nhs90556.hal.ac.u_22.Weather

/**
 * スライドショーのフラグメント
 */

val IMG_RES_ID = "IMG_RES_ID"

class HomeImageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_image, container, false)
    }

//    companion object {
//        fun newInstance(imageResourceId: Int) : HomeImageFragment {
//            val bundle = Bundle()
//            bundle.putInt(IMG_RES_ID, imageResourceId)
//            val imageFragment = HomeImageFragment()
//            imageFragment.arguments = bundle
//            return imageFragment
//        }
//    }
//
//    private var imgResId: Int? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let{
//            imgResId = it.getInt(IMG_RES_ID)
//        }
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        imgResId?.let {
//            slideImageView.setImageResource(it)
//        }
//    }

    companion object {
        fun newInstance(imageResourceId: String) : HomeImageFragment {
            val bundle = Bundle()
            bundle.putString(IMG_RES_ID, imageResourceId)
            val imageFragment = HomeImageFragment()
            imageFragment.arguments = bundle
            return imageFragment
        }
    }

    private var imgResId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            imgResId = it.getString(IMG_RES_ID)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val windowMetrics = activity!!.windowManager!!.currentWindowMetrics
        val screenWidth = windowMetrics!!.bounds!!.width()

        // メモリから登録データの取得
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        indexText.setText(String.format("服装指数：%s",
            pref.getString("CLOTHING_INDEX","50")))

        imgResId?.let {
//            slideImageView.setImageResource(it)
            Picasso.get()
                .load(it)
                .resize(screenWidth,500)
                .centerCrop()
                .into(slideImageView)
        }
    }


}
