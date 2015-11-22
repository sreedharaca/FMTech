package com.fmtech.fmdianping.configservice.impl;

import com.fmtech.fmdianping.app.FMApplication;

import org.json.JSONObject;

/**
 * ==================================================================
 * Copyright (C) 2015 FMTech All Rights Reserved.
 *
 * @author Drew.Chiang
 * @version v1.0.0
 * @email chiangchuna@gmail.com
 * @create_date 2015/11/13 11:23
 * @description ${todo}
 * <p/>
 * Modification History:
 * Date            Author            Version         Description
 * -----------------------------------------------------------------
 * 2015/11/13 11:23  Drew.Chiang       v1.0.0          create
 * <p/>
 * ==================================================================
 */

public class ConfigHelper {
    public static String InviteFriendUrl;
    public static int MaxBookingCount;
    public static String MyMovieSchema;
    public static String SplashPicSizeList;
    public static String SplashUrlTemplate;
    public static String TTCommonFileList;
    public static String appSkinConfig;
    public static String appVersion;
    public static String availableMCUrl;
    public static String babyProductSearchCategoryList;
    public static String babyProductSearchCityList;
    public static String bluetoothCacheDuration;
    public static String bluetoothScanDuration;
    public static boolean cansmart;
    public static boolean cellLocationEnableWifi;
    public static String citiesForBabyProduct;
    public static int configCheckNtfInterval;
    public static int configCheckNtfWait;
    public static double configDistanceFactor;
    public static int configGpsAccuracy;
    public static int configGpsExpire;
    public static int configMapDisable;
    public static String cooperationUrl;
    public static String countMergeConfig;
    public static int crashReportCount;
    public static String cssLink;
    public static String cssVersion;
    public static String customServiceStart;
    public static String cxk;
    public static int disableFeed;
    public static boolean disableHotelGuide;
    public static boolean disableMcdonalds;
    public static boolean disableQRGrouponVerify;
    public static boolean disableSafeLooper;
    public static boolean disableSceneryOrder;
    public static boolean disableweblogin;
    public static String domainMap;
    public static String downloadFileSha1;
    public static boolean dynamicLogin;
    public static boolean enableCard;
    public static boolean enableCardGift;
    public static boolean enableCardQRcode;
    public static boolean enableEbizPromo;
    public static boolean enableGrandNoviceGift;
    public static boolean enableHackPhone;
    public static boolean enableKTVNewStyle;
    public static boolean enableMOPay;
    public static boolean enableMSiteNoviceGift;
    public static boolean enableNewPay;
    public static boolean enableNewStatistics;
    public static boolean enablePullService;
    public static boolean enableQCloud;
    public static boolean enableShoufaSplash;
    public static boolean enableSplashCache;
    public static boolean enableSplashSync;
    public static boolean enableTakeaway;
    public static boolean enableXljNoviceGift;
    public static boolean enableYY;
    public static boolean enablevoice;
    public static int expiredVersionCode;
    public static String feedDetailUrl;
    public static boolean forceUpdate;
    public static String forceUpdateDesc;
    public static int homeTuanDelay;
    public static String hotelCateIds;
    public static boolean iamShoperSwitch;
    public static String landingPageUrl;
    public static String locDex5ClassName;
    public static String locDex5MD5;
    public static boolean locDex5UpdateNow;
    public static String locDex5Url;
    public static int locDex5Version;
    public static String mcWeixinUrl;
    public static int memberCardDefaultPage;
    public static int minimumReviewWord;
    public static String movieBookingConfig;
    public static String oldApkSha1;
    public static String orderListTabs;
    public static int pushFlag;
    public static int pushRandRange;
    public static String realTimeLocate;
    public static String resetPageIdTrigger;
    public static int scaleImageSizeHeight;
    public static int scaleImageSizeWidth;
    public static String sinaAppKey;
    public static String smartDownloadFileSha1;
    public static String smartSha1;
    public static String smarturl;
    public static String spDefaultKey;
    public static String tuanActivityUrl;
    public static String tunnelBlacklist;
    public static boolean tunnelEnabled;
    public static int tunnelServerTimeout;
    public static int tunnelTimeout;
    public static String url;
    public static boolean usenewupdateservice;
    public static int verifyDistance = 0;
    public static int versionCode;
    public static String versionImg;
    public static String versionName;
    public static String versionNote;
    public static String weddingProductSearchCategoryList;
    public static String weddingProductSearchCityList;
    public static boolean yyDiscountFC;

    static
    {
        enableCardQRcode = false;
        locDex5UpdateNow = false;
        availableMCUrl = null;
        mcWeixinUrl = null;
        enableEbizPromo = false;
        minimumReviewWord = 30;
        landingPageUrl = null;
        feedDetailUrl = null;
        yyDiscountFC = false;
        memberCardDefaultPage = 0;
        disableweblogin = false;
        enableGrandNoviceGift = false;
        usenewupdateservice = false;
        enableMSiteNoviceGift = false;
        enableXljNoviceGift = false;
        enableTakeaway = false;
        disableQRGrouponVerify = false;
        dynamicLogin = false;
        disableSceneryOrder = false;
        enableNewPay = false;
        tuanActivityUrl = null;
        cssLink = null;
        cssVersion = null;
        enableMOPay = false;
        enableCardGift = false;
        cooperationUrl = null;
        disableHotelGuide = false;
        customServiceStart = null;
        pushFlag = 0;
        homeTuanDelay = 0;
        tunnelEnabled = false;
        tunnelTimeout = 0;
        tunnelServerTimeout = 0;
        citiesForBabyProduct = null;
        cxk = "12345678";
        enableSplashCache = false;
        enableSplashSync = false;
        scaleImageSizeWidth = 1400;
        scaleImageSizeHeight = 1400;
        enablePullService = false;
        iamShoperSwitch = true;
        weddingProductSearchCityList = null;
        weddingProductSearchCategoryList = null;
        babyProductSearchCityList = null;
        babyProductSearchCategoryList = null;
        tunnelBlacklist = null;
        SplashUrlTemplate = null;
        enableKTVNewStyle = true;
        spDefaultKey = "splashpic";
        oldApkSha1 = null;
        smarturl = null;
        smartDownloadFileSha1 = null;
        smartSha1 = null;
        disableSafeLooper = false;
        appVersion = null;
        resetPageIdTrigger = "//home#/searchdealgn.bin?";
        configMapDisable = 0;
        sinaAppKey = "844890293";
        MaxBookingCount = 4;
        configCheckNtfInterval = 60000;
        configDistanceFactor = 1.0D;
        expiredVersionCode = 0;
        enableNewStatistics = true;
        cansmart = false;
        movieBookingConfig = null;
        domainMap = null;
        hotelCateIds = null;
        SplashPicSizeList = null;
        MyMovieSchema = null;
        forceUpdate = false;
        InviteFriendUrl = null;
        realTimeLocate = null;
        crashReportCount = 0;
        enableHackPhone = false;
        enableQCloud = true;
        orderListTabs = null;
        forceUpdateDesc = null;
        bluetoothScanDuration = null;
        bluetoothCacheDuration = null;
        appSkinConfig = null;
        TTCommonFileList = null;
        countMergeConfig = "{\"actSupported\":[1,3],\"timerInterval\":2000,\"maxNum\":20}";
        versionImg = null;
        url = null;
        configGpsAccuracy = 0;
        configGpsExpire = 0;
        configCheckNtfWait = 30000;
        cellLocationEnableWifi = false;
        pushRandRange = 0;
        enablevoice = false;
        disableMcdonalds = false;
        enableYY = false;
        enableCard = false;
        disableFeed = 0;
        versionName = null;
        versionCode = 0;
        versionNote = null;
        downloadFileSha1 = null;
        locDex5Version = 0;
        locDex5ClassName = null;
        locDex5Url = null;
        locDex5MD5 = null;
        enableShoufaSplash = true;
//        FMApplication._instance().getService("config");
    }

