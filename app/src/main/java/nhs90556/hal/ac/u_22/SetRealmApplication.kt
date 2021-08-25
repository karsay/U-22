package nhs90556.hal.ac.u_22

import android.app.Application
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import nhs90556.hal.ac.u_22.models.CoordinateModel
import nhs90556.hal.ac.u_22.models.FavoriteModel
import nhs90556.hal.ac.u_22.models.RecommendItemModel


class SetRealmApplication : Application() {

    private lateinit var realm: Realm

    override fun onCreate() {
        super.onCreate()
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
//
//        // 検証用レコード追加
//        realm.executeTransaction { db: Realm ->
//            val maxId = db.where<CoordinateModel>().max("coordinate_id")
//            val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
//            val coordinate = db.createObject<CoordinateModel>(nextId)
//            coordinate.coordinate_url = "https://icon-library.com/images/0-icon/0-icon-2.jpg"
//            coordinate.coordinate_detail = "これで快適！"
//            coordinate.coordinate_index = 10
//            coordinate.coordinate_weather = 1
//        }
//
//        realm.executeTransaction { db: Realm ->
//            val maxId = db.where<FavoriteModel>().max("favorite_id")
//            val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
//            val coordinate = db.createObject<FavoriteModel>(nextId)
//            coordinate.favorite_coordinate_id = 4
//        }
//
//        realm.executeTransaction { db: Realm ->
//            val maxId = db.where<CoordinateModel>().max("coordinate_id")
//            val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
//            val coordinate = db.createObject<CoordinateModel>(nextId)
//            coordinate.coordinate_url = "https://nukumori-icon.com/wp/wp-content/uploads/2020/05/nu00511p.jpg"
//            coordinate.coordinate_detail = "これで快適！"
//            coordinate.coordinate_index = 10
//            coordinate.coordinate_weather = 1
//        }
//
//        realm.executeTransaction { db: Realm ->
//            val maxId = db.where<FavoriteModel>().max("favorite_id")
//            val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
//            val coordinate = db.createObject<FavoriteModel>(nextId)
//            coordinate.favorite_coordinate_id = 5
//        }
//
//        realm.executeTransaction { db: Realm ->
//            val maxId = db.where<CoordinateModel>().max("coordinate_id")
//            val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
//            val coordinate = db.createObject<CoordinateModel>(nextId)
//            coordinate.coordinate_url = "https://nukumori-icon.com/wp/wp-content/uploads/2020/05/nu00512p.jpg"
//            coordinate.coordinate_detail = "これで快適！"
//            coordinate.coordinate_index = 10
//            coordinate.coordinate_weather = 1
//        }
//
//        realm.executeTransaction { db: Realm ->
//            val maxId = db.where<FavoriteModel>().max("favorite_id")
//            val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
//            val coordinate = db.createObject<FavoriteModel>(nextId)
//            coordinate.favorite_coordinate_id = 6
//        }
//
//        realm.executeTransaction { db: Realm ->
//            val maxId = db.where<CoordinateModel>().max("coordinate_id")
//            val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
//            val coordinate = db.createObject<CoordinateModel>(nextId)
//            coordinate.coordinate_url = "https://nukumori-icon.com/wp/wp-content/uploads/2020/04/nu00439p.jpg"
//            coordinate.coordinate_detail = "これで快適！"
//            coordinate.coordinate_index = 10
//            coordinate.coordinate_weather = 1
//        }
//
//        realm.executeTransaction { db: Realm ->
//            val maxId = db.where<FavoriteModel>().max("favorite_id")
//            val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
//            val coordinate = db.createObject<FavoriteModel>(nextId)
//            coordinate.favorite_coordinate_id = 7
//        }
//
//        realm.executeTransaction { db: Realm ->
//            val maxId = db.where<CoordinateModel>().max("coordinate_id")
//            val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
//            val coordinate = db.createObject<CoordinateModel>(nextId)
//            coordinate.coordinate_url = "https://icon-library.com/images/number-4-icon/number-4-icon-29.jpg"
//            coordinate.coordinate_detail = "これで快適！"
//            coordinate.coordinate_index = 10
//            coordinate.coordinate_weather = 1
//        }
//
//        realm.executeTransaction { db: Realm ->
//            val maxId = db.where<FavoriteModel>().max("favorite_id")
//            val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
//            val coordinate = db.createObject<FavoriteModel>(nextId)
//            coordinate.favorite_coordinate_id = 8
//        }
//
//        realm.executeTransaction { db: Realm ->
//            val maxId = db.where<CoordinateModel>().max("coordinate_id")
//            val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
//            val coordinate = db.createObject<CoordinateModel>(nextId)
//            coordinate.coordinate_url = "https://img.icons8.com/metro/452/5-circle-c.png"
//            coordinate.coordinate_detail = "これで快適！"
//            coordinate.coordinate_index = 10
//            coordinate.coordinate_weather = 1
//        }
//
//        realm.executeTransaction { db: Realm ->
//            val maxId = db.where<FavoriteModel>().max("favorite_id")
//            val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
//            val coordinate = db.createObject<FavoriteModel>(nextId)
//            coordinate.favorite_coordinate_id = 9
//        }
//
//        realm.executeTransaction { db: Realm ->
//            val maxId = db.where<CoordinateModel>().max("coordinate_id")
//            val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
//            val coordinate = db.createObject<CoordinateModel>(nextId)
//            coordinate.coordinate_url = "https://akissutest.s3.us-east-2.amazonaws.com/d05cb1aa079df16cc63bd1a58fd92a34.jpeg"
//            coordinate.coordinate_detail = "これで快適！"
//            coordinate.coordinate_index = 10
//            coordinate.coordinate_weather = 1
//        }
//
//        realm.executeTransaction { db: Realm ->
//            val maxId = db.where<FavoriteModel>().max("favorite_id")
//            val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
//            val coordinate = db.createObject<FavoriteModel>(nextId)
//            coordinate.favorite_coordinate_id = 10
//        }
//
//        realm.executeTransaction { db: Realm ->
//            val maxId = db.where<CoordinateModel>().max("coordinate_id")
//            val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
//            val coordinate = db.createObject<CoordinateModel>(nextId)
//            coordinate.coordinate_url = "https://akissutest.s3.us-east-2.amazonaws.com/d05cb1aa079df16cc63bd1a58fd92a34.jpeg"
//            coordinate.coordinate_detail = "これで快適！"
//            coordinate.coordinate_index = 10
//            coordinate.coordinate_weather = 1
//        }
//
//        realm.executeTransaction { db: Realm ->
//            val maxId = db.where<FavoriteModel>().max("favorite_id")
//            val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
//            val coordinate = db.createObject<FavoriteModel>(nextId)
//            coordinate.favorite_coordinate_id = 11
//        }

        // コーディネート画像のURLを格納
        val coordinateUrl: List<String> = listOf(
            "https://akissutest.s3.us-east-2.amazonaws.com/d05cb1aa079df16cc63bd1a58fd92a34.jpeg",
            "https://akissutest.s3.us-east-2.amazonaws.com/Gucci_2020SS_001.jpeg",
            "https://akissutest.s3.us-east-2.amazonaws.com/3.webp"
        )

        // コーディネート画像のURLを格納
        val coordinateDetail: List<String> = listOf(
            "これで快適!",
            "これで快適だよなぁ！",
            "これで快適ですよね！"
        )

        // 検証用レコード追加: 服装指数90
        repeat(coordinateUrl.size) {
            realm.executeTransaction { db: Realm ->
                val maxId = db.where<CoordinateModel>().max("coordinate_id")
                val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
                val coordinate = db.createObject<CoordinateModel>(nextId)
                coordinate.coordinate_url = coordinateUrl[it]
                coordinate.coordinate_detail = coordinateDetail[it]
                coordinate.coordinate_index = 90
                coordinate.coordinate_weather = 1
            }
        }

        // 検証用レコード追加: 服装指数70
        repeat(coordinateUrl.size) {
            realm.executeTransaction { db: Realm ->
                val maxId = db.where<CoordinateModel>().max("coordinate_id")
                val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
                val coordinate = db.createObject<CoordinateModel>(nextId)
                coordinate.coordinate_url = coordinateUrl[it]
                coordinate.coordinate_detail = coordinateDetail[it]
                coordinate.coordinate_index = 70
                coordinate.coordinate_weather = 1
            }
        }

        // おすすめアイテム画像のURLを格納
        val recommendUrl: List<String> = listOf(
            "https://akissutest.s3.us-east-2.amazonaws.com/d05cb1aa079df16cc63bd1a58fd92a34.jpeg",
            "https://akissutest.s3.us-east-2.amazonaws.com/Gucci_2020SS_001.jpeg",
            "https://akissutest.s3.us-east-2.amazonaws.com/3.webp",
            "https://akissutest.s3.us-east-2.amazonaws.com/large_36e37ca6-ca31-486b-a42f-b53eb6e92307.jpeg",
            "https://akissutest.s3.us-east-2.amazonaws.com/tshirt_item.jpeg"

        )

        // おすすめアイテムの名前を格納
        val recommendName: List<String> = listOf(
            "ポカリスエット",
            "ヒヤロン",
            "公共交通機関",
            "帽子",
            "シーブリーズ",
            "クールチューブ",
            "エナジードリンク",
            "自動車",
            "ランニングシューズ",
            "双眼鏡",
            "厚手の靴下",
            "自転車",
            "ヒートテック",
            "ハンドクリーム",
            "手袋",
            "コート",
            "ホットレモン",
            "ダウンジャケット",
            "カイロ",
            "電熱ベスト"
        )

        // おすすめアイテムの名前を格納
        val recommendCategory: List<Int> = listOf(1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 5,
            6, 6, 7, 7, 8, 8
        )

        // おすすめアイテム登録
        var that = 0
        repeat(recommendName.size) {
            realm.executeTransaction { db: Realm ->
                val maxId = db.where<RecommendItemModel>().max("item_id")
                val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
                val recommendItem = db.createObject<RecommendItemModel>(nextId)
                recommendItem.item_name = recommendName[it]
//              recommendItem.item_url = recommendUrl[it]
                recommendItem.item_category = recommendCategory[it]
                // 画像が少ない為、5つの画像でループしている
                recommendItem.item_url = recommendUrl[that]
                that++
                if (that == 5) that = 0
            }
        }
        realm.close()
    }
}