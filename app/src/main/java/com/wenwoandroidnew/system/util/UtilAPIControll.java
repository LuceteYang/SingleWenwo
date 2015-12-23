package com.wenwoandroidnew.system.util;

import android.util.Log;

import com.wenwoandroidnew.system.AppGlobalSetting;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

// API처리 상수 값
public class UtilAPIControll {

    // API 이름 리스트
    public static String API_LOGIN = "login";
    public static String API_JOIN = "signup";
    public static String API_NICKNAMECHK = "nickname";
    public static String API_ALL_QUESTION_LIST = "questionList";
    public static String API_MY_QUESTION_LIST = "myfeedList";
    public static String API_ANSWER_LIST = "answerList";
    public static String API_MAGAZINE_LIST = "magazineList";
    public static String API_FRIEND_LIST = "friendList";
    public static String API_BESTANSWER_LIST ="bestAnswerList";
    public static String API_MY_INFO = "myInfo";
    public static String API_QUESTION = "question";
    public static String API_FRIEND_QUESTION = "friendQuestion";
    public static String API_ANSWER_PICK = "answerPick";
    public static String API_FRIEND_DELETE = "friendDelete";
    public static String API_FRIEND_ADD = "friend";
    public static String API_ANSWERER_INFO = "answererInfo";
    public static String API_EMAILCHECK = "emailCheck";



    //OpenAPI
    public static final String GEOCODDING_URL = "https://apis.skplanetx.com/tmap/geo/reversegeocoding?lat=%s&coordType=WGS84GEO&lon=%s&version=1";
    public static final String WEATHER_URL = "http://apis.skplanetx.com/weather/summary?lat=%s&lon=%s&version=1";
    public static final String CURRENCY_URL = "https://openexchangerates.org/api/latest.json?app_id=%s";

    // Server 정보
    public static String SERVER_PROTOCAL = "http://";
    public static String SERVER_SECURE_PROTOCAL = "https://";
    public static String SERVER_IP = "52.25.234.27";
    public static String SERVER_PORT = "";
    public static String URL_DELIM = "/";



    // 로그인 URL, JSON 데이터
    public static String LOGIN_JSON = "{\"code\":200,\"msg\":\"login success\",\"result\":{\"qemail\":\"류준열짱@love.com\",\"profileImage\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_류준열짱@love.com_1447913000875.jpg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_류준열짱@love.com_1447913000875.jpg\"}],\"nickname\":\"marryme\"}}";

    // 회원가입 URL, JSON 데이터
    public static String JOIN_JSON = "{\"code\":200,\"msg\":\"Signup Success\",\"result\":{}}";

    // 닉네임 중복확인
    public static String NICKNAME_JSON = "{\"code\":200,\"msg\":\"not exist\",\"result\":{}}";

    //이메일 중복확인
    public static String EMAIL_JSON="{\"code\":200,\"msg\":\"Success\",\"result\":{}}";

    //답변채택
    public static String ANSWER_PICK_JSON = "{\"code\":400,\"msg\":\"already exist\",\"result\":{}}";

    //Register 등록 데이터
    public static String REGISTER_JSON="";

    //올피드 질문 목록
    public static String ALLFEED_QUESTION_LIST_JSON = "{\"code\":200,\"msg\":\"Success\",\"result\":{\"data\":[{\"profileImage\":[],\"nickname\":\"yoni\",\"anum\":[],\"qemail\":\"yoni@naver.com\",\"writtenTime\":\"2015-11-26T08:33:36.438Z\",\"si\":\"\",\"gu\":\"\",\"dong\":\"\",\"status\":0,\"pickTime\":\"2015-11-26T08:33:36.439Z\",\"image\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/audio/undefined_1448526815842_0.mp3\"}],\"__v\":0,\"duetime\":\"2015-11-26T09:03:00.000Z\",\"category\":\"Traffic\",\"dueTime\":30,\"spentSeed\":12,\"open\":false,\"type\":\"2\",\"text\":\"스스스스크트트트\",\"title\":\"케테테테\",\"qnum\":129,\"_id\":\"5656c3e07bfaca7676aa2a87\"},{\"profileImage\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"nickname\":\"yoni\",\"anum\":[],\"qemail\":\"yoni@naver.com\",\"writtenTime\":\"2015-11-26T08:30:21.995Z\",\"si\":\"\",\"gu\":\"\",\"dong\":\"\",\"status\":1,\"pickTime\":\"2015-11-26T08:30:21.995Z\",\"image\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"__v\":0,\"duetime\":\"2015-11-26T10:30:00.000Z\",\"category\":\"Traffic\",\"dueTime\":120,\"spentSeed\":12,\"open\":false,\"type\":\"1\",\"text\":\"테르트\",\"title\":\"음성테스트\",\"qnum\":128,\"_id\":\"5656c31daab182b974cba658\"},{\"profileImage\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"nickname\":\"yoni\",\"anum\":[],\"qemail\":\"yoni@naver.com\",\"writtenTime\":\"2015-11-26T08:24:26.757Z\",\"si\":\"\",\"gu\":\"\",\"dong\":\"\",\"status\":2,\"pickTime\":\"2015-11-26T08:24:26.758Z\",\"image\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/audio/undefined_1448526266178_0.mp3\"}],\"__v\":0,\"duetime\":\"2015-11-26T10:24:00.000Z\",\"category\":\"Traffic\",\"dueTime\":120,\"spentSeed\":13,\"open\":true,\"type\":\"2\",\"text\":\"크게말하겠습니더 \",\"title\":\"음성녹음 테스트\",\"qnum\":127,\"_id\":\"5656c1baaab182b974cba657\"}]}}";
    public static String MYFEED_QUESTION_LIST_JSON = "{\"code\":200,\"msg\":\"Success\",\"result\":{\"data\":[{\"profileImage\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"nickname\":\"yoni\",\"anum\":[],\"qemail\":\"yoni@naver.com\",\"writtenTime\":\"2015-11-26T08:33:36.438Z\",\"si\":\"\",\"gu\":\"\",\"dong\":\"\",\"status\":1,\"pickTime\":\"2015-11-26T08:33:36.439Z\",\"image\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/audio/undefined_1448526815842_0.mp3\"}],\"__v\":0,\"duetime\":\"2015-11-26T09:03:00.000Z\",\"category\":\"Traffic\",\"dueTime\":30,\"spentSeed\":12,\"open\":false,\"type\":\"2\",\"text\":\"스스스스크트트트\",\"title\":\"케테테테\",\"qnum\":129,\"_id\":\"5656c3e07bfaca7676aa2a87\"},{\"profileImage\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"nickname\":\"yoni\",\"anum\":[],\"qemail\":\"yoni@naver.com\",\"writtenTime\":\"2015-11-26T08:30:21.995Z\",\"si\":\"\",\"gu\":\"\",\"dong\":\"\",\"status\":1,\"pickTime\":\"2015-11-26T08:30:21.995Z\",\"image\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/audio/undefined_1448526621435_0.mp3\"}],\"__v\":0,\"duetime\":\"2015-11-26T10:30:00.000Z\",\"category\":\"Traffic\",\"dueTime\":120,\"spentSeed\":12,\"open\":false,\"type\":\"2\",\"text\":\"테르트\",\"title\":\"음성테스트\",\"qnum\":128,\"_id\":\"5656c31daab182b974cba658\"},{\"profileImage\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"nickname\":\"yoni\",\"anum\":[],\"qemail\":\"yoni@naver.com\",\"writtenTime\":\"2015-11-26T08:24:26.757Z\",\"si\":\"\",\"gu\":\"\",\"dong\":\"\",\"status\":1,\"pickTime\":\"2015-11-26T08:24:26.758Z\",\"image\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/audio/undefined_1448526266178_0.mp3\"}],\"__v\":0,\"duetime\":\"2015-11-26T10:24:00.000Z\",\"category\":\"Traffic\",\"dueTime\":120,\"spentSeed\":13,\"open\":true,\"type\":\"2\",\"text\":\"크게말하겠습니더 \",\"title\":\"음성녹음 테스트\",\"qnum\":127,\"_id\":\"5656c1baaab182b974cba657\"}]}}";
    public static String MYFEED_WAIT_QUESTION_LIST_JSON = "{\"code\":200,\"msg\":\"Success\",\"result\":{\"data\":[{\"profileImage\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"nickname\":\"yoni\",\"anum\":[],\"qemail\":\"yoni@naver.com\",\"writtenTime\":\"2015-11-26T08:33:36.438Z\",\"si\":\"\",\"gu\":\"\",\"dong\":\"\",\"status\":1,\"pickTime\":\"2015-11-26T08:33:36.439Z\",\"image\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/audio/undefined_1448526815842_0.mp3\"}],\"__v\":0,\"duetime\":\"2015-11-26T09:03:00.000Z\",\"category\":\"Traffic\",\"dueTime\":30,\"spentSeed\":12,\"open\":false,\"type\":\"2\",\"text\":\"스스스스크트트트\",\"title\":\"케테테테\",\"qnum\":129,\"_id\":\"5656c3e07bfaca7676aa2a87\"},{\"profileImage\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"nickname\":\"yoni\",\"anum\":[],\"qemail\":\"yoni@naver.com\",\"writtenTime\":\"2015-11-26T08:30:21.995Z\",\"si\":\"\",\"gu\":\"\",\"dong\":\"\",\"status\":1,\"pickTime\":\"2015-11-26T08:30:21.995Z\",\"image\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/audio/undefined_1448526621435_0.mp3\"}],\"__v\":0,\"duetime\":\"2015-11-26T10:30:00.000Z\",\"category\":\"Traffic\",\"dueTime\":120,\"spentSeed\":12,\"open\":false,\"type\":\"2\",\"text\":\"테르트\",\"title\":\"음성테스트\",\"qnum\":128,\"_id\":\"5656c31daab182b974cba658\"},{\"profileImage\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"nickname\":\"yoni\",\"anum\":[],\"qemail\":\"yoni@naver.com\",\"writtenTime\":\"2015-11-26T08:24:26.757Z\",\"si\":\"\",\"gu\":\"\",\"dong\":\"\",\"status\":1,\"pickTime\":\"2015-11-26T08:24:26.758Z\",\"image\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/audio/undefined_1448526266178_0.mp3\"}],\"__v\":0,\"duetime\":\"2015-11-26T10:24:00.000Z\",\"category\":\"Traffic\",\"dueTime\":120,\"spentSeed\":13,\"open\":true,\"type\":\"2\",\"text\":\"크게말하겠습니더 \",\"title\":\"음성녹음 테스트\",\"qnum\":127,\"_id\":\"5656c1baaab182b974cba657\"}]}}";
    public static String MYFEED_YET_QUESTION_LIST_JSON = "{\"code\":200,\"msg\":\"Success\",\"result\":{\"data\":[{\"profileImage\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"nickname\":\"yoni\",\"anum\":[],\"qemail\":\"yoni@naver.com\",\"writtenTime\":\"2015-11-26T08:33:36.438Z\",\"si\":\"\",\"gu\":\"\",\"dong\":\"\",\"status\":1,\"pickTime\":\"2015-11-26T08:33:36.439Z\",\"image\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/audio/undefined_1448526815842_0.mp3\"}],\"__v\":0,\"duetime\":\"2015-11-26T09:03:00.000Z\",\"category\":\"Traffic\",\"dueTime\":30,\"spentSeed\":12,\"open\":false,\"type\":\"2\",\"text\":\"스스스스크트트트\",\"title\":\"케테테테\",\"qnum\":129,\"_id\":\"5656c3e07bfaca7676aa2a87\"},{\"profileImage\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"nickname\":\"yoni\",\"anum\":[],\"qemail\":\"yoni@naver.com\",\"writtenTime\":\"2015-11-26T08:30:21.995Z\",\"si\":\"\",\"gu\":\"\",\"dong\":\"\",\"status\":1,\"pickTime\":\"2015-11-26T08:30:21.995Z\",\"image\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/audio/undefined_1448526621435_0.mp3\"}],\"__v\":0,\"duetime\":\"2015-11-26T10:30:00.000Z\",\"category\":\"Traffic\",\"dueTime\":120,\"spentSeed\":12,\"open\":false,\"type\":\"2\",\"text\":\"테르트\",\"title\":\"음성테스트\",\"qnum\":128,\"_id\":\"5656c31daab182b974cba658\"},{\"profileImage\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"nickname\":\"yoni\",\"anum\":[],\"qemail\":\"yoni@naver.com\",\"writtenTime\":\"2015-11-26T08:24:26.757Z\",\"si\":\"\",\"gu\":\"\",\"dong\":\"\",\"status\":1,\"pickTime\":\"2015-11-26T08:24:26.758Z\",\"image\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/audio/undefined_1448526266178_0.mp3\"}],\"__v\":0,\"duetime\":\"2015-11-26T10:24:00.000Z\",\"category\":\"Traffic\",\"dueTime\":120,\"spentSeed\":13,\"open\":true,\"type\":\"2\",\"text\":\"크게말하겠습니더 \",\"title\":\"음성녹음 테스트\",\"qnum\":127,\"_id\":\"5656c1baaab182b974cba657\"}]}}";
    public static String MYFEED_ACCEPT_QUESTION_LIST_JSON = "{\"code\":200,\"msg\":\"Success\",\"result\":{\"data\":[{\"profileImage\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"nickname\":\"yoni\",\"anum\":[],\"qemail\":\"yoni@naver.com\",\"writtenTime\":\"2015-11-26T08:33:36.438Z\",\"si\":\"\",\"gu\":\"\",\"dong\":\"\",\"status\":1,\"pickTime\":\"2015-11-26T08:33:36.439Z\",\"image\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/audio/undefined_1448526815842_0.mp3\"}],\"__v\":0,\"duetime\":\"2015-11-26T09:03:00.000Z\",\"category\":\"Traffic\",\"dueTime\":30,\"spentSeed\":12,\"open\":false,\"type\":\"2\",\"text\":\"스스스스크트트트\",\"title\":\"케테테테\",\"qnum\":129,\"_id\":\"5656c3e07bfaca7676aa2a87\"},{\"profileImage\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"nickname\":\"yoni\",\"anum\":[],\"qemail\":\"yoni@naver.com\",\"writtenTime\":\"2015-11-26T08:30:21.995Z\",\"si\":\"\",\"gu\":\"\",\"dong\":\"\",\"status\":1,\"pickTime\":\"2015-11-26T08:30:21.995Z\",\"image\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/audio/undefined_1448526621435_0.mp3\"}],\"__v\":0,\"duetime\":\"2015-11-26T10:30:00.000Z\",\"category\":\"Traffic\",\"dueTime\":120,\"spentSeed\":12,\"open\":false,\"type\":\"2\",\"text\":\"테르트\",\"title\":\"음성테스트\",\"qnum\":128,\"_id\":\"5656c31daab182b974cba658\"},{\"profileImage\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"nickname\":\"yoni\",\"anum\":[],\"qemail\":\"yoni@naver.com\",\"writtenTime\":\"2015-11-26T08:24:26.757Z\",\"si\":\"\",\"gu\":\"\",\"dong\":\"\",\"status\":1,\"pickTime\":\"2015-11-26T08:24:26.758Z\",\"image\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/audio/undefined_1448526266178_0.mp3\"}],\"__v\":0,\"duetime\":\"2015-11-26T10:24:00.000Z\",\"category\":\"Traffic\",\"dueTime\":120,\"spentSeed\":13,\"open\":true,\"type\":\"2\",\"text\":\"크게말하겠습니더 \",\"title\":\"음성녹음 테스트\",\"qnum\":127,\"_id\":\"5656c1baaab182b974cba657\"}]}}";

    //콘텐츠 목록
    public static String MAGAZINE_LIST_JSON="{\"code\":200,\"msg\":\"Success\",\"result\":{\"data\":[{\"_id\":\"5641b035e5bf27e9155d1f55\",\"mnum\":5,\"aemail\":\"answer5@naver.com\",\"title\":\"외대맛집을 알아봅시다\",\"text\":\"요기요기요기\",\"category\":\"food\",\"__v\":0,\"savedCount\":0,\"like\":0,\"image\":[\"/5555555.jpg\"],\"writtenTime\":\"2015-11-10T08:52:05.486Z\"},{\"_id\":\"5641b030e5bf27e9155d1f54\",\"mnum\":4,\"aemail\":\"answer4@naver.com\",\"title\":\"외대맛집을 알아봅시다\",\"text\":\"요기요기요기\",\"category\":\"food\",\"__v\":0,\"savedCount\":0,\"like\":0,\"image\":[\"/4444444.jpg\"],\"writtenTime\":\"2015-11-10T08:52:00.796Z\"},{\"_id\":\"5641b025e5bf27e9155d1f53\",\"mnum\":3,\"aemail\":\"answer3@naver.com\",\"title\":\"외대맛집을 알아봅시다\",\"text\":\"요기요기요기\",\"category\":\"food\",\"__v\":0,\"savedCount\":0,\"like\":0,\"image\":[\"/333333.jpg\"],\"writtenTime\":\"2015-11-10T08:51:49.657Z\"},{\"_id\":\"5641b01ae5bf27e9155d1f52\",\"mnum\":2,\"aemail\":\"answer2@naver.com\",\"title\":\"숙대맛집을 알아봅시다\",\"text\":\"요기요기요기\",\"category\":\"food\",\"__v\":0,\"savedCount\":0,\"like\":0,\"image\":[\"/2222222.jpg\"],\"writtenTime\":\"2015-11-10T08:51:38.854Z\"},{\"_id\":\"5641affee5bf27e9155d1f51\",\"mnum\":1,\"aemail\":\"answer1@naver.com\",\"title\":\"홍대맛집을 알아봅시다\",\"text\":\"요기요기요기\",\"category\":\"food\",\"__v\":0,\"savedCount\":0,\"like\":0,\"image\":[\"/111111111.jpg\"],\"writtenTime\":\"2015-11-10T08:51:10.181Z\"}]}}";
    public static String WRITER_MAGAZINE_LIST_JSON="{\"code\":200,\"msg\":\"Success\",\"result\":{\"data\":[{\"_id\":\"5641b035e5bf27e9155d1f55\",\"mnum\":5,\"aemail\":\"answer5@naver.com\",\"title\":\"외대맛집을 알아봅시다\",\"text\":\"요기요기요기\",\"category\":\"food\",\"__v\":0,\"savedCount\":0,\"like\":0,\"image\":[\"/5555555.jpg\"],\"writtenTime\":\"2015-11-10T08:52:05.486Z\"},{\"_id\":\"5641b030e5bf27e9155d1f54\",\"mnum\":4,\"aemail\":\"answer4@naver.com\",\"title\":\"외대맛집을 알아봅시다\",\"text\":\"요기요기요기\",\"category\":\"food\",\"__v\":0,\"savedCount\":0,\"like\":0,\"image\":[\"/4444444.jpg\"],\"writtenTime\":\"2015-11-10T08:52:00.796Z\"},{\"_id\":\"5641b025e5bf27e9155d1f53\",\"mnum\":3,\"aemail\":\"answer3@naver.com\",\"title\":\"외대맛집을 알아봅시다\",\"text\":\"요기요기요기\",\"category\":\"food\",\"__v\":0,\"savedCount\":0,\"like\":0,\"image\":[\"/333333.jpg\"],\"writtenTime\":\"2015-11-10T08:51:49.657Z\"},{\"_id\":\"5641b01ae5bf27e9155d1f52\",\"mnum\":2,\"aemail\":\"answer2@naver.com\",\"title\":\"숙대맛집을 알아봅시다\",\"text\":\"요기요기요기\",\"category\":\"food\",\"__v\":0,\"savedCount\":0,\"like\":0,\"image\":[\"/2222222.jpg\"],\"writtenTime\":\"2015-11-10T08:51:38.854Z\"},{\"_id\":\"5641affee5bf27e9155d1f51\",\"mnum\":1,\"aemail\":\"answer1@naver.com\",\"title\":\"홍대맛집을 알아봅시다\",\"text\":\"요기요기요기\",\"category\":\"food\",\"__v\":0,\"savedCount\":0,\"like\":0,\"image\":[\"/111111111.jpg\"],\"writtenTime\":\"2015-11-10T08:51:10.181Z\"}]}}";
    public static String QUESTIONER_MAGAZINE_LIST_JSON="{\"code\":200,\"msg\":\"Success\",\"result\":{\"data\":[{\"_id\":\"5641b035e5bf27e9155d1f55\",\"mnum\":5,\"aemail\":\"answer5@naver.com\",\"title\":\"외대맛집을 알아봅시다\",\"text\":\"요기요기요기\",\"category\":\"food\",\"__v\":0,\"savedCount\":0,\"like\":0,\"image\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"writtenTime\":\"2015-11-10T08:52:05.486Z\"},{\"_id\":\"5641b030e5bf27e9155d1f54\",\"mnum\":4,\"aemail\":\"answer4@naver.com\",\"title\":\"외대맛집을 알아봅시다\",\"text\":\"요기요기요기\",\"category\":\"food\",\"__v\":0,\"savedCount\":0,\"like\":0,\"image\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"writtenTime\":\"2015-11-10T08:52:00.796Z\"},{\"_id\":\"5641b025e5bf27e9155d1f53\",\"mnum\":3,\"aemail\":\"answer3@naver.com\",\"title\":\"외대맛집을 알아봅시다\",\"text\":\"요기요기요기\",\"category\":\"food\",\"__v\":0,\"savedCount\":0,\"like\":0,\"image\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"writtenTime\":\"2015-11-10T08:51:49.657Z\"},{\"_id\":\"5641b01ae5bf27e9155d1f52\",\"mnum\":2,\"aemail\":\"answer2@naver.com\",\"title\":\"숙대맛집을 알아봅시다\",\"text\":\"요기요기요기\",\"category\":\"food\",\"__v\":0,\"savedCount\":0,\"like\":0,\"image\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"writtenTime\":\"2015-11-10T08:51:38.854Z\"},{\"_id\":\"5641affee5bf27e9155d1f51\",\"mnum\":1,\"aemail\":\"answer1@naver.com\",\"title\":\"홍대맛집을 알아봅시다\",\"text\":\"요기요기요기\",\"category\":\"food\",\"__v\":0,\"savedCount\":0,\"like\":0,\"image\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"writtenTime\":\"2015-11-10T08:51:10.181Z\"}]}}";

    //Me Info
    public static String MY_INFO_JSON= "{\"code\":200,\"msg\":\"Success\",\"result\":{\"data\":{\"questionCount\":0,\"qemail\":\"questioner1@naver.com\",\"seeds\":0,\"nickname\":\"*1\",\"profileImage\":[{\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/magazine/thumbnail/th_answer@wenwo.com_1448682760410.jpg\",\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/magazine/answer@wenwo.com_1448682760410.jpg\"}],\"savedContents\":[],\"shareQnQ\":[],\"birthDate\":\"1970-01-01T00:01:51.111Z\",\"sex\":\"w\",\"pw\":\"1\"}}}";

    //답변자 정보 Info
    public static String ANSWERER_INFO_JSON="{\"code\":200,\"msg\":\"success\",\"result\":{\"data\":{\"aemail\":\"answer1@naver.com\",\"nickname\":\"11111\",\"seedHistory\":50,\"answerRate\":50}}}";
    //답변 목록
    public static String ANSWER_LIST_JSON = "{\"code\":200,\"msg\":\"Success\",\"result\":{\"data\":[{\"profileImage\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"nickname\":\"11111\",\"status\":1,\"image\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"type\":1,\"__v\":0,\"qnum\":\"1\",\"category\":\"traffic\",\"text\":\"가족생활동\",\"aemail\":\"answer1@naver.com\",\"anum\":1,\"_id\":\"5641b23ee5bf27e9155d1f56\"}]}}";

   //대표 답변
    public static String BEST_ANSWER_LIST_JSON = "{\"code\":200,\"msg\":\"Success\",\"result\":{\"data\":[{\"profileImage\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"nickname\":\"11111\",\"status\":1,\"image\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"type\":1,\"__v\":0,\"qnum\":\"1\",\"category\":\"traffic\",\"text\":\"가족생활동\",\"aemail\":\"answer1@naver.com\",\"anum\":1,\"_id\":\"5641b23ee5bf27e9155d1f56\"}]}}";

    //지역코드
    public static String GEOCODING_JSON ="{\"addressInfo\":{\"fullAddress\":\"서울특별시 관악구 봉천동  산8-17\", \"addressType\":\"A02\", \"city_do\":\"서울특별시\", \"gu_gun\":\"관악구\", \"eup_myun\":\"\", \"adminDong\":\"\", \"adminDongCode\":\"\", \"legalDong\":\"봉천동\", \"legalDongCode\":\"1162010100\", \"ri\":\"\", \"bunji\":\"산8-17\", \"roadName\":\"\", \"buildingIndex\":\"\", \"buildingName\":\"\", \"mappingDistance\":\"\", \"roadCode\":\"\"}}\n";

    //날씨코드
    public static String WEATHER_JSON ="{\"result\":{\"message\":\"성공\",\"code\":9200,\"requestUrl\":\"/weather/summary?lon=126.97&stnid=&lat=37.56&version=1\"},\"common\":{\"alertYn\":\"Y\",\"stormYn\":\"N\"},\"weather\":{\"summary\":[{\"grid\":{\"latitude\":\"37.5798800000\",\"longitude\":\"126.9893600000\",\"city\":\"서울\",\"county\":\"중구\",\"village\":\"을지로2가\"},\"timeRelease\":\"2015-12-18 18:00:00\",\"yesterday\":{\"precipitation\":{\"rain\":\"0.00\",\"snow\":\"0.00\"},\"sky\":{\"name\":\"흐림\",\"code\":\"SKY_Y04\"},\"temperature\":{\"tmax\":\"-0.40\",\"tmin\":\"-5.70\"}},\"today\":{\"sky\":{\"name\":\"구름조금\",\"code\":\"SKY_D02\"},\"temperature\":{\"tmax\":\"6.00\",\"tmin\":\"-4.00\"}},\"tomorrow\":{\"sky\":{\"name\":\"흐림\",\"code\":\"SKY_M04\"},\"temperature\":{\"tmax\":\"5.00\",\"tmin\":\"-2.00\"}},\"dayAfterTomorrow\":{\"sky\":{\"name\":\"구름많음\",\"code\":\"SKY_M03\"},\"temperature\":{\"tmax\":\"6.00\",\"tmin\":\"1.00\"}}}]}}";
    //환전Info
    public static String CURRENCY_JSON ="{\"disclaimer\":\"Exchange rates are provided for informational purposes only, and do not constitute financial advice of any kind. Although every attempt is made to ensure quality, NO guarantees are given whatsoever of accuracy, validity, availability, or fitness for any purpose - please use at your own risk. All usage is subject to your acceptance of the Terms and Conditions of Service, available at: https://openexchangerates.org/terms/\",\"license\":\"Data sourced from various providers with public-facing APIs; copyright may apply; resale is prohibited; no warranties given of any kind. Bitcoin data provided by http://coindesk.com. All usage is subject to your acceptance of the License Agreement available at: https://openexchangerates.org/license/\",\"timestamp\":1450353651,\"base\":\"USD\",\"rates\":{\"AED\":3.673045,\"AFN\":67.769999,\"ALL\":126.855299,\"AMD\":483.735001,\"ANG\":1.788725,\"AOA\":135.273335,\"ARS\":9.804025,\"AUD\":1.387844,\"AWG\":1.793333,\"AZN\":1.047963,\"BAM\":1.798941,\"BBD\":2,\"BDT\":78.71032,\"BGN\":1.799918,\"BHD\":0.377184,\"BIF\":1572.73,\"BMD\":1,\"BND\":1.415278,\"BOB\":6.923004,\"BRL\":3.900225,\"BSD\":1,\"BTC\":0.0021874897,\"BTN\":66.544482,\"BWP\":11.087263,\"BYR\":18359.5,\"BZD\":1.999684,\"CAD\":1.380867,\"CDF\":928.2565,\"CHF\":0.995608,\"CLF\":0.024598,\"CLP\":707.535199,\"CNY\":6.481419,\"COP\":3339.779987,\"CRC\":532.691799,\"CUC\":1,\"CUP\":1.001325,\"CVE\":101.671056165,\"CZK\":24.88503,\"DJF\":177.672751,\"DKK\":6.868301,\"DOP\":45.71278,\"DZD\":107.3148,\"EEK\":14.365375,\"EGP\":7.832053,\"ERN\":15.0015,\"ETB\":21.18461,\"EUR\":0.922061,\"FJD\":2.13675,\"FKP\":0.669718,\"GBP\":0.669718,\"GEL\":2.39335,\"GGP\":0.669718,\"GHS\":3.828944,\"GIP\":0.669718,\"GMD\":39.55159,\"GNF\":7787.299951,\"GTQ\":7.622019,\"GYD\":206.238669,\"HKD\":7.751675,\"HNL\":22.0383,\"HRK\":7.041461,\"HTG\":56.667662,\"HUF\":290.7176,\"IDR\":14006.75,\"ILS\":3.890289,\"IMP\":0.669718,\"INR\":66.46935,\"IQD\":1104.306659,\"IRR\":30000,\"ISK\":129.8717,\"JEP\":0.669718,\"JMD\":120.185401,\"JOD\":0.709796,\"JPY\":122.3031,\"KES\":102.3431,\"KGS\":75.86845,\"KHR\":4068.3325,\"KMF\":451.914681,\"KPW\":900.09,\"KRW\":1181.17334}}";

