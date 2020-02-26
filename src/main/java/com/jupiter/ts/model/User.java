package com.jupiter.ts.model;

public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_user.id
     *
     * @mbg.generated Mon Nov 18 18:25:50 GMT+08:00 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_user.username
     *
     * @mbg.generated Mon Nov 18 18:25:50 GMT+08:00 2019
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_user.password
     *
     * @mbg.generated Mon Nov 18 18:25:50 GMT+08:00 2019
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_user.phone
     *
     * @mbg.generated Mon Nov 18 18:25:50 GMT+08:00 2019
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_user.email
     *
     * @mbg.generated Mon Nov 18 18:25:50 GMT+08:00 2019
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_user.us_create
     *
     * @mbg.generated Mon Nov 18 18:25:50 GMT+08:00 2019
     */
    private Long usCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_user.us_modified
     *
     * @mbg.generated Mon Nov 18 18:25:50 GMT+08:00 2019
     */
    private Long usModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_user.id
     *
     * @return the value of ts_user.id
     *
     * @mbg.generated Mon Nov 18 18:25:50 GMT+08:00 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_user.id
     *
     * @param id the value for ts_user.id
     *
     * @mbg.generated Mon Nov 18 18:25:50 GMT+08:00 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_user.username
     *
     * @return the value of ts_user.username
     *
     * @mbg.generated Mon Nov 18 18:25:50 GMT+08:00 2019
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_user.username
     *
     * @param username the value for ts_user.username
     *
     * @mbg.generated Mon Nov 18 18:25:50 GMT+08:00 2019
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_user.password
     *
     * @return the value of ts_user.password
     *
     * @mbg.generated Mon Nov 18 18:25:50 GMT+08:00 2019
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_user.password
     *
     * @param password the value for ts_user.password
     *
     * @mbg.generated Mon Nov 18 18:25:50 GMT+08:00 2019
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_user.phone
     *
     * @return the value of ts_user.phone
     *
     * @mbg.generated Mon Nov 18 18:25:50 GMT+08:00 2019
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_user.phone
     *
     * @param phone the value for ts_user.phone
     *
     * @mbg.generated Mon Nov 18 18:25:50 GMT+08:00 2019
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_user.email
     *
     * @return the value of ts_user.email
     *
     * @mbg.generated Mon Nov 18 18:25:50 GMT+08:00 2019
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_user.email
     *
     * @param email the value for ts_user.email
     *
     * @mbg.generated Mon Nov 18 18:25:50 GMT+08:00 2019
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_user.us_create
     *
     * @return the value of ts_user.us_create
     *
     * @mbg.generated Mon Nov 18 18:25:50 GMT+08:00 2019
     */
    public Long getUsCreate() {
        return usCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_user.us_create
     *
     * @param usCreate the value for ts_user.us_create
     *
     * @mbg.generated Mon Nov 18 18:25:50 GMT+08:00 2019
     */
    public void setUsCreate(Long usCreate) {
        this.usCreate = usCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_user.us_modified
     *
     * @return the value of ts_user.us_modified
     *
     * @mbg.generated Mon Nov 18 18:25:50 GMT+08:00 2019
     */
    public Long getUsModified() {
        return usModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_user.us_modified
     *
     * @param usModified the value for ts_user.us_modified
     *
     * @mbg.generated Mon Nov 18 18:25:50 GMT+08:00 2019
     */
    public void setUsModified(Long usModified) {
        this.usModified = usModified;
    }
}