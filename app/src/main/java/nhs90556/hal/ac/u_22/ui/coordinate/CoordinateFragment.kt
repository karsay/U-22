package nhs90556.hal.ac.u_22.ui.coordinate


import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
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
import nhs90556.hal.ac.u_22.models.FavoriteModel


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

        // realmインスタンス作成
        realm = Realm.getDefaultInstance()

        // クラスの初期化
        val weather = Weather("nagoya-shi", "92a88046b4ab7f78a4feb2675631128d")
        weather.setLocalWeather()
        // 天気情報の取得
        val weatherData = weather.getWeather()
        // 服装指数の取得
        val clothingIndex = weather.getClothingIndex()
        // 性別の取得
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val genderId = pref.getInt("GENDER_ID", 1)
//        Log.d("GENDER_ID", genderId.toString())

        val feelTempText: List<TextView> = listOf(feelTemp1, feelTemp2, feelTemp3)
        val clothingIndexText: List<TextView> = listOf(clothingIndex1, clothingIndex2, clothingIndex3)
        val imgBtnList: List<ImageButton> = listOf(imageButton1, imageButton2, lastImageButton)
        val imgViewList: List<ImageView> = listOf(imageView, imageView2, lastImageView)
        val commentList: List<TextView> = listOf(comment1, comment2, lastComment)

        // 服装指数からレコード取得
        var coor = realm.where<CoordinateModel>()
            .equalTo("coordinate_index", clothingIndex[0].toString().toInt())    // 何故かtoInt()がないとエラーになる
            .equalTo("coordinate_gender", genderId.toInt())
            .findAll()

        // コンテンツの書き換え
        for (i in 0..2) {
            // coordinate_idをtagに格納
            imgBtnList[i].setTag(coor[i]?.coordinate_id)
//          Log.d("タグ確認", imgBtnList[i].getTag().toString())

            // お気に入りボタンの判定
            var fvrt = realm.where<FavoriteModel>()
                .equalTo("favorite_coordinate_id", imgBtnList[i].getTag().toString().toInt())
                .findFirst()

            if (fvrt == null) {
                // 未登録の場合、白いブックマーク画像をセット
                imgBtnList[i].setImageResource(R.drawable.codbutton)
            } else {
                // 登録済の場合、赤いブックマーク画像をセット
                imgBtnList[i].setImageResource(R.drawable.codbutton2)
            }
            // 体感温度の表示
            feelTempText[i].setText(String.format("%s℃", weatherData[i][5]?.substring(0, 2)))

            // 服装指数の表示
            clothingIndexText[i].setText(clothingIndex[0].toString())

            // 一言コメントの表示
            commentList[i].setText(coor[i]?.coordinate_detail)

            // コーディネート画像切り替え
            Picasso.get()
                .load(coor[i]?.coordinate_url)
                .into(imgViewList[i])

            // お気に入りボタンの画像切り替え、削除処理. 未登録:白, 登録済:赤
            imgBtnList[i].setOnClickListener(FavoriteEvent())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

}
// お気に入りボタンの画像切り替え.未登録1:白,登録済2:黒
class FavoriteEvent : View.OnClickListener {
    override fun onClick(v: View?) {

        // realmインスタンス作成
        val realm:Realm = Realm.getDefaultInstance()

        // imageButtonの取得
        val imageButton = v?.findViewById(v.id) as ImageButton

        // idの取得
        var setCoordinateId = v.getTag().toString().toInt()
//      Log.d("getTag()できているか確認", setCoordinateId.toString())

        // Tagにセットされたidと一致するレコードをCoordinateテーブルから取得する
        val coor = realm.where<CoordinateModel>()
            .equalTo("coordinate_id", setCoordinateId)
            .findFirst()

        if (coor?.coordinate_id != null) {

            // favoriteテーブルに既に存在しているかどうかの確認
            val fvrt = realm.where<FavoriteModel>()
                .equalTo("favorite_coordinate_id", coor.coordinate_id)
                .findFirst()

            // Favoriteテーブルにない場合
            if (fvrt == null) {
                // Favoriteテーブルに追加する
                realm.executeTransaction { db: Realm ->
                    val maxId = db.where<FavoriteModel>().max("favorite_id")
                    val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
                    val favorite = db.createObject<FavoriteModel>(nextId)
                    favorite.favorite_coordinate_id = coor.coordinate_id
                }
                // ボタンを登録済に切り替える
                imageButton.setImageResource(R.drawable.codbutton2)
            } else {
                // 既にFavoriteテーブルに存在する場合テーブルから削除する
                realm.executeTransaction { db: Realm ->
                    fvrt.deleteFromRealm()
                }
                // ボタンを未登録に切り替える
                imageButton.setImageResource(R.drawable.codbutton)
            }
        }
    }
}