package com.wenwoandroidnew.system.manager;

import android.util.Log;

import com.wenwoandroidnew.system.AppGlobalSetting;
import com.wenwoandroidnew.system.model.LocalLoginUser;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by SeungJin on 2015-10-29.
 */
public class DataManager {

    private Realm realm = null;
    private static DataManager wDataManager = null;
    public static DataManager getInstance(){
        if( wDataManager == null){
            wDataManager = new DataManager();
        }
        return wDataManager;
    }
    private DataManager(){
        this.realm = Realm.getInstance(AppGlobalSetting.context);
    }



    // 로그인 사용자 저장
    public String insertLoginCheck( final LocalLoginUser user){

        deleteLoginCheck();
        realm.beginTransaction();
        LocalLoginUser realmUser = realm.copyToRealm(user);
        realm.commitTransaction();

/* 비동기 처리
        realm.executeTransaction(new Realm.Transaction() {

            @Override
            public void execute(Realm realm) {
                LocalLoginUser _user = realm.createObject(LocalLoginUser.class);
                _user = user;
            }
        }, new Realm.Transaction.Callback() {

            @Override
            public void onSuccess() {
                super.onSuccess();
            }

            @Override
            public void onError(Exception e) {
                super.onError(e);
            }
        });
*/

        return realmUser.getEmail();
    }

    //로그인 사용자 삭제
    public void deleteLoginCheck( ){

        realm.beginTransaction();
        RealmResults<LocalLoginUser> result = getRealmResult(realm);

        if( result == null || result.size() == 0){
            realm.commitTransaction();
            return;
        }

        result.clear();

        realm.commitTransaction();
    }


    //로그인 사용자 삭제
    public void deleteLoginCheck( String email){

        realm.beginTransaction();
        RealmResults<LocalLoginUser> result = getRealmResult(realm, email);

        if( result == null || result.size() == 0){
            realm.commitTransaction();
            return;
        }

        result.clear();

        realm.commitTransaction();
    }

    // 로그인 정보 업데이트
    public void updateLoginCheck() {
        deleteLoginCheck();
    }


    public LocalLoginUser getLoginUser( ){
        RealmResults<LocalLoginUser> query = getRealmResult(realm);
        if( query == null || query.size() == 0 ){
            return null;
        }
/*        LocalLoginUser tempUser = new LocalLoginUser();
        tempUser.setEmail(query.get(0).getEmail());
        tempUser.setName(query.get(0).getName());
        tempUser.setbLogin(query.get(0).isbLogin());
        tempUser.setPassword( query.get(0).getPassword());
        tempUser.setLastLoginDate( query.get(0).getLastLoginDate());*/

        return query.get(0);
    }

    // 이메일 검색결과 가져오기
    private RealmResults<LocalLoginUser> getRealmResult( Realm realm, String email){
        return realm.where(LocalLoginUser.class)
                        .equalTo("email", email)
                        .findAll();
    }
    private RealmResults<LocalLoginUser> getRealmResult( Realm realm){
        return realm.where(LocalLoginUser.class)
                .findAll();
    }


}
