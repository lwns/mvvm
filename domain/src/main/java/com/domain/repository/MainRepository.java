package com.domain.repository;

import com.domain.bean.BangumiRecommend;
import com.domain.bean.LiveIndex;
import com.domain.bean.NewBangumiSerial;
import com.domain.bean.SeasonNewBangumi;

import rx.Observable;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/10/11
 */
public interface MainRepository {

    Observable<LiveIndex> liveIndex();

    Observable getLiveUrl(int id);

    Observable<BangumiRecommend> getBangumiRecommended();

    Observable<SeasonNewBangumi> getSeasonNewBangumiList();

    Observable<NewBangumiSerial> getBangumiSerialList();

}