    public static void refresh(JSONObject paramJSONObject)
    {
        verifyDistance = paramJSONObject.optInt("verifyDistance", 0);
        enableCardQRcode = paramJSONObject.optBoolean("enableCardQRcode", false);
        locDex5UpdateNow = paramJSONObject.optBoolean("locDex5UpdateNow", false);
        availableMCUrl = paramJSONObject.optString("availableMCUrl", null);
        mcWeixinUrl = paramJSONObject.optString("mcWeixinUrl", null);
        enableEbizPromo = paramJSONObject.optBoolean("enableEbizPromo", false);
        minimumReviewWord = paramJSONObject.optInt("minimumReviewWord", 30);
        landingPageUrl = paramJSONObject.optString("landingPageUrl", null);
        feedDetailUrl = paramJSONObject.optString("feedDetailUrl", null);
        yyDiscountFC = paramJSONObject.optBoolean("yyDiscountFC", false);
        memberCardDefaultPage = paramJSONObject.optInt("memberCardDefaultPage", 0);
        disableweblogin = paramJSONObject.optBoolean("disableweblogin", false);
        enableGrandNoviceGift = paramJSONObject.optBoolean("enableGrandNoviceGift", false);
        usenewupdateservice = paramJSONObject.optBoolean("usenewupdateservice", false);
        enableMSiteNoviceGift = paramJSONObject.optBoolean("enableMSiteNoviceGift", false);
        enableXljNoviceGift = paramJSONObject.optBoolean("enableXljNoviceGift", false);
        enableTakeaway = paramJSONObject.optBoolean("enableTakeaway", false);
        disableQRGrouponVerify = paramJSONObject.optBoolean("disableQRGrouponVerify", false);
        dynamicLogin = paramJSONObject.optBoolean("dynamicLogin", false);
        disableSceneryOrder = paramJSONObject.optBoolean("disableSceneryOrder", false);
        enableNewPay = paramJSONObject.optBoolean("enableNewPay", false);
        tuanActivityUrl = paramJSONObject.optString("tuanActivityUrl", null);
        cssLink = paramJSONObject.optString("cssLink", null);
        cssVersion = paramJSONObject.optString("cssVersion", null);
        enableMOPay = paramJSONObject.optBoolean("enableMOPay", false);
        enableCardGift = paramJSONObject.optBoolean("enableCardGift", false);
        cooperationUrl = paramJSONObject.optString("cooperationUrl", null);
        disableHotelGuide = paramJSONObject.optBoolean("disableHotelGuide", false);
        customServiceStart = paramJSONObject.optString("customServiceStart", null);
        pushFlag = paramJSONObject.optInt("pushFlag", 0);
        homeTuanDelay = paramJSONObject.optInt("homeTuanDelay", 0);
        tunnelEnabled = paramJSONObject.optBoolean("tunnelEnabled", false);
        tunnelTimeout = paramJSONObject.optInt("tunnelTimeout", 0);
        tunnelServerTimeout = paramJSONObject.optInt("tunnelServerTimeout", 0);
        citiesForBabyProduct = paramJSONObject.optString("citiesForBabyProduct", null);
        cxk = paramJSONObject.optString("cxk", "12345678");
        enableSplashCache = paramJSONObject.optBoolean("enableSplashCache", false);
        enableSplashSync = paramJSONObject.optBoolean("enableSplashSync", false);
        scaleImageSizeWidth = paramJSONObject.optInt("scaleImageSizeWidth", 1400);
        scaleImageSizeHeight = paramJSONObject.optInt("scaleImageSizeHeight", 1400);
        enablePullService = paramJSONObject.optBoolean("enablePullService", false);
        iamShoperSwitch = paramJSONObject.optBoolean("iamShoperSwitch", true);
        weddingProductSearchCityList = paramJSONObject.optString("weddingProductSearchCityList", null);
        weddingProductSearchCategoryList = paramJSONObject.optString("weddingProductSearchCategoryList", null);
        babyProductSearchCityList = paramJSONObject.optString("babyProductSearchCityList", null);
        babyProductSearchCategoryList = paramJSONObject.optString("babyProductSearchCategoryList", null);
        tunnelBlacklist = paramJSONObject.optString("tunnelBlacklist", null);
        SplashUrlTemplate = paramJSONObject.optString("SplashUrlTemplate", null);
        enableKTVNewStyle = paramJSONObject.optBoolean("enableKTVNewStyle", true);
        spDefaultKey = paramJSONObject.optString("spDefaultKey", "splashpic");
        oldApkSha1 = paramJSONObject.optString("oldApkSha1", null);
        smarturl = paramJSONObject.optString("smarturl", null);
        smartDownloadFileSha1 = paramJSONObject.optString("smartDownloadFileSha1", null);
        smartSha1 = paramJSONObject.optString("smartSha1", null);
        disableSafeLooper = paramJSONObject.optBoolean("disableSafeLooper", false);
        appVersion = paramJSONObject.optString("appVersion", null);
        resetPageIdTrigger = paramJSONObject.optString("resetPageIdTrigger", "//home#/searchdealgn.bin?");
        configMapDisable = paramJSONObject.optInt("configMapDisable", 0);
        sinaAppKey = paramJSONObject.optString("sinaAppKey", "844890293");
        MaxBookingCount = paramJSONObject.optInt("MaxBookingCount", 4);
        configCheckNtfInterval = paramJSONObject.optInt("configCheckNtfInterval", 60000);
        configDistanceFactor = paramJSONObject.optDouble("configDistanceFactor", 1.0D);
        expiredVersionCode = paramJSONObject.optInt("expiredVersionCode", 0);
        enableNewStatistics = paramJSONObject.optBoolean("enableNewStatistics", true);
        cansmart = paramJSONObject.optBoolean("cansmart", false);
        movieBookingConfig = paramJSONObject.optString("movieBookingConfig", null);
        domainMap = paramJSONObject.optString("domainMap", null);
        hotelCateIds = paramJSONObject.optString("hotelCateIds", null);
        SplashPicSizeList = paramJSONObject.optString("SplashPicSizeList", null);
        MyMovieSchema = paramJSONObject.optString("MyMovieSchema", null);
        forceUpdate = paramJSONObject.optBoolean("forceUpdate", false);
        InviteFriendUrl = paramJSONObject.optString("InviteFriendUrl", null);
        realTimeLocate = paramJSONObject.optString("realTimeLocate", null);
        crashReportCount = paramJSONObject.optInt("crashReportCount", 0);
        enableHackPhone = paramJSONObject.optBoolean("enableHackPhone", false);
        enableQCloud = paramJSONObject.optBoolean("enableQCloud", true);
        orderListTabs = paramJSONObject.optString("orderListTabs", null);
        forceUpdateDesc = paramJSONObject.optString("forceUpdateDesc", null);
        bluetoothScanDuration = paramJSONObject.optString("bluetoothScanDuration", null);
        bluetoothCacheDuration = paramJSONObject.optString("bluetoothCacheDuration", null);
        appSkinConfig = paramJSONObject.optString("appSkinConfig", null);
        TTCommonFileList = paramJSONObject.optString("TTCommonFileList", null);
        countMergeConfig = paramJSONObject.optString("countMergeConfig", "{\"actSupported\":[1,3],\"timerInterval\":2000,\"maxNum\":20}");
        versionImg = paramJSONObject.optString("versionImg", null);
        url = paramJSONObject.optString("url", null);
        configGpsAccuracy = paramJSONObject.optInt("configGpsAccuracy", 0);
        configGpsExpire = paramJSONObject.optInt("configGpsExpire", 0);
        configCheckNtfWait = paramJSONObject.optInt("configCheckNtfWait", 30000);
        cellLocationEnableWifi = paramJSONObject.optBoolean("cellLocationEnableWifi", false);
        pushRandRange = paramJSONObject.optInt("pushRandRange", 0);
        enablevoice = paramJSONObject.optBoolean("enablevoice", false);
        disableMcdonalds = paramJSONObject.optBoolean("disableMcdonalds", false);
        enableYY = paramJSONObject.optBoolean("enableYY", false);
        enableCard = paramJSONObject.optBoolean("enableCard", false);
        disableFeed = paramJSONObject.optInt("disableFeed", 0);
        versionName = paramJSONObject.optString("versionName", null);
        versionCode = paramJSONObject.optInt("versionCode", 0);
        versionNote = paramJSONObject.optString("versionNote", null);
        downloadFileSha1 = paramJSONObject.optString("downloadFileSha1", null);
        locDex5Version = paramJSONObject.optInt("locDex5Version", 0);
        locDex5ClassName = paramJSONObject.optString("locDex5ClassName", null);
        locDex5Url = paramJSONObject.optString("locDex5Url", null);
        locDex5MD5 = paramJSONObject.optString("locDex5MD5", null);
        enableShoufaSplash = paramJSONObject.optBoolean("enableShoufaSplash", false);
    }
}