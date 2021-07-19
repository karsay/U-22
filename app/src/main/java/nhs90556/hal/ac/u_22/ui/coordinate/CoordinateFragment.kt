package nhs90556.hal.ac.u_22.ui.coordinate


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.fragment_coordinate.*
import nhs90556.hal.ac.u_22.R
import nhs90556.hal.ac.u_22.Weather
import nhs90556.hal.ac.u_22.models.CoordinateModel


/**
 * A simple [Fragment] subclass.
 */
class CoordinateFragment : Fragment() {

    private lateinit var realm: Realm

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coordinate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //DBの初期値設定
        realm = Realm.getDefaultInstance()

        // 検証用レコード追加
        realm.executeTransaction { db: Realm ->
            val maxId = db.where<CoordinateModel>().max("coordinate_id")
            val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
            val coordinate = db.createObject<CoordinateModel>(nextId)
            coordinate.coordinate_url = "https://akissutest.s3.us-east-2.amazonaws.com/d05cb1aa079df16cc63bd1a58fd92a34.jpeg"
            coordinate.coordinate_detail = "これで快適！"
            coordinate.coordinate_index = 10
            coordinate.coordinate_weather = 1
        }


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

        // DBからレコード取得(検証用)
        val coor = realm.where<CoordinateModel>()
            .equalTo("coordinate_url","https://akissutest.s3.us-east-2.amazonaws.com/d05cb1aa079df16cc63bd1a58fd92a34.jpeg")
            .findFirst()
        // 取得したレコードからレイアウトの書き換え
        // 変化が分かるように2個目のコンテンツのみ書き換え
        // 服装指数
        clothingIndexText[1].setText(coor?.coordinate_index.toString())
        // 服の画像
        val imageView2: ImageView = imageView2
        Picasso.get()
            .load(coor?.coordinate_url)
            .into(imageView2)
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
