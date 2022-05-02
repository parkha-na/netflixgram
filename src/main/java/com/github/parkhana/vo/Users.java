package com.github.parkhana.vo;

import java.util.Date;
import org.apache.commons.lang.StringUtils;


import lombok.Data;


@Data
public class Users {

	private String id;
    private String name;
    private String email;
    private String nickname;
    private String sns_id;
    private String sns_type;
    private String birthday;
    private String profile_image;
    private String gender;
    private String birthyear;
    private String mobile;
    private String mobile_e164;
    private Date create_date;

    public void put(String key, Object value) {
        if (StringUtils.equalsIgnoreCase(key, "id")) {
            setId((String) value);
        }
        if (StringUtils.equalsIgnoreCase(key, "name")) {
            setName((String) value);
        }
        if (StringUtils.equalsIgnoreCase(key, "email")) {
            setEmail((String) value);
        }
        if (StringUtils.equalsIgnoreCase(key, "nickname")) {
            setNickname((String) value);
        }
        if (StringUtils.equalsIgnoreCase(key, "id") || StringUtils.equalsIgnoreCase(key, "sns_id")) {
            setSns_id((String) value);
        }
        if (StringUtils.equalsIgnoreCase(key, "sns_type")) {
            setSns_type((String) value);
        }
        if (StringUtils.equalsIgnoreCase(key, "birthday")) {
            setBirthday((String) value);
        }
        if (StringUtils.equalsIgnoreCase(key, "profile_image")) {
            setProfile_image((String) value);
        }
        if (StringUtils.equalsIgnoreCase(key, "gender")) {
            setGender((String) value);
        }
        if (StringUtils.equalsIgnoreCase(key, "birthyear")) {
            setBirthyear((String) value);
        }
        if (StringUtils.equalsIgnoreCase(key, "mobile")) {
            setMobile((String) value);
        }
        if (StringUtils.equalsIgnoreCase(key, "mobile_e164")) {
            setMobile_e164((String) value);
        }
        if (StringUtils.equalsIgnoreCase(key, "create_date")) {
            setCreate_date((Date) value);
        }
    }
}
