package nhs90556.hal.ac.u_22.ui.home

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import nhs90556.hal.ac.u_22.MainActivity
import nhs90556.hal.ac.u_22.R
import nhs90556.hal.ac.u_22.Weather
import kotlin.concurrent.timer


class HomeFragment : Fragment() {

    // スライドショー
    class MyAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        private val resources = listOf(
            R.drawable.cor1,
            R.drawable.cor2,
            R.drawable.cor3
        )

        override fun getCount(): Int {
            return resources.size
        }

        override fun getItem(position: Int): Fragment {
            return HomeImageFragment.newInstance(resources[position])
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //スライドショー
        pager.adapter = MyAdapter(childFragmentManager)
        val handler = Handler()
        timer(period = 5000) {
            handler.post{
                pager?.currentItem = (pager.currentItem + 1) % 3
            }
        }

        //天候取得
        val weather = Weather("nagoya-shi","6e8e86e4e208ed9480deb401bd28ba65")
        val sb = StringBuilder()
        for(i in 0..7){
            sb.append(weather.getWeather()[i])
        }


        val tableDay: List<TextView> = listOf(tableDay1, tableDay2, tableDay3, tableDay4, tableDay5, tableDay6, tableDay7)
        val tableTemp: List<TextView> = listOf(tableTemp1, tableTemp2, tableTemp3, tableTemp4, tableTemp5, tableTemp6, tableTemp7)
        val tableWeather: List<ImageView> = listOf(tableWeather1, tableWeather2, tableWeather3, tableWeather4, tableWeather5, tableWeather6, tableWeather7)
        val tableHum: List<TextView> = listOf(tableHum1, tableHum2, tableHum3, tableHum4, tableHum5, tableHum6, tableHum7)
//        val tableDay: List<TextView> = listOf(tableDay1, tableDay2, tableDay3, tableDay4, tableDay5, tableDay6, tableDay7)

        val weatherData = weather.getWeather()
        for (i in 0..6) {
            tableDay[i].setText(weatherData[i][0])
            tableTemp[i].setText(String.format("%s°",weatherData[i][1]?.substring(0,2)))
            tableWeather[i].setImageResource(resources.getIdentifier(String.format("w%s", weatherData[i][2]), "drawable", getActivity()?.getPackageName()))
            tableHum[i].setText(String.format("%s％",weatherData[i][3]))
        }
    }
}