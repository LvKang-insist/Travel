package com.admin.work.main.home.startegy;

import java.util.List;

public class StartegyBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : 河北秦皇岛三日游
         * detail : [{"name":"山海关老龙头-老虎石","data":"","day":[{"name":"山海关","data":"山海关建于公元1381年，作为万里长城东部起点的第一座关隘， 是关内关外的分界线。山海关汇聚了中国古长城之精华，有\u201c天下第一关\"之称，闻名天下。山海关城，周长约4公里，与长城相连"},{"name":"老龙头","data":"万里长城唯一段海中长城， 雄伟壮观，被称为\u201c人类历史上的千古奇观\u201d。站在城楼上，南望浩瀚大海，感受波涛汹涌的壮美景观，海滩空气清新，沙软潮平，是海上观日出的绝好去处"},{"name":"老虎石","data":"老虎石在中海滩东面的沙滩上，由几块形态不一的礁石组成。散卧在海岸边，状似群虎而得名。海滩上可以做日光浴和沙浴，朋友们可以用湿润的砂砾制作一-件件沙雕作品，或者一起打打沙滩排球"}]},{"name":"鸽子窝-海誓花园-北戴河艺术村","data":"","day":[{"name":"鸽子窝","data":"鸽子窝是秦皇岛市北戴河风景名胜区四大景区之一-， 是北戴河最适合看日出的地方之一 ， 常常可见到浴日\u201d奇景:太阳升出海面时，另一轮太阳紧贴在升起的太阳下面"},{"name":"海誓花园","data":"很多人都不知道，竟然还有这么-块世外桃源!老码头的海滩又叫\u201c海誓花园\u201d，因为在这里有几栋礼堂风格的建筑物矗立在天使湾旁，给人一种莫名的向往!秦皇岛版的798艺术区\u201d，港口内有帆\n船、游轮等体验项目，精品小众且有较高的体验度\n"},{"name":"北戴河艺术村","data":"一个非常有艺术气息的村庄，还被评为\u201c中国乡村旅游创客示范基地\u201d，这里质朴的人民用双手创造\n\n了这片艺术的土地，村落中有一家\" 蝉享小院\u201d，是一-座 充满历史气息的老宅院，我们更习惯称之为\n繁华世界的一方净土，在这里可以找寻到那份久违的亲昵。\n"}]},{"name":"乐岛海洋王国","data":"","day":[{"name":"乐岛","data":"乐岛是国内独具特色的集海滨自然观光、海洋动物互动、亲海戏水游玩、餐饮娱乐休闲于一-体的海洋主题乐园。乐岛海洋王国分为海乐岛、水乐岛、风情迈阿密、海洋嘉年华四大区域，以海上摩天轮、空中漂流、海豚白鲸表演、儿童水乐园为王牌项目，海狮表演是这里必看表演之一"}]}]
         */

        private String title;
        private List<DetailBean> detail;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<DetailBean> getDetail() {
            return detail;
        }

        public void setDetail(List<DetailBean> detail) {
            this.detail = detail;
        }

        public static class DetailBean {
            /**
             * name : 山海关老龙头-老虎石
             * data :
             * day : [{"name":"山海关","data":"山海关建于公元1381年，作为万里长城东部起点的第一座关隘， 是关内关外的分界线。山海关汇聚了中国古长城之精华，有\u201c天下第一关\"之称，闻名天下。山海关城，周长约4公里，与长城相连"},{"name":"老龙头","data":"万里长城唯一段海中长城， 雄伟壮观，被称为\u201c人类历史上的千古奇观\u201d。站在城楼上，南望浩瀚大海，感受波涛汹涌的壮美景观，海滩空气清新，沙软潮平，是海上观日出的绝好去处"},{"name":"老虎石","data":"老虎石在中海滩东面的沙滩上，由几块形态不一的礁石组成。散卧在海岸边，状似群虎而得名。海滩上可以做日光浴和沙浴，朋友们可以用湿润的砂砾制作一-件件沙雕作品，或者一起打打沙滩排球"}]
             */

            private String name;
            private String data;
            private List<DayBean> day;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public List<DayBean> getDay() {
                return day;
            }

            public void setDay(List<DayBean> day) {
                this.day = day;
            }

            public static class DayBean {
                /**
                 * name : 山海关
                 * data : 山海关建于公元1381年，作为万里长城东部起点的第一座关隘， 是关内关外的分界线。山海关汇聚了中国古长城之精华，有“天下第一关"之称，闻名天下。山海关城，周长约4公里，与长城相连
                 */

                private String name;
                private String data;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getData() {
                    return data;
                }

                public void setData(String data) {
                    this.data = data;
                }
            }
        }
    }
}
