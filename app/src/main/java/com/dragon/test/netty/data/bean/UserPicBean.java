package com.dragon.test.netty.data.bean;

import android.os.Parcel;
import android.os.Parcelable;


import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import com.aptdev.ismartface.greendao.DaoSession;
import com.aptdev.ismartface.greendao.UserPicBeanDao;

/**
 * 单个用户头像信息
 */
@Entity(active = true)
public class UserPicBean implements Parcelable {

    public static final String FACE_WEB_CHANNEL = "web";
    public static final String FACE_USB_CHANNEL = "usb";
    public static final String FACE_CAMERA_CHANNEL = "camera";

    @Id(autoincrement = true)
    private Long ID;

    private String empId;
    private String empName;
    private String fileName;
    private String filePath;
    private String createTime;

    private String faceChannel;//头像的来源，可以是企惠宝后台，也可以是u盘录入
    private String faceChangeTime;//头像最后更新时间，主要用于由USB切换到企惠宝后台更新的判断

    private String downloadFailMsg;//储存下载失败信息

    public UserPicBean() {
    }

    protected UserPicBean(Parcel in) {
        if (in.readByte() == 0) {
            ID = null;
        } else {
            ID = in.readLong();
        }
        empId = in.readString();
        empName = in.readString();
        fileName = in.readString();
        filePath = in.readString();
        createTime = in.readString();
        faceChannel = in.readString();
        faceChangeTime = in.readString();
        downloadFailMsg = in.readString();
    }

    @Generated(hash = 1849292221)
    public UserPicBean(Long ID, String empId, String empName, String fileName,
            String filePath, String createTime, String faceChannel,
            String faceChangeTime, String downloadFailMsg) {
        this.ID = ID;
        this.empId = empId;
        this.empName = empName;
        this.fileName = fileName;
        this.filePath = filePath;
        this.createTime = createTime;
        this.faceChannel = faceChannel;
        this.faceChangeTime = faceChangeTime;
        this.downloadFailMsg = downloadFailMsg;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (ID == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(ID);
        }
        dest.writeString(empId);
        dest.writeString(empName);
        dest.writeString(fileName);
        dest.writeString(filePath);
        dest.writeString(createTime);
        dest.writeString(faceChannel);
        dest.writeString(faceChangeTime);
        dest.writeString(downloadFailMsg);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserPicBean> CREATOR = new Creator<UserPicBean>() {
        @Override
        public UserPicBean createFromParcel(Parcel in) {
            return new UserPicBean(in);
        }

        @Override
        public UserPicBean[] newArray(int size) {
            return new UserPicBean[size];
        }
    };
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 2097719366)
    private transient UserPicBeanDao myDao;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getID() {
        return this.ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getDownloadFailMsg() {
        return downloadFailMsg;
    }

    //下载失败的原因
    public void setDownloadFailMsg(String downloadFailMsg) {
        this.downloadFailMsg = downloadFailMsg;
    }

    public String getFaceChannel() {
        return this.faceChannel;
    }

    public void setFaceChannel(String faceChannel) {
        this.faceChannel = faceChannel;
    }

    public String getFaceChangeTime() {
        return this.faceChangeTime;
    }

    public void setFaceChangeTime(String faceChangeTime) {
        this.faceChangeTime = faceChangeTime;
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
    @Generated(hash = 737583630)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserPicBeanDao() : null;
    }

}
