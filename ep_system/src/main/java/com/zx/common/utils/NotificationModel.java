package com.zx.common.utils;

import com.zx.system.model.SysNotification;

import java.util.List;

/**
 * Class description
 *
 * @author V.E.
 * @version 1.0, 17/03/27
 */
public class NotificationModel {

    /** Field description */
    private Integer allCount;

    /** Field description */
    private Integer noticesCount;

    /** Field description */
    private Integer surveysCount;

    /** Field description */
    private Integer appliesCount;

    /** Field description */
    private List<SysNotification> notices;

    /** Field description */
    private List<SysNotification> surveys;

    /** Field description */
    private List<SysNotification> applies;

    /**
     * Method description
     *
     *
     * @return
     */
    public List<SysNotification> getNotices() {
        return notices;
    }

    /**
     * Method description
     *
     *
     * @param notices
     */
    public void setNotices(List<SysNotification> notices) {
        this.notices = notices;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public List<SysNotification> getSurveys() {
        return surveys;
    }

    /**
     * Method description
     *
     *
     * @param surveys
     */
    public void setSurveys(List<SysNotification> surveys) {
        this.surveys = surveys;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public List<SysNotification> getApplies() {
        return applies;
    }

    /**
     * Method description
     *
     *
     * @param applies
     */
    public void setApplies(List<SysNotification> applies) {
        this.applies = applies;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getAppliesCount() {
        return appliesCount;
    }

    /**
     * Method description
     *
     *
     * @param appliesCount
     */
    public void setAppliesCount(Integer appliesCount) {
        this.appliesCount = appliesCount;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getNoticesCount() {
        return noticesCount;
    }

    /**
     * Method description
     *
     *
     * @param noticesCount
     */
    public void setNoticesCount(Integer noticesCount) {
        this.noticesCount = noticesCount;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getSurveysCount() {
        return surveysCount;
    }

    /**
     * Method description
     *
     *
     * @param surveysCount
     */
    public void setSurveysCount(Integer surveysCount) {
        this.surveysCount = surveysCount;
    }

    public Integer getAllCount() {
        return allCount;
    }

    public void setAllCount(Integer allCount) {
        this.allCount = allCount;
    }
}


