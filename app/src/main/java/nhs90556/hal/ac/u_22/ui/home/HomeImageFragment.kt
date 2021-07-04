package nhs90556.hal.ac.u_22.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowId
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home_image.*
import nhs90556.hal.ac.u_22.R

/**
 * A simple [Fragment] subclass.
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

    companion object {
        fun newInstance(imageResourceId: Int) : HomeImageFragment {
            val bundle = Bundle()
            bundle.putInt(IMG_RES_ID, imageResourceId)
            val imageFragment = HomeImageFragment()
            imageFragment.arguments = bundle
            return imageFragment
        }
    }

    private var imgResId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            imgResId = it.getInt(IMG_RES_ID)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imgResId?.let {
            slideImageView.setImageResource(it)
        }
    }


}
