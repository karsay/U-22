package nhs90556.hal.ac.u_22

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class Weather constructor(_city: String, _cnt: Int, _apiKey: String){
    val city:String
    val cnt:Int
    val apiKey:String
    init {
        city = _city
        cnt = _cnt
        apiKey = _apiKey
    }

    var weatherDatas: Array<String?> = arrayOfNulls(cnt)

    // unixtimeからフォーマットの日付に変換
    private fun unixTimeChange(unixTime: String): String {
        val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm")
        val nowTime = Date(unixTime.toInt() * 1000L)
        return sdf.format(nowTime)
    }

    // 天候データの取得
    private fun setWeather(): Job = GlobalScope.launch {

        // APIを使う際に必要なKEY
        val API_KEY = apiKey
        // URL。場所と言語・API_KEYを添付
        val API_URL = "https://api.openweathermap.org/data/2.5/forecast?" +
                "q=" + city + ",jp&" +
                "lang=" + "ja" + "&" +
                "units=" + "metric" + "&" +
                "cnt=" + cnt.toString() + "&" +
                "appid=" + API_KEY
        val url = URL(API_URL)

        //APIから情報を取得する.
        val br = BufferedReader(InputStreamReader(url.openStream()))
        // 所得した情報を文字列化
        val str = br.readText()
        //json形式のデータとして識別
        val json = JSONObject(str)
        // 配列を取得
        val list = json.getJSONArray("list")

        for (i in 1..cnt) {
            //時間帯？
            val Object = list.getJSONObject(i - 1)
            // 日時
            val date = Object.getString("dt")
            // 天候
            val weather = Object.getJSONArray("weather").getJSONObject(0).getString("description")
            // 気温
            val temperature = Object.getJSONObject("main").getString("temp")
            // 湿度
            val humidity = Object.getJSONObject("main").getString("humidity")
            // 降水量
            var precipitation = "0"
            try {
                precipitation = Object.getJSONObject("rain").getString("3h")
            } catch (e: Exception) {
            }

            weatherDatas[i - 1] = "日時:" + unixTimeChange(date) + "\n天候:" + weather + "\n気温:" + temperature + "°" + "\n湿度:" + humidity + "%" +
                        "\n降水量:" + precipitation + "mm" + "\n-----------------------------\n"
        }
    }

    fun getWeather():Array<String?>{
        setWeather()
        Thread.sleep(500)
        return weatherDatas
    }
}