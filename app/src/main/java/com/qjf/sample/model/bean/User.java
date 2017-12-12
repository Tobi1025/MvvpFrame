package com.qjf.sample.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by qiaojingfei on 2017/11/16.
 */

public class User implements Parcelable {
    private String id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public static final Creator<User> CREATOR = new Creator<User>() {
        //这个接口实现了从Percel容器读取Person数据，并返回Person对象给逻辑层使用
        //.实现Parcelable.Creator接口对象名必须为CREATOR，不如同样会报错上面所提到的错；
        //在读取Parcel容器里的数据事，必须按成员变量声明的顺序读取数据，不然会出现获取数据出错
        @Override
        public User createFromParcel(Parcel in) {
            User user = new User();
            user.setId(in.readString());
            user.setName(in.readString());
            return user;
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //  TODO Auto-generated method stub
        // 1.必须按成员变量声明的顺序封装数据，不然会出现获取数据出错
        // 2.序列化对象
        dest.writeString(id);
        dest.writeString(name);
    }
}
