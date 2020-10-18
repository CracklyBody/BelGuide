package com.example.testframe.ui

import android.net.Uri
import com.example.testframe.ui.entertainment.place.Place
import com.example.testframe.ui.market.ItemMarket

val cinema_list = listOf(
    Place("ГриннФильм", Uri.parse("https://www.malls.ru/upload/resize_cache/iblock/fe7/632_700_1bb65d3589fb69dd4a17961e4a75772f4/fe7e33e1b622db1ea001f0bf908be9ae.jpg")),
    Place("СинемаПарк", Uri.parse("https://avatars.mds.yandex.net/get-altay/1426646/2a0000016848f38ee93c61db4a3254d4890e/XXL")),
    Place("Русич", Uri.parse("https://vibirai.ru/image/1321492.r379x254.jpg?1490860981")),
    Place("Победа",Uri.parse("https://avatars.mds.yandex.net/get-altay/247136/2a0000015b161bf2dd5f92f2514498e5692b/XXL")),
    Place("Радуга",Uri.parse("https://cdn.kino.ru/images/resizes/619/57.jpg")),
    Place("СинемаСтар", Uri.parse("https://img.icity.life/upload/2017/112/e2a/313/full/e2a313b26698289e0a8f1937a68d9320.jpg"))
)

val park_list = listOf(
    Place("Победа",Uri.parse("https://bel.cultreg.ru/uploads/370c4d2fc7f0a815d9fd55909c5d4f4d_w540_h360.jpg")),
    Place("Центарльный",Uri.parse("https://media-cdn.tripadvisor.com/media/photo-s/06/68/b2/34/caption.jpg")),
    Place("Пушкинская аллея",Uri.parse("https://gidavia.com/wp-content/uploads/2018/12/6-%D0%9F%D1%83%D1%88%D0%BA%D0%B8%D0%BD%D1%81%D0%BA%D0%B0%D1%8F-%D0%B0%D0%BB%D0%BB%D0%B5%D1%8F.jpg")),
    Place("Юнити", Uri.parse("https://lh3.googleusercontent.com/proxy/Odc8lB4DT4evGqj5HViBQJomgl5kK5BML89nzawrj90adbZo_eyyLnOrXj9dgk3QjSdg5X24tqtJRazEI0bvhCzLVEP61ooRYSyAalt815E")),
    Place("Котофей",Uri.parse("https://img02.rl0.ru/5e5ecc2134958c8bd0c27a51ffa82af2/765x525i/https/news.rambler.ru/img/2018/10/53693a9272b0e.jpg")),
    Place("Динопарк", Uri.parse("https://bel.cultreg.ru/uploads/1e22a767c45236a907eb2ec79ac85ea5_w540_h360.jpg"))
)

val shop_center_list = listOf(
    Place("Белгород", Uri.parse("https://avatars.mds.yandex.net/get-altay/200322/2a0000015b170f184a46fc9e00c7c094ee9c/XXL")),
    Place("Славянский",Uri.parse("https://avatars.mds.yandex.net/get-altay/1908863/2a0000016ae8e533a1ad81f21e94c2b56470/XXL")),
    Place("Виктория",Uri.parse("https://cdn-p.cian.site/images/3/497/793/viktoriya-belgorod-397794365-6.jpg")),
    Place("СитиМолл",Uri.parse("https://www.belcitymall.ru/images/new/o-kom1.jpg")),
    Place("Пассаж",Uri.parse("https://avatars.mds.yandex.net/get-altay/1632633/2a0000016986e9a527506cb6761cb074faf0/XXL")),
    Place("МАЯК",Uri.parse("https://shopandmall.ru/foto/logo/gb_b873489a9cdd6dc0f76b5b5c05ebcee2.jpg")),
    Place("РИО",Uri.parse("https://imageproxy.ru/img/crop/414x186/https/belgorod.riomalls.ru/storage/app/uploads/public/5cb/9d9/f6c/5cb9d9f6c0546054124049.jpg")),
    Place("Мега-Гринн",Uri.parse("https://fonar.tv/uploads/img/2017/05/18/591d55d28daaf.jpg"))
)

val theatre_list = listOf(
    Place("Театр им. М.С.Щепкина",Uri.parse("https://cdn-tn.fishki.net/20/upload/post/2018/08/16/2678401/inknh26gx.jpg")),
    Place("Театр кукол",Uri.parse("https://quicktickets.ru/files/organisation/belgorodskiy-gosudarstvennyy-teatr-kukol/info/7a373ea135766626e61e8f78eda549dd.jpg")),
    Place("Новая-сцена-2", Uri.parse("https://avatars.mds.yandex.net/get-altay/2068435/2a0000016b1438055559c97e668f956d813a/XXL")),
    Place("СПИЧКА",Uri.parse("https://bel.cultreg.ru/uploads/8146c9b7504f49e03e8cc6c11c9380b9.jpg"))
)

val museum_list= listOf(
    Place("Художественный музей",Uri.parse("https://www.rewizor.ru/files/95858i210.jpg")),
    Place("Краеведческий музей",Uri.parse("https://goskatalog.ru/muzfo-imaginator/rest/images/public/750/7510910/7510910.jpg")),
    Place("Музей народной культуры",Uri.parse("https://media-cdn.tripadvisor.com/media/photo-s/10/37/ef/a6/61.jpg")),
    Place("Литературный музей",Uri.parse("https://avatars.mds.yandex.net/get-altay/247136/2a0000015b2ec0b1a87ff438d4733260d62e/XXL")),
    Place("Музей С.С.Косенкова",Uri.parse("https://mirbelogorya.ru/images/stories/news/2017/03/DSC_0003.jpg")),
    Place("Пушкинский музей",Uri.parse("https://цбс-белгород.рф/wp-content/uploads/2016/02/PBM-1.jpg")),
    Place("Музей связи",Uri.parse("https://klub31.ru/datas/logos/71_logo.jpg")),
    Place("Музей фотоискусства", Uri.parse("https://bel.cultreg.ru/uploads/958488752a6302df146c73559bb5ed82_w540_h360.jpg"))
)

val market_list = listOf(
    ItemMarket("iPhoneX",20000,Uri.parse("https://sintetiki.net/images/product/19435/562/Apple-iPhone-X-Black.png")),
    ItemMarket("iPhoneXs",25000,Uri.parse("https://www.notebookcheck-ru.com/uploads/tx_nbc2/4_zu_3_Apple_iPhone_Xs_Max.jpg")),
    ItemMarket("Samsung Galaxy S20",25000,Uri.parse("https://shop.mts.ru/upload/iblock/299/smartfon_samsung_g980_galaxy_s20_5g_8_128gb_blue.jpg/resize/470x470/")),
    ItemMarket("Samsung A50",5000,Uri.parse("https://static.beeline.ru/shop/media/goods/334x434/5973d246-3663-4dfe-b76a-22e96e91d0da.png")),
    ItemMarket("Huawei P40",2000,Uri.parse("https://avatars.mds.yandex.net/get-mpic/1859495/img_id2718348110862722613.jpeg/orig")),
    ItemMarket("Nokia 2.3",3000,Uri.parse("https://avatars.mds.yandex.net/get-mpic/2009321/img_id1893353858738320543.png/orig")),
    ItemMarket("Xiaomi Mi CC9",4000,Uri.parse("https://sintetiki.net/images/product/20266/1239/cc9-white.png")),
    ItemMarket("Asus ZenFone Max Pro",13000,Uri.parse("https://www.asus.com/media/global/products/JnIzW5AoKK3JccyH/P_setting_fff_1_90_end_600.png"))
)
