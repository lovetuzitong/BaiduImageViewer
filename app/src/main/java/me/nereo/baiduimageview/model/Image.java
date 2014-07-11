package me.nereo.baiduimageview.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2014-07-07.
 */
public class Image implements Serializable{

    private String id;
    private String desc;
    private String[] tags;
    private Owner owner;
    private String fromPageTitle;
    private String column;
    private String parentTag;
    private String date;
    private String downloadUrl;
    private String imageUrl;
    private Long imageWidth;
    private Long imageHeight;
    private String thumbnailUrl;
    private Long thumbnailWidth;
    private Long thumbnailHeight;
    private Long thumbLargeWidth;
    private Long thumbLargeHeight;
    private String thumbLargeUrl;
    private String siteName;
    private String siteLogo;
    private String siteUrl;
    private String fromUrl;
    private String isBook;
    private String bookId;
    private String objUrl;
    private String shareUrl;
    private String setId;
    private String albumId;
    private Long isAlbum;
    private Long albumNum;
    private String userId;
    private Long isVip;
    private Long isDapei;
    private String dressId;
    private String dressBuyLink;
    private Long dressPrice;
    private Long dressDiscount;
    private String dressExtInfo;
    private String dressTag;
    private Long dressNum;
    private String objTag;
    private Long dressImgNum;
    private String hostName;
    private String pictureId;
    private String pictureSign;
    private String dataSrc;
    private String contentSign;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getFromPageTitle() {
        return fromPageTitle;
    }

    public void setFromPageTitle(String fromPageTitle) {
        this.fromPageTitle = fromPageTitle;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getParentTag() {
        return parentTag;
    }

    public void setParentTag(String parentTag) {
        this.parentTag = parentTag;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(Long imageWidth) {
        this.imageWidth = imageWidth;
    }

    public Long getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(Long imageHeight) {
        this.imageHeight = imageHeight;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Long getThumbnailWidth() {
        return thumbnailWidth;
    }

    public void setThumbnailWidth(Long thumbnailWidth) {
        this.thumbnailWidth = thumbnailWidth;
    }

    public Long getThumbnailHeight() {
        return thumbnailHeight;
    }

    public void setThumbnailHeight(Long thumbnailHeight) {
        this.thumbnailHeight = thumbnailHeight;
    }

    public Long getThumbLargeWidth() {
        return thumbLargeWidth;
    }

    public void setThumbLargeWidth(Long thumbLargeWidth) {
        this.thumbLargeWidth = thumbLargeWidth;
    }

    public Long getThumbLargeHeight() {
        return thumbLargeHeight;
    }

    public void setThumbLargeHeight(Long thumbLargeHeight) {
        this.thumbLargeHeight = thumbLargeHeight;
    }

    public String getThumbLargeUrl() {
        return thumbLargeUrl;
    }

    public void setThumbLargeUrl(String thumbLargeUrl) {
        this.thumbLargeUrl = thumbLargeUrl;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteLogo() {
        return siteLogo;
    }

    public void setSiteLogo(String siteLogo) {
        this.siteLogo = siteLogo;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getFromUrl() {
        return fromUrl;
    }

    public void setFromUrl(String fromUrl) {
        this.fromUrl = fromUrl;
    }

    public String getIsBook() {
        return isBook;
    }

    public void setIsBook(String isBook) {
        this.isBook = isBook;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getObjUrl() {
        return objUrl;
    }

    public void setObjUrl(String objUrl) {
        this.objUrl = objUrl;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public Long getIsAlbum() {
        return isAlbum;
    }

    public void setIsAlbum(Long isAlbum) {
        this.isAlbum = isAlbum;
    }

    public Long getAlbumNum() {
        return albumNum;
    }

    public void setAlbumNum(Long albumNum) {
        this.albumNum = albumNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getIsVip() {
        return isVip;
    }

    public void setIsVip(Long isVip) {
        this.isVip = isVip;
    }

    public Long getIsDapei() {
        return isDapei;
    }

    public void setIsDapei(Long isDapei) {
        this.isDapei = isDapei;
    }

    public String getDressId() {
        return dressId;
    }

    public void setDressId(String dressId) {
        this.dressId = dressId;
    }

    public String getDressBuyLink() {
        return dressBuyLink;
    }

    public void setDressBuyLink(String dressBuyLink) {
        this.dressBuyLink = dressBuyLink;
    }

    public Long getDressPrice() {
        return dressPrice;
    }

    public void setDressPrice(Long dressPrice) {
        this.dressPrice = dressPrice;
    }

    public Long getDressDiscount() {
        return dressDiscount;
    }

    public void setDressDiscount(Long dressDiscount) {
        this.dressDiscount = dressDiscount;
    }

    public String getDressExtInfo() {
        return dressExtInfo;
    }

    public void setDressExtInfo(String dressExtInfo) {
        this.dressExtInfo = dressExtInfo;
    }

    public String getDressTag() {
        return dressTag;
    }

    public void setDressTag(String dressTag) {
        this.dressTag = dressTag;
    }

    public Long getDressNum() {
        return dressNum;
    }

    public void setDressNum(Long dressNum) {
        this.dressNum = dressNum;
    }

    public String getObjTag() {
        return objTag;
    }

    public void setObjTag(String objTag) {
        this.objTag = objTag;
    }

    public Long getDressImgNum() {
        return dressImgNum;
    }

    public void setDressImgNum(Long dressImgNum) {
        this.dressImgNum = dressImgNum;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    public String getPictureSign() {
        return pictureSign;
    }

    public void setPictureSign(String pictureSign) {
        this.pictureSign = pictureSign;
    }

    public String getDataSrc() {
        return dataSrc;
    }

    public void setDataSrc(String dataSrc) {
        this.dataSrc = dataSrc;
    }

    public String getContentSign() {
        return contentSign;
    }

    public void setContentSign(String contentSign) {
        this.contentSign = contentSign;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
