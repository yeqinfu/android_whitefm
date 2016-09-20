package com.whitefm.main.api;

import com.whitefm.main.bean.BN_BaiduMusicBody;
import com.whitefm.main.bean.BN_HomePageBody;
import com.whitefm.main.bean.BN_RobotBody;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by yeqinfu on 8/24/16.
 */
public interface API_Interface {

    /**
     * 服务器数据 测试
     */
    @GET("MusicController/getAllMusic")
    Observable<BN_HomePageBody> getAllMusic();

    /**
     * 聊天机器人
     */
    @GET("iqa/query")
    Observable<BN_RobotBody> getAnswer(@Query("appkey") String appkey,
                                       @Query("question") String question
    );

    /**
     * 百度音乐列表
     * <p/>
     * 参数：
     * type = 1-新歌榜,2-热歌榜,11-摇滚榜,12-爵士,16-流行,21-欧美金曲榜,22-经典老歌榜,23-情歌对唱榜,24-影视金曲榜,25-网络歌曲榜
     * size = 10 //返回条目数量
     * offset = 0 //获取偏移
     */
    @GET("?method=baidu.ting.billboard.billList")
    Observable<BN_BaiduMusicBody> getBaiduMusicList(@Query("type") int type,
                                                    @Query("size") int size,
                                                    @Query("offset") int offset
    );


}
