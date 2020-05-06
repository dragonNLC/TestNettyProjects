package com.dragon.test.netty.data.api;


import com.dragon.test.netty.data.bean.QRConsumeResultBean;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import rx.Observable;

public interface APIServer {

    /**
     *
     * @param tranCode 交易代码
     * @param flowActionName 类型参数
     * @param corp 企业id
     * @param reqData 加密内容
     * @return
     */
    //先拿用户信息数据下载
    @POST("{trancode}.flowc")
    @FormUrlEncoded
    Observable<ResponseBody> requestPersons(@Path("trancode") String tranCode, @Query("flowActionName") String flowActionName, @Field("corp") String corp, @Field("reqdata") String reqData);


    /**
     *
     * @param version 接口版本号
     * @param deviceNo 设备id
     * @param authCode 二维码内容
     * @param totalFee 消费金额
     * @param type 交易类型
     * @param icCard IC卡卡号
     * @return
     */
    //二维码核销接口
    @GET("qrcode.flowc?flowActionName=decrypt")
    Observable<QRConsumeResultBean> qrCodeConsume(@Query("version") String version, @Query(value = "device_no", encoded = true) String deviceNo, @Query(value = "auth_code", encoded = true) String authCode, @Query("total_fee") String totalFee, @Query("type") String type, @Query("ic_card") String icCard);

    /**
     * 请求下载文件
     * @return
     *
     * */
    @Streaming
    @FormUrlEncoded
    @POST("{trancode}.flowc")
    Observable<ResponseBody> downloadFile(@Path("trancode") String tranCode, @Query("flowActionName") String flowActionName, @Field("corp") String corp, @Field("reqdata") String reqData);

}
