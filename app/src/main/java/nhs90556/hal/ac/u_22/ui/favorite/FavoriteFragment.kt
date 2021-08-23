package nhs90556.hal.ac.u_22.ui.favorite


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_favorite.*
import nhs90556.hal.ac.u_22.R
import nhs90556.hal.ac.u_22.SunnyActivity


/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //一覧の画面に飛ぶ
        imageButton0.setOnClickListener {
            val intent = Intent(context, SunnyActivity::class.java)
            intent.putExtra("Category", 1.toString())
            startActivity(intent)
        }
        imageButton1.setOnClickListener {
            val intent = Intent(context, SunnyActivity::class.java)
            intent.putExtra("Category", 2.toString())
            startActivity(intent)
        }
        imageButton2.setOnClickListener {
            val intent = Intent(context, SunnyActivity::class.java)
            intent.putExtra("Category", 3.toString())
            startActivity(intent)
        }
        imageButton3.setOnClickListener {
            val intent = Intent(context, SunnyActivity::class.java)
            intent.putExtra("Category", 4.toString())
            startActivity(intent)
        }

    }


}
