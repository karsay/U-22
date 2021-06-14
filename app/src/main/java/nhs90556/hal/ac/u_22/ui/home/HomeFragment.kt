package nhs90556.hal.ac.u_22.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import nhs90556.hal.ac.u_22.R
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


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
        getWeather("nagoya-shi")
        Thread.sleep(1000)

        weatherText.text = resultText
    }

    // unixtimeからフォーマットの日付に変換
    private fun unixTimeChange(unixTime: String): String {
        val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm")
        val nowTime = Date(unixTime.toInt() * 1000L)
        return sdf.format(nowTime)
    }

    // ケルビンから摂氏に変換
//    private fun kelvin2Celsius(kelvin: Float): Float{
////        val
//    }

    // 天候データの取得
    private fun getWeather(city: String):Job = GlobalScope.launch {

        // APIを使う際に必要なKEY
        val API_KEY = getString(R.string.weather_api_key)
        // URL。場所と言語・API_KEYを添付
        val API_URL = "https://api.openweathermap.org/data/2.5/forecast?" +
                "q=" + city + ",jp&" +
                "lang=" + "ja" + "&" +
                "APPID=" + API_KEY
        val url = URL(API_URL)

        //APIから情報を取得する.
        val br = BufferedReader(InputStreamReader(url.openStream()))
        // 所得した情報を文字列化
        val str = br.readText()
        //json形式のデータとして識別
        val json = JSONObject(str)
        // 配列を取得
        val list = json.getJSONArray("list")

        //時間帯？
        val Object = list.getJSONObject(0)

        // 日時
        val date = Object.getString("dt")
        // 天候
        val weather = Object.getJSONArray("weather").getJSONObject(0).getString("description")
        // 気温
        val temperature = Object.getJSONObject("main").getString("temp")
        // 湿度
        val humidity = Object.getJSONObject("main").getString("humidity")

        val descriptionText = "日時:" + unixTimeChange(date) + "\n天候:" + weather + "\n気温:" + temperature + "\n湿度" + humidity

        resultText = descriptionText

    }

}