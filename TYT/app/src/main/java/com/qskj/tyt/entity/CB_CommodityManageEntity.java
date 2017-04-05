package com.qskj.tyt.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 商品管理-商品列表实体，商品详情实体
 */
public class CB_CommodityManageEntity {

    /**
     * pageIndex : 1
     * pageSize : 10
     * rows : [{"commodityAttribute":{"categoryCode":"27000000","categoryId":3085915,"categoryName":"其他物品","commodityBrand":"332","id":3140100,"isPublishToElecplt":false,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"specifications":"32"},"commodityNameCn":"3232","commodityNameEn":"3232","commodityPrice":{"effectiveFlag":false,"id":3140100,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"unit":"035","unitCn":"千克","unitEn":"KGS","unitId":102},"commodityPriceId":3140300,"customsRecordNo":"3114151001700209","hsCode":"9505100090","id":3140100,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"relatedCompanyName":"宁波联合集团进出口股份有限公司","retRate":0.13,"status":4,"statusView":"已备案","unitDetail":{"cn":"千克","code":"035","en":"KGS","id":102},"vatRate":0.17},{"commodityAttribute":{"categoryCode":"27000000","categoryId":3085915,"categoryName":"其他物品","commodityBrand":"手套","id":3137200,"isPublishToElecplt":false,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"specifications":"200"},"commodityNameCn":"手套","commodityNameEn":"手套","commodityPrice":{"effectiveFlag":false,"id":3137200,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"unit":"035","unitCn":"千克","unitEn":"KGS","unitId":102},"commodityPriceId":3137400,"customsRecordNo":"3114151001700204","hsCode":"6116990093","id":3137200,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"relatedCompanyName":"宁波联合集团进出口股份有限公司","retRate":0.17,"status":4,"statusView":"已备案","unitDetail":{"cn":"千克","code":"035","en":"KGS","id":102},"vatRate":0.17},{"commodityAttribute":{"categoryCode":"27000000","categoryId":3085915,"categoryName":"其他物品","commodityBrand":"无","id":3136001,"isPublishToElecplt":false,"materialQuality":"竹子","placeOfOrigin":"中国","relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"specifications":"无"},"commodityNameCn":"竹制工艺品（竹扫把）","commodityNameEn":"竹制工艺品（竹扫把）","commodityPrice":{"effectiveFlag":false,"id":3136001,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"unit":"007","unitCn":"个","unitEn":"PCS","unitId":67},"commodityPriceId":3136201,"customsRecordNo":"3114151001700203","hsCode":"4420109090","id":3136001,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"relatedCompanyName":"宁波联合集团进出口股份有限公司","retRate":0.13,"status":4,"statusView":"已备案","unitDetail":{"cn":"个","code":"007","en":"PCS","id":67},"vatRate":0.17},{"commodityAttribute":{"categoryCode":"27000000","categoryId":3085915,"categoryName":"其他物品","commodityBrand":"无","id":3136000,"isPublishToElecplt":false,"materialQuality":"塑料","placeOfOrigin":"中国","relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"specifications":"无"},"commodityNameCn":"节日用品（摆件）","commodityNameEn":"节日用品（摆件）","commodityPrice":{"effectiveFlag":false,"id":3136000,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"unit":"007","unitCn":"个","unitEn":"PCS","unitId":67},"commodityPriceId":3136200,"customsRecordNo":"3114151001700202","hsCode":"9505900000","id":3136000,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"relatedCompanyName":"宁波联合集团进出口股份有限公司","retRate":0.13,"status":4,"statusView":"已备案","unitDetail":{"cn":"个","code":"007","en":"PCS","id":67},"vatRate":0.17},{"commodityAttribute":{"categoryCode":"27000000","categoryId":3085915,"categoryName":"其他物品","commodityBrand":"无","id":3133403,"isPublishToElecplt":false,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"specifications":"无"},"commodityNameCn":"淋浴屏","commodityNameEn":"淋浴屏","commodityPrice":{"effectiveFlag":false,"id":3133403,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"unit":"035","unitCn":"千克","unitEn":"KGS","unitId":102},"commodityPriceId":3133603,"customsRecordNo":"3114151001700200","hsCode":"7610900000","id":3133403,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"relatedCompanyName":"宁波联合集团进出口股份有限公司","retRate":0.13,"status":4,"statusView":"已备案","unitDetail":{"cn":"千克","code":"035","en":"KGS","id":102},"vatRate":0.17},{"commodityAttribute":{"categoryCode":"27000000","categoryId":3085915,"categoryName":"其他物品","commodityBrand":"mesh","id":3133402,"isPublishToElecplt":false,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"specifications":"46x56"},"commodityNameCn":"网眼袋","commodityNameEn":"网眼袋","commodityPrice":{"effectiveFlag":false,"id":3133402,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"unit":"015","unitCn":"条","unitEn":"PCS","unitId":92},"commodityPriceId":3133602,"customsRecordNo":"3114151001700199","hsCode":"6305330010","id":3133402,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"relatedCompanyName":"宁波联合集团进出口股份有限公司","retRate":0.17,"status":4,"statusView":"已备案","unitDetail":{"cn":"条","code":"015","en":"PCS","id":92},"vatRate":0.17},{"commodityAttribute":{"categoryCode":"27000000","categoryId":3085915,"categoryName":"其他物品","commodityBrand":"manshi","id":3133401,"isPublishToElecplt":false,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"specifications":"43x56"},"commodityNameCn":"网眼袋","commodityNameEn":"网眼袋","commodityPrice":{"effectiveFlag":false,"id":3133401,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"unit":"015","unitCn":"条","unitEn":"PCS","unitId":92},"commodityPriceId":3133601,"customsRecordNo":"3114151001700198","hsCode":"6305330010","id":3133401,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"relatedCompanyName":"宁波联合集团进出口股份有限公司","retRate":0.17,"status":4,"statusView":"已备案","unitDetail":{"cn":"条","code":"015","en":"PCS","id":92},"vatRate":0.17},{"commodityAttribute":{"categoryCode":"27000000","categoryId":3085915,"categoryName":"其他物品","commodityBrand":"lianhe","id":3133400,"isPublishToElecplt":false,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"specifications":"42X58"},"commodityNameCn":"WANGYANDAI","commodityNameEn":"WANGYANDAI","commodityPrice":{"effectiveFlag":false,"id":3133400,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"unit":"015","unitCn":"条","unitEn":"PCS","unitId":92},"commodityPriceId":3133600,"customsRecordNo":"3114151001700197","hsCode":"6305330010","id":3133400,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"relatedCompanyName":"宁波联合集团进出口股份有限公司","retRate":0.17,"status":4,"statusView":"已备案","unitDetail":{"cn":"条","code":"015","en":"PCS","id":92},"vatRate":0.17},{"commodityAttribute":{"categoryCode":"27000000","categoryId":3085915,"categoryName":"其他物品","commodityBrand":"无","id":3131800,"isPublishToElecplt":false,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"specifications":"无"},"commodityNameCn":"节日用品","commodityNameEn":"节日用品","commodityPrice":{"effectiveFlag":false,"id":3131800,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"unit":"007","unitCn":"个","unitEn":"PCS","unitId":67},"commodityPriceId":3132000,"customsRecordNo":"3114151001700196","hsCode":"9505900000","id":3131800,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"relatedCompanyName":"宁波联合集团进出口股份有限公司","retRate":0.13,"status":4,"statusView":"已备案","unitDetail":{"cn":"个","code":"007","en":"PCS","id":67},"vatRate":0.17},{"commodityAttribute":{"categoryCode":"27000000","categoryId":3085915,"categoryName":"其他物品","commodityBrand":"无","id":3129100,"isPublishToElecplt":false,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"specifications":"无"},"commodityNameCn":"线盘","commodityNameEn":"线盘","commodityPrice":{"effectiveFlag":false,"id":3129100,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"unit":"007","unitCn":"个","unitEn":"PCS","unitId":67},"commodityPriceId":3129300,"customsRecordNo":"3114151001700194","hsCode":"9505900000","id":3129100,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"relatedCompanyName":"宁波联合集团进出口股份有限公司","retRate":0.13,"status":4,"statusView":"已备案","unitDetail":{"cn":"个","code":"007","en":"PCS","id":67},"vatRate":0.17}]
     * total : 50
     * totalPage : 5
     */

    private int pageIndex;
    private int pageSize;
    private int total;
    private int totalPage;
    /**
     * commodityAttribute : {"categoryCode":"27000000","categoryId":3085915,"categoryName":"其他物品",
     * "commodityBrand":"332","id":3140100,"isPublishToElecplt":false,"relatedCompanyCommodityId":0,"relatedCompanyId":3010000,"specifications":"32"}
     * commodityNameCn : 3232
     * commodityNameEn : 3232
     * commodityPrice : {"effectiveFlag":false,"id":3140100,"relatedCompanyCommodityId":0,
     * "relatedCompanyId":3010000,"unit":"035","unitCn":"千克","unitEn":"KGS","unitId":102}
     * commodityPriceId : 3140300
     * customsRecordNo : 3114151001700209
     * hsCode : 9505100090
     * id : 3140100
     * relatedCompanyCommodityId : 0
     * relatedCompanyId : 3010000
     * relatedCompanyName : 宁波联合集团进出口股份有限公司
     * retRate : 0.13
     * status : 4
     * statusView : 已备案
     * unitDetail : {"cn":"千克","code":"035","en":"KGS","id":102}
     * vatRate : 0.17
     */

    private List<RowsEntity> rows;

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setRows(List<RowsEntity> rows) {
        this.rows = rows;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotal() {
        return total;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public List<RowsEntity> getRows() {
        return rows;
    }

    public static class RowsEntity implements Serializable {
        /**
         * categoryCode : 27000000
         * categoryId : 3085915
         * categoryName : 其他物品
         * commodityBrand : 332
         * id : 3140100
         * isPublishToElecplt : false
         * relatedCompanyCommodityId : 0
         * relatedCompanyId : 3010000
         * specifications : 32
         */

        private CommodityAttributeEntity commodityAttribute;
        private String commodityNameCn;
        private String commodityNameEn;
        /**
         * effectiveFlag : false
         * id : 3140100
         * relatedCompanyCommodityId : 0
         * relatedCompanyId : 3010000
         * unit : 035
         * unitCn : 千克
         * unitEn : KGS
         * unitId : 102
         */

        private CommodityPriceEntity commodityPrice;
        private int commodityPriceId;
        private String customsRecordNo;
        private String hsCode;
        private int id;
        private int relatedCompanyCommodityId;
        private int relatedCompanyId;
        private String relatedCompanyName;
        private double retRate;
        private int status;
        private String statusView;
        /**
         * cn : 千克
         * code : 035
         * en : KGS
         * id : 102
         */

        private UnitDetailEntity unitDetail;
        private double vatRate;

        public void setCommodityAttribute(CommodityAttributeEntity commodityAttribute) {
            this.commodityAttribute = commodityAttribute;
        }

        public void setCommodityNameCn(String commodityNameCn) {
            this.commodityNameCn = commodityNameCn;
        }

        public void setCommodityNameEn(String commodityNameEn) {
            this.commodityNameEn = commodityNameEn;
        }

        public void setCommodityPrice(CommodityPriceEntity commodityPrice) {
            this.commodityPrice = commodityPrice;
        }

        public void setCommodityPriceId(int commodityPriceId) {
            this.commodityPriceId = commodityPriceId;
        }

        public void setCustomsRecordNo(String customsRecordNo) {
            this.customsRecordNo = customsRecordNo;
        }

        public void setHsCode(String hsCode) {
            this.hsCode = hsCode;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setRelatedCompanyCommodityId(int relatedCompanyCommodityId) {
            this.relatedCompanyCommodityId = relatedCompanyCommodityId;
        }

        public void setRelatedCompanyId(int relatedCompanyId) {
            this.relatedCompanyId = relatedCompanyId;
        }

        public void setRelatedCompanyName(String relatedCompanyName) {
            this.relatedCompanyName = relatedCompanyName;
        }

        public void setRetRate(double retRate) {
            this.retRate = retRate;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setStatusView(String statusView) {
            this.statusView = statusView;
        }

        public void setUnitDetail(UnitDetailEntity unitDetail) {
            this.unitDetail = unitDetail;
        }

        public void setVatRate(double vatRate) {
            this.vatRate = vatRate;
        }

        public CommodityAttributeEntity getCommodityAttribute() {
            return commodityAttribute;
        }

        public String getCommodityNameCn() {
            return commodityNameCn;
        }

        public String getCommodityNameEn() {
            return commodityNameEn;
        }

        public CommodityPriceEntity getCommodityPrice() {
            return commodityPrice;
        }

        public int getCommodityPriceId() {
            return commodityPriceId;
        }

        public String getCustomsRecordNo() {
            return customsRecordNo;
        }

        public String getHsCode() {
            return hsCode;
        }

        public int getId() {
            return id;
        }

        public int getRelatedCompanyCommodityId() {
            return relatedCompanyCommodityId;
        }

        public int getRelatedCompanyId() {
            return relatedCompanyId;
        }

        public String getRelatedCompanyName() {
            return relatedCompanyName;
        }

        public double getRetRate() {
            return retRate;
        }

        public int getStatus() {
            return status;
        }

        public String getStatusView() {
            return statusView;
        }

        public UnitDetailEntity getUnitDetail() {
            return unitDetail;
        }

        public double getVatRate() {
            return vatRate;
        }

        public static class CommodityAttributeEntity implements Serializable {
            private String categoryCode;
            private int categoryId;
            private String categoryName;
            private String commodityBrand;
            private int id;
            private boolean isPublishToElecplt;
            private int relatedCompanyCommodityId;
            private int relatedCompanyId;
            private String specifications;
            private String supervisionCondition;

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            private String description;

            public String getPlaceOfOrigin() {
                return placeOfOrigin;
            }

            public void setPlaceOfOrigin(String placeOfOrigin) {
                this.placeOfOrigin = placeOfOrigin;
            }

            private String placeOfOrigin;

            public String getMaterialQuality() {
                return materialQuality;
            }

            public void setMaterialQuality(String materialQuality) {
                this.materialQuality = materialQuality;
            }

            private String materialQuality;

            public boolean isPublishToElecplt() {
                return isPublishToElecplt;
            }

            public String getSupervisionCondition() {
                return supervisionCondition;
            }

            public void setSupervisionCondition(String supervisionCondition) {
                this.supervisionCondition = supervisionCondition;
            }

            public void setCategoryCode(String categoryCode) {
                this.categoryCode = categoryCode;
            }

            public void setCategoryId(int categoryId) {
                this.categoryId = categoryId;
            }

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
            }

            public void setCommodityBrand(String commodityBrand) {
                this.commodityBrand = commodityBrand;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setIsPublishToElecplt(boolean isPublishToElecplt) {
                this.isPublishToElecplt = isPublishToElecplt;
            }

            public void setRelatedCompanyCommodityId(int relatedCompanyCommodityId) {
                this.relatedCompanyCommodityId = relatedCompanyCommodityId;
            }

            public void setRelatedCompanyId(int relatedCompanyId) {
                this.relatedCompanyId = relatedCompanyId;
            }

            public void setSpecifications(String specifications) {
                this.specifications = specifications;
            }

            public String getCategoryCode() {
                return categoryCode;
            }

            public int getCategoryId() {
                return categoryId;
            }

            public String getCategoryName() {
                return categoryName;
            }

            public String getCommodityBrand() {
                return commodityBrand;
            }

            public int getId() {
                return id;
            }

            public boolean isIsPublishToElecplt() {
                return isPublishToElecplt;
            }

            public int getRelatedCompanyCommodityId() {
                return relatedCompanyCommodityId;
            }

            public int getRelatedCompanyId() {
                return relatedCompanyId;
            }

            public String getSpecifications() {
                return specifications;
            }
        }

        public static class CommodityPriceEntity implements Serializable {
            private boolean effectiveFlag;
            private int id;
            private int relatedCompanyCommodityId;
            private int relatedCompanyId;
            private String unit;
            private String unitCn;
            private String unitEn;
            private double price;
            private String priceCurrency;
            private int unitId;

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getPriceCurrency() {
                return priceCurrency;
            }

            public void setPriceCurrency(String priceCurrency) {
                this.priceCurrency = priceCurrency;
            }

            public void setEffectiveFlag(boolean effectiveFlag) {
                this.effectiveFlag = effectiveFlag;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setRelatedCompanyCommodityId(int relatedCompanyCommodityId) {
                this.relatedCompanyCommodityId = relatedCompanyCommodityId;
            }

            public void setRelatedCompanyId(int relatedCompanyId) {
                this.relatedCompanyId = relatedCompanyId;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public void setUnitCn(String unitCn) {
                this.unitCn = unitCn;
            }

            public void setUnitEn(String unitEn) {
                this.unitEn = unitEn;
            }

            public void setUnitId(int unitId) {
                this.unitId = unitId;
            }

            public boolean isEffectiveFlag() {
                return effectiveFlag;
            }

            public int getId() {
                return id;
            }

            public int getRelatedCompanyCommodityId() {
                return relatedCompanyCommodityId;
            }

            public int getRelatedCompanyId() {
                return relatedCompanyId;
            }

            public String getUnit() {
                return unit;
            }

            public String getUnitCn() {
                return unitCn;
            }

            public String getUnitEn() {
                return unitEn;
            }

            public int getUnitId() {
                return unitId;
            }
        }

        public static class UnitDetailEntity implements Serializable {
            private String cn;
            private String code;
            private String en;
            private int id;

            public void setCn(String cn) {
                this.cn = cn;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public void setEn(String en) {
                this.en = en;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCn() {
                return cn;
            }

            public String getCode() {
                return code;
            }

            public String getEn() {
                return en;
            }

            public int getId() {
                return id;
            }
        }
    }
}
