package com.dragon.test.netty.data.bean;

import android.os.Parcel;
import android.os.Parcelable;


import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Transient;
import com.aptdev.ismartface.greendao.DaoSession;
import com.aptdev.ismartface.greendao.UserPicBeanDao;
import com.aptdev.ismartface.greendao.WhiteListUserBeanDao;

/**
 * 白名单信息，包含web端的员工数据，头像信息
 */
@Entity(active = true)
public class WhiteListUserBean implements Parcelable {

    @Id(autoincrement = true)
    private Long ID;

    private Long uPicId;//外键

    @ToOne(joinProperty = "uPicId")
    private UserPicBean userPicBean;

    private String empID;//员工编号
    private String empName;//员工姓名
    private String certType;//证件类型
    private String certCode;//证件号码
    private String cardNo;//工行卡号
    private String empType;//员工类型
    private String depId1;//一级部门
    private String depId2;//二级部门
    private String defCantId;//默认饭堂ID
    private String icCardNo;//IC卡号
    private String duty;//职务
    private String sex;//性别1男2女
    private String teleNum;//电话
    private String mobiNum;//手机
    @Transient //排除密码字段
    private String passNum;//密码
    private String entryDate;//入职时间yyyymmdd
    private String leaveDate;//离职时间yyyymmdd
    private String createTime;//创建时间yyyymmddhh24miss//修改数据库字段结构，改为long类型
    private String updateTime;//最后修改时间yyyymmhh24miss修改数据库字段结构，改为long类型//如果找不到判断的话
    private String backUp1;//备用字段
    private String backUp2;//备用字段
    private String backUp3;//备用字段

    private int faceId = -1;//这代表人脸的id，一个人只有一个人脸id
    private String mOriginalFacePath;//原图识别后截取的人脸信息保存地址

    private float mMoney;//用户账户金额

    public WhiteListUserBean() {
    }

    protected WhiteListUserBean(Parcel in) {
        if (in.readByte() == 0) {
            ID = null;
        } else {
            ID = in.readLong();
        }
        if (in.readByte() == 0) {
            uPicId = null;
        } else {
            uPicId = in.readLong();
        }
        userPicBean = in.readParcelable(UserPicBean.class.getClassLoader());
        empID = in.readString();
        empName = in.readString();
        certType = in.readString();
        certCode = in.readString();
        cardNo = in.readString();
        empType = in.readString();
        depId1 = in.readString();
        depId2 = in.readString();
        defCantId = in.readString();
        icCardNo = in.readString();
        duty = in.readString();
        sex = in.readString();
        teleNum = in.readString();
        mobiNum = in.readString();
        passNum = in.readString();
        entryDate = in.readString();
        leaveDate = in.readString();
        createTime = in.readString();
        updateTime = in.readString();
        backUp1 = in.readString();
        backUp2 = in.readString();
        backUp3 = in.readString();
        faceId = in.readInt();
        mOriginalFacePath = in.readString();
        mMoney = in.readFloat();
    }

    @Generated(hash = 1166451950)
    public WhiteListUserBean(Long ID, Long uPicId, String empID, String empName, String certType,
            String certCode, String cardNo, String empType, String depId1, String depId2,
            String defCantId, String icCardNo, String duty, String sex, String teleNum, String mobiNum,
            String entryDate, String leaveDate, String createTime, String updateTime, String backUp1,
            String backUp2, String backUp3, int faceId, String mOriginalFacePath, float mMoney) {
        this.ID = ID;
        this.uPicId = uPicId;
        this.empID = empID;
        this.empName = empName;
        this.certType = certType;
        this.certCode = certCode;
        this.cardNo = cardNo;
        this.empType = empType;
        this.depId1 = depId1;
        this.depId2 = depId2;
        this.defCantId = defCantId;
        this.icCardNo = icCardNo;
        this.duty = duty;
        this.sex = sex;
        this.teleNum = teleNum;
        this.mobiNum = mobiNum;
        this.entryDate = entryDate;
        this.leaveDate = leaveDate;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.backUp1 = backUp1;
        this.backUp2 = backUp2;
        this.backUp3 = backUp3;
        this.faceId = faceId;
        this.mOriginalFacePath = mOriginalFacePath;
        this.mMoney = mMoney;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (ID == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(ID);
        }
        if (uPicId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(uPicId);
        }
        dest.writeParcelable(userPicBean, flags);
        dest.writeString(empID);
        dest.writeString(empName);
        dest.writeString(certType);
        dest.writeString(certCode);
        dest.writeString(cardNo);
        dest.writeString(empType);
        dest.writeString(depId1);
        dest.writeString(depId2);
        dest.writeString(defCantId);
        dest.writeString(icCardNo);
        dest.writeString(duty);
        dest.writeString(sex);
        dest.writeString(teleNum);
        dest.writeString(mobiNum);
        dest.writeString(passNum);
        dest.writeString(entryDate);
        dest.writeString(leaveDate);
        dest.writeString(createTime);
        dest.writeString(updateTime);
        dest.writeString(backUp1);
        dest.writeString(backUp2);
        dest.writeString(backUp3);
        dest.writeInt(faceId);
        dest.writeString(mOriginalFacePath);
        dest.writeFloat(mMoney);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WhiteListUserBean> CREATOR = new Creator<WhiteListUserBean>() {
        @Override
        public WhiteListUserBean createFromParcel(Parcel in) {
            return new WhiteListUserBean(in);
        }

        @Override
        public WhiteListUserBean[] newArray(int size) {
            return new WhiteListUserBean[size];
        }
    };

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1806952619)
    private transient WhiteListUserBeanDao myDao;

    @Generated(hash = 1788491279)
    private transient Long userPicBean__resolvedKey;

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
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

    public String getIcCardNo() {
        return icCardNo;
    }

    public void setIcCardNo(String icCardNo) {
        this.icCardNo = icCardNo;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTeleNum() {
        return teleNum;
    }

    public void setTeleNum(String teleNum) {
        this.teleNum = teleNum;
    }

    public String getMobiNum() {
        return mobiNum;
    }

    public void setMobiNum(String mobiNum) {
        this.mobiNum = mobiNum;
    }

    public String getPassNum() {
        return passNum;
    }

    public void setPassNum(String passNum) {
        this.passNum = passNum;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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

    public Long getID() {
        return this.ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public int getFaceId() {
        return this.faceId;
    }

    public void setFaceId(int faceId) {
        this.faceId = faceId;
    }

    public Long getUPicId() {
        return this.uPicId;
    }

    public void setUPicId(Long uPicId) {
        this.uPicId = uPicId;
    }

    public String getMOriginalFacePath() {
        return this.mOriginalFacePath;
    }

    public void setMOriginalFacePath(String mOriginalFacePath) {
        this.mOriginalFacePath = mOriginalFacePath;
    }

    public float getMMoney() {
        return this.mMoney;
    }

    public void setMMoney(float mMoney) {
        this.mMoney = mMoney;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1773525466)
    public UserPicBean getUserPicBean() {
        Long __key = this.uPicId;
        if (userPicBean__resolvedKey == null || !userPicBean__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserPicBeanDao targetDao = daoSession.getUserPicBeanDao();
            UserPicBean userPicBeanNew = targetDao.load(__key);
            synchronized (this) {
                userPicBean = userPicBeanNew;
                userPicBean__resolvedKey = __key;
            }
        }
        return userPicBean;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 708643262)
    public void setUserPicBean(UserPicBean userPicBean) {
        synchronized (this) {
            this.userPicBean = userPicBean;
            uPicId = userPicBean == null ? null : userPicBean.getID();
            userPicBean__resolvedKey = uPicId;
        }
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
    @Generated(hash = 97483670)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getWhiteListUserBeanDao() : null;
    }

}
