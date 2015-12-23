package com.wenwoandroidnew.system.model.query;

/**
 * Created by SeungJin on 2015-11-05.
 */
public class ModelLoginQuery {

    public String email;

    public String passworrd;

    public String registerationId;

    public ModelLoginQuery(){}

    public ModelLoginQuery(String email, String passworrd) {
        this.email = email;
        this.passworrd = passworrd;
    }

    public ModelLoginQuery(String email, String passworrd, String registerationId) {
        this.email = email;
        this.passworrd = passworrd;
        this.registerationId = registerationId;
    }

    @Override
    public String toString() {
        return "ModelLoginQuery{" +
                "email='" + email + '\'' +
                ", name='" + passworrd + '\'' +
                '}';
    }
}
