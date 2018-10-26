package com.zx.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feidengke on 2017/3/18.
 */
public class FlowEnum {
    public enum ApplyState {
        删除(-1), 待审批(0), 待配发(1), 待确认(2), 已驳回(3), 已完成(4);
        private int value;

        ApplyState(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public static String getName(int value) {
            for (com.zx.common.enums.FlowEnum.ApplyState type : com.zx.common.enums.FlowEnum.ApplyState.values()) {
                if (type.getValue() == value) {
                    return type.toString();
                }
            }
            return "";
        }

        public static List<SelectItem> toList() {
            List<SelectItem> list = new ArrayList<SelectItem>();
            SelectItem allitem = new SelectItem();
            allitem.setText("==全部申请状态==");
            list.add(allitem);
            for (ApplyState type : ApplyState.values()) {
                SelectItem item = new SelectItem();
                item.setText(type.toString());
                item.setValue(String.valueOf(type.getValue()));
                list.add(item);
            }

            return list;
        }
    }

    /*1军火使用申请2军火需求申请3运营商数据*/
    public enum ApplyType {
        军火使用申请(1), 军火需求申请(2), 运营商数据申请(3);
        private int value;

        ApplyType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public static String getName(int value) {
            for (com.zx.common.enums.FlowEnum.ApplyType type : com.zx.common.enums.FlowEnum.ApplyType.values()) {
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
            SelectItem allitem = new SelectItem();
            allitem.setText("==全部申请类别==");
            list.add(allitem);
            for (ApplyType type : ApplyType.values()) {
                SelectItem item = new SelectItem();
                item.setText(type.toString());
                item.setValue(String.valueOf(type.getValue()));
                list.add(item);
            }
            return list;
        }

    }
    /*1军火使用申请2军火需求申请3运营商数据*/
    public enum AssignState {
        删除(-1), 待确认(0), 已确认(1);
        private int value;

        AssignState(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public static String getName(int value) {
            for (com.zx.common.enums.FlowEnum.AssignState type : com.zx.common.enums.FlowEnum.AssignState.values()) {
                if (type.getValue() == value) {
                    return type.toString();
                }
            }
            return "";
        }

    }
    /*1军火使用申请2军火需求申请3运营商数据*/
    public enum StepType {
        审批(1), 确认(2), 特殊操作(3);
        private int value;

        StepType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public static String getName(int value) {
            for (com.zx.common.enums.FlowEnum.StepType type : com.zx.common.enums.FlowEnum.StepType.values()) {
                if (type.getValue() == value) {
                    return type.toString();
                }
            }
            return "";
        }

    }
    public enum RecordState {
        删除(-1),待处理(0),已确认(1),已同意(2),已驳回(3),已召回(4);
        private int value;

        RecordState(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public static String getName(int value) {
            for (com.zx.common.enums.FlowEnum.RecordState type : com.zx.common.enums.FlowEnum.RecordState.values()) {
                if (type.getValue() == value) {
                    return type.toString();
                }
            }
            return "";
        }

    }

    public enum ApplyAssignType {
       IM(1),密讯(2);
        private int value;

        ApplyAssignType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public static String getName(int value) {
            for (com.zx.common.enums.FlowEnum.ApplyAssignType type : com.zx.common.enums.FlowEnum.ApplyAssignType.values()) {
                if (type.getValue() == value) {
                    return type.toString();
                }
            }
            return "";
        }

        public static List<SelectItem> toList() {
            List<SelectItem> list = new ArrayList<SelectItem>();
            for (ApplyAssignType type : ApplyAssignType.values()) {
                SelectItem item = new SelectItem();
                item.setText(type.toString());
                item.setValue(String.valueOf(type.getValue()));
                list.add(item);
            }
            return list;
        }

    }

}
