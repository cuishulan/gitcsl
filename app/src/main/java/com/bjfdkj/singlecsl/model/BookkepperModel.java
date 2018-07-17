package com.bjfdkj.singlecsl.model;

import java.util.List;

/**
 * Created by Administrator on 2018/5/29 0029.
 *
 * Bookkeeper实体
 */

public class BookkepperModel {
    public static class BookkeeperInfo {
        private String name;//组织机构管理
        private List<BokkeeperInfo2> list;// 下一级

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<BokkeeperInfo2> getList() {
            return list;
        }

        public void setList(List<BokkeeperInfo2> list) {
            this.list = list;
        }
    }
    //每一个子级
    public static class BokkeeperInfo2 {
        private String name2; //部门管理
        private List<BookkeeperInfo3> list2;//下一级

        public String getName2() {
            return name2;
        }

        public void setName2(String name2) {
            this.name2 = name2;
        }

        public List<BookkeeperInfo3> getList2() {
            return list2;
        }

        public void setList2(List<BookkeeperInfo3> list2) {
            this.list2 = list2;
        }
    }

    public static class BookkeeperInfo3 {
        private String name3;//部门管理个数

        public String getName3() {
            return name3;
        }

        public void setName3(String name3) {
            this.name3 = name3;
        }
    }

}
