package nhs90556.hal.ac.u_22.models

import io.realm.RealmObject
import io.realm.annotations.*

open class RecommendItemModel: RealmObject() {
    @PrimaryKey
    var item_id: Int = 0
    @Required
    var item_name: String = ""
    @Required
    var item_url: String = ""
    // 1:30℃以上, 2:25℃~29℃, 3:20℃~24℃, 4:19℃~15℃, 5:14℃~10℃, 6:9℃~5℃, 7:4℃~0℃, 8:-1℃以下
    var item_category: Int = 0
}