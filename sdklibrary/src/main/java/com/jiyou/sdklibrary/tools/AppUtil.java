package com.jiyou.sdklibrary.tools;

import android.os.Environment;
import android.text.TextUtils;

import org.json.JSONObject;

/**
 * Created by fhaoquan on 16/6/3.
 */
public class AppUtil {
    public static final String platformKey = "dkmGameSdk";
    /**
     * SD卡目录
     */
    public static String SDCARD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();

    /**
     * 应用根目录
     */
    public static String ROOT_PATH = SDCARD_PATH + "/Android/data/dkmGameSdk/";

    /**
     * 用户数据目录
     */
    public static String USER_DATA_PATH = ROOT_PATH + "/UserData/";
    /**
     * 下载目录
     */
    public static String DOWNLOAD_PATH = SDCARD_PATH + "/dkmGameSdk/downs/";
    /**
     * 配置目录
     */
    public static String CONFIG_PATH = ROOT_PATH + "/Config/";

    /**
     * 配置文件路径
     */
    //存放用户信息
    public static String CONFIG_FILE = CONFIG_PATH + "dkmGameSdk_config.cfg";

    //存放所有app信息
    public static String AKCONFIG_FILE = CONFIG_PATH + "dkmAkGameSdk_config.cfg";
    /**
     *
     * @Title: getDownsPath(获取下载目录)
     * @author Jeahong
     * @data 2013年9月4日 下午3:04:38
     * @return String 返回类型
     */
    public static String getDownsPath() {
        if (!FileUtilEx.isSDCardAvailable()) {
            return "";
        }
        if (FileUtilEx.createFolder(DOWNLOAD_PATH, FileUtilEx.MODE_UNCOVER)) {
            return DOWNLOAD_PATH;
        }
        return "";
    }

    /**
     * 保存数据
     *
     * @param baseData
     */
//    public static void saveDatatoFile(BaseData baseData) {
//        if (baseData == null) {
//            return;
//        }
//        if (!FileUtilEx.isSDCardAvailable()) {
//            return;
//        }
//        String filePath = baseData.getPath();
//        if (!FileUtilEx.isExist(filePath)) {
//            FileUtilEx.createFile(filePath, FileUtilEx.MODE_COVER);
//        }
//        byte[] data = baseData.getBytes();
//        // 先加密
//        encrypt(data);
//        FileUtilEx.rewriteData(filePath, data);
//        // 保存 用户路径
//        saveLatestUserDataFilePath(filePath);
//    }

    /**
     * 删除数据
     */
//    public static void deleteDataFile(BaseData baseData) {
//        if (baseData == null) {
//            return;
//        }
//        if (!FileUtilEx.isSDCardAvailable()) {
//            return;
//        }
//        String filePath = baseData.getPath();
//        if (FileUtilEx.isExist(filePath)) {
//            FileUtilEx.deleteFile(filePath);
//        }
//    }

    /**
     * 获取数据
     *
     * @return
     */
//    public static UserData getUserData() {
//        if (!FileUtilEx.isSDCardAvailable()) {
//            if(AkSDKConfig.getInstance().getUserData() == null){
//                return null;
//            }else{
//                return AkSDKConfig.getInstance().getUserData();
//            }
//        }
//        String latestUserDataFilePath = getLatestUserDataFilePath();
//        if (FileUtilEx.isExist(latestUserDataFilePath)) {
//            byte[] data = FileUtilEx.getFileData(latestUserDataFilePath);
//            if (data != null && data.length > 0) {
//                // 先解密
//                unEncrypt(data);
//                String json = new String(data);
//                if (!TextUtils.isEmpty(json)) {
//                    return new UserData(json);
//                }
//            }
//        } else {
//            ArrayList<?> list = getDatasFromFilePath(USER_DATA_PATH, UserData.class);
//            if (list != null && list.size() > 0) {
//                return (UserData) list.get(0);
//            }
//        }
//        return null;
//    }

    /***
     *
     * @Title: getUserData(这里用一句话描述这个方法的作用)
     * @author xiaoming.yuan
     * @data 2013-9-26 下午5:57:33
     * @return UserData 返回类型
     */
//    public static ArrayList<UserData> getAllUserData(Context context) {
//        return (ArrayList<UserData>) getDatasFromFilePath(USER_DATA_PATH, UserData.class);
//	}

