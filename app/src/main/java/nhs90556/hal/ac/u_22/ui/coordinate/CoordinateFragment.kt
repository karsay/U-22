package nhs90556.hal.ac.u_22.ui.coordinate


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.setFragmentResultListener
import kotlinx.android.synthetic.main.fragment_coordinate.*
import nhs90556.hal.ac.u_22.R
import nhs90556.hal.ac.u_22.Weather

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

        // クラスの初期化
        val weather = Weather("nagoya-shi","92a88046b4ab7f78a4feb2675631128d")
        // 天気情報の取得
        val weatherData = weather.getWeather()
        // 服装指数の取得
        val clothingIndex = weather.getClothingIndex()

        // 体感温度の表示
        val feelTempText: List<TextView> = listOf(feelTemp1, feelTemp2, feelTemp3)
        for (i in 0..2) {
            feelTempText[i].setText(String.format("%s℃",weatherData[i][5]?.substring(0,2)))
        }
        // 服装指数の表示
        val clothingIndexText: List<TextView> = listOf(clothingIndex1, clothingIndex2, clothingIndex3)
        for (i in 0..2) {
            clothingIndexText[i].setText(clothingIndex)
        }

    }

}
