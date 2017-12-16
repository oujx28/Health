package com.luckystar.health.db.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;


/**
 * Created by Administrator on 2017/9/14.
 */
@Entity
public class UserInfo {
    @Id(autoincrement = true)
    private Long id;// 自增Id（主键）
    private String user_id;// 用户Id（null即未注册）
    @NotNull
    private String user_name;// 用户名
    private String head_img;// 头像
    @NotNull
    private String sex;// 性别
    private int age; // 年龄
    private Integer height;// 身高
    private Integer weight;// 体重
    private String address;// 地址
    private String address_code;// 地址编码
    private String phone;// 手机
    private String blood_type_id;// 血型Id
    private String blood_type;// 血型
    private String allergy_his_id;// 药物过敏史Id
    private String allergy_his;// 药物过敏史
    private String past_his_id;// 既往病史Id
    private String past_his;// 既往病史
    private String genetic_his_id;// 遗传病史Id
    private String genetic_his;// 遗传病史
    private String problem;// 健康问题
    @NotNull
    private String is_perfect;// 是否完善：0.未完善；1.已完善
    @NotNull
    private String is_delete;// 是否删除：0.未删除；1.已删除
    @NotNull
    private String is_binding;// 是否绑定设备：0.未绑定；1.已绑定
    @NotNull
    private String is_modify; // 数据表是否修改:0.未修改 : 1. 已修改
    @NotNull
    private String create_time;// 创建时间

    public UserInfo(String user_id, @NotNull String user_name,
            String head_img, @NotNull String sex, int age, Integer height,
            Integer weight, String address, String address_code, String phone,
            String blood_type_id, String blood_type, String allergy_his_id,
            String allergy_his, String past_his_id, String past_his,
            String genetic_his_id, String genetic_his, String problem,
            @NotNull String is_perfect, @NotNull String is_delete,
            @NotNull String is_binding, @NotNull String is_modify,
            @NotNull String create_time) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.head_img = head_img;
        this.sex = sex;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.address = address;
        this.address_code = address_code;
        this.phone = phone;
        this.blood_type_id = blood_type_id;
        this.blood_type = blood_type;
        this.allergy_his_id = allergy_his_id;
        this.allergy_his = allergy_his;
        this.past_his_id = past_his_id;
        this.past_his = past_his;
        this.genetic_his_id = genetic_his_id;
        this.genetic_his = genetic_his;
        this.problem = problem;
        this.is_perfect = is_perfect;
        this.is_delete = is_delete;
        this.is_binding = is_binding;
        this.is_modify = is_modify;
        this.create_time = create_time;
    }
    @Generated(hash = 1279772520)
    public UserInfo() {
    }
    @Generated(hash = 1693357228)
    public UserInfo(Long id, String user_id, @NotNull String user_name,
            String head_img, @NotNull String sex, int age, Integer height,
            Integer weight, String address, String address_code, String phone,
            String blood_type_id, String blood_type, String allergy_his_id,
            String allergy_his, String past_his_id, String past_his,
            String genetic_his_id, String genetic_his, String problem,
            @NotNull String is_perfect, @NotNull String is_delete,
            @NotNull String is_binding, @NotNull String is_modify,
            @NotNull String create_time) {
        this.id = id;
        this.user_id = user_id;
        this.user_name = user_name;
        this.head_img = head_img;
        this.sex = sex;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.address = address;
        this.address_code = address_code;
        this.phone = phone;
        this.blood_type_id = blood_type_id;
        this.blood_type = blood_type;
        this.allergy_his_id = allergy_his_id;
        this.allergy_his = allergy_his;
        this.past_his_id = past_his_id;
        this.past_his = past_his;
        this.genetic_his_id = genetic_his_id;
        this.genetic_his = genetic_his;
        this.problem = problem;
        this.is_perfect = is_perfect;
        this.is_delete = is_delete;
        this.is_binding = is_binding;
        this.is_modify = is_modify;
        this.create_time = create_time;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUser_id() {
        return this.user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getUser_name() {
        return this.user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getHead_img() {
        return this.head_img;
    }
    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public Integer getHeight() {
        return this.height;
    }
    public void setHeight(Integer height) {
        this.height = height;
    }
    public Integer getWeight() {
        return this.weight;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress_code() {
        return this.address_code;
    }
    public void setAddress_code(String address_code) {
        this.address_code = address_code;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getBlood_type_id() {
        return this.blood_type_id;
    }
    public void setBlood_type_id(String blood_type_id) {
        this.blood_type_id = blood_type_id;
    }
    public String getBlood_type() {
        return this.blood_type;
    }
    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }
    public String getAllergy_his_id() {
        return this.allergy_his_id;
    }
    public void setAllergy_his_id(String allergy_his_id) {
        this.allergy_his_id = allergy_his_id;
    }
    public String getAllergy_his() {
        return this.allergy_his;
    }
    public void setAllergy_his(String allergy_his) {
        this.allergy_his = allergy_his;
    }
    public String getPast_his_id() {
        return this.past_his_id;
    }
    public void setPast_his_id(String past_his_id) {
        this.past_his_id = past_his_id;
    }
    public String getPast_his() {
        return this.past_his;
    }
    public void setPast_his(String past_his) {
        this.past_his = past_his;
    }
    public String getGenetic_his_id() {
        return this.genetic_his_id;
    }
    public void setGenetic_his_id(String genetic_his_id) {
        this.genetic_his_id = genetic_his_id;
    }
    public String getGenetic_his() {
        return this.genetic_his;
    }
    public void setGenetic_his(String genetic_his) {
        this.genetic_his = genetic_his;
    }
    public String getProblem() {
        return this.problem;
    }
    public void setProblem(String problem) {
        this.problem = problem;
    }
    public String getIs_perfect() {
        return this.is_perfect;
    }
    public void setIs_perfect(String is_perfect) {
        this.is_perfect = is_perfect;
    }
    public String getIs_delete() {
        return this.is_delete;
    }
    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }
    public String getIs_binding() {
        return this.is_binding;
    }
    public void setIs_binding(String is_binding) {
        this.is_binding = is_binding;
    }
    public String getIs_modify() {
        return this.is_modify;
    }
    public void setIs_modify(String is_modify) {
        this.is_modify = is_modify;
    }
    public String getCreate_time() {
        return this.create_time;
    }
    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