    // 친구 목록
    public static String FRIEND_LIST_JSON = "{\"code\":200,\"msg\":\"Success\",\"result\":{\"data\":[{\"qemail\":\"questioner2@naver.com\",\"nickname\":\"*2\",\"profileImage\":[],\"shareQnQ\":0,\"favorite\":0},{\"qemail\":\"questioner3@naver.com\",\"nickname\":\"*3\",\"profileImage\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"shareQnQ\":0,\"favorite\":0}]}}";
    // 질문등록 결과
    public static String QUESTION_REGISTER_JSON="{\"code\":200,\"msg\":\"Question post Success\",\"result\":{}}";

    //친구 공유 질문
    public static String FRIEND_QUESTION = "{\"code\":200,\"msg\":\"Success\",\"result\":{\"data\":[{\"profileImage\":[],\"nickname\":\"yoni\",\"anum\":[],\"qemail\":\"yoni@naver.com\",\"writtenTime\":\"2015-11-26T08:33:36.438Z\",\"si\":\"\",\"gu\":\"\",\"dong\":\"\",\"status\":0,\"pickTime\":\"2015-11-26T08:33:36.439Z\",\"image\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/audio/undefined_1448526815842_0.mp3\"}],\"__v\":0,\"duetime\":\"2015-11-26T09:03:00.000Z\",\"category\":\"Traffic\",\"dueTime\":30,\"spentSeed\":12,\"open\":false,\"type\":\"2\",\"text\":\"스스스스크트트트\",\"title\":\"케테테테\",\"qnum\":129,\"_id\":\"5656c3e07bfaca7676aa2a87\"},{\"profileImage\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"nickname\":\"yoni\",\"anum\":[],\"qemail\":\"yoni@naver.com\",\"writtenTime\":\"2015-11-26T08:30:21.995Z\",\"si\":\"\",\"gu\":\"\",\"dong\":\"\",\"status\":1,\"pickTime\":\"2015-11-26T08:30:21.995Z\",\"image\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"__v\":0,\"duetime\":\"2015-11-26T10:30:00.000Z\",\"category\":\"Traffic\",\"dueTime\":120,\"spentSeed\":12,\"open\":false,\"type\":\"1\",\"text\":\"테르트\",\"title\":\"음성테스트\",\"qnum\":128,\"_id\":\"5656c31daab182b974cba658\"},{\"profileImage\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/profile_yoni@naver.com_1448521242327.jpeg\",\"th_path\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/image/thumbnail/th_profile_yoni@naver.com_1448521242327.jpeg\"}],\"nickname\":\"yoni\",\"anum\":[],\"qemail\":\"yoni@naver.com\",\"writtenTime\":\"2015-11-26T08:24:26.757Z\",\"si\":\"\",\"gu\":\"\",\"dong\":\"\",\"status\":2,\"pickTime\":\"2015-11-26T08:24:26.758Z\",\"image\":[{\"originalPath\":\"https://s3-ap-northeast-1.amazonaws.com/wenwotest/Data/audio/undefined_1448526266178_0.mp3\"}],\"__v\":0,\"duetime\":\"2015-11-26T10:24:00.000Z\",\"category\":\"Traffic\",\"dueTime\":120,\"spentSeed\":13,\"open\":true,\"type\":\"2\",\"text\":\"크게말하겠습니더 \",\"title\":\"음성녹음 테스트\",\"qnum\":127,\"_id\":\"5656c1baaab182b974cba657\"}]}}";

    //친구 삭제
    public static String FRIEND_DELETE ="{\"code\":200,\"msg\":\"Success\",\"result\":{\"data\":[{\"_id\":12,\"__v\":0,\"friend\":[],\"shareQnQ\":[],\"seed\":0,\"savedContents\":[],\"profileImage\":[]},{\"_id\":12,\"__v\":0,\"friend\":[],\"shareQnQ\":[],\"seed\":0,\"savedContents\":[],\"profileImage\":[]}]}}";


    public static String makeRESTURL( String apiName , Hashtable<String, String> params){

        StringBuilder sb = new StringBuilder(SERVER_PROTOCAL).append(SERVER_IP).append(":")
            .append(SERVER_PORT)
            .append(URL_DELIM)
            .append(apiName);


        if( params == null || params.size() == 0){
            return sb.toString();
        }

        Enumeration<String> keys = params.keys();

        sb = sb.append("?");
        while ( keys.hasMoreElements()){
            String key = keys.nextElement();
            sb = sb.append( key).append("=").append( params.get( key)).append("&");
        }

        return sb.toString();
    }

    public static String makePostRESTURL( String apiName){

        StringBuilder sb = new StringBuilder(SERVER_PROTOCAL).append(SERVER_IP).append(":")
                .append(SERVER_PORT)
                .append(URL_DELIM)
                .append(apiName);

        return sb.toString();
    }




