package nhs90556.hal.ac.u_22

import android.app.Application
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import nhs90556.hal.ac.u_22.models.CoordinateModel
import nhs90556.hal.ac.u_22.models.FavoriteModel


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

        // 検証用レコード追加
        realm.executeTransaction { db: Realm ->
            val maxId = db.where<CoordinateModel>().max("coordinate_id")
            val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
            val coordinate = db.createObject<CoordinateModel>(nextId)
            coordinate.coordinate_url = "https://akissutest.s3.us-east-2.amazonaws.com/d05cb1aa079df16cc63bd1a58fd92a34.jpeg"
            coordinate.coordinate_detail = "これで快適!"
            coordinate.coordinate_index = 90
            coordinate.coordinate_weather = 1
        }
// 検証用レコード追加2
        realm.executeTransaction { db: Realm ->
            val maxId = db.where<CoordinateModel>().max("coordinate_id")
            val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
            val coordinate = db.createObject<CoordinateModel>(nextId)
            coordinate.coordinate_url = "https://akissutest.s3.us-east-2.amazonaws.com/Gucci_2020SS_001.jpeg"
            coordinate.coordinate_detail = "これで快適だよなぁ！"
            coordinate.coordinate_index = 90
            coordinate.coordinate_weather = 1
        }
// 検証用レコード追加3
        realm.executeTransaction { db: Realm ->
            val maxId = db.where<CoordinateModel>().max("coordinate_id")
            val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
            val coordinate = db.createObject<CoordinateModel>(nextId)
            coordinate.coordinate_url = "https://akissutest.s3.us-east-2.amazonaws.com/3.webp"
            coordinate.coordinate_detail = "これで快適ですよね！"
            coordinate.coordinate_index = 90
            coordinate.coordinate_weather = 1
        }


        realm.close()
    }
}