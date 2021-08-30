package nhs90556.hal.ac.u_22

import android.preference.PreferenceManager
import android.util.Log
import kotlinx.android.synthetic.main.fragment_home.*
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
              0 = 時間
              1 = 温度
              2 = 天候id
              3 = 湿度
              4 = 降水量
              5 = 体感温度
              6 = 日付
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
    val clothingIndexStr: Array<String?> = arrayOfNulls(8)
    val weatherDatas = Array(8, {arrayOfNulls<String>(7)})
    var globalWetherIcons = arrayOf<String>()

    init {
        city = _city
        apiKey = _apiKey
    }

    // unixtimeからフォーマットの時間に変換
    private fun unixTimeChange(unixTime: String): String {
        val sdf = SimpleDateFormat("HH:mm")
        val nowTime = Date(unixTime.toInt() * 1000L)
        return sdf.format(nowTime)
    }

    // unixtimeからフォーマットの日付に変換
    private fun unixDateChange(unixTime: String): String {
        val sdf = SimpleDateFormat("dd")
        val nowTime = Date(unixTime.toInt() * 1000L)
        return sdf.format(nowTime)
    }

    // 全国の天候データの取得
    fun setGlobalWeather(): Job = GlobalScope.launch {
        // APIを使う際に必要なKEY
        val API_KEY = apiKey

        val areas = arrayOf("Okinawa","Sapporo","Niigata","Kanazawa","Hiroshima","Fukuoka","kagoshima","Kochi","Osaka","Nagoya","Tokyo","Sendai")

        for(area in areas){
            // URL。場所と言語・API_KEYを添付
            val API_URL = "https://api.openweathermap.org/data/2.5/forecast?" +
                    "q=" + area + ",jp&" +
                    "units=" + "metric" + "&" +
                    "cnt=" + "1" + "&" +
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
            val Object = list.getJSONObject(0)
            globalWetherIcons += Object.getJSONArray("weather").getJSONObject(0).getString("icon")
        }

    }

    // 地域天候データの取得
    fun setLocalWeather(): Job = GlobalScope.launch {

        // APIを使う際に必要なKEY
        val API_KEY = apiKey
        // URL。場所と言語・API_KEYを添付
        val API_URL = "https://api.openweathermap.org/data/2.5/forecast?" +
                "q=" + city + ",jp&" +
                "lang=" + "ja" + "&" +
                "units=" + "metric" + "&" +
                "cnt=" + "8" + "&" +
                "exclude=current,minutely,hourly,alerts&" +
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
            // 降水確率
            val pop = Object.getString("pop").toDouble()
            val changePop = Math.round(pop * 10.0) / 10.0
            // 体感温度
            val feelTemp = Object.getJSONObject("main").getString("feels_like")

            weatherDatas[i][0] = unixTimeChange(date)
            weatherDatas[i][1] = temperature
            weatherDatas[i][2] = weather
            weatherDatas[i][3] = humidity
            weatherDatas[i][4] = changePop.toString()
            weatherDatas[i][5] = feelTemp
            weatherDatas[i][6] = unixDateChange(date)

            // ここに服装指数の計算式を入れる
            when {
                weatherDatas[i][1].toString().substring(0, 2).toInt() > 26 -> {
                    clothingIndexStr[i] = "90"
                }
                weatherDatas[i][1].toString().substring(0, 2).toInt() > 21 -> {
                    clothingIndexStr[i] = "70"
                }
                weatherDatas[i][1].toString().substring(0, 2).toInt() > 16 -> {
                    clothingIndexStr[i] = "50"
                }
                else -> {
                    clothingIndexStr[i] = "30"
                }
            }
//            Log.d("服装指数検証用", clothingIndexStr[i].toString())
        }
    }

    // 天候情報を返す関数
    fun getWeather():Array<Array<String?>>{
        Thread.sleep(100)
        return weatherDatas
    }

    // 服装指数を返す関数
    fun getClothingIndex(): Array<String?> {
        Thread.sleep(300)
        return clothingIndexStr
    }

    // 全国の天気を返す関数
    fun getGlobalWeatherIcons():Array<String>{
        Thread.sleep(5000)
        return globalWetherIcons
    }

}