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

class Weather constructor(_city: String, _apiKey: String){

    /*

        気象情報を取得するクラス
        ・getWeatherメソッド
            - ３時間毎の気象情報を二次元配列で返す
              [x][y]左が時間帯、右が項目
              0 = 日時
              1 = 温度
              2 = 天候id
              3 = 湿度
              4 = 降水量
              5 = 体感温度
              （例）
              [0][1]直近の温度を取得
              [1][3]3時間後の湿度を取得
        .
        ・getClothingIndexメソッド
            - 服装指数を文字列形式で返す
            計算式未実装

     */

    val city:String
    val apiKey:String
    val clothingIndexStr:String = "80"
    val weatherDatas = Array(8, {arrayOfNulls<String>(6)})

    init {
        city = _city
        apiKey = _apiKey
        initWeather()
    }

    // unixtimeからフォーマットの日付に変換
    private fun unixTimeChange(unixTime: String): String {
        val sdf = SimpleDateFormat("HH:mm")
        val nowTime = Date(unixTime.toInt() * 1000L)
        return sdf.format(nowTime)
    }

    // 天候データの取得
    private fun initWeather(): Job = GlobalScope.launch {

        // APIを使う際に必要なKEY
        val API_KEY = apiKey
        // URL。場所と言語・API_KEYを添付
        val API_URL = "https://api.openweathermap.org/data/2.5/forecast?" +
                "q=" + city + ",jp&" +
                "lang=" + "ja" + "&" +
                "units=" + "metric" + "&" +
                "cnt=" + "8" + "&" +
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

        for (i in 0..7) {
            //時間帯？
            val Object = list.getJSONObject(i)
            // 日時
            val date = Object.getString("dt")
            // 天候
            val weather = Object.getJSONArray("weather").getJSONObject(0).getString("icon")
            // 気温
            val temperature = Object.getJSONObject("main").getString("temp")
            // 湿度
            val humidity = Object.getJSONObject("main").getString("humidity")
            // 降水量
            var precipitation = "0"
            // 体感温度
            val feelTemp = Object.getJSONObject("main").getString("feels_like")
            try {
                precipitation = Object.getJSONObject("rain").getString("3h")
            } catch (e: Exception) {
            }

            weatherDatas[i][0] = unixTimeChange(date)
            weatherDatas[i][1] = temperature
            weatherDatas[i][2] = weather
            weatherDatas[i][3] = humidity
            weatherDatas[i][4] = precipitation
            weatherDatas[i][5] = feelTemp

        }

        // ここに服装指数の計算式を入れる
        //clothingIndexStr =
    }

    // 天候情報を返す関数
    fun getWeather():Array<Array<String?>>{
        Thread.sleep(500)
        return weatherDatas
    }

    // 服装指数を返す関数
    fun getClothingIndex(): String {
        return clothingIndexStr
    }

}