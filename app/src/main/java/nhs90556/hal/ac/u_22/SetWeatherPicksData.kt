package nhs90556.hal.ac.u_22

import android.app.Application
import android.preference.PreferenceManager
import android.util.Log
import androidx.core.content.edit
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import nhs90556.hal.ac.u_22.models.CoordinateModel
import nhs90556.hal.ac.u_22.models.RecommendItemModel

class SetWeatherPicksData : Application() {

    private lateinit var realm: Realm

    override fun onCreate() {
        super.onCreate()

        // 全国の天気の初期化
        val weather = Weather("","6e8e86e4e208ed9480deb401bd28ba65")
        weather.setGlobalWeather()
        val globalWetherIcons = weather.getGlobalWeatherIcons()
        // フラグメント生成時に毎回api１２回投げてるとすぐ利用制限かかるかつ時間もかかるため全国天気は内部メモリに保存
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        // アプリ起動時に一旦クリア
        pref.edit{
            clear()
        }
        // データの代入
        val editor = pref.edit()
        editor.putString("OKINAWA", globalWetherIcons[0])
        editor.putString("SAPPORO", globalWetherIcons[1])
        editor.putString("NIIGATA", globalWetherIcons[2])
        editor.putString("KANAZAWA", globalWetherIcons[3])
        editor.putString("HIROSHIMA", globalWetherIcons[4])
        editor.putString("FUKUOKA", globalWetherIcons[5])
        editor.putString("KAGOSHIMA", globalWetherIcons[6])
        editor.putString("KOCHI", globalWetherIcons[7])
        editor.putString("OSAKA", globalWetherIcons[8])
        editor.putString("NAGOYA", globalWetherIcons[9])
        editor.putString("TOKYO", globalWetherIcons[10])
        editor.putString("SENDAI", globalWetherIcons[11])
            .apply()

        // データベース
        Realm.init(this)

        //設定
        val config = RealmConfiguration.
        Builder().
        deleteRealmIfMigrationNeeded().
        build()
        Realm.deleteRealm(config)
        Realm.setDefaultConfiguration(config)

// realmインスタンス作成
        realm = Realm.getDefaultInstance()
// 男性: 服装指数90
        val coordinateUrlMan90: List<String> = listOf(
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/hansode/mens/00001.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/hansode/mens/00002.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/hansode/mens/00003.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/hansode/mens/00004.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/hansode/mens/00005.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/hansode/mens/00006.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/hansode/mens/00007.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/hansode/mens/00008.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/hansode/mens/00009.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/hansode/mens/00010.png"
        )
// 一言コメント
        val coordinateDetailMan90: List<String> = listOf(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10"
        )
// 検証用レコード追加
        repeat(coordinateUrlMan90.size) {
            realm.executeTransaction { db: Realm ->
                val maxId = db.where<CoordinateModel>().max("coordinate_id")
                val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
                val coordinate = db.createObject<CoordinateModel>(nextId)
                coordinate.coordinate_url = coordinateUrlMan90[it]
                coordinate.coordinate_detail = coordinateDetailMan90[it]
                coordinate.coordinate_index = 90
                coordinate.coordinate_weather = 1
                coordinate.coordinate_gender = 1
            }
        }
// 男性: 服装指数70
        val coordinateUrlMan70: List<String> = listOf(
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/nagasode/mens/00001.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/nagasode/mens/00002.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/nagasode/mens/00003.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/nagasode/mens/00004.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/nagasode/mens/00005.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/nagasode/mens/00006.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/nagasode/mens/00007.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/nagasode/mens/00008.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/nagasode/mens/00009.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/nagasode/mens/00010.png"
        )
// 一言コメント
        val coordinateDetailMan70: List<String> = listOf(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10"
        )
// 検証用レコード追加
        repeat(coordinateUrlMan70.size) {
            realm.executeTransaction { db: Realm ->
                val maxId = db.where<CoordinateModel>().max("coordinate_id")
                val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
                val coordinate = db.createObject<CoordinateModel>(nextId)
                coordinate.coordinate_url = coordinateUrlMan70[it]
                coordinate.coordinate_detail = coordinateDetailMan70[it]
                coordinate.coordinate_index = 70
                coordinate.coordinate_weather = 1
                coordinate.coordinate_gender = 1
            }
        }
// 男性: 服装指数50
        val coordinateUrlMan50: List<String> = listOf(
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/ichimaihaori/mens/00001.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/ichimaihaori/mens/00002.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/ichimaihaori/mens/00003.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/ichimaihaori/mens/00004.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/ichimaihaori/mens/00005.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/ichimaihaori/mens/00006.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/ichimaihaori/mens/00007.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/ichimaihaori/mens/00008.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/ichimaihaori/mens/00009.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/ichimaihaori/mens/00010.png"
        )
// 一言コメント
        val coordinateDetailMan50: List<String> = listOf(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10"
        )
// 検証用レコード追加
        repeat(coordinateUrlMan50.size) {
            realm.executeTransaction { db: Realm ->
                val maxId = db.where<CoordinateModel>().max("coordinate_id")
                val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
                val coordinate = db.createObject<CoordinateModel>(nextId)
                coordinate.coordinate_url = coordinateUrlMan50[it]
                coordinate.coordinate_detail = coordinateDetailMan50[it]
                coordinate.coordinate_index = 50
                coordinate.coordinate_weather = 1
                coordinate.coordinate_gender = 1
            }
        }
// 男性: 服装指数30
        val coordinateUrlMan30: List<String> = listOf(
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/boukangu/mens/00001.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/boukangu/mens/00002.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/boukangu/mens/00003.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/boukangu/mens/00004.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/boukangu/mens/00005.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/boukangu/mens/00006.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/boukangu/mens/00007.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/boukangu/mens/00008.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/boukangu/mens/00009.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/boukangu/mens/00010.png"
        )
// 一言コメント
        val coordinateDetailMan30: List<String> = listOf(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10"
        )
// 検証用レコード追加
        repeat(coordinateUrlMan30.size) {
            realm.executeTransaction { db: Realm ->
                val maxId = db.where<CoordinateModel>().max("coordinate_id")
                val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
                val coordinate = db.createObject<CoordinateModel>(nextId)
                coordinate.coordinate_url = coordinateUrlMan30[it]
                coordinate.coordinate_detail = coordinateDetailMan30[it]
                coordinate.coordinate_index = 30
                coordinate.coordinate_weather = 1
                coordinate.coordinate_gender = 1
            }
        }
// 女性: 服装指数90
        val coordinateUrlWoman90: List<String> = listOf(
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/hansode/ladies/00001.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/hansode/ladies/00002.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/hansode/ladies/00003.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/hansode/ladies/00004.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/hansode/ladies/00005.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/hansode/ladies/00006.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/hansode/ladies/00007.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/hansode/ladies/00008.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/hansode/ladies/00009.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/hansode/ladies/00010.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/hansode/ladies/00011.png"
        )
// 一言コメント
        val coordinateDetailWoman90: List<String> = listOf(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11"
        )
// 検証用レコード追加
        repeat(coordinateUrlWoman90.size) {
            realm.executeTransaction { db: Realm ->
                val maxId = db.where<CoordinateModel>().max("coordinate_id")
                val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
                val coordinate = db.createObject<CoordinateModel>(nextId)
                coordinate.coordinate_url = coordinateUrlWoman90[it]
                coordinate.coordinate_detail = coordinateDetailWoman90[it]
                coordinate.coordinate_index = 90
                coordinate.coordinate_weather = 1
                coordinate.coordinate_gender = 2
            }
        }
// 女性: 服装指数70
        val coordinateUrlWoman70: List<String> = listOf(
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/nagasode/ladies/00001.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/nagasode/ladies/00002.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/nagasode/ladies/00003.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/nagasode/ladies/00004.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/nagasode/ladies/00005.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/nagasode/ladies/00006.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/nagasode/ladies/00007.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/nagasode/ladies/00008.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/nagasode/ladies/00009.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/nagasode/ladies/00010.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/nagasode/ladies/00011.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/nagasode/ladies/00012.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/nagasode/ladies/00013.png"
        )
// 一言コメント
        val coordinateDetailWoman70: List<String> = listOf(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12",
            "13"
        )
// 検証用レコード追加
        repeat(coordinateUrlWoman70.size) {
            realm.executeTransaction { db: Realm ->
                val maxId = db.where<CoordinateModel>().max("coordinate_id")
                val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
                val coordinate = db.createObject<CoordinateModel>(nextId)
                coordinate.coordinate_url = coordinateUrlWoman70[it]
                coordinate.coordinate_detail = coordinateDetailWoman70[it]
                coordinate.coordinate_index = 70
                coordinate.coordinate_weather = 1
                coordinate.coordinate_gender = 2
            }
        }
// 女性: 服装指数50
        val coordinateUrlWoman50: List<String> = listOf(
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/ichimaihaori/ladies/00001.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/ichimaihaori/ladies/00002.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/ichimaihaori/ladies/00003.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/ichimaihaori/ladies/00004.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/ichimaihaori/ladies/00005.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/ichimaihaori/ladies/00006.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/ichimaihaori/ladies/00007.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/ichimaihaori/ladies/00008.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/ichimaihaori/ladies/00009.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/ichimaihaori/ladies/00010.png"
        )
// 一言コメント
        val coordinateDetailWoman50: List<String> = listOf(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10"
        )
// 検証用レコード追加
        repeat(coordinateUrlWoman50.size) {
            realm.executeTransaction { db: Realm ->
                val maxId = db.where<CoordinateModel>().max("coordinate_id")
                val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
                val coordinate = db.createObject<CoordinateModel>(nextId)
                coordinate.coordinate_url = coordinateUrlWoman50[it]
                coordinate.coordinate_detail = coordinateDetailWoman50[it]
                coordinate.coordinate_index = 50
                coordinate.coordinate_weather = 1
                coordinate.coordinate_gender = 2
            }
        }
// 女性: 服装指数30
        val coordinateUrlWoman30: List<String> = listOf(
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/boukangu/ladies/00001.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/boukangu/ladies/00002.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/boukangu/ladies/00003.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/boukangu/ladies/00004.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/boukangu/ladies/00005.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/boukangu/ladies/00006.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/boukangu/ladies/00007.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/boukangu/ladies/00008.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/boukangu/ladies/00009.png",
            "https://akissutest.s3.us-east-2.amazonaws.com/fashion/boukangu/ladies/000010.png"
        )
// 一言コメント
        val coordinateDetailWoman30: List<String> = listOf(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10"
        )
// 検証用レコード追加
        repeat(coordinateUrlWoman30.size) {
            realm.executeTransaction { db: Realm ->
                val maxId = db.where<CoordinateModel>().max("coordinate_id")
                val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
                val coordinate = db.createObject<CoordinateModel>(nextId)
                coordinate.coordinate_url = coordinateUrlWoman30[it]
                coordinate.coordinate_detail = coordinateDetailWoman30[it]
                coordinate.coordinate_index = 30
                coordinate.coordinate_weather = 1
                coordinate.coordinate_gender = 2
            }
        }

        // おすすめアイテム画像のURLを格納
        val recommendUrl: List<String> = listOf(
            "https://akissutest.s3.us-east-2.amazonaws.com/%E4%BE%BF%E5%88%A9%E3%82%A2%E3%82%A4%E3%83%86%E3%83%A0/pocari.jpg",
            "https://akissutest.s3.us-east-2.amazonaws.com/%E4%BE%BF%E5%88%A9%E3%82%A2%E3%82%A4%E3%83%86%E3%83%A0/hiyaron.jpg",
            "https://akissutest.s3.us-east-2.amazonaws.com/%E4%BE%BF%E5%88%A9%E3%82%A2%E3%82%A4%E3%83%86%E3%83%A0/neckcooler.jpg",
            "https://akissutest.s3.us-east-2.amazonaws.com/%E4%BE%BF%E5%88%A9%E3%82%A2%E3%82%A4%E3%83%86%E3%83%A0/shiotab.jpg",
            "https://akissutest.s3.us-east-2.amazonaws.com/%E4%BE%BF%E5%88%A9%E3%82%A2%E3%82%A4%E3%83%86%E3%83%A0/SEABREEZE.jpg",
            "https://akissutest.s3.us-east-2.amazonaws.com/%E4%BE%BF%E5%88%A9%E3%82%A2%E3%82%A4%E3%83%86%E3%83%A0/asefuki.jpeg",
            "https://akissutest.s3.us-east-2.amazonaws.com/%E4%BE%BF%E5%88%A9%E3%82%A2%E3%82%A4%E3%83%86%E3%83%A0/hat.jpg",
            "https://akissutest.s3.us-east-2.amazonaws.com/%E4%BE%BF%E5%88%A9%E3%82%A2%E3%82%A4%E3%83%86%E3%83%A0/handyfan.webp",
            "https://akissutest.s3.us-east-2.amazonaws.com/%E4%BE%BF%E5%88%A9%E3%82%A2%E3%82%A4%E3%83%86%E3%83%A0/shoes.jpg",
            "https://akissutest.s3.us-east-2.amazonaws.com/%E4%BE%BF%E5%88%A9%E3%82%A2%E3%82%A4%E3%83%86%E3%83%A0/binoculars.jpg",
            "https://akissutest.s3.us-east-2.amazonaws.com/%E4%BE%BF%E5%88%A9%E3%82%A2%E3%82%A4%E3%83%86%E3%83%A0/mushiyoke.jpg",
            "https://akissutest.s3.us-east-2.amazonaws.com/%E4%BE%BF%E5%88%A9%E3%82%A2%E3%82%A4%E3%83%86%E3%83%A0/bicycle.jpg",
            "https://akissutest.s3.us-east-2.amazonaws.com/%E4%BE%BF%E5%88%A9%E3%82%A2%E3%82%A4%E3%83%86%E3%83%A0/heatTech.jpg",
            "https://akissutest.s3.us-east-2.amazonaws.com/%E4%BE%BF%E5%88%A9%E3%82%A2%E3%82%A4%E3%83%86%E3%83%A0/handcream.jpg",
            "https://akissutest.s3.us-east-2.amazonaws.com/%E4%BE%BF%E5%88%A9%E3%82%A2%E3%82%A4%E3%83%86%E3%83%A0/socks.jpg",
            "https://akissutest.s3.us-east-2.amazonaws.com/%E4%BE%BF%E5%88%A9%E3%82%A2%E3%82%A4%E3%83%86%E3%83%A0/Downjacket.jpg",
            "https://akissutest.s3.us-east-2.amazonaws.com/%E4%BE%BF%E5%88%A9%E3%82%A2%E3%82%A4%E3%83%86%E3%83%A0/glovesjpg.jpg",
            "https://akissutest.s3.us-east-2.amazonaws.com/%E4%BE%BF%E5%88%A9%E3%82%A2%E3%82%A4%E3%83%86%E3%83%A0/coat.jpg",
            "https://akissutest.s3.us-east-2.amazonaws.com/%E4%BE%BF%E5%88%A9%E3%82%A2%E3%82%A4%E3%83%86%E3%83%A0/kairo.jpeg",
            "https://akissutest.s3.us-east-2.amazonaws.com/%E4%BE%BF%E5%88%A9%E3%82%A2%E3%82%A4%E3%83%86%E3%83%A0/heatbesuto.jpg"
        )

        // おすすめアイテムの名前を格納
        val recommendName: List<String> = listOf(
            "ポカリスエット",
            "ヒヤロン",
            "ネッククーラー",
            "塩タブレット",
            "シーブリーズ",
            "制汗シート",
            "帽子",
            "ハンディファン",
            "ランニングシューズ",
            "双眼鏡",
            "虫除けスプレー",
            "自転車",
            "ヒートテック",
            "ハンドクリーム",
            "厚手の靴下",
            "ダウン",
            "手袋",
            "コート",
            "カイロ",
            "電熱ベスト"
        )

        // おすすめアイテムの名前を格納
        val recommendCategory: List<Int> = listOf(1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4,
            4, 4, 5, 5, 5, 5
        )

        // おすすめアイテム登録
        repeat(recommendName.size) {
            realm.executeTransaction { db: Realm ->
                val maxId = db.where<RecommendItemModel>().max("item_id")
                val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
                val recommendItem = db.createObject<RecommendItemModel>(nextId)
                recommendItem.item_name = recommendName[it]
                recommendItem.item_url = recommendUrl[it]
                recommendItem.item_category = recommendCategory[it]
            }
        }
        realm.close()
    }
}