package com.zx.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 类型枚举类
 *
 * @author V.E.
 * @version 1.0, 17/03/17
 */
public class TypeEnums {

    /**
     * 功能模块类型
     */
    public enum ModuleType {
        菜单(0), 功能(1);

        private int value;

        ModuleType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public static String getName(int value) {
            for (ModuleType type : ModuleType.values()) {
                if (type.getValue() == value) {
                    return type.toString();
                }
            }
            return "";
        }
    }

    /**
     * 角色类型
     */
    public enum RoleType {
        普通(2), 内置(1), 超管(0);

        private int value;

        RoleType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public static String getName(int value) {
            for (RoleType type : RoleType.values()) {
                if (type.getValue() == value) {
                    return type.toString();
                }
            }
            return "";
        }
        /**
         * 枚举名称转为List
         */
        public static List<SelectItem> toList() {
            List<SelectItem> list = new ArrayList<SelectItem>();
            for (RoleType type : RoleType.values()) {
                SelectItem item = new SelectItem();
                item.setText(type.toString());
                item.setValue(String.valueOf(type.getValue()));
                list.add(item);
            }
            return list;
        }

    }


    /**
     * 通知类型
     */
    public enum NotificationType {
        流程(0), 公告(1), 调研(2);

        private int value;

        NotificationType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public static String getName(int value) {
            for (NotificationType type : NotificationType.values()) {
                if (type.getValue() == value) {
                    return type.toString();
                }
            }
            return "";
        }

        /**
         * 枚举名称转为List
         */
        public static List<SelectItem> toList() {
            List<SelectItem> list = new ArrayList<SelectItem>();
            for (NotificationType type : NotificationType.values()) {
                SelectItem item = new SelectItem();
                item.setText(type.toString());
                item.setValue(String.valueOf(type.getValue()));
                list.add(item);
            }
            return list;
        }
    }
    /**
     * 目标类型
     */
    public enum TargetType {
        个人(0), 部门(1), 角色(2);

        private int value;

        TargetType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public static String getName(int value) {
            for (TargetType type : TargetType.values()) {
                if (type.getValue() == value) {
                    return type.toString();
                }
            }
            return "";
        }
    }


    static class SelectItem {
        public String getText() {
            return Text;
        }

        public void setText(String text) {
            Text = text;
        }

        public String getValue() {
            return Value;
        }

        public void setValue(String value) {
            Value = value;
        }

        private String Text;
        private String Value;
    }

}


