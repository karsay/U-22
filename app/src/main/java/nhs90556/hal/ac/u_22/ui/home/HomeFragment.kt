package nhs90556.hal.ac.u_22.ui.home

import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.squareup.picasso.Picasso
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.fragment_home.*
import nhs90556.hal.ac.u_22.R
import nhs90556.hal.ac.u_22.Weather
import nhs90556.hal.ac.u_22.models.CoordinateModel
import nhs90556.hal.ac.u_22.models.RecommendItemModel
import kotlin.concurrent.timer


class HomeFragment : Fragment() {

    private lateinit var realm: Realm

    // スライドショー
    class MyAdapter(fm: FragmentManager, clothingIndex:Array<String?>, genderId:Int) : FragmentPagerAdapter(fm) {
//        val pref = PreferenceManager.getDefaultSharedPreferences(this)
//        val genderId = pref.getInt("GENDER_ID", 1)
        // realmインスタンス作成
        val realm = Realm.getDefaultInstance()
        val coor = realm.where<CoordinateModel>()
            .equalTo("coordinate_index", clothingIndex[0].toString().toInt())
            .equalTo("coordinate_gender", genderId.toInt())
            .findAll()
        private val resources = listOf(
            coor[0]!!.coordinate_url,
            coor[1]!!.coordinate_url,
            coor[2]!!.coordinate_url
//            "https://akissutest.s3.us-east-2.amazonaws.com/d05cb1aa079df16cc63bd1a58fd92a34.jpeg",
//            "https://akissutest.s3.us-east-2.amazonaws.com/Gucci_2020SS_001.jpeg",
//            "https://akissutest.s3.us-east-2.amazonaws.com/3.webp"
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

        // 登録都道府県の取得
        // メモリから登録データの取得
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val predectureId = pref.getInt("PREFECTURE_ID", 22)
        val genderId = pref.getInt("GENDER_ID", 1)
        // xmlファイルから都道府県の配列を取得
        val items = resources.getStringArray(R.array.txt_area_arr)
        homeAreaText.setText(String.format("本日の%sの天気", items[predectureId]))
        // xmlファイルから都道府県コードの配列を取得
        val codeItems = resources.getStringArray(R.array.txt_area_code_arr)
        val predectureCode = codeItems[predectureId]

        // 天候取得
        val weather = Weather(predectureCode,"6e8e86e4e208ed9480deb401bd28ba65")
        weather.setLocalWeather()
        val sb = StringBuilder()
        for(i in 0..7){
            sb.append(weather.getWeather()[i])
        }

        val tableDay: List<TextView> = listOf(tableDay1, tableDay2, tableDay3, tableDay4, tableDay5, tableDay6, tableDay7)
        val tableTemp: List<TextView> = listOf(tableTemp1, tableTemp2, tableTemp3, tableTemp4, tableTemp5, tableTemp6, tableTemp7)
        val tableWeather: List<ImageView> = listOf(tableWeather1, tableWeather2, tableWeather3, tableWeather4, tableWeather5, tableWeather6, tableWeather7)
        val tableHum: List<TextView> = listOf(tableHum1, tableHum2, tableHum3, tableHum4, tableHum5, tableHum6, tableHum7)
        val tablePop: List<TextView> = listOf(tablePop1, tablePop2, tablePop3, tablePop4, tablePop5, tablePop6, tablePop7)
        val tableDate: List<TextView> = listOf(tableDate1, tableDate2, tableDate3, tableDate4, tableDate5, tableDate6, tableDate7)

        val weatherData = weather.getWeather()
        // 天候情報の代入
        for (i in 0..6) {
            tableDay[i].setText(weatherData[i][0])
            tableTemp[i].setText(String.format("%s°",weatherData[i][1]?.substring(0,2)))
            tableWeather[i].setImageResource(resources.getIdentifier(String.format("w%s", weatherData[i][2]), "drawable", getActivity()?.getPackageName()))
            tableHum[i].setText(String.format("%s％",weatherData[i][3]))
            tablePop[i].setText(String.format("%s％", weatherData[i][4]))
            tableDate[i].setText(String.format("%s日", weatherData[i][6]))
        }
        val clothingIndex = weather.getClothingIndex()
        val editor = pref.edit()
        editor.putString("CLOTHING_INDEX", clothingIndex[0])
            .apply()

        //スライドショー
        pager.adapter = MyAdapter(childFragmentManager, clothingIndex, genderId)
        val handler = Handler()
        timer(period = 5000) {
            handler.post{
                pager?.currentItem = (pager.currentItem + 1) % 3
            }
        }

        // 全国の天気取得
        nahaButton.setImageResource(resources.getIdentifier(String.format("w%s", pref.getString("OKINAWA", "01d")),"drawable", getActivity()?.getPackageName()))
        imageButton1.setImageResource(resources.getIdentifier(String.format("w%s", pref.getString("SAPPORO", "01d")),"drawable", getActivity()?.getPackageName()))
        niigataButton.setImageResource(resources.getIdentifier(String.format("w%s", pref.getString("NIIGATA", "01d")),"drawable", getActivity()?.getPackageName()))
        kanazawaButton.setImageResource(resources.getIdentifier(String.format("w%s", pref.getString("KANAZAWA", "01d")),"drawable", getActivity()?.getPackageName()))
        hirosimaButton.setImageResource(resources.getIdentifier(String.format("w%s", pref.getString("HIROSHIMA", "01d")),"drawable", getActivity()?.getPackageName()))
        fukuokaButton.setImageResource(resources.getIdentifier(String.format("w%s", pref.getString("FUKUOKA", "01d")),"drawable", getActivity()?.getPackageName()))
        kagoshimaButton.setImageResource(resources.getIdentifier(String.format("w%s", pref.getString("KAGOSHIMA", "01d")),"drawable", getActivity()?.getPackageName()))
        kochiButton.setImageResource(resources.getIdentifier(String.format("w%s", pref.getString("KOCHI", "01d")),"drawable", getActivity()?.getPackageName()))
        osakaButtin.setImageResource(resources.getIdentifier(String.format("w%s", pref.getString("OSAKA", "01d")),"drawable", getActivity()?.getPackageName()))
        nagoyaButton.setImageResource(resources.getIdentifier(String.format("w%s", pref.getString("NAGOYA", "01d")),"drawable", getActivity()?.getPackageName()))
        tokyoButton.setImageResource(resources.getIdentifier(String.format("w%s", pref.getString("TOKYO", "01d")),"drawable", getActivity()?.getPackageName()))
        sendaiButton.setImageResource(resources.getIdentifier(String.format("w%s", pref.getString("SENDAI", "01d")),"drawable", getActivity()?.getPackageName()))

        // おすすめアイテム一覧セット
        realm = Realm.getDefaultInstance()

        val itemCategory = when(clothingIndex[0]) {
            "90" -> "1"
            "70" -> "2"
            "50" -> "3"
            "30" -> "4"
            "10" -> "5"
            else -> "0"
        }

        val recItem = realm.where<RecommendItemModel>()
            .equalTo("item_category",itemCategory.toInt())
            .findAll()

        val itemImages: List<ImageView> = listOf(itemImage1, itemImage2, itemImage3, itemImage4)
        val itemNames: List<TextView> = listOf(itemName1, itemName2, itemName3, itemName4)

        for (i in 0..3) {
            // 画像の設定
            Picasso.get()
                .load(recItem[i]?.item_url)
                .into(itemImages[i])
            // アイテム名の設定
            itemNames[i].setText(recItem[i]?.item_name)
        }

    }
}