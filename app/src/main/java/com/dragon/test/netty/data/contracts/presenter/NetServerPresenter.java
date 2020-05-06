package com.dragon.test.netty.data.contracts.presenter;

import android.text.TextUtils;

import com.dragon.test.netty.data.bean.QRConsumeResultBean;
import com.dragon.test.netty.data.bean.ResultBean;
import com.dragon.test.netty.data.bean.UseMealResultBean;
import com.dragon.test.netty.data.bean.UserPicBean;
import com.dragon.test.netty.data.bean.UsersMealBean;
import com.dragon.test.netty.data.bean.UsersPicBean;
import com.dragon.test.netty.data.bean.WhiteListPackBean;
import com.dragon.test.netty.data.constracts.XMLConstant;
import com.dragon.test.netty.data.contracts.NetServerContract;
import com.dragon.test.netty.data.contracts.model.NetServerModel;
import com.dragon.test.netty.data.utils.XmlHelper;
import com.dragon.test.netty.utils.FileUtils;
import com.dragondevl.clog.CLog;

import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class NetServerPresenter extends BasePresenter<NetServerContract.View> implements NetServerContract.Presenter {

    private NetServerModel mModel;

    public NetServerPresenter() {
        this.mModel = new NetServerModel();
    }

    @Override
    public void requestPersons(final String empId, final String path, String corp, String content) {
        if (checkViewNoNull()) {
            mView.showLoading();
            mModel.requestPersons(path, corp, content)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ResponseBody>() {
                        @Override
                        public void onCompleted() {
                            /*if (checkViewNoNull()) {
                                mView.hideLoading();
                            }*/
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            if (checkViewNoNull()) {
                                mView.hideLoading();
                                onResultFail2View(empId, path, "获取失败，错误信息：" + e.getMessage());
                            }
                        }

                        @Override
                        public void onNext(ResponseBody s) {
                            if (checkViewNoNull()) {
                                mView.hideLoading();
                                if (s != null) {
                                    try {
                                        String content = s.string();
                                        onResultSuccess2View(empId, path, content);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                        onResultFail2View(empId, path, "获取失败，错误信息：" + e.getMessage());
                                    }
                                }
                            }
                        }
                    });
        }
    }

    @Override
    public void qrCodeConsume(String version, String deviceNo, final String authCode, String totalFee, String type, String icCard) {
        if (checkViewNoNull()) {
            mView.showLoading();
            mModel.qrCodeConsume(version, deviceNo, authCode, totalFee, type, icCard)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<QRConsumeResultBean>() {
                        @Override
                        public void onCompleted() {
                            if (checkViewNoNull()) {
                                mView.hideLoading();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            if (checkViewNoNull()) {
                                mView.hideLoading();
                                mView.onConsumeQRCodeFail(authCode, "获取失败，错误信息：" + e.getMessage());
                            }
                        }

                        @Override
                        public void onNext(QRConsumeResultBean s) {
                            if (checkViewNoNull()) {
                                if (s != null) {
                                    mView.onConsumeQRCodeResponse(authCode, s);
                                }
                            }
                        }
                    });
        }
    }

    @Override
    public void downloadPic(final String empId, final String nativePath, String path, String corp, String content) {
        if (checkViewNoNull()) {
            mView.showLoading();
            mModel.downloadPic(path, corp, content)
                    .subscribeOn(Schedulers.io())
                    .map(new Func1<ResponseBody, String>() {
                        @Override
                        public String call(ResponseBody responseBody) {
                            return FileUtils.writeToDisk(nativePath, responseBody);
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<String>() {
                        @Override
                        public void onCompleted() {
                            if (checkViewNoNull()) {
                                mView.hideLoading();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            if (checkViewNoNull()) {
                                mView.hideLoading();
                                mView.onDownloadFail(empId, "文件下载失败，错误信息：" + e.getMessage());
                            }
                        }

                        @Override
                        public void onNext(String s) {
                            if (checkViewNoNull()) {
                                if (TextUtils.isEmpty(s)) {
                                    mView.onDownloadFail(empId, "文件下载失败！");
                                } else {
                                    mView.onDownloadSuccess(empId, s);
                                }
                            }
                        }
                    });
        }
    }

    private void onResultSuccess2View(String empId, String type, String content) {
        if (type.equals(XMLConstant.WHITE_LIST_TAG.toLowerCase())) {//如果是白名单请求的
            try {
                ResultBean<WhiteListPackBean> resultBean = XmlHelper.parseRootUserContents(content);
                WhiteListPackBean wlp = resultBean.getContent();
                if (ResultBean.REQUEST_SUCCESS_CODE.equals(resultBean.getRepCode())) {
                    if (ResultBean.REQUEST_SUCCESS_BUT_EMPTY.equals(resultBean.getErrorMsg())) {
                        mView.onPullWhiteListFail("该企业下没有用户信息！");
                    } else {
                        mView.onPullWhiteListSuccess(wlp.hasNext(), wlp.getDataSet());
                    }
                } else {
                    mView.onPullWhiteListFail(resultBean.getErrorMsg());
                }
            } catch (DocumentException e) {
                e.printStackTrace();
                mView.onPullWhiteListFail("解析失败，错误信息：" + e.getMessage());
            }
        } else if (type.equals(XMLConstant.FILE_QUERY_TAG.toLowerCase())) {//如果是用户头像请求的
            try {
                ResultBean<UsersPicBean> rb = XmlHelper.parseRootUsersPic(content);
                if (rb.getContent() != null) {
                    UsersPicBean usersPicBean = rb.getContent();
                    if (XMLConstant.RESULT_SUCCESS.equals(usersPicBean.getRetCode())) {
                        List<UserPicBean> userPicBeans = usersPicBean.getContents();
                        if (userPicBeans != null && userPicBeans.size() > 0) {//有用户头像数据回来，那么我就可以使用下载接口去下载
                            UserPicBean uPic = userPicBeans.get(0);//只取第一条
                            CLog.e("uPic = " + uPic.toString());
                            mView.onPullUserPicStateSuccess(empId, uPic);
                        } else {
                            mView.onPullUserPicStateFail(empId, "该用户暂时还没有上传图片哦！");
                        }
                    } else {
                        mView.onPullUserPicStateFail(empId, "操作失败，错误信息：" + usersPicBean.getRetMsg());
                    }
                } else {
                    mView.onPullUserPicStateFail(empId, "获取用户图片失败，XML文档解析失败");
                }
            } catch (DocumentException e) {
                e.printStackTrace();
                mView.onPullUserPicStateFail(empId, "解析失败，错误信息：" + e.getMessage());
            }
        } else if (type.equals(XMLConstant.ORDER_QUERY_TAG.toLowerCase())) {
            ResultBean<UsersMealBean> rb = null;
            try {
                rb = XmlHelper.parseRootUsersMeal(content);
                if (rb.getContent() != null) {
                    UsersMealBean usersMealBean = rb.getContent();
                    if (usersMealBean != null && usersMealBean.getContents() != null) {
                        mView.onConsumeListResponse(empId, usersMealBean.hasNextPage(), usersMealBean.getContents());
                        return;
                    }
                }
                mView.onConsumeListFail("获取用户订餐信息失败！");
            } catch (DocumentException e) {
                e.printStackTrace();
                mView.onConsumeListFail("解析失败，错误信息：" + e.getMessage());
            }
        } else if (type.equals(XMLConstant.USE_NTC_TAG.toLowerCase())) {
            ResultBean<UseMealResultBean> rb = null;
            try {
                rb = XmlHelper.parseRootUseMeal(content);
                if (rb.getContent() != null) {
                    UseMealResultBean useMealResultBean = rb.getContent();
                    if (useMealResultBean != null) {
                        mView.onConsumeUserMealResponse(useMealResultBean);
                        return;
                    }
                }
                mView.onConsumeUserMealFail("用户订餐核销失败！");
            } catch (DocumentException e) {
                e.printStackTrace();
                mView.onConsumeUserMealFail("解析失败，错误信息：" + e.getMessage());
            }
        }
    }

    //统一的操作异常返回
    private void onResultFail2View(String empId, String type, String errorInfo) {
        if (type.equals(XMLConstant.WHITE_LIST_TAG.toLowerCase())) {//如果是白名单请求的
            mView.onPullWhiteListFail(errorInfo);
        } else if (type.equals(XMLConstant.FILE_QUERY_TAG.toLowerCase())) {//如果是用户头像请求的
            mView.onPullUserPicStateFail(empId, errorInfo);
        } else if (type.equals(XMLConstant.ORDER_QUERY_TAG.toLowerCase())) {
            mView.onConsumeListFail(errorInfo);
        } else if (type.equals(XMLConstant.USE_NTC_TAG.toLowerCase())) {
            mView.onConsumeUserMealFail(errorInfo);
        }
    }

}
