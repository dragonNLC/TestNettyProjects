package com.dragon.test.netty.data.constracts;

public class XMLConstant {

    public static final int MODE_CONSUME = 0x1;//本地核销模式
    public static final int MODE_PAYMENT = 0x10;//支付模式

    public static final String MULTI_ONLINE_MODE_SERVER = "multi_online_mode_server";//服务站模式
    public static final String MULTI_ONLINE_MODE_CHILD = "multi_online_mode_child";//终端模式

    public static final String RESULT_SUCCESS = "0";//默认的执行成功返回码

    public static final String QUERY_TAG = "decrypt";//默认的查询标识
    public static final String CROP_ID = "00000074";//企业ID（测试）
    public static final String CANT_ID = "00000129";//饭堂ID（测试）
    public static final String APP_SECRET = "hukQ93CFcKJohWcLPT0adYbpEPdwhXCi";//API密钥（测试）

    /*public static final String CROP_ID = "00000106";//企业ID（测试）
    public static final String CANT_ID = "00000499";//饭堂ID（测试）
    public static final String APP_SECRET = "aIB5gPutkbBJ5YN1TGvOMWiAeYD7rZGw";//API密钥（测试）*/

    public static final String CANT_ALL = "ALL";//选择所有饭堂的标识
    public static final String API_VERSION = "3.0";//API版本

    ///////////////////白名单操作类型/////////////////////
    public static final String ACT_TYPE_1 = "1";//增加
    public static final String ACT_TYPE_2 = "2";//删除
    public static final String ACT_TYPE_3 = "3";//单个查询
    public static final String ACT_TYPE_4 = "4";//修改
    public static final String ACT_TYPE_5 = "5";//查询所有

    public static final String FILE_TYPE_USER_PIC = "1";//下载的文件类型，1：图片

    public static final String FILE_DOWNLOAD_ACT_TYPE_QUERY = "1";//文件操作标志，1：查询

    public static final String FILE_DOWNLOAD_ACT_TYPE_DOWNLOAD = "2";//文件操作标志，2：下载

    public static final String XML_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";//xml文档头

    //////////////////////请求动作////////////////////////////////////////
    public static final String WHITE_LIST_TAG = "emlpoyeeMng";//白名单操作
    public static final String FILE_QUERY_TAG = "FileQry";//文件查询
    public static final String FILE_DOWNLOAD_TAG = "FileDownload";//文件查询
    public static final String ORDER_QUERY_TAG = "orderQry";//订餐明细查询
    public static final String USE_NTC_TAG = "useNtc";//用餐信息通知
    //////////////////////请求动作////////////////////////////////////////

    /////////////////////可能会用到的XML文档标记，避免写错，直接写一个模板在这里//////////////////////////////////////////
    public static final String[] XML_TAG_ROOT = new String[]{"<root>", "</root>"};
    public static final String[] XML_TAG_HEAD = new String[]{"<head>", "</head>"};
    public static final String[] XML_TAG_BODY = new String[]{"<body>", "</body>"};
    public static final String[] XML_TAG_TRAN_CODE = new String[]{"<TranCode>", "</TranCode>"};
    public static final String[] XML_TAG_TRAN_DATE = new String[]{"<TranDate>", "</TranDate>"};
    public static final String[] XML_TAG_TRAN_TIME = new String[]{"<TranTime>", "</TranTime>"};
    public static final String[] XML_TAG_SEQ_NO = new String[]{"<SeqNo>", "</SeqNo>"};
    public static final String[] XML_TAG_CROP_ID = new String[]{"<CropID>", "</CropID>"};
    public static final String[] XML_TAG_CANT_ID = new String[]{"<CantID>", "</CantID>"};
    public static final String[] XML_TAG_VERSION = new String[]{"<Version>", "</Version>"};
    public static final String[] XML_TAG_ACT_TYPE = new String[]{"<ActType>", "</ActType>"};
    public static final String[] XML_TAG_EMP_ID = new String[]{"<EmpID>", "</EmpID>"};
    public static final String[] XML_TAG_EMP_NAME = new String[]{"<EmpName>", "</EmpName>"};
    public static final String[] XML_TAG_EMP_TYPE = new String[]{"<EmpType>", "</EmpType>"};
    public static final String[] XML_TAG_CERT_TYPE = new String[]{"<CertType>", "</CertType>"};
    public static final String[] XML_TAG_CERT_CODE = new String[]{"<CertCode>", "</CertCode>"};
    public static final String[] XML_TAG_CARD_NO = new String[]{"<CardNo>", "</CardNo>"};
    public static final String[] XML_TAG_DEP_ID1_NO = new String[]{"<DepID1>", "</DepID1>"};
    public static final String[] XML_TAG_DEP_ID2_NO = new String[]{"<DepID2>", "</DepID1>"};
    public static final String[] XML_TAG_DEF_CANT_NO = new String[]{"<DefCantID>", "</DefCantID>"};
    public static final String[] XML_TAG_SEX = new String[]{"<Sex>", "</Sex>"};
    public static final String[] XML_TAG_DUTY = new String[]{"<Duty>", "</Duty>"};
    public static final String[] XML_TAG_BACKUP1 = new String[]{"<Backup1>", "</Backup1>"};
    public static final String[] XML_TAG_BACKUP2 = new String[]{"<Backup2>", "</Backup2>"};
    public static final String[] XML_TAG_BACKUP3 = new String[]{"<Backup3>", "</Backup3>"};
    public static final String[] XML_TAG_PAGE_NO = new String[]{"<PageNo>", "</PageNo>"};
    public static final String[] XML_TAG_PAGE_SIZE = new String[]{"<PageSize>", "</PageSize>"};
    public static final String[] XML_TAG_FILE_TYPE = new String[]{"<FileType>", "</FileType>"};
    /////////////////////可能会用到的XML文档标记，避免写错，直接写一个模板在这里//////////////////////////////////////////

}
