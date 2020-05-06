package com.dragon.test.netty.data.contracts.model;


import com.dragon.test.netty.data.api.APIServer;
import com.dragon.test.netty.data.api.NetServerManager;
import com.dragon.test.netty.data.bean.QRConsumeResultBean;
import com.dragon.test.netty.data.constracts.XMLConstant;
import com.dragon.test.netty.data.contracts.NetServerContract;

import okhttp3.ResponseBody;
import rx.Observable;

public class NetServerModel implements NetServerContract.Model {

    @Override
    public Observable<ResponseBody> requestPersons(String path, String corp, String content) {
        return NetServerManager.getInstance().getRFInstance().create(APIServer.class).requestPersons(path, XMLConstant.QUERY_TAG, corp, content);
    }

    @Override
    public Observable<QRConsumeResultBean> qrCodeConsume(String version, String deviceNo, String authCode, String totalFee, String type, String icCard) {
        return NetServerManager.getInstance().getRFInstance().create(APIServer.class).qrCodeConsume(version, deviceNo, authCode, totalFee, type, icCard);
    }

    @Override
    public Observable<ResponseBody> downloadPic(String path, String corp, String content) {
        return NetServerManager.getInstance().getRFInstance().create(APIServer.class).downloadFile(path, XMLConstant.QUERY_TAG, corp, content);
    }

}