    /***
     * 根据关键字获取本地数据
     * @ConfigPath: 存放数据路径
     * @name 存放json key 值
     * @fallback 获取失败返回值
     * @return String 返回类型
     */
	public static String   getLocalConfigData(String ConfigPath, String name, String fallback){
		if (!FileUtilEx.isSDCardAvailable()) {
			return null;
		}
		if (FileUtilEx.isExist(ConfigPath)) {
			byte[] data = FileUtilEx.getFileData(ConfigPath);
			if (data != null && data.length > 0) {
				// 先解密
				unEncrypt(data);
				String json = new String(data);
				if (!TextUtils.isEmpty(json)) {
					try {
						JSONObject jsonObj = new JSONObject(json);
						String srcData = jsonObj.optString(name, fallback);
						return srcData;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

    /***
     * 根据关键字保存本地数据
     * @ConfigPath: 存放数据路径
     * @name 存放json key 值
     * @src 存放json value 值
     * @return void 返回类型
     */
	public static void saveLocalConfigData(String ConfigPath, String name, String src) {
		if (TextUtils.isEmpty(name)|| TextUtils.isEmpty(src)) {
			return;
		}
		if (!FileUtilEx.isSDCardAvailable()) {
			return;
		}
		if (!FileUtilEx.isExist(ConfigPath)) {
            FileUtilEx.createFile(ConfigPath, FileUtilEx.MODE_COVER);
		}

		if (FileUtilEx.isExist(ConfigPath)) {
			byte[] filedata = FileUtilEx.getFileData(ConfigPath);
			if (filedata != null&& filedata.length > 0) {
				unEncrypt(filedata);
				String json = new String(filedata);
				try {
					JSONObject jsonObj = new JSONObject(json);
					jsonObj.put(name, src);
					byte[] data = jsonObj.toString().getBytes();
					// 先加密
					encrypt(data);
                    FileUtilEx.rewriteData(ConfigPath, data);
				} catch (Exception e) {
				}
			}else {
                try {
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put(name, src);
                    byte[] data = jsonObj.toString().getBytes();
                    // 先加密
                    encrypt(data);
                    FileUtilEx.rewriteData(ConfigPath, data);
                } catch (Exception e) {
                }
            }
		}

	}
    /**
     * 获取最新登录的用户数据文件路径
     *
     * @return 用户数据文件路径
     */
    public static String getLatestUserDataFilePath() {
        if (!FileUtilEx.isSDCardAvailable()) {
            return null;
        }
        if (FileUtilEx.isExist(CONFIG_FILE)) {
            byte[] data = FileUtilEx.getFileData(CONFIG_FILE);
            if (data != null && data.length > 0) {
                // 先解密
                unEncrypt(data);
                String json = new String(data);
                if (!TextUtils.isEmpty(json)) {
                    try {
                        JSONObject jsonObj = new JSONObject(json);
                        String userDataFilePath = jsonObj.getString("userDataFilePath");
                        return userDataFilePath;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    /**
     * 保存最新使用的用户数据文件路径
     *
     * @param userDataFilePath
     *            用户数据文件路径
     */
    public static void saveLatestUserDataFilePath(String userDataFilePath) {
        if (TextUtils.isEmpty(userDataFilePath)) {
            return;
        }
        if (!FileUtilEx.isSDCardAvailable()) {
            return;
        }
        if (!FileUtilEx.isExist(CONFIG_FILE)) {
            FileUtilEx.createFile(CONFIG_FILE, FileUtilEx.MODE_COVER);
        }
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("userDataFilePath", userDataFilePath);
            byte[] data = jsonObj.toString().getBytes();
            // 先加密
            encrypt(data);
            FileUtilEx.rewriteData(CONFIG_FILE, data);
        } catch (Exception e) {
        }
    }

    private final static byte[] encrypt_key = new byte[] { 0x1a, 0x2b, 0x3c, 0x4d, 0x5e, 0x6f };

    /**
     * 加密
     *
     * @param source
     */
    public static void encrypt(byte[] source) {
        if (source != null && source.length > 0) {
            int length = source.length;
            int keyLen = encrypt_key.length;
            for (int i = 0; i < length; i++) {
                source[i] ^= encrypt_key[i % keyLen];
            }
        }
    }

    /**
     * 解密
     *
     * @param source
     */
    public static void unEncrypt(byte[] source) {
        if (source != null && source.length > 0) {
            int length = source.length;
            int keyLen = encrypt_key.length;
            for (int i = 0; i < length; i++) {
                source[i] ^= encrypt_key[i % keyLen];
            }
        }
    }

    /**
     * 获取路径下的所有数据对象,根据修改时间排序
     * <p>
     * 调用方法:getDatasFromFilePath(USER_DATA_PATH, UserData.class)
     * </p>
     *
     * @param path
     *            路径
     * @param cls
     *            对象类名
     * @return
     */
//    public static ArrayList<? extends BaseData> getDatasFromFilePath(String path, Class<? extends BaseData> cls, Context context) {
//
//
//        /**
//         * 读取所有账号
//         */
//        ArrayList<BaseData> datas = new ArrayList<BaseData>();
//        if (!FileUtilEx.isSDCardAvailable()) {
//            return datas;
//        }
//        if (!FileUtilEx.isExist(path)) {
//            return datas;
//        }
//
//        File[] files = FileUtilEx.listFiles(path);
//        if(files==null){
//            return datas;
//        }
//        int len = files.length;
//        for (int i = 0; i < len; ++i) {
//            for (int j = 0; j < len - i - 1; j++) {
//                File temp = null;
//                if (files[j].lastModified() < files[j + 1].lastModified()) {
//                    temp = files[j + 1];
//                    files[j + 1] = files[j];
//                    files[j] = temp;
//                }
//            }
//
//        }
//        for (int i = 0; i < len; i++) {
//            byte[] data = FileUtilEx.getFileData(files[i].getAbsolutePath());
//            if (data == null || data.length <= 0) {
//                continue;
//            }
//            // 先解密
//            unEncrypt(data);
//            String json = new String(data);
//            if (TextUtils.isEmpty(json)) {
//                continue;
//            }
//
//            try {
//                // 获取cls只有一个参数并且参数为String的构成函数
//                Constructor cons = cls.getDeclaredConstructor(new Class[] { String.class });
//                Object obj = cons.newInstance(json);
//                datas.add(cls.cast(obj));
//            } catch (NoSuchMethodException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IllegalArgumentException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (InstantiationException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//        return datas;
//    }

//    public static ArrayList<? extends BaseData> getDatasFromFilePath(String path, Class<? extends BaseData> cls) {
//        /**
//         * 读取所有账号
//         */
//        ArrayList<BaseData> datas = new ArrayList<BaseData>();
//        if (!FileUtilEx.isSDCardAvailable()) {
//            return datas;
//        }
//        if (!FileUtilEx.isExist(path)) {
//            return datas;
//        }
//
//        File[] files = FileUtilEx.listFiles(path);
//        if(files==null){
//            return datas;
//        }
//
//        int len = files.length;
//        for (int i = 0; i < len; ++i) {
//            for (int j = 0; j < len - i - 1; j++) {
//                File temp = null;
//                if (files[j].lastModified() < files[j + 1].lastModified()) {
//                    temp = files[j + 1];
//                    files[j + 1] = files[j];
//                    files[j] = temp;
//                }
//            }
//
//        }
//        for (int i = 0; i < len; i++) {
//            byte[] data = FileUtilEx.getFileData(files[i].getAbsolutePath());
//            if (data == null || data.length <= 0) {
//                continue;
//            }
//            // 先解密
//            unEncrypt(data);
//            String json = new String(data);
//            if (TextUtils.isEmpty(json)) {
//                continue;
//            }
//
//            try {
//                // 获取cls只有一个参数并且参数为String的构成函数
//                Constructor cons = cls.getDeclaredConstructor(new Class[] { String.class });
//                Object obj = cons.newInstance(json);
//                datas.add(cls.cast(obj));
//            } catch (NoSuchMethodException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IllegalArgumentException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (InstantiationException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//        return datas;
//    }
    /**
     * 根据文件名获取数据类对象
     *
     * <p>
     * 调用方法:getDataFromFile(USER_DATA_PATH, UserData.class)
     * </p>
     *
     * @param fileName
     *            文件名（包含路径）
     * @param cls
     *            对象类名
     * @return
     */
//    public static BaseData getDataFromFile(String fileName, Class<? extends BaseData> cls) {
//        if (!FileUtilEx.isSDCardAvailable()) {
//            return null;
//        }
//        if (!FileUtilEx.isExist(fileName)) {
//            return null;
//        }
//        if (!cls.getClass().getSuperclass().equals(BaseData.class)) {
//            return null;
//        }
//        byte[] data = FileUtilEx.getFileData(new File(fileName).getAbsolutePath());
//        if (data == null || data.length <= 0) {
//            return null;
//        }
//        // 先解密
//        unEncrypt(data);
//        String json = new String(data);
//        if (TextUtils.isEmpty(json)) {
//            return null;
//        }
//
//        try {
//            // 获取cls只有一个参数并且参数为String的构成函数
//            Constructor cons = cls.getDeclaredConstructor(new Class[] { String.class });
//            Object obj = cons.newInstance(json);
//            return cls.cast(obj);
//        } catch (NoSuchMethodException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace(); // To change body of catch statement use File |
//            // Settings | File Templates.
//        } catch (InstantiationException e) {
//            e.printStackTrace(); // To change body of catch statement use File |
//            // Settings | File Templates.
//        } catch (IllegalAccessException e) {
//            e.printStackTrace(); // To change body of catch statement use File |
//            // Settings | File Templates.
//        }
//        return null;
//    }

}
