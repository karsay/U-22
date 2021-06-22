package nhs90556.hal.ac.u_22.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import nhs90556.hal.ac.u_22.R
import nhs90556.hal.ac.u_22.Weather


class HomeFragment : Fragment() {

    private var resultText = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weather = Weather("nagoya-shi",5,"6e8e86e4e208ed9480deb401bd28ba65")
        val sb = StringBuilder()
        for(i in 0..4){
            sb.append(weather.getWeather()[i])
        }
        weatherText.text = sb.toString()
    }

}