package com.dragon.test.netty.data.utils;

import android.text.TextUtils;

import com.dragon.test.netty.data.bean.ResultBean;
import com.dragon.test.netty.data.bean.UseMealResultBean;
import com.dragon.test.netty.data.bean.UserCtrlResultBean;
import com.dragon.test.netty.data.bean.UserMealBean;
import com.dragon.test.netty.data.bean.UserPicBean;
import com.dragon.test.netty.data.bean.UsersMealBean;
import com.dragon.test.netty.data.bean.UsersPicBean;
import com.dragon.test.netty.data.bean.WhiteListPackBean;
import com.dragon.test.netty.data.bean.WhiteListUserBean;
import com.dragon.test.netty.data.constracts.Constant;
import com.dragon.test.netty.data.constracts.XMLConstant;
import com.dragon.test.netty.data.eos.CipherUtil;
import com.dragon.test.netty.utils.TimeUtils;
import com.dragondevl.clog.CLog;
import com.dragondevl.utils.NumberUtil;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlHelper {

    //解析所有白名单用户的信息
    public static ResultBean<WhiteListPackBean> parseRootUserContents(String content) throws DocumentException {
        ResultBean<WhiteListPackBean> rb = unpackingContent(content);
        if (!TextUtils.isEmpty(rb.getDataPack())) {
            CLog.e("rb = " + rb.getDataPack());
            WhiteListPackBean userBeans = parseUsers(rb.getDataPack());
            rb.setContent(userBeans);
        }
        return rb;
    }

    //解析白名单操作的返回值
    public static ResultBean<UserCtrlResultBean> parseRootCtrlResult(String content) throws DocumentException {
        ResultBean<UserCtrlResultBean> rb = unpackingContent(content);
        if (!TextUtils.isEmpty(rb.getDataPack())) {
            CLog.e("rb = " + rb.getDataPack());
            UserCtrlResultBean userCtrlResultBean = parseCtrlResult(rb.getDataPack());
            rb.setContent(userCtrlResultBean);
        }
        return rb;
    }

    //解析所有白名单用户的图片
    public static ResultBean<UsersPicBean> parseRootUsersPic(String content) throws DocumentException {
        ResultBean<UsersPicBean> rb = unpackingContent(content);
        if (!TextUtils.isEmpty(rb.getDataPack())) {
            CLog.e("rb = " + rb.getDataPack());
            UsersPicBean usersPicBean = parseUsersPic(rb.getDataPack());
            rb.setContent(usersPicBean);
        }
        return rb;
    }

    //解析所有白名单用户的订餐信息
    public static ResultBean<UsersMealBean> parseRootUsersMeal(String content) throws DocumentException {
        ResultBean<UsersMealBean> rb = unpackingContent(content);
        if (!TextUtils.isEmpty(rb.getDataPack())) {
            CLog.e("rb = " + rb.getDataPack());
            UsersMealBean usersPicBean = parseUsersMeal(rb.getDataPack());
            rb.setContent(usersPicBean);
        }
        return rb;
    }

    //解析消费订餐的返回信息
    public static ResultBean<UseMealResultBean> parseRootUseMeal(String content) throws DocumentException {
        ResultBean<UseMealResultBean> rb = unpackingContent(content);
        if (!TextUtils.isEmpty(rb.getDataPack())) {
            CLog.e("rb = " + rb.getDataPack());
            UseMealResultBean usersPicBean = parseUseMealResult(rb.getDataPack());
            rb.setContent(usersPicBean);
        }
        return rb;
    }

    //解包，获取实际返回内容
    public static <T> ResultBean<T> unpackingContent(String dataPack) throws DocumentException {
        ResultBean<T> rb = new ResultBean<T>();
        Document document = DocumentHelper.parseText(dataPack);
        Element root = document.getRootElement();
        Element bodyElement = root.element("body");
        if (bodyElement != null) {
            Element retCode = bodyElement.element("RetCode");
            Element retMsg = bodyElement.element("RetMsg");
            if (retCode != null && retMsg != null) {//如果外部就有返回码的，应该就是表示获取失败，那么直接返回错误信息获取
                String reqFailCode = retCode.getStringValue();
                String reqFailMsg = retMsg.getStringValue();
                rb.setRepCode(reqFailCode);
                rb.setErrorMsg(reqFailMsg);
            } else {
                rb.setRepCode(ResultBean.REQUEST_SUCCESS_CODE);
                Element repData = bodyElement.element("repdata");
                if (repData != null) {
                    String repContent = repData.getStringValue();
                    repContent = CipherUtil.decryptData(repContent);//解析出实际的xml内容
                    //CLog.e("reqContent = " + repContent);
                    rb.setDataPack(repContent);
                } else {
                    rb.setErrorMsg(ResultBean.REQUEST_SUCCESS_BUT_EMPTY);
                }
            }
        }
        return rb;
    }

    private static WhiteListPackBean parseUsers(String dataPack) throws DocumentException {
        WhiteListPackBean wlp = new WhiteListPackBean();
        List<WhiteListUserBean> userBeans = new ArrayList<>();
        String hasNext = "0";
        Document document = DocumentHelper.parseText(dataPack);
        Element root = document.getRootElement();
        if (root != null) {
            Element bodyElement = root.element("body");
            if (bodyElement != null) {
                Element hasNextElement = bodyElement.element("HasNext");
                if (hasNextElement != null) {
                    hasNext = hasNextElement.getStringValue();
                }
                Element details = bodyElement.element("Details");
                if (details != null) {
                    List<Element> detailList = details.elements("Detail");
                    if (detailList != null && detailList.size() > 0) {
                        for (Element detail :
                                detailList) {
                            userBeans.add(parseUser(detail));
                        }
                    }
                }
            }
        }
        wlp.setDataSet(userBeans);
        wlp.setHasNext(hasNext);
        return wlp;
    }

    private static WhiteListUserBean parseUser(Element detail) {
        WhiteListUserBean u = new WhiteListUserBean();
        Element empIdElement = detail.element("EmpID");
        if (empIdElement != null) {
            String empId = empIdElement.getStringValue();
            u.setEmpID(empId);
        }
        Element empNameElement = detail.element("EmpName");
        if (empNameElement != null) {
            String empName = empNameElement.getStringValue();
            u.setEmpName(empName);
        }
        Element certTypeElement = detail.element("CertType");
        if (certTypeElement != null) {
            String certType = certTypeElement.getStringValue();
            u.setCertType(certType);
        }
        Element certCodeElement = detail.element("CertCode");
        if (certCodeElement != null) {
            String certCode = certCodeElement.getStringValue();
            u.setCertCode(certCode);
        }
        Element cardNoElement = detail.element("CardNo");
        if (cardNoElement != null) {
            String cardNo = cardNoElement.getStringValue();
            u.setCardNo(cardNo);
        }
        Element empTypeElement = detail.element("EmpType");
        if (empTypeElement != null) {
            String empType = empTypeElement.getStringValue();
            u.setEmpType(empType);
        }
        Element depID1Element = detail.element("DepID1");
        if (depID1Element != null) {
            String depID1 = depID1Element.getStringValue();
            u.setDepId1(depID1);
        }
        Element depID2Element = detail.element("DepID2");
        if (depID2Element != null) {
            String depID2 = depID2Element.getStringValue();
            u.setDepId2(depID2);
        }
        Element defCantIDElement = detail.element("DefCantID");
        if (defCantIDElement != null) {
            String defCantID = defCantIDElement.getStringValue();
            u.setDefCantId(defCantID);
        }
        Element iCCardNoElement = detail.element("ICCardNo");
        if (iCCardNoElement != null) {
            String iCCardNo = iCCardNoElement.getStringValue();
            u.setIcCardNo(iCCardNo);
        }
        Element dutyElement = detail.element("Duty");
        if (dutyElement != null) {
            String duty = dutyElement.getStringValue();
            u.setDuty(duty);
        }
        Element sexElement = detail.element("Sex");
        if (sexElement != null) {
            String sex = sexElement.getStringValue();
            u.setSex(sex);
        }
        Element teleNumElement = detail.element("TeleNum");
        if (teleNumElement != null) {
            String teleNum = teleNumElement.getStringValue();
            u.setTeleNum(teleNum);
        }
        Element mobiNumElement = detail.element("MobiNum");
        if (mobiNumElement != null) {
            String mobiNum = mobiNumElement.getStringValue();
            u.setMobiNum(mobiNum);
        }
        Element passNumElement = detail.element("PassNum");
        if (passNumElement != null) {
            String passNum = passNumElement.getStringValue();
            u.setPassNum(passNum);
        }
        Element entryDateElement = detail.element("EntryDate");
        if (entryDateElement != null) {
            String entryDate = entryDateElement.getStringValue();
            u.setEntryDate(entryDate);
        }
        Element leaveDateElement = detail.element("LeaveDate");
        if (leaveDateElement != null) {
            String leaveDate = leaveDateElement.getStringValue();
            u.setLeaveDate(leaveDate);
        }
        Element createTimeElement = detail.element("CreateTime");
        if (createTimeElement != null) {
            String createTime = createTimeElement.getStringValue();
            u.setCreateTime(createTime);
        }
        Element updateTimeElement = detail.element("UpdateTime");
        if (updateTimeElement != null) {
            String updateTime = updateTimeElement.getStringValue();
            u.setUpdateTime(updateTime);
        }
        Element backup1Element = detail.element("Backup1");
        if (backup1Element != null) {
            String backup1 = backup1Element.getStringValue();
            u.setBackUp1(backup1);
        }
        Element backup2Element = detail.element("Backup2");
        if (backup2Element != null) {
            String backup2 = backup2Element.getStringValue();
            u.setBackUp2(backup2);
        }
        Element backup3Element = detail.element("Backup3");
        if (backup3Element != null) {
            String backup3 = backup3Element.getStringValue();
            u.setBackUp3(backup3);
        }
        return u;
    }

    public static UserCtrlResultBean parseCtrlResult(String dataPack) throws DocumentException {
        UserCtrlResultBean uCtrl = new UserCtrlResultBean();
        Document document = DocumentHelper.parseText(dataPack);
        Element root = document.getRootElement();
        if (root != null) {
            Element bodyElement = root.element("body");
            if (bodyElement != null) {
                Element retCodeElement = bodyElement.element("RetCode");
                if (retCodeElement != null) {
                    String retCode = retCodeElement.getStringValue();
                    uCtrl.setRetCode(retCode);
                }
                Element retMsgElement = bodyElement.element("RetMsg");
                if (retMsgElement != null) {
                    String retMsg = retMsgElement.getStringValue();
                    uCtrl.setRetMsg(retMsg);
                }
                Element certTypeElement = bodyElement.element("CertType");
                if (certTypeElement != null) {
                    String certType = certTypeElement.getStringValue();
                    uCtrl.setCertType(certType);
                }
                Element certCodeElement = bodyElement.element("CertCode");
                if (certCodeElement != null) {
                    String certCode = certCodeElement.getStringValue();
                    uCtrl.setCertCode(certCode);
                }
                Element cardNoElement = bodyElement.element("CardNo");
                if (cardNoElement != null) {
                    String cardNo = cardNoElement.getStringValue();
                    uCtrl.setCardNo(cardNo);
                }
                Element empTypeElement = bodyElement.element("EmpType");
                if (empTypeElement != null) {
                    String empType = empTypeElement.getStringValue();
                    uCtrl.setEmpType(empType);
                }
                Element depID1Element = bodyElement.element("DepID1");
                if (depID1Element != null) {
                    String depId1 = depID1Element.getStringValue();
                    uCtrl.setDepId1(depId1);
                }
                Element depID2Element = bodyElement.element("DepID2");
                if (depID2Element != null) {
                    String depID2 = depID2Element.getStringValue();
                    uCtrl.setDepId2(depID2);
                }
                Element defCantIDElement = bodyElement.element("DefCantID");
                if (defCantIDElement != null) {
                    String defCantID = defCantIDElement.getStringValue();
                    uCtrl.setDefCantId(defCantID);
                }
                Element icCardNoElement = bodyElement.element("ICCardNo");
                if (icCardNoElement != null) {
                    String icCardNo = icCardNoElement.getStringValue();
                    uCtrl.setIcCardNo(icCardNo);
                }
                Element dutyElement = bodyElement.element("Duty");
                if (dutyElement != null) {
                    String duty = dutyElement.getStringValue();
                    uCtrl.setDuty(duty);
                }
                Element sexElement = bodyElement.element("Sex");
                if (sexElement != null) {
                    String sex = sexElement.getStringValue();
                    uCtrl.setSex(sex);
                }
                Element teleNumElement = bodyElement.element("TeleNum");
                if (teleNumElement != null) {
                    String teleNum = teleNumElement.getStringValue();
                    uCtrl.setTeleNum(teleNum);
                }
                Element mobiNumElement = bodyElement.element("MobiNum");
                if (mobiNumElement != null) {
                    String mobiNum = mobiNumElement.getStringValue();
                    uCtrl.setMobiNum(mobiNum);
                }
                Element passNumElement = bodyElement.element("PassNum");
                if (passNumElement != null) {
                    String passNum = passNumElement.getStringValue();
                    uCtrl.setPassNum(passNum);
                }
                Element entryDateElement = bodyElement.element("EntryDate");
                if (entryDateElement != null) {
                    String entryDate = entryDateElement.getStringValue();
                    uCtrl.setEntryDate(entryDate);
                }
                Element leaveDateElement = bodyElement.element("LeaveDate");
                if (leaveDateElement != null) {
                    String leaveDate = leaveDateElement.getStringValue();
                    uCtrl.setLeaveDate(leaveDate);
                }
                Element createTimeElement = bodyElement.element("CreateTime");
                if (createTimeElement != null) {
                    String createTime = createTimeElement.getStringValue();
                    uCtrl.setCreateTime(createTime);
                }
                Element updateTimeElement = bodyElement.element("UpdateTime");
                if (updateTimeElement != null) {
                    String updateTime = updateTimeElement.getStringValue();
                    uCtrl.setUpdateTime(updateTime);
                }
                Element backup1Element = bodyElement.element("Backup1");
                if (backup1Element != null) {
                    String backup1 = backup1Element.getStringValue();
                    uCtrl.setBackUp1(backup1);
                }
                Element backup2Element = bodyElement.element("Backup2");
                if (backup2Element != null) {
                    String backup2 = backup2Element.getStringValue();
                    uCtrl.setBackUp2(backup2);
                }
                Element backup3Element = bodyElement.element("Backup3");
                if (backup3Element != null) {
                    String backup3 = backup3Element.getStringValue();
                    uCtrl.setBackUp3(backup3);
                }
            }
        }
        return uCtrl;
    }

    public static UsersPicBean parseUsersPic(String dataPack) throws DocumentException {
        UsersPicBean usersPic = new UsersPicBean();
        List<UserPicBean> userPicBeans = new ArrayList<>();
        usersPic.setContents(userPicBeans);

        Document document = DocumentHelper.parseText(dataPack);
        Element root = document.getRootElement();
        if (root != null) {
            Element bodyElement = root.element("body");
            if (bodyElement != null) {
                Element retCodeElement = bodyElement.element("RetCode");
                if (retCodeElement != null) {
                    String retCode = retCodeElement.getStringValue();
                    usersPic.setRetCode(retCode);
                }
                Element retMsgElement = bodyElement.element("RetMsg");
                if (retMsgElement != null) {
                    String retMsg = retMsgElement.getStringValue();
                    usersPic.setRetMsg(retMsg);
                }
                Element hasNextElement = bodyElement.element("HasNext");
                if (hasNextElement != null) {
                    String hasNext = hasNextElement.getStringValue();
                    usersPic.setHasNext(hasNext);
                }
                Element details = bodyElement.element("Details");
                if (details != null) {
                    List<Element> detailList = details.elements("Detail");
                    if (detailList != null && detailList.size() > 0) {
                        for (Element detail :
                                detailList) {
                            userPicBeans.add(parseUserPic(detail));
                        }
                    }
                }
            }
        }
        return usersPic;
    }

    private static UserPicBean parseUserPic(Element detail) {
        UserPicBean uPic = new UserPicBean();
        Element empIdElement = detail.element("EmpID");
        if (empIdElement != null) {
            String empId = empIdElement.getStringValue();
            uPic.setEmpId(empId);
        }
        Element empNameElement = detail.element("EmpName");
        if (empNameElement != null) {
            String empName = empNameElement.getStringValue();
            uPic.setEmpName(empName);
        }
        Element fileNameElement = detail.element("FileName");
        if (fileNameElement != null) {
            String fileName = fileNameElement.getStringValue();
            uPic.setFileName(fileName);

            String nativePath = Constant.TEMP_PATH + File.separator + fileName;//检查是否已经下载了
            File nativeFile = new File(nativePath);
            if (nativeFile.exists()) {
                uPic.setFilePath(nativePath);
            }
        }
        Element createTimeElement = detail.element("CreateTime");
        if (createTimeElement != null) {
            String createTime = createTimeElement.getStringValue();
            uPic.setCreateTime(createTime);
        }
        return uPic;
    }

    public static UsersMealBean parseUsersMeal(String dataPack) throws DocumentException {
        UsersMealBean usersMealBean = new UsersMealBean();
        List<UserMealBean> userMealBeans = new ArrayList<>();
        usersMealBean.setContents(userMealBeans);

        Document document = DocumentHelper.parseText(dataPack);
        Element root = document.getRootElement();
        if (root != null) {
            Element bodyElement = root.element("body");
            if (bodyElement != null) {
                Element retCodeElement = bodyElement.element("RetCode");
                if (retCodeElement != null) {
                    String retCode = retCodeElement.getStringValue();
                    usersMealBean.setRetCode(retCode);
                }
                Element retMsgElement = bodyElement.element("RetMsg");
                if (retMsgElement != null) {
                    String retMsg = retMsgElement.getStringValue();
                    usersMealBean.setRetMsg(retMsg);
                }
                Element hasNextElement = bodyElement.element("HasNext");
                if (hasNextElement != null) {
                    String hasNext = hasNextElement.getStringValue();
                    usersMealBean.setHasNext(hasNext);
                }
                Element details = bodyElement.element("Details");
                if (details != null) {
                    List<Element> detailList = details.elements("Detail");
                    if (detailList != null && detailList.size() > 0) {
                        for (Element detail :
                                detailList) {
                            userMealBeans.add(parseUsersMeal(detail));
                        }
                    }
                }
            }
        }
        return usersMealBean;
    }

    private static UserMealBean parseUsersMeal(Element detail) {
        UserMealBean uMeal = new UserMealBean();
        Element empIdElement = detail.element("EmpID");
        if (empIdElement != null) {
            String empId = empIdElement.getStringValue();
            uMeal.setEmpId(empId);
        }
        Element empNameElement = detail.element("EmpName");
        if (empNameElement != null) {
            String empName = empNameElement.getStringValue();
            uMeal.setEmpName(empName);
        }

        Element empTypeElement = detail.element("EmpType");
        if (empTypeElement != null) {
            String empType = empTypeElement.getStringValue();
            uMeal.setEmpType(empType);
        }

        Element depID1Element = detail.element("DepID1");
        if (depID1Element != null) {
            String depID1 = depID1Element.getStringValue();
            uMeal.setDepId1(depID1);
        }

        Element depID2Element = detail.element("DepID2");
        if (depID2Element != null) {
            String depID2 = depID2Element.getStringValue();
            uMeal.setDepId2(depID2);
        }

        Element defCantIDElement = detail.element("DefCantID");
        if (defCantIDElement != null) {
            String defCantID = defCantIDElement.getStringValue();
            uMeal.setDefCantId(defCantID);
        }

        Element tranIDElement = detail.element("TranID");
        if (tranIDElement != null) {
            String tranID = tranIDElement.getStringValue();
            uMeal.setTranId(tranID);
        }

        Element mealDateElement = detail.element("MealDate");
        if (mealDateElement != null) {
            String mealDate = mealDateElement.getStringValue();
            uMeal.setMealDate(mealDate);
        }

        Element mealIDElement = detail.element("MealID");
        if (mealIDElement != null) {
            String mealID = mealIDElement.getStringValue();
            uMeal.setMealId(mealID);
        }

        Element tranTimeElement = detail.element("TranTime");
        if (tranTimeElement != null) {
            String tranTime = tranTimeElement.getStringValue();
            uMeal.setTranTime(tranTime);
        }

        Element countElement = detail.element("Count");
        if (countElement != null) {
            String count = countElement.getStringValue();
            uMeal.setCount(count);
        }

        Element amountElement = detail.element("Amount");
        if (amountElement != null) {
            String amount = amountElement.getStringValue();
            uMeal.setAmount(amount);
        }

        Element statusElement = detail.element("Status");
        if (statusElement != null) {
            String status = statusElement.getStringValue();
            uMeal.setStatus(status);
        }

        Element channelElement = detail.element("Channel");
        if (channelElement != null) {
            String channel = channelElement.getStringValue();
            uMeal.setChannel(channel);
        }

        Element useTimeElement = detail.element("UseTime");
        if (useTimeElement != null) {
            String useTime = useTimeElement.getStringValue();
            uMeal.setUserTime(useTime);
        }

        Element useSeqElement = detail.element("UseSeq");
        if (useSeqElement != null) {
            String useSeq = useSeqElement.getStringValue();
            uMeal.setUseSeq(useSeq);
        }

        Element cancelTimeElement = detail.element("CancelTime");
        if (cancelTimeElement != null) {
            String cancelTime = cancelTimeElement.getStringValue();
            uMeal.setCancelTime(cancelTime);
        }

        Element payEmpIDElement = detail.element("PayEmpID");
        if (payEmpIDElement != null) {
            String payEmpID = payEmpIDElement.getStringValue();
            uMeal.setPayEmpId(payEmpID);
        }

        Element payEmpNameElement = detail.element("PayEmpName");
        if (payEmpNameElement != null) {
            String payEmpName = payEmpNameElement.getStringValue();
            uMeal.setPayEmpName(payEmpName);
        }

        Element backup1Element = detail.element("Backup1");
        if (backup1Element != null) {
            String backup1 = backup1Element.getStringValue();
            uMeal.setBackUp1(backup1);
        }


        Element backup2Element = detail.element("Backup2");
        if (backup2Element != null) {
            String backup2 = backup2Element.getStringValue();
            uMeal.setBackUp2(backup2);
        }


        Element backup3Element = detail.element("Backup3");
        if (backup3Element != null) {
            String backup3 = backup3Element.getStringValue();
            uMeal.setBackUp3(backup3);
        }

        return uMeal;
    }

    public static UseMealResultBean parseUseMealResult(String dataPack) throws DocumentException {
        UseMealResultBean useMealResultBean = new UseMealResultBean();

        Document document = DocumentHelper.parseText(dataPack);
        Element root = document.getRootElement();
        if (root != null) {
            Element bodyElement = root.element("body");
            if (bodyElement != null) {
                Element retCodeElement = bodyElement.element("RetCode");
                if (retCodeElement != null) {
                    String retCode = retCodeElement.getStringValue();
                    useMealResultBean.setRetCode(retCode);
                }
                Element retMsgElement = bodyElement.element("RetMsg");
                if (retMsgElement != null) {
                    String retMsg = retMsgElement.getStringValue();
                    useMealResultBean.setRetMsg(retMsg);
                }
                Element hasNextElement = bodyElement.element("HasNext");
                if (hasNextElement != null) {
                    String hasNext = hasNextElement.getStringValue();
                    useMealResultBean.setErrorTranId(hasNext);
                }
            }
        }
        return useMealResultBean;
    }

    public static String assembleWhiteList(String pageNum, String pageSize) {
        StringBuilder sb = new StringBuilder();
        sb.append(XmlHelper.xmlHead(XMLConstant.WHITE_LIST_TAG, XMLConstant.CANT_ALL));
        sb.append(XMLConstant.XML_TAG_ACT_TYPE[0]);
        sb.append(XMLConstant.ACT_TYPE_5);
        sb.append(XMLConstant.XML_TAG_ACT_TYPE[1]);
        sb.append(XMLConstant.XML_TAG_PAGE_NO[0]);
        sb.append(pageNum);
        sb.append(XMLConstant.XML_TAG_PAGE_NO[1]);
        sb.append(XMLConstant.XML_TAG_PAGE_SIZE[0]);
        sb.append(pageSize);
        sb.append(XMLConstant.XML_TAG_PAGE_SIZE[1]);
        sb.append(XMLConstant.XML_TAG_BACKUP1[0]);
        sb.append(XMLConstant.XML_TAG_BACKUP1[1]);
        sb.append(XMLConstant.XML_TAG_BACKUP2[0]);
        sb.append(XMLConstant.XML_TAG_BACKUP2[1]);
        sb.append(XMLConstant.XML_TAG_BACKUP3[0]);
        sb.append(XMLConstant.XML_TAG_BACKUP3[1]);
        sb.append(XmlHelper.xmlFoot());

        return sb.toString();
    }

    //虽然设置了页码跟文件数，但是我们只拿第一条
    public static String assembleUserImage(String empId, String empName, String pageNum, String pageSize) {
        StringBuilder sb = new StringBuilder();
        sb.append(XmlHelper.xmlHead(XMLConstant.FILE_QUERY_TAG, XMLConstant.CANT_ALL));
        sb.append(XMLConstant.XML_TAG_EMP_ID[0]);
        sb.append(empId);
        sb.append(XMLConstant.XML_TAG_EMP_ID[1]);
        sb.append(XMLConstant.XML_TAG_EMP_NAME[0]);
        sb.append(empName);
        sb.append(XMLConstant.XML_TAG_EMP_NAME[1]);
        sb.append(XMLConstant.XML_TAG_FILE_TYPE[0]);
        sb.append(XMLConstant.FILE_DOWNLOAD_ACT_TYPE_QUERY);
        sb.append(XMLConstant.XML_TAG_FILE_TYPE[1]);
        sb.append(XMLConstant.XML_TAG_PAGE_NO[0]);
        sb.append(pageNum);
        sb.append(XMLConstant.XML_TAG_PAGE_NO[1]);
        sb.append(XMLConstant.XML_TAG_PAGE_SIZE[0]);
        sb.append(pageSize);
        sb.append(XMLConstant.XML_TAG_PAGE_SIZE[1]);
        sb.append(XmlHelper.xmlFoot());

        return sb.toString();
    }

    //打包获取所有用户订餐信息的数据
    public static String assembleUserMeals(String pageIdx) {
        StringBuilder sb = new StringBuilder();
        sb.append(XmlHelper.xmlHead(XMLConstant.ORDER_QUERY_TAG, XMLConstant.CANT_ID));
        sb.append("<MealDate>");
        sb.append(TimeUtils.formatDay());
        sb.append("</MealDate>");
        sb.append("<MealID>");
        sb.append("0");
        sb.append("</MealID>");
        sb.append("<MealSub>");
        sb.append("0");
        sb.append("</MealSub>");
        sb.append("<Status>");
        sb.append("1");//只查询已订餐未使用
        sb.append("</Status>");;
        sb.append("<PageNo>");
        sb.append(pageIdx);
        sb.append("</PageNo>");
        sb.append("<PageSize>");
        sb.append("10");
        sb.append("</PageSize>");
        sb.append("<Backup1>");
        sb.append("</Backup1>");
        sb.append("<Backup2>");
        sb.append("</Backup2>");
        sb.append("<Backup3>");
        sb.append("</Backup3>");
        sb.append(XmlHelper.xmlFoot());

        return sb.toString();
    }

    //打包获取单个用户订餐信息的数据
    public static String assembleUserMeal(String empId, String empName, String pageIdx) {
        StringBuilder sb = new StringBuilder();
        sb.append(XmlHelper.xmlHead(XMLConstant.ORDER_QUERY_TAG, XMLConstant.CANT_ID));
        sb.append("<MealDate>");
        sb.append(TimeUtils.formatDay());
        sb.append("</MealDate>");
        sb.append("<MealID>");
        sb.append("0");
        sb.append("</MealID>");
        sb.append("<MealSub>");
        sb.append("0");
        sb.append("</MealSub>");
        sb.append("<EmpID>");
        sb.append(empId);
        sb.append("</EmpID>");;
        sb.append("<EmpName>");
        sb.append(empName);
        sb.append("</EmpName>");;
        sb.append("<Status>");
        //sb.append("1");//只查询已订餐未使用
        sb.append("0");//改为查询所有，获取所有之后再剔除
        sb.append("</Status>");;
        sb.append("<PageNo>");
        sb.append(pageIdx);
        sb.append("</PageNo>");
        sb.append("<PageSize>");
        sb.append("10");
        sb.append("</PageSize>");
        sb.append("<Backup1>");
        sb.append("</Backup1>");
        sb.append("<Backup2>");
        sb.append("</Backup2>");
        sb.append("<Backup3>");
        sb.append("</Backup3>");
        sb.append(XmlHelper.xmlFoot());

        return sb.toString();
    }

    public static String assembleDownload(String empId, String empName) {
        StringBuilder sb = new StringBuilder();
        sb.append(XmlHelper.xmlHead(XMLConstant.FILE_DOWNLOAD_TAG, XMLConstant.CANT_ALL));
        sb.append(XMLConstant.XML_TAG_ACT_TYPE[0]);
        sb.append(XMLConstant.FILE_DOWNLOAD_ACT_TYPE_DOWNLOAD);//下载
        sb.append(XMLConstant.XML_TAG_ACT_TYPE[1]);
        sb.append(XMLConstant.XML_TAG_EMP_ID[0]);
        sb.append(empId);
        sb.append(XMLConstant.XML_TAG_EMP_ID[1]);
        sb.append(XMLConstant.XML_TAG_EMP_NAME[0]);
        sb.append(empName);
        sb.append(XMLConstant.XML_TAG_EMP_NAME[1]);
        sb.append(XMLConstant.XML_TAG_FILE_TYPE[0]);
        sb.append(XMLConstant.FILE_TYPE_USER_PIC);
        sb.append(XMLConstant.XML_TAG_FILE_TYPE[1]);
        sb.append(XmlHelper.xmlFoot());

        return sb.toString();
    }

    public static String assembleConsumeUserMeal(int takeCount, UserMealBean userMealBean) {
        int status = 2;//默认全部取完
        if (NumberUtil.isNumeric(userMealBean.getCount())) {
            int mealCount = Integer.parseInt(userMealBean.getCount());
            if (takeCount < mealCount) {//如果取餐份数小于总份数的，代表着是分批取餐
                status = 5;
            } else {//如果是大于等于的情况，则表示是全部取餐
                status = 2;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(XmlHelper.xmlHead(XMLConstant.USE_NTC_TAG, XMLConstant.CANT_ID));
        sb.append("<Details>");
        sb.append("<Detail>");
        sb.append("<EmpID>");
        sb.append(userMealBean.getEmpId());
        sb.append("</EmpID>");
        sb.append("<EmpName>");
        sb.append(userMealBean.getEmpName());
        sb.append("</EmpName>");
        sb.append("<TranID>");
        sb.append(userMealBean.getTranId());
        sb.append("</TranID>");
        sb.append("<MealDate>");
        sb.append(userMealBean.getMealDate());
        sb.append("</MealDate>");
        sb.append("<MealID>");
        sb.append(userMealBean.getMealId());
        sb.append("</MealID>");
        sb.append("<MealSub>");
        sb.append(1);
        sb.append("</MealSub>");
        sb.append("<Status>");
        sb.append(status);
        sb.append("</Status>");
        sb.append("<TranTime>");
        sb.append(TimeUtils.formatDay());
        sb.append(TimeUtils.formatTime());
        sb.append("</TranTime>");
        sb.append("<Count>");
        sb.append(takeCount);
        sb.append("</Count>");
        sb.append("<Backup1>");
        sb.append("</Backup1>");
        sb.append("<Backup2>");
        sb.append("</Backup2>");
        sb.append("<Backup3>");
        sb.append("</Backup3>");
        sb.append("</Detail>");
        sb.append("</Details>");
        sb.append(XmlHelper.xmlFoot());

        CLog.e("sb = " + sb.toString());
        return sb.toString();
    }

    public static String xmlHead(String tranCode, String cantType) {
        String day = TimeUtils.formatDay();
        String time = TimeUtils.formatTime();
        String seqNo = day + time + "0000000000";

        StringBuilder sb = new StringBuilder();
        sb.append(XMLConstant.XML_HEAD);
        sb.append(XMLConstant.XML_TAG_ROOT[0]);
        sb.append(XMLConstant.XML_TAG_HEAD[0]);
        sb.append(XMLConstant.XML_TAG_TRAN_CODE[0]);
        sb.append(tranCode);
        sb.append(XMLConstant.XML_TAG_TRAN_CODE[1]);
        sb.append(XMLConstant.XML_TAG_TRAN_DATE[0]);
        sb.append(day);
        sb.append(XMLConstant.XML_TAG_TRAN_DATE[1]);
        sb.append(XMLConstant.XML_TAG_TRAN_TIME[0]);
        sb.append(time);
        sb.append(XMLConstant.XML_TAG_TRAN_TIME[1]);
        sb.append(XMLConstant.XML_TAG_SEQ_NO[0]);
        sb.append(seqNo);
        sb.append(XMLConstant.XML_TAG_SEQ_NO[1]);
        sb.append(XMLConstant.XML_TAG_CROP_ID[0]);
        sb.append(XMLConstant.CROP_ID);
        sb.append(XMLConstant.XML_TAG_CROP_ID[1]);
        sb.append(XMLConstant.XML_TAG_CANT_ID[0]);
        sb.append(cantType);
        sb.append(XMLConstant.XML_TAG_CANT_ID[1]);
        sb.append(XMLConstant.XML_TAG_VERSION[0]);
        sb.append(XMLConstant.API_VERSION);
        sb.append(XMLConstant.XML_TAG_VERSION[1]);
        sb.append(XMLConstant.XML_TAG_HEAD[1]);

        sb.append(XMLConstant.XML_TAG_BODY[0]);
        return sb.toString();
    }

    public static String xmlFoot() {
        StringBuilder sb = new StringBuilder();
        sb.append(XMLConstant.XML_TAG_BODY[1]);
        sb.append(XMLConstant.XML_TAG_ROOT[1]);
        return sb.toString();
    }

}
