package com.dragon.test.netty.data.bean;

import android.graphics.Bitmap;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.DaoException;
import com.aptdev.ismartface.greendao.DaoSession;
import com.aptdev.ismartface.greendao.UserMealBeanDao;

/**
 * 单个订餐信息
 */
@Entity(active = true)
public class UserMealBean {

    public static final String BK_MEAL_ID = "1";//早餐
    public static final String LC_MEAL_ID = "2";//午餐
    public static final String DN_MEAL_ID = "3";//晚餐
    public static final String NS_MEAL_ID = "4";//宵夜

    @Id(autoincrement = true)
    private Long umID;

    private String empId;//
    private String empName;//
    private String empType;//
    private String depId1;//
    private String depId2;//
    private String defCantId;//
    private String tranId;
    private String mealDate;
    private String mealId;
    private String mealSub;
    private String mealSubName;
    private String tranTime;
    private String count;
    private String amount;
    private String status;
    private String channel;
    private String userTime;
    private String useSeq;
    private String cancelTime;
    private String payEmpId;
    private String payEmpName;
    private String useCount;
    private String backUp1;
    private String backUp2;
    private String backUp3;

    @Transient
    private transient Bitmap mDetectBitmap;

    @Transient
    private String mOriginalFacePath;

    @Transient
    private String faceId;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 914936131)
    private transient UserMealBeanDao myDao;

    public UserMealBean() {
    }

    @Generated(hash = 725869790)
    public UserMealBean(Long umID, String empId, String empName, String empType, String depId1, String depId2, String defCantId, String tranId, String mealDate, String mealId, String mealSub, String mealSubName, String tranTime, String count, String amount, String status, String channel, String userTime,
            String useSeq, String cancelTime, String payEmpId, String payEmpName, String useCount, String backUp1, String backUp2, String backUp3) {
        this.umID = umID;
        this.empId = empId;
        this.empName = empName;
        this.empType = empType;
        this.depId1 = depId1;
        this.depId2 = depId2;
        this.defCantId = defCantId;
        this.tranId = tranId;
        this.mealDate = mealDate;
        this.mealId = mealId;
        this.mealSub = mealSub;
        this.mealSubName = mealSubName;
        this.tranTime = tranTime;
        this.count = count;
        this.amount = amount;
        this.status = status;
        this.channel = channel;
        this.userTime = userTime;
        this.useSeq = useSeq;
        this.cancelTime = cancelTime;
        this.payEmpId = payEmpId;
        this.payEmpName = payEmpName;
        this.useCount = useCount;
        this.backUp1 = backUp1;
        this.backUp2 = backUp2;
        this.backUp3 = backUp3;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }

    public String getDepId1() {
        return depId1;
    }

    public void setDepId1(String depId1) {
        this.depId1 = depId1;
    }

    public String getDepId2() {
        return depId2;
    }

    public void setDepId2(String depId2) {
        this.depId2 = depId2;
    }

    public String getDefCantId() {
        return defCantId;
    }

    public void setDefCantId(String defCantId) {
        this.defCantId = defCantId;
    }

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

    public String getMealDate() {
        return mealDate;
    }

    public void setMealDate(String mealDate) {
        this.mealDate = mealDate;
    }

    public String getMealId() {
        return mealId;
    }

    public String coverMealId() {
        if ("1".equals(mealId)) {
            return "早餐";
        } else if ("2".equals(mealId)) {
            return "午餐";
        } else if ("3".equals(mealId)) {
            return "晚餐";
        } else if ("4".equals(mealId)) {
            return "宵夜";
        } else {
            return "未知";
        }
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getTranTime() {
        return tranTime;
    }

    public void setTranTime(String tranTime) {
        this.tranTime = tranTime;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public boolean isUse() {
        return status.equals("2");
    }

    public boolean skipMeal() {//跳过某种取餐状态，如果是退餐，统计模式取餐的，全部不允许使用
        return /*status.equals("2") || */status.equals("3") || status.equals("4");
    }

    public String coverStatus() {
        if ("1".equals(status)) {
            return "已订餐未用餐";
        } else if ("2".equals(status)) {
            return "已用餐";
        } else if ("3".equals(status)) {
            return "统计模式取餐";
        } else if ("4".equals(status)) {
            return "退餐";
        } else if ("5".equals(status)) {
            return "部分取餐";
        } else {
            return "未知";
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUserTime() {
        return userTime;
    }

    public void setUserTime(String userTime) {
        this.userTime = userTime;
    }

    public String getUseSeq() {
        return useSeq;
    }

    public void setUseSeq(String useSeq) {
        this.useSeq = useSeq;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getPayEmpId() {
        return payEmpId;
    }

    public void setPayEmpId(String payEmpId) {
        this.payEmpId = payEmpId;
    }

    public String getPayEmpName() {
        return payEmpName;
    }

    public void setPayEmpName(String payEmpName) {
        this.payEmpName = payEmpName;
    }

    public String getBackUp1() {
        return backUp1;
    }

    public void setBackUp1(String backUp1) {
        this.backUp1 = backUp1;
    }

    public String getBackUp2() {
        return backUp2;
    }

    public void setBackUp2(String backUp2) {
        this.backUp2 = backUp2;
    }

    public String getBackUp3() {
        return backUp3;
    }

    public void setBackUp3(String backUp3) {
        this.backUp3 = backUp3;
    }

    public Long getUmID() {
        return this.umID;
    }

    public void setUmID(Long umID) {
        this.umID = umID;
    }

    public boolean isBK() {
        return BK_MEAL_ID.equals(mealId);
    }

    public boolean isLC() {
        return LC_MEAL_ID.equals(mealId);
    }

    public boolean isDN() {
        return DN_MEAL_ID.equals(mealId);
    }

    public boolean isNS() {
        return NS_MEAL_ID.equals(mealId);
    }

    public Bitmap getDetectBitmap() {
        return mDetectBitmap;
    }

    public void setDetectBitmap(Bitmap detectBitmap) {
        this.mDetectBitmap = detectBitmap;
    }

    public String getOriginalFacePath() {
        return mOriginalFacePath;
    }

    public void setOriginalFacePath(String originalFacePath) {
        this.mOriginalFacePath = originalFacePath;
    }

    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    public String getMealSub() {
        return this.mealSub;
    }

    public void setMealSub(String mealSub) {
        this.mealSub = mealSub;
    }

    public String getMealSubName() {
        return this.mealSubName;
    }

    public void setMealSubName(String mealSubName) {
        this.mealSubName = mealSubName;
    }

    public String getUseCount() {
        return this.useCount;
    }

    public void setUseCount(String useCount) {
        this.useCount = useCount;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2001689686)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserMealBeanDao() : null;
    }
}
