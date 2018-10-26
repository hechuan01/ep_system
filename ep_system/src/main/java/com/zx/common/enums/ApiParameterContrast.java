package com.zx.common.enums;

/**
 * Created by wang on 2017/3/16.
 */
public enum ApiParameterContrast {

    //IspUserInfos
    //username,email,mobile,city,id, continent, accountid
    u_name("username", "u_name"),
    u_email("email", "u_email"),
    u_mobile("mobile", "u_mobile"),
    u_city("city", "u_city"),
    u_id("id", "u_id"),
    u_continent("continent", "u_continent"),
    u_accountid("accountid", "u_accountid"),

    //IspDialRecords
    //accountid,dialType, ipAddress,id
    dr_accountid("accountid", "dr_accountid"),
    dr_dialType("dialType", "dr_dialType"),
    dr_ipAddress("ipAddress", "dr_ipAddress"),
    dr_id("id", "dr_id"),

    //IspRouters
    //ip,role,city,id, asnumber
    r_ip("ip", "r_ip"),
    r_role("role", "r_role"),
    r_city("city", "r_city"),
    r_id("id", "r_id"),
    r_asnumber("asnumber", "r_asnumber");

    private String originalParameter;
    private String nowParameter;

    public static String getOriginalParameter(String nowParameter) {
        for (ApiParameterContrast parameterContrast : ApiParameterContrast.values()) {
            if (parameterContrast.getNowParameter().equals(nowParameter)) {
                return parameterContrast.getOriginalParameter();
            }
        }
        return "";
    }

    public static String contrastParameter(String searchText) {
        if (searchText == null || "".equals(searchText)) {
            return searchText;
        }
        searchText = searchText.replaceAll("\\s+?", "");
        for (ApiParameterContrast parameterContrast : ApiParameterContrast.values()) {
            if (searchText.contains(parameterContrast.getNowParameter() + "=")) {
                searchText = searchText.replace(parameterContrast.getNowParameter(), parameterContrast.getOriginalParameter());
            }
        }
        return searchText;
    }


    public static boolean isContainsParameter(String searchText, String prefix) {
        searchText = searchText.replaceAll("\\s+?", "");
        for (ApiParameterContrast parameterContrast : ApiParameterContrast.values()) {
            if (parameterContrast.getNowParameter().startsWith(prefix) && searchText.contains(parameterContrast.getNowParameter() + "=")) {
                return true;
            }
        }
        return false;
    }

    ApiParameterContrast(String originalParameter, String nowParameter) {
        this.originalParameter = originalParameter;
        this.nowParameter = nowParameter;
    }

    public String getOriginalParameter() {
        return originalParameter;
    }

    public void setOriginalParameter(String originalParameter) {
        this.originalParameter = originalParameter;
    }

    public String getNowParameter() {
        return nowParameter;
    }

    public void setNowParameter(String nowParameter) {
        this.nowParameter = nowParameter;
    }
}
