package com.admin.work.main.home;

import java.util.List;

public class HomeBean {
    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2020-04-15 00:00","title":"河北：春日美景\u201c搬\u201d网上 居家线上\u201c云旅游\u201d","description":"旅游资讯","picUrl":"http://travel.news.cn/titlepic/112585/1125857205_1586912505801_title0h.jpg","url":"http://travel.news.cn/2020-04/15/c_1125857205.htm"},{"ctime":"2020-03-25 00:00","title":"河北正定：部分景区景点有序对外开放","description":"旅游资讯","picUrl":"http://travel.news.cn/titlepic/112576/1125764154_1585099944829_title0h.jpg","url":"http://travel.news.cn/2020-03/25/c_1125764154.htm"},{"ctime":"2020-02-11 00:00","title":"河北：闭馆之后游客可在家\u201c云逛\u201d数字博物馆","description":"旅游资讯","picUrl":"http://travel.news.cn/titlepic/112555/1125557994_1581389689833_title0h.jpg","url":"http://travel.news.cn/2020-02/11/c_1125557994.htm"},{"ctime":"2019-11-15 00:00","title":"河北邯郸：\u201c工矿废弃地\u201d的冬日\u201c美颜\u201d","description":"旅游资讯","picUrl":"http://travel.news.cn/titlepic/112523/1125234254_1_title0h.jpg","url":"http://travel.news.cn/2019-11/15/c_1125234256.htm"},{"ctime":"2019-11-11 00:00","title":"河北迁安：乡村垃圾坑变身水上公园","description":"旅游资讯","picUrl":"http://travel.news.cn/titlepic/112521/1125215467_1_title0h.jpg","url":"http://travel.news.cn/2019-11/11/c_1125215485.htm"},{"ctime":"2019-10-14 00:00","title":"河北永清：\u201c农业 文化 旅游\u201d推动乡村振兴","description":"旅游资讯","picUrl":"http://travel.news.cn/titlepic/112510/1125100869_1571014408780_title0h.jpg","url":"http://travel.news.cn/2019-10/14/c_1125100869.htm"},{"ctime":"2019-10-08 00:00","title":"河北迁安：玻璃栈道上体验多彩假期","description":"旅游资讯","picUrl":"http://travel.news.cn/titlepic/112507/1125077073_1_title0h.jpg","url":"http://travel.news.cn/2019-10/08/c_1125077080.htm"}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * ctime : 2020-04-15 00:00
         * title : 河北：春日美景“搬”网上 居家线上“云旅游”
         * description : 旅游资讯
         * picUrl : http://travel.news.cn/titlepic/112585/1125857205_1586912505801_title0h.jpg
         * url : http://travel.news.cn/2020-04/15/c_1125857205.htm
         */

        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
