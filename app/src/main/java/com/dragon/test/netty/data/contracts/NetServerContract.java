package com.dragon.test.netty.data.contracts;


import com.dragon.test.netty.data.bean.QRConsumeResultBean;
import com.dragon.test.netty.data.bean.UseMealResultBean;
import com.dragon.test.netty.data.bean.UserMealBean;
import com.dragon.test.netty.data.bean.UserPicBean;
import com.dragon.test.netty.data.bean.WhiteListUserBean;

import java.util.List;

import okhttp3.ResponseBody;
import rx.Observable;

public interface NetServerContract {

    interface Model {
        Observable<ResponseBody> requestPersons(String path, String corp, String content);
        Observable<QRConsumeResultBean> qrCodeConsume(String version, String deviceNo, String authCode, String totalFee, String type, String icCard);
        Observable<ResponseBody> downloadPic(String path, String corp, String content);
    }

    interface Presenter {
        void requestPersons(String empId, String path, String corp, String content);
        void qrCodeConsume(String version, String deviceNo, String authCode, String totalFee, String type, String icCard);
        void downloadPic(String empId, String nativePath, String path, String corp, String content);
    }

    interface View extends BaseView {

        void onConsumeQRCodeResponse(String authCode, QRConsumeResultBean data);
        void onConsumeQRCodeFail(String authCode, String errorInfo);

        void onConsumeUserMealResponse(UseMealResultBean data);
        void onConsumeUserMealFail(String errorInfo);

        void onPullWhiteListSuccess(boolean hasNext, List<WhiteListUserBean> dataSet);
        void onPullWhiteListFail(String errorInfo);

        void onPullUserPicStateSuccess(String empId, UserPicBean picBean);
        void onPullUserPicStateFail(String empId, String errorInfo);

        void onConsumeListResponse(String type, boolean hasNext, List<UserMealBean> dataSet);
        void onConsumeListFail(String errorInfo);

        void onDownloadSuccess(String empId, String filePath);
        void onDownloadFail(String empId, String errorInfo);
    }

    public static class SimpleNetServerView implements View {

        @Override
        public void onConsumeQRCodeResponse(String authCode, QRConsumeResultBean data) {

        }

        @Override
        public void onConsumeQRCodeFail(String authCode, String errorInfo) {

        }

        @Override
        public void onConsumeUserMealResponse(UseMealResultBean data) {

        }

        @Override
        public void onConsumeUserMealFail(String errorInfo) {

        }

        @Override
        public void onPullWhiteListSuccess(boolean hasNext, List<WhiteListUserBean> dataSet) {

        }

        @Override
        public void onPullWhiteListFail(String errorInfo) {

        }

        @Override
        public void onPullUserPicStateSuccess(String empId, UserPicBean picBean) {

        }

        @Override
        public void onPullUserPicStateFail(String empId, String errorInfo) {

        }

        @Override
        public void onConsumeListResponse(String type, boolean hasNext, List<UserMealBean> dataSet) {

        }

        @Override
        public void onConsumeListFail(String errorInfo) {

        }

        @Override
        public void onDownloadSuccess(String empId, String filePath) {

        }

        @Override
        public void onDownloadFail(String empId, String errorInfo) {

        }

        @Override
        public void showLoading() {

        }

        @Override
        public void hideLoading() {

        }

        @Override
        public void showTips(boolean success, String tips) {

        }

        @Override
        public void hideTips(String tips) {

        }

        @Override
        public void onEmpty() {

        }
    }

}