    public static String makeGeocodingRESTURL( String apiName ,String latitude,String longitude){

        String urlText = String.format(apiName, latitude, longitude);
        return urlText;
    }

    public static String makeCurrencyRESTURL( String apiName, String appId){

        String urlText = String.format(apiName,appId);
        return urlText;
    }



    public static String callServerData(String url, String json){

        Log.d("@@@@@@@@2" , url);
        Log.d("@@@@@@@@3" , json);

        try {
            return (AppSetting.NET_TYPE == true? UtilNetwork.callGetAPI(url) : json );
        } catch ( Exception e) {
            UtilCommon.showLog("callServerData", e.toString());
            UtilCommon.showLog("callServerData", url);
        }
        return null;
    }

    public static String callPostServerData(String url, String postjson,String json){

        try {
            return (AppSetting.NET_TYPE == true? UtilNetwork.callPostAPI(url, postjson) : json );
        } catch (Exception e) {
            UtilCommon.showMessage(AppGlobalSetting.context, e.toString());
        }
        return null;
    }


    public static String callPostFileServerData(String url, Hashtable<String, String> data, List<String> imageList, String voicePath, String json){

        try {
            return (AppSetting.NET_TYPE == true? UtilNetwork.callPostUploadFileAPI(url, data, imageList, voicePath) : json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static String callGEOServerData(String url, String json){
            return (AppSetting.NET_TYPE == true? UtilNetwork.callGEOGetAPI(url) : json );
    }

    public static String callCurrencyServerData(String url, String json){
        return (AppSetting.NET_TYPE == true? UtilNetwork.callCurrencyGetAPI(url) : json );
    }

    // 호출 Path parameter API 생성 (임시)
    public static String makeRESTURL2( String apiName ,String Path ,Hashtable<String, String> params){

        StringBuilder sb = new StringBuilder(SERVER_PROTOCAL).append(SERVER_IP).append(":")
                .append(SERVER_PORT)
                .append(URL_DELIM)
                .append(apiName)
                .append(URL_DELIM)
                .append(Path);

        if( params == null || params.size() == 0){
            return sb.toString();
        }

        Enumeration<String> keys = params.keys();

        sb = sb.append("?");
        while ( keys.hasMoreElements()){
            String key = keys.nextElement();
            sb = sb.append( key).append("=").append( params.get( key)).append("&");
        }

        return sb.toString();
    }



}

