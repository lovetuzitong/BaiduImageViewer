package me.nereo.baiduimageview.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014-07-08.
 */
public class ImageData {
    private String col;
    private String tag;
    private String tag3;
    private String sort;
    private Long totalNum;
    private Long startIndex;
    private Long returnNumber;
    private List<Image> imgs = new ArrayList<Image>();

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Long getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Long startIndex) {
        this.startIndex = startIndex;
    }

    public Long getReturnNumber() {
        return returnNumber;
    }

    public void setReturnNumber(Long returnNumber) {
        this.returnNumber = returnNumber;
    }

    public List<Image> getImgs() {
        return imgs;
    }

    public void setImgs(List<Image> imgs) {
        this.imgs = imgs;
    }
}
