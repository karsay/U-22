package nhs90556.hal.ac.u_22.ui.coordinate


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import kotlinx.android.synthetic.main.fragment_coordinate.*
import nhs90556.hal.ac.u_22.R

/**
 * A simple [Fragment] subclass.
 */
class CoordinateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coordinate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // 設定した requestKey を元にbundleを受け取る.服装指数と天候情報の受け取り.
//        setFragmentResultListener("weather_info") { requestKey, bundle ->
//            val weather = bundle.getString("result_weather")             // 天候
//            val clothesIndex = bundle.getInt("result_clothes_index")   // 服装指数
//
//            // 値の受け渡し確認のテスト
//            weatherInfoText.text = weather
//            clothesIndexText.text = clothesIndex.toString()
//        }

    }

}
