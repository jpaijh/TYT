package com.qskj.tyt.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 管理详情-实体类
 */
public class CB_ManageDetailsEntity implements Serializable {

    /**
     * accountCode : null
     * acceptTime : 2015-11-24T15:19:00
     * completeTime : null
     * submitTime : 2015-11-24T15:19:00
     * createDate : 2015-11-24T15:18:59
     * modifyDate : null
     * settleFlag : false
     * financeFlag : false
     * customerViewed : false
     * systemViewed : false
     * isImportQp : false
     * completedExchange : false
     * completedInvoice : false
     * completedPayment : false
     * rebateStatus : null
     * creatorType : 1
     * orderItems : [{"nodePositionId":null,"nodePositionName":null,"statusCn":"进行中","operable":false,"itemMetaDataName":"货代进仓","operatorId":3010201,"operatorName":"npsel","createDate":"2015-11-24T15:18:59","agreement":[{"id":3139601,"ownerId":3139501,"settlementId":700,"formula":"700#$#","deductType":0}],"nodes":[{"id":2983301,"name":"新制","seq":1,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"录入","positionName":null,"nodeStatus":1,"fallbackFlag":false,"description":"等待经营单位录入进仓单明细和进仓单附带的报关信息","actionStatus":{"id":3139906,"itemId":3139501,"itemMetaDataId":1011,"operatorId":0,"operatorName":null,"operateDate":"2015-11-24T15:19:00","status":"进行中","description":"接收服务项","inner":false},"actionExcuted":true},{"id":2983303,"name":"待录入","seq":2,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"确认","positionName":null,"nodeStatus":0,"fallbackFlag":false,"description":"等待经营单位录入进仓单明细和进仓单附带的报关信息的确认","actionStatus":null,"actionExcuted":false},{"id":2983304,"name":"待确认","seq":3,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"提交","positionName":null,"nodeStatus":0,"fallbackFlag":false,"description":"进仓单商品明细提交备案","actionStatus":null,"actionExcuted":false},{"id":2983305,"name":"待备案","seq":4,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"收货","positionName":null,"nodeStatus":0,"fallbackFlag":false,"description":"等待仓库收货","actionStatus":null,"actionExcuted":false},{"id":2983306,"name":"待收货","seq":5,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"申报","positionName":null,"nodeStatus":0,"fallbackFlag":false,"description":"等待报关行发送申报指令","actionStatus":null,"actionExcuted":false},{"id":3011201,"name":"待申报","seq":6,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"报关","positionName":null,"nodeStatus":0,"fallbackFlag":false,"description":"等待报关行报关操作","actionStatus":null,"actionExcuted":false},{"id":3011204,"name":"待报关","seq":7,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"完成报关","positionName":null,"nodeStatus":0,"fallbackFlag":false,"description":"报关行完成报关","actionStatus":null,"actionExcuted":false}],"curNodeDisplay":null,"attachments":null,"enabled":true,"isUsed":true,"removeFlag":true,"depends":[],"mutexes":[],"id":3139501,"customStatus":null,"ownerId":3139401,"status":1,"subStatus":2983301,"subStatusName":null,"handlingFee":null,"itemMetaDataId":1011,"deductType":null},{"nodePositionId":null,"nodePositionName":null,"statusCn":null,"operable":false,"itemMetaDataName":"出口通关","operatorId":null,"operatorName":null,"createDate":null,"agreement":[],"nodes":[{"id":11,"name":"制单中","seq":1,"createDate":null,"creator":null,"lockChargeRate":false,"actionName":"提交报关","positionName":"客服,业务员","nodeStatus":0,"fallbackFlag":false,"description":"此节点表明目前业务流程处于需要制作单据中，比如制作托运委托书、装箱单、报关单等。录入相关信息后，导出各种单据给报关行，然后点\u201c提交报关\u201d业务流程进入\u201c报关中\u201d。","actionStatus":null,"actionExcuted":false},{"id":12,"name":"报关中","seq":2,"createDate":null,"creator":null,"lockChargeRate":false,"actionName":"报关完成","positionName":"单证员,业务员,客服","nodeStatus":0,"fallbackFlag":false,"description":"此节点表明目前业务流程处于线下报关中。等到线下实际报关完成后，按实际报关的情况调整之前录入的信息后，点\u201c报关完成\u201d业务流程进入\u201c报关完成\u201d阶段。","actionStatus":null,"actionExcuted":false},{"id":14,"name":"报关完成","seq":5,"createDate":null,"creator":null,"lockChargeRate":false,"actionName":"","positionName":"","nodeStatus":0,"fallbackFlag":false,"description":"此节点表明目前业务流程处于报关完成，意味着一般贸易出口通关服务已经全部完成。","actionStatus":null,"actionExcuted":false}],"curNodeDisplay":null,"attachments":null,"enabled":false,"isUsed":false,"removeFlag":true,"depends":[],"mutexes":[1008],"id":0,"customStatus":null,"ownerId":3139401,"status":0,"subStatus":0,"subStatusName":null,"handlingFee":null,"itemMetaDataId":1001,"deductType":null},{"nodePositionId":null,"nodePositionName":null,"statusCn":null,"operable":false,"itemMetaDataName":"退税融资","operatorId":null,"operatorName":null,"createDate":null,"agreement":[],"nodes":[{"id":41,"name":"等待增票中","seq":1,"createDate":null,"creator":null,"lockChargeRate":false,"actionName":"增票收齐","positionName":"财务,客服,业务员","nodeStatus":0,"fallbackFlag":false,"description":"此节点表明目前业务流程处于等待客户开来增票或者已开来等待录入和匹配的阶段。在增票完成录入且与订单匹配后，系统将自动进入下一个业务流程\u201c退税结算中\u201d。","actionStatus":null,"actionExcuted":false},{"id":1893000,"name":"退税结算中","seq":2,"createDate":null,"creator":null,"lockChargeRate":false,"actionName":"结算","positionName":"客服,业务员","nodeStatus":0,"fallbackFlag":false,"description":"此节点表明目前业务流程处于给客户核算退税款的阶段。系统将自动算出手续费、客户退税额，支持修改，确认无误后，点\u201c结算\u201d，业务流程进入\u201c放款准备中\u201d。","actionStatus":null,"actionExcuted":false},{"id":1893002,"name":"放款准备中","seq":4,"createDate":null,"creator":null,"lockChargeRate":false,"actionName":"放款","positionName":"客服,业务员","nodeStatus":0,"fallbackFlag":false,"description":"此节点表明目前业务流程处于准备给客户放退税款阶段。主要是核实之前结算阶段算出的退税金额信息是否正确，如果正确，点\u201c放款\u201d，业务流程进入\u201c款已划入客户资金账户\u201d阶段，此时客户可以在门户上看到此笔款项，随后可以启动实际付款申请流程。","actionStatus":null,"actionExcuted":false},{"id":1893003,"name":"款已划入客户资金账户","seq":5,"createDate":null,"creator":null,"lockChargeRate":false,"actionName":"已退税","positionName":"财务","nodeStatus":0,"fallbackFlag":false,"description":"此节点表明目前退税款已经划入客户资金账户，等待走线下实际退税流程。等线下实际退税流程完成后，维护好实际退税信息后，点击\u201c已退税\u201d，业务流程进入\u201c已退税\u201d。","actionStatus":null,"actionExcuted":false},{"id":1893004,"name":"已退税","seq":6,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"","positionName":"","nodeStatus":0,"fallbackFlag":false,"description":"此节点表明已完成实际退税，意味着整个退税融资服务流程已经完成。","actionStatus":null,"actionExcuted":false}],"curNodeDisplay":null,"attachments":null,"enabled":false,"isUsed":false,"removeFlag":true,"depends":[1001],"mutexes":[],"id":0,"customStatus":null,"ownerId":3139401,"status":0,"subStatus":0,"subStatusName":null,"handlingFee":null,"itemMetaDataId":1004,"deductType":null},{"nodePositionId":null,"nodePositionName":null,"statusCn":null,"operable":false,"itemMetaDataName":"赊销融资","operatorId":null,"operatorName":null,"createDate":null,"agreement":[],"nodes":[{"id":51,"name":"准备出运中","seq":10,"createDate":null,"creator":null,"lockChargeRate":false,"actionName":"提交投保","positionName":"客服,业务员","nodeStatus":0,"fallbackFlag":false,"description":"此节点表明目前业务流程处于工厂已完成备货，甚至货已经拉进码头，等待上船，出运。由于专款专用的要求，此时，需要核实赊销款贷款人的信息，主要是收款单位名称、收款银行及账号信息。等正式出运了，就可以进行线下投保操作了，点\u201c提交投保\u201d，业务流程进入\u201c等待投保中\u201d。","actionStatus":null,"actionExcuted":false},{"id":52,"name":"等待投保中","seq":20,"createDate":null,"creator":null,"lockChargeRate":false,"actionName":"投保完成","positionName":"中信保专员","nodeStatus":0,"fallbackFlag":false,"description":"此节点表明目前业务流程处于线下投保中。等投保完成后，信保专员把实际的投保信息维护进系统后，点击\u201c投保完成\u201d，业务流程进入\u201c核定额度中\u201d。","actionStatus":null,"actionExcuted":false},{"id":53,"name":"核定额度中","seq":30,"createDate":null,"creator":null,"lockChargeRate":false,"actionName":"提交审批","positionName":"客服,业务员","nodeStatus":0,"fallbackFlag":false,"description":"此节点表明目前业务流程处于为客户核定赊销融资款放款额度中。录入拟定汇率、融资比例、保费率等，系统将自动算出预定利息等信息。确认无误后，点击\u201c提交审批\u201d，业务流程进入\u201c额度审核中\u201d。","actionStatus":null,"actionExcuted":false},{"id":54,"name":"额度审核中","seq":40,"createDate":null,"creator":null,"lockChargeRate":false,"actionName":"审批","positionName":"风控部","nodeStatus":0,"fallbackFlag":false,"description":"此节点表明目前业务流程处于风控审核此订单的赊销融资款能够放款。通过，流程进入到\u201c放款准备中\u201d；不通过，流程回退到\u201c核定额度中\u201d。","actionStatus":null,"actionExcuted":false},{"id":1880100,"name":"放款准备中","seq":50,"createDate":null,"creator":null,"lockChargeRate":false,"actionName":"放款","positionName":"客服,业务员","nodeStatus":0,"fallbackFlag":false,"description":"此节点表明目前业务流程处于准备给客户放赊销融资款中。再次确认信息无误后，点击\u201c放款\u201d，业务流程进入\u201c款已划入客户资金账户\u201d阶段，此时客户可以在门户上看到此笔款项，随后可以启动实际付款申请流程。","actionStatus":null,"actionExcuted":false},{"id":1894600,"name":"款已划入客户资金账户","seq":60,"createDate":null,"creator":null,"lockChargeRate":false,"actionName":"还款","positionName":"客服,业务员","nodeStatus":0,"fallbackFlag":false,"description":"此节点表明目前赊销融资款已经划入客户资金账户。等线下收汇完成，就可以进行还款操作。系统将自动算出应付利息，支持修改，通过利息差额，多退少补。完成后，点击\u201c还款\u201d，业务流程进入\u201c已还款\u201d。融资款会从客户的资金账户里进行扣除。","actionStatus":null,"actionExcuted":false},{"id":1900500,"name":"已还款","seq":70,"createDate":null,"creator":null,"lockChargeRate":false,"actionName":"已付尾款","positionName":"业务员,客服","nodeStatus":0,"fallbackFlag":false,"description":"此节点表明融资款已经还完，等待付尾款，系统将自动算出未付货款。然后可以启动尾款付款申请流程。尾款支付完成后，点击\u201c已付尾款\u201d，业务流程进入\u201c已付尾款\u201d。","actionStatus":null,"actionExcuted":false},{"id":2557800,"name":"已付尾款","seq":80,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"","positionName":"业务员,客服","nodeStatus":0,"fallbackFlag":false,"description":"此节点表明目前业务流程处于尾款已经支付完毕，意味着赊销融资服务已经全部完成。","actionStatus":null,"actionExcuted":false}],"curNodeDisplay":null,"attachments":null,"enabled":false,"isUsed":false,"removeFlag":true,"depends":[],"mutexes":[],"id":0,"customStatus":null,"ownerId":3139401,"status":0,"subStatus":0,"subStatusName":null,"handlingFee":null,"itemMetaDataId":1005,"deductType":null},{"nodePositionId":null,"nodePositionName":null,"statusCn":null,"operable":false,"itemMetaDataName":"货代订舱","operatorId":null,"operatorName":null,"createDate":null,"agreement":[],"nodes":[{"id":71,"name":"准备订舱","seq":1,"createDate":null,"creator":null,"lockChargeRate":false,"actionName":"提交订舱","positionName":"客服","nodeStatus":0,"fallbackFlag":false,"description":"","actionStatus":null,"actionExcuted":false},{"id":72,"name":"订舱中","seq":2,"createDate":null,"creator":null,"lockChargeRate":false,"actionName":"订舱完成","positionName":"客服","nodeStatus":0,"fallbackFlag":false,"description":"","actionStatus":null,"actionExcuted":false},{"id":73,"name":"订舱完成","seq":3,"createDate":null,"creator":null,"lockChargeRate":false,"actionName":"","positionName":"","nodeStatus":0,"fallbackFlag":false,"description":"","actionStatus":null,"actionExcuted":false}],"curNodeDisplay":null,"attachments":null,"enabled":false,"isUsed":false,"removeFlag":true,"depends":[],"mutexes":[],"id":0,"customStatus":null,"ownerId":3139401,"status":0,"subStatus":0,"subStatusName":null,"handlingFee":null,"itemMetaDataId":1007,"deductType":null},{"nodePositionId":null,"nodePositionName":null,"statusCn":null,"operable":false,"itemMetaDataName":"市场采购出口通关","operatorId":null,"operatorName":null,"createDate":null,"agreement":[],"nodes":[{"id":2011100,"name":"制单中","seq":1,"createDate":null,"creator":null,"lockChargeRate":false,"actionName":"提交报关","positionName":"客服,业务员","nodeStatus":0,"fallbackFlag":false,"description":"","actionStatus":null,"actionExcuted":false},{"id":2018400,"name":"报关中","seq":2,"createDate":null,"creator":null,"lockChargeRate":false,"actionName":"报关完成","positionName":"单证员","nodeStatus":0,"fallbackFlag":false,"description":"","actionStatus":null,"actionExcuted":false},{"id":2018401,"name":"报关已完成","seq":3,"createDate":null,"creator":null,"lockChargeRate":false,"actionName":"","positionName":"操作员","nodeStatus":0,"fallbackFlag":false,"description":"","actionStatus":null,"actionExcuted":false}],"curNodeDisplay":null,"attachments":null,"enabled":false,"isUsed":false,"removeFlag":true,"depends":[],"mutexes":[1001,1004],"id":0,"customStatus":null,"ownerId":3139401,"status":0,"subStatus":0,"subStatusName":null,"handlingFee":null,"itemMetaDataId":1008,"deductType":null},{"nodePositionId":null,"nodePositionName":null,"statusCn":null,"operable":false,"itemMetaDataName":"资信调查","operatorId":null,"operatorName":null,"createDate":null,"agreement":[],"nodes":[{"id":2659600,"name":"资料准备中","seq":1,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"提交","positionName":"客服","nodeStatus":0,"fallbackFlag":false,"description":"","actionStatus":null,"actionExcuted":false},{"id":2659601,"name":"调查完成","seq":2,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"","positionName":"","nodeStatus":0,"fallbackFlag":false,"description":"","actionStatus":null,"actionExcuted":false}],"curNodeDisplay":null,"attachments":null,"enabled":false,"isUsed":false,"removeFlag":true,"depends":[],"mutexes":[],"id":0,"customStatus":null,"ownerId":3139401,"status":0,"subStatus":0,"subStatusName":null,"handlingFee":null,"itemMetaDataId":1010,"deductType":null}]
     * orderDelivery : {"storageStatus":1,"declareNo":null,"approveDate":null,"generatedDeclarationFlag":false,"generatedDeclarationPersion":0,"generatedDeclarationDate":null,"generatedPreDeclarationFlag":false,"generatedPreDeclarationPersion":0,"generatedPreDeclarationDate":null,"customsAreaEntrySended":false,"customsAreaEntryStatus":0,"nodes":[{"id":2983301,"name":"新制","seq":1,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"录入","positionName":null,"nodeStatus":1,"fallbackFlag":false,"description":"等待经营单位录入进仓单明细和进仓单附带的报关信息","actionStatus":{"id":3139906,"itemId":3139501,"itemMetaDataId":1011,"operatorId":0,"operatorName":null,"operateDate":"2015-11-24T15:19:00","status":"进行中","description":"接收服务项","inner":false},"actionExcuted":true},{"id":2983303,"name":"待录入","seq":2,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"确认","positionName":null,"nodeStatus":0,"fallbackFlag":false,"description":"等待经营单位录入进仓单明细和进仓单附带的报关信息的确认","actionStatus":null,"actionExcuted":false},{"id":2983304,"name":"待确认","seq":3,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"提交","positionName":null,"nodeStatus":0,"fallbackFlag":false,"description":"进仓单商品明细提交备案","actionStatus":null,"actionExcuted":false},{"id":2983305,"name":"待备案","seq":4,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"收货","positionName":null,"nodeStatus":0,"fallbackFlag":false,"description":"等待仓库收货","actionStatus":null,"actionExcuted":false},{"id":2983306,"name":"待收货","seq":5,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"申报","positionName":null,"nodeStatus":0,"fallbackFlag":false,"description":"等待报关行发送申报指令","actionStatus":null,"actionExcuted":false},{"id":3011201,"name":"待申报","seq":6,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"报关","positionName":null,"nodeStatus":0,"fallbackFlag":false,"description":"等待报关行报关操作","actionStatus":null,"actionExcuted":false},{"id":3011204,"name":"待报关","seq":7,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"完成报关","positionName":null,"nodeStatus":0,"fallbackFlag":false,"description":"报关行完成报关","actionStatus":null,"actionExcuted":false}],"businessEnterprise":null,"forwarderConfirmed":false,"id":3139801,"ownerId":3139401,"ordOrderExportId":3139701,"receiverId":0,"receiverName":"NUGIE","receiverContactsId":0,"receiverContacts":null,"receiverContactTellPhone":null,"businessEnterpriseId":0,"businessEnterpriseCode":null,"businessEnterpriseName":null,"businessEnterpriseContact":null,"businessEnterpriseContactTelephone":null,"businessEnterpriseAddress":null,"deliveryEnterpriseId":0,"deliveryEnterpriseCode":null,"deliveryEnterpriseName":null,"declareEnterpriseId":3009702,"declareEnterpriseCode":"3302480040","declareEnterpriseName":"宁波联合报关有限公司","deliveryInvoiceCode":"NUGIE323wwe","customsRecordNo":null,"estimatedSchedual":"2015-11-24","startTimeOfDelivery":"2015-11-22T08:00:00","latestTimeOfDelivery":"2015-11-23T08:00:00","deliveryCarNo":null,"deliveryCarDriverTelephone":null,"warehouseId":2989102,"warehouseName":"天翔外贸跨境仓库","warehouseRemark":"宁波天翔货柜有限公司 霞浦仓库（原宏达四堆场）","warehouseAddress":"宁波市北仑区霞浦国际物流园区云台山路51号（南门）　妙峰山路3号(北门)","warehouseContactsId":2989102,"warehouseContacts":"JENNY WANG","warehouseContactCellPhone":"0574-86831622","warehouseContactTellPhone":"0574-86831622","warehouseContactFax":"0574-86897610","isGeneratedLink":true,"linkAddress":"/center/storage/storageModifyExternal/3139401/4bdfd589-290e-4e1e-87b2-0b9ad913721a","entryStatus":0,"exemptionWayId":2811610,"domesticSaleRate":0,"declareDate":null}
     * orderExport : {"matchType":0,"matchTypeFormat":"未匹配","serviceType":1,"shippingDetailList":[],"detailList":[],"orderContainers":[],"combineDetailList":[],"detailAllList":[],"certificateList":null,"goodsInspection":null,"licenseNo":null,"licenseId":null,"ownerId":3139401,"code":"201511241003330002","foreignId":null,"foreignName":null,"shippmentDate":null,"exportPort":"CNNBO","exportPortCn":"宁波","exportPortEn":"NINGBO","transshippmentPort":null,"transshippmentPortCn":null,"transshippmentPortEn":null,"destinationPort":null,"destinationPortCn":"布兰卡港","destinationPortEn":"BAHIA BLANCA","destinationQpPort":null,"destinationQpPortCn":null,"amount":0,"settleAmount":null,"settleNo":null,"currency":"USD","priceTerm":"FOB","priceTermDesc":null,"insuranceTerm":null,"payment1":"2","payment1Period":null,"payment1Rate":null,"payment1Desc":null,"payment2":null,"payment2Period":null,"payment2Rate":null,"payment2Desc":null,"remark":null,"commodityDescCn":"3","commodityDescEn":null,"modeOfTransportation":1,"grossWeight":3,"netWeight":null,"weightUnit":"QK","weightUnitId":6,"weightUnitCn":"千克","weightUnitEn":"KG","vol":3,"volUnit":"LFM","volUnitId":9,"volUnitCn":"立方米","volUnitEn":"CB.M","modeOfPacking":null,"source":null,"rmbRate":null,"usdRate":null,"modeOfTrade":"一般贸易","inspectionFlag":false,"finalDestCountry":null,"finalDestCountryId":null,"finalDestCountryCn":null,"finalDestCountryEn":null,"packingQuantity":3,"packingUnit":"T/12","packingUnitId":13,"packingUnitCn":"纸箱","packingUnitEn":"CTNS","salesOrder":null,"salesOrderSignDate":null,"invoicingDate":null,"invoicingCompanyId":null,"invoicingCompanyName":null,"invoiceAmount":null,"swapRate":null,"consignee":null,"consignor":null,"notifier":null,"customerPurchaseOrder":null,"closingDate":null,"shippingSchedule":null,"shippingContractNo":null,"forwarder":null,"forwardingContactor":null,"forwardingPhone":null,"forwardingEmail":null,"forwardingFax":null,"forwardingRemark":null,"forwardingAddress":null,"factoryName":null,"factoryContactor":null,"factoryPhone":null,"factoryEmail":null,"factoryFax":null,"factoryAddress":null,"factoryRemark":null,"lcNo":null,"lcAmount":null,"completeDeliveryDate":null,"docPaymentBank":null,"docPaymentDate":null,"forecastReceiptDate":null,"docPaymentStatus":0,"actualCustomsClearanceDate":null,"rtnBackCustomsAmount":null,"actualAmount":0,"rtnBackCustomsClearanceDate":null,"entrustLetter":null,"freightChargeModeId":null,"freight":null,"freightCurrency":null,"premiumChargeModeId":null,"premium":null,"premiumCurrency":null,"otherFeeChargeModeId":null,"otherFee":null,"otherFeeCurrency":null,"isSignAgreement":null,"frontMark":"N/M","sideMark":null,"type":null,"matchCode":null,"eta":null,"blId":null,"blNo":null,"blTelexReleasedFlag":null,"etd":null,"vesselName":null,"voyageNo":null,"shipper":null,"shippingOrderNo":null,"packingFactory":null,"packingContactor":null,"packingLocale":null,"shipperContractNo":null,"operatorConfirmFlag":false,"ckNeedInsurance":false,"ckNeedAuthorityCustomsClearance":false,"ckCustomsClearanceNo":null,"tsActualDate":null,"tsActualAmount":null,"tsRebateDeadline":null,"tsDirectlyPayment":null,"tsFormalitiesCharges":null,"tsApprovalResults":null,"tsApprovalOpinion":null,"tsPaymentAmount":null,"tsRemark":null,"tsCustomRebateAmount":null,"tsIsInvoiceMatch":false,"tsOperatorConfirmFlag":false,"tsIsPaymentCost":false,"tsRebateReceiveDate":null,"tsRebateReceiveCreatorId":null,"tsRebateReceiveCreatorName":null,"sxUnPaymentAmount":null,"zxCompanyId":null,"zxCompanyName":null,"zxContactor":null,"zxContactWay":null,"zxEmail":null,"zxAddress":null,"zxFax":null,"zxRemark":null,"zxCreditInvestigationAmount":null,"sxPayeeId":null,"sxFinanceAmount":null,"sxPayeeName":null,"sxPayeeBank":null,"sxPayeeBankNo":null,"sxSinosureCurrency":null,"sxSinosureAmount":null,"sxForceLoan":null,"sxForceRefund":null,"sxFinacingDays":null,"sxPaymentType":null,"sxShipmentDate":null,"sxSinosureRate":null,"sxReservationRate":null,"sxFinancingProportion":null,"sxFinancionCreditLimit":null,"sxSinosureCost":null,"sxSinosureCostRate":null,"sxSinosureTtCost":null,"sxReservationInterest":null,"sxEndDate":null,"sxPaymentDate":null,"sxApprovalResults":null,"sxApprovalOpinion":null,"sxActualPaymentDate":null,"sxActualFkDate":null,"sxActualPaymentAmount":null,"sxActualInterest":null,"sxDifferenceAmount":null,"sxFineInterest":null,"sxPaymentRemark":null,"sxCustomCreditLimit":null,"sxSinosureNo":null,"sxSinosureCreditLimit":null,"sxIsCustomsConfirm":false,"sxOperatorConfirmFlag":false,"tsMustActualAmount":null,"customs":"3101","customsName":"宁波海关","sccgJfd":null,"bookingType":null,"sccgIdentifyingInformation":null,"taxNature":null,"tsSettleFlag":null,"isHaveOraginalDeclaration":null,"exportPortAreaName":null,"exportPortArea":null,"carrierId":null,"carrierNameCn":null,"carrierNameEn":null,"isTransport":null,"freightTerms":null,"shippingCompanyCode":null,"shippingCompanyName":null,"shippingOrderType":"LCL","expressMailNo":null,"deliveryWarehouseNo":null,"contractNo":null,"containerNo":null,"id":3139701,"accountId":3009701,"accountCode":null,"accountName":"宁波港东南物流有限公司","accountUserId":3010201,"accountUserName":"npsel","accountContactor":null,"accountContactPhone":null,"accountContactMobilePhone":null,"accountContactQq":null,"accountContactSkype":null,"titleId":1569600,"titleName":"Ningbo United Group I&E","creates":null,"updates":null,"deletes":null}
     * forwardingContainer : []
     * attachments : []
     * customsClearanceFlag : false
     * orderReportId : 0
     * attachmentTypeIds : []
     * id : 3139401
     * code : 201511241003330002
     * reason : null
     * settleRemark : null
     * status : 2
     * statusFormatCn : 进行中
     * clerkId : 2320200
     * clerkName : 管理员
     * salesId : null
     * salesName : null
     * accountId : 3009701
     * accountName : 宁波港东南物流有限公司
     * accountUserId : 3010201
     * accountUserName : npsel
     * accountContactor : null
     * accountContactPhone : null
     * accountContactMobilePhone : null
     * accountContactFax : null
     * accountContactQq : null
     * accountContactSkype : null
     * accountEmail : null
     * remark : null
     * sysCompanyId : 2674400
     * sysCompanyName : null
     * titleId : 1569600
     * titleName : Ningbo United Group I&E
     * orderType : 1
     * isMarketPruchaseModel : false
     * isPltReceipt : true
     * serviceIds : null
     */

    private Object accountCode;
    private String acceptTime;
    private Object completeTime;
    private String submitTime;
    private String createDate;
    private Object modifyDate;
    private boolean settleFlag;
    private boolean financeFlag;
    private boolean customerViewed;
    private boolean systemViewed;
    private boolean isImportQp;
    private boolean completedExchange;
    private boolean completedInvoice;
    private boolean completedPayment;
    private Object rebateStatus;
    private int creatorType;
    /**
     * storageStatus : 1
     * declareNo : null
     * approveDate : null
     * generatedDeclarationFlag : false
     * generatedDeclarationPersion : 0
     * generatedDeclarationDate : null
     * generatedPreDeclarationFlag : false
     * generatedPreDeclarationPersion : 0
     * generatedPreDeclarationDate : null
     * customsAreaEntrySended : false
     * customsAreaEntryStatus : 0
     * nodes : [{"id":2983301,"name":"新制","seq":1,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"录入","positionName":null,"nodeStatus":1,"fallbackFlag":false,"description":"等待经营单位录入进仓单明细和进仓单附带的报关信息","actionStatus":{"id":3139906,"itemId":3139501,"itemMetaDataId":1011,"operatorId":0,"operatorName":null,"operateDate":"2015-11-24T15:19:00","status":"进行中","description":"接收服务项","inner":false},"actionExcuted":true},{"id":2983303,"name":"待录入","seq":2,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"确认","positionName":null,"nodeStatus":0,"fallbackFlag":false,"description":"等待经营单位录入进仓单明细和进仓单附带的报关信息的确认","actionStatus":null,"actionExcuted":false},{"id":2983304,"name":"待确认","seq":3,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"提交","positionName":null,"nodeStatus":0,"fallbackFlag":false,"description":"进仓单商品明细提交备案","actionStatus":null,"actionExcuted":false},{"id":2983305,"name":"待备案","seq":4,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"收货","positionName":null,"nodeStatus":0,"fallbackFlag":false,"description":"等待仓库收货","actionStatus":null,"actionExcuted":false},{"id":2983306,"name":"待收货","seq":5,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"申报","positionName":null,"nodeStatus":0,"fallbackFlag":false,"description":"等待报关行发送申报指令","actionStatus":null,"actionExcuted":false},{"id":3011201,"name":"待申报","seq":6,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"报关","positionName":null,"nodeStatus":0,"fallbackFlag":false,"description":"等待报关行报关操作","actionStatus":null,"actionExcuted":false},{"id":3011204,"name":"待报关","seq":7,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"完成报关","positionName":null,"nodeStatus":0,"fallbackFlag":false,"description":"报关行完成报关","actionStatus":null,"actionExcuted":false}]
     * businessEnterprise : null
     * forwarderConfirmed : false
     * id : 3139801
     * ownerId : 3139401
     * ordOrderExportId : 3139701
     * receiverId : 0
     * receiverName : NUGIE
     * receiverContactsId : 0
     * receiverContacts : null
     * receiverContactTellPhone : null
     * businessEnterpriseId : 0
     * businessEnterpriseCode : null
     * businessEnterpriseName : null
     * businessEnterpriseContact : null
     * businessEnterpriseContactTelephone : null
     * businessEnterpriseAddress : null
     * deliveryEnterpriseId : 0
     * deliveryEnterpriseCode : null
     * deliveryEnterpriseName : null
     * declareEnterpriseId : 3009702
     * declareEnterpriseCode : 3302480040
     * declareEnterpriseName : 宁波联合报关有限公司
     * deliveryInvoiceCode : NUGIE323wwe
     * customsRecordNo : null
     * estimatedSchedual : 2015-11-24
     * startTimeOfDelivery : 2015-11-22T08:00:00
     * latestTimeOfDelivery : 2015-11-23T08:00:00
     * deliveryCarNo : null
     * deliveryCarDriverTelephone : null
     * warehouseId : 2989102
     * warehouseName : 天翔外贸跨境仓库
     * warehouseRemark : 宁波天翔货柜有限公司 霞浦仓库（原宏达四堆场）
     * warehouseAddress : 宁波市北仑区霞浦国际物流园区云台山路51号（南门）　妙峰山路3号(北门)
     * warehouseContactsId : 2989102
     * warehouseContacts : JENNY WANG
     * warehouseContactCellPhone : 0574-86831622
     * warehouseContactTellPhone : 0574-86831622
     * warehouseContactFax : 0574-86897610
     * isGeneratedLink : true
     * linkAddress : /center/storage/storageModifyExternal/3139401/4bdfd589-290e-4e1e-87b2-0b9ad913721a
     * entryStatus : 0
     * exemptionWayId : 2811610
     * domesticSaleRate : 0
     * declareDate : null
     */

    private OrderDeliveryEntity orderDelivery;
    /**
     * matchType : 0
     * matchTypeFormat : 未匹配
     * serviceType : 1
     * shippingDetailList : []
     * detailList : []
     * orderContainers : []
     * combineDetailList : []
     * detailAllList : []
     * certificateList : null
     * goodsInspection : null
     * licenseNo : null
     * licenseId : null
     * ownerId : 3139401
     * code : 201511241003330002
     * foreignId : null
     * foreignName : null
     * shippmentDate : null
     * exportPort : CNNBO
     * exportPortCn : 宁波
     * exportPortEn : NINGBO
     * transshippmentPort : null
     * transshippmentPortCn : null
     * transshippmentPortEn : null
     * destinationPort : null
     * destinationPortCn : 布兰卡港
     * destinationPortEn : BAHIA BLANCA
     * destinationQpPort : null
     * destinationQpPortCn : null
     * amount : 0
     * settleAmount : null
     * settleNo : null
     * currency : USD
     * priceTerm : FOB
     * priceTermDesc : null
     * insuranceTerm : null
     * payment1 : 2
     * payment1Period : null
     * payment1Rate : null
     * payment1Desc : null
     * payment2 : null
     * payment2Period : null
     * payment2Rate : null
     * payment2Desc : null
     * remark : null
     * commodityDescCn : 3
     * commodityDescEn : null
     * modeOfTransportation : 1
     * grossWeight : 3
     * netWeight : null
     * weightUnit : QK
     * weightUnitId : 6
     * weightUnitCn : 千克
     * weightUnitEn : KG
     * vol : 3
     * volUnit : LFM
     * volUnitId : 9
     * volUnitCn : 立方米
     * volUnitEn : CB.M
     * modeOfPacking : null
     * source : null
     * rmbRate : null
     * usdRate : null
     * modeOfTrade : 一般贸易
     * inspectionFlag : false
     * finalDestCountry : null
     * finalDestCountryId : null
     * finalDestCountryCn : null
     * finalDestCountryEn : null
     * packingQuantity : 3
     * packingUnit : T/12
     * packingUnitId : 13
     * packingUnitCn : 纸箱
     * packingUnitEn : CTNS
     * salesOrder : null
     * salesOrderSignDate : null
     * invoicingDate : null
     * invoicingCompanyId : null
     * invoicingCompanyName : null
     * invoiceAmount : null
     * swapRate : null
     * consignee : null
     * consignor : null
     * notifier : null
     * customerPurchaseOrder : null
     * closingDate : null
     * shippingSchedule : null
     * shippingContractNo : null
     * forwarder : null
     * forwardingContactor : null
     * forwardingPhone : null
     * forwardingEmail : null
     * forwardingFax : null
     * forwardingRemark : null
     * forwardingAddress : null
     * factoryName : null
     * factoryContactor : null
     * factoryPhone : null
     * factoryEmail : null
     * factoryFax : null
     * factoryAddress : null
     * factoryRemark : null
     * lcNo : null
     * lcAmount : null
     * completeDeliveryDate : null
     * docPaymentBank : null
     * docPaymentDate : null
     * forecastReceiptDate : null
     * docPaymentStatus : 0
     * actualCustomsClearanceDate : null
     * rtnBackCustomsAmount : null
     * actualAmount : 0
     * rtnBackCustomsClearanceDate : null
     * entrustLetter : null
     * freightChargeModeId : null
     * freight : null
     * freightCurrency : null
     * premiumChargeModeId : null
     * premium : null
     * premiumCurrency : null
     * otherFeeChargeModeId : null
     * otherFee : null
     * otherFeeCurrency : null
     * isSignAgreement : null
     * frontMark : N/M
     * sideMark : null
     * type : null
     * matchCode : null
     * eta : null
     * blId : null
     * blNo : null
     * blTelexReleasedFlag : null
     * etd : null
     * vesselName : null
     * voyageNo : null
     * shipper : null
     * shippingOrderNo : null
     * packingFactory : null
     * packingContactor : null
     * packingLocale : null
     * shipperContractNo : null
     * operatorConfirmFlag : false
     * ckNeedInsurance : false
     * ckNeedAuthorityCustomsClearance : false
     * ckCustomsClearanceNo : null
     * tsActualDate : null
     * tsActualAmount : null
     * tsRebateDeadline : null
     * tsDirectlyPayment : null
     * tsFormalitiesCharges : null
     * tsApprovalResults : null
     * tsApprovalOpinion : null
     * tsPaymentAmount : null
     * tsRemark : null
     * tsCustomRebateAmount : null
     * tsIsInvoiceMatch : false
     * tsOperatorConfirmFlag : false
     * tsIsPaymentCost : false
     * tsRebateReceiveDate : null
     * tsRebateReceiveCreatorId : null
     * tsRebateReceiveCreatorName : null
     * sxUnPaymentAmount : null
     * zxCompanyId : null
     * zxCompanyName : null
     * zxContactor : null
     * zxContactWay : null
     * zxEmail : null
     * zxAddress : null
     * zxFax : null
     * zxRemark : null
     * zxCreditInvestigationAmount : null
     * sxPayeeId : null
     * sxFinanceAmount : null
     * sxPayeeName : null
     * sxPayeeBank : null
     * sxPayeeBankNo : null
     * sxSinosureCurrency : null
     * sxSinosureAmount : null
     * sxForceLoan : null
     * sxForceRefund : null
     * sxFinacingDays : null
     * sxPaymentType : null
     * sxShipmentDate : null
     * sxSinosureRate : null
     * sxReservationRate : null
     * sxFinancingProportion : null
     * sxFinancionCreditLimit : null
     * sxSinosureCost : null
     * sxSinosureCostRate : null
     * sxSinosureTtCost : null
     * sxReservationInterest : null
     * sxEndDate : null
     * sxPaymentDate : null
     * sxApprovalResults : null
     * sxApprovalOpinion : null
     * sxActualPaymentDate : null
     * sxActualFkDate : null
     * sxActualPaymentAmount : null
     * sxActualInterest : null
     * sxDifferenceAmount : null
     * sxFineInterest : null
     * sxPaymentRemark : null
     * sxCustomCreditLimit : null
     * sxSinosureNo : null
     * sxSinosureCreditLimit : null
     * sxIsCustomsConfirm : false
     * sxOperatorConfirmFlag : false
     * tsMustActualAmount : null
     * customs : 3101
     * customsName : 宁波海关
     * sccgJfd : null
     * bookingType : null
     * sccgIdentifyingInformation : null
     * taxNature : null
     * tsSettleFlag : null
     * isHaveOraginalDeclaration : null
     * exportPortAreaName : null
     * exportPortArea : null
     * carrierId : null
     * carrierNameCn : null
     * carrierNameEn : null
     * isTransport : null
     * freightTerms : null
     * shippingCompanyCode : null
     * shippingCompanyName : null
     * shippingOrderType : LCL
     * expressMailNo : null
     * deliveryWarehouseNo : null
     * contractNo : null
     * containerNo : null
     * id : 3139701
     * accountId : 3009701
     * accountCode : null
     * accountName : 宁波港东南物流有限公司
     * accountUserId : 3010201
     * accountUserName : npsel
     * accountContactor : null
     * accountContactPhone : null
     * accountContactMobilePhone : null
     * accountContactQq : null
     * accountContactSkype : null
     * titleId : 1569600
     * titleName : Ningbo United Group I&E
     * creates : null
     * updates : null
     * deletes : null
     */

    private OrderExportEntity orderExport;
    private boolean customsClearanceFlag;
    private int orderReportId;
    private int id;
    private String code;
    private Object reason;
    private Object settleRemark;
    private int status;
    private String statusFormatCn;
    private int clerkId;
    private String clerkName;
    private Object salesId;
    private Object salesName;
    private int accountId;
    private String accountName;
    private int accountUserId;
    private String accountUserName;
    private Object accountContactor;
    private Object accountContactPhone;
    private Object accountContactMobilePhone;
    private Object accountContactFax;
    private Object accountContactQq;
    private Object accountContactSkype;
    private Object accountEmail;
    private Object remark;
    private int sysCompanyId;
    private Object sysCompanyName;
    private int titleId;
    private String titleName;
    private int orderType;
    private boolean isMarketPruchaseModel;
    private boolean isPltReceipt;
    private Object serviceIds;
    /**
     * nodePositionId : null
     * nodePositionName : null
     * statusCn : 进行中
     * operable : false
     * itemMetaDataName : 货代进仓
     * operatorId : 3010201
     * operatorName : npsel
     * createDate : 2015-11-24T15:18:59
     * agreement : [{"id":3139601,"ownerId":3139501,"settlementId":700,"formula":"700#$#","deductType":0}]
     * nodes : [{"id":2983301,"name":"新制","seq":1,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"录入","positionName":null,"nodeStatus":1,"fallbackFlag":false,"description":"等待经营单位录入进仓单明细和进仓单附带的报关信息","actionStatus":{"id":3139906,"itemId":3139501,"itemMetaDataId":1011,"operatorId":0,"operatorName":null,"operateDate":"2015-11-24T15:19:00","status":"进行中","description":"接收服务项","inner":false},"actionExcuted":true},{"id":2983303,"name":"待录入","seq":2,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"确认","positionName":null,"nodeStatus":0,"fallbackFlag":false,"description":"等待经营单位录入进仓单明细和进仓单附带的报关信息的确认","actionStatus":null,"actionExcuted":false},{"id":2983304,"name":"待确认","seq":3,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"提交","positionName":null,"nodeStatus":0,"fallbackFlag":false,"description":"进仓单商品明细提交备案","actionStatus":null,"actionExcuted":false},{"id":2983305,"name":"待备案","seq":4,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"收货","positionName":null,"nodeStatus":0,"fallbackFlag":false,"description":"等待仓库收货","actionStatus":null,"actionExcuted":false},{"id":2983306,"name":"待收货","seq":5,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"申报","positionName":null,"nodeStatus":0,"fallbackFlag":false,"description":"等待报关行发送申报指令","actionStatus":null,"actionExcuted":false},{"id":3011201,"name":"待申报","seq":6,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"报关","positionName":null,"nodeStatus":0,"fallbackFlag":false,"description":"等待报关行报关操作","actionStatus":null,"actionExcuted":false},{"id":3011204,"name":"待报关","seq":7,"createDate":null,"creator":null,"lockChargeRate":true,"actionName":"完成报关","positionName":null,"nodeStatus":0,"fallbackFlag":false,"description":"报关行完成报关","actionStatus":null,"actionExcuted":false}]
     * curNodeDisplay : null
     * attachments : null
     * enabled : true
     * isUsed : true
     * removeFlag : true
     * depends : []
     * mutexes : []
     * id : 3139501
     * customStatus : null
     * ownerId : 3139401
     * status : 1
     * subStatus : 2983301
     * subStatusName : null
     * handlingFee : null
     * itemMetaDataId : 1011
     * deductType : null
     */

    private List<OrderItemsEntity> orderItems;
    private List<?> forwardingContainer;
    private List<?> attachments;
    private List<?> attachmentTypeIds;

    public void setAccountCode(Object accountCode) {
        this.accountCode = accountCode;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public void setCompleteTime(Object completeTime) {
        this.completeTime = completeTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setModifyDate(Object modifyDate) {
        this.modifyDate = modifyDate;
    }

    public void setSettleFlag(boolean settleFlag) {
        this.settleFlag = settleFlag;
    }

    public void setFinanceFlag(boolean financeFlag) {
        this.financeFlag = financeFlag;
    }

    public void setCustomerViewed(boolean customerViewed) {
        this.customerViewed = customerViewed;
    }

    public void setSystemViewed(boolean systemViewed) {
        this.systemViewed = systemViewed;
    }

    public void setIsImportQp(boolean isImportQp) {
        this.isImportQp = isImportQp;
    }

    public void setCompletedExchange(boolean completedExchange) {
        this.completedExchange = completedExchange;
    }

    public void setCompletedInvoice(boolean completedInvoice) {
        this.completedInvoice = completedInvoice;
    }

    public void setCompletedPayment(boolean completedPayment) {
        this.completedPayment = completedPayment;
    }

    public void setRebateStatus(Object rebateStatus) {
        this.rebateStatus = rebateStatus;
    }

    public void setCreatorType(int creatorType) {
        this.creatorType = creatorType;
    }

    public void setOrderDelivery(OrderDeliveryEntity orderDelivery) {
        this.orderDelivery = orderDelivery;
    }

    public void setOrderExport(OrderExportEntity orderExport) {
        this.orderExport = orderExport;
    }

    public void setCustomsClearanceFlag(boolean customsClearanceFlag) {
        this.customsClearanceFlag = customsClearanceFlag;
    }

    public void setOrderReportId(int orderReportId) {
        this.orderReportId = orderReportId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setReason(Object reason) {
        this.reason = reason;
    }

    public void setSettleRemark(Object settleRemark) {
        this.settleRemark = settleRemark;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setStatusFormatCn(String statusFormatCn) {
        this.statusFormatCn = statusFormatCn;
    }

    public void setClerkId(int clerkId) {
        this.clerkId = clerkId;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }

    public void setSalesId(Object salesId) {
        this.salesId = salesId;
    }

    public void setSalesName(Object salesName) {
        this.salesName = salesName;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountUserId(int accountUserId) {
        this.accountUserId = accountUserId;
    }

    public void setAccountUserName(String accountUserName) {
        this.accountUserName = accountUserName;
    }

    public void setAccountContactor(Object accountContactor) {
        this.accountContactor = accountContactor;
    }

    public void setAccountContactPhone(Object accountContactPhone) {
        this.accountContactPhone = accountContactPhone;
    }

    public void setAccountContactMobilePhone(Object accountContactMobilePhone) {
        this.accountContactMobilePhone = accountContactMobilePhone;
    }

    public void setAccountContactFax(Object accountContactFax) {
        this.accountContactFax = accountContactFax;
    }

    public void setAccountContactQq(Object accountContactQq) {
        this.accountContactQq = accountContactQq;
    }

    public void setAccountContactSkype(Object accountContactSkype) {
        this.accountContactSkype = accountContactSkype;
    }

    public void setAccountEmail(Object accountEmail) {
        this.accountEmail = accountEmail;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public void setSysCompanyId(int sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }

    public void setSysCompanyName(Object sysCompanyName) {
        this.sysCompanyName = sysCompanyName;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public void setIsMarketPruchaseModel(boolean isMarketPruchaseModel) {
        this.isMarketPruchaseModel = isMarketPruchaseModel;
    }

    public void setIsPltReceipt(boolean isPltReceipt) {
        this.isPltReceipt = isPltReceipt;
    }

    public void setServiceIds(Object serviceIds) {
        this.serviceIds = serviceIds;
    }

    public void setOrderItems(List<OrderItemsEntity> orderItems) {
        this.orderItems = orderItems;
    }

    public void setForwardingContainer(List<?> forwardingContainer) {
        this.forwardingContainer = forwardingContainer;
    }

    public void setAttachments(List<?> attachments) {
        this.attachments = attachments;
    }

    public void setAttachmentTypeIds(List<?> attachmentTypeIds) {
        this.attachmentTypeIds = attachmentTypeIds;
    }

    public Object getAccountCode() {
        return accountCode;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public Object getCompleteTime() {
        return completeTime;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public String getCreateDate() {
        return createDate;
    }

    public Object getModifyDate() {
        return modifyDate;
    }

    public boolean isSettleFlag() {
        return settleFlag;
    }

    public boolean isFinanceFlag() {
        return financeFlag;
    }

    public boolean isCustomerViewed() {
        return customerViewed;
    }

    public boolean isSystemViewed() {
        return systemViewed;
    }

    public boolean isIsImportQp() {
        return isImportQp;
    }

    public boolean isCompletedExchange() {
        return completedExchange;
    }

    public boolean isCompletedInvoice() {
        return completedInvoice;
    }

    public boolean isCompletedPayment() {
        return completedPayment;
    }

    public Object getRebateStatus() {
        return rebateStatus;
    }

    public int getCreatorType() {
        return creatorType;
    }

    public OrderDeliveryEntity getOrderDelivery() {
        return orderDelivery;
    }

    public OrderExportEntity getOrderExport() {
        return orderExport;
    }

    public boolean isCustomsClearanceFlag() {
        return customsClearanceFlag;
    }

    public int getOrderReportId() {
        return orderReportId;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public Object getReason() {
        return reason;
    }

    public Object getSettleRemark() {
        return settleRemark;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusFormatCn() {
        return statusFormatCn;
    }

    public int getClerkId() {
        return clerkId;
    }

    public String getClerkName() {
        return clerkName;
    }

    public Object getSalesId() {
        return salesId;
    }

    public Object getSalesName() {
        return salesName;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public int getAccountUserId() {
        return accountUserId;
    }

    public String getAccountUserName() {
        return accountUserName;
    }

    public Object getAccountContactor() {
        return accountContactor;
    }

    public Object getAccountContactPhone() {
        return accountContactPhone;
    }

    public Object getAccountContactMobilePhone() {
        return accountContactMobilePhone;
    }

    public Object getAccountContactFax() {
        return accountContactFax;
    }

    public Object getAccountContactQq() {
        return accountContactQq;
    }

    public Object getAccountContactSkype() {
        return accountContactSkype;
    }

    public Object getAccountEmail() {
        return accountEmail;
    }

    public Object getRemark() {
        return remark;
    }

    public int getSysCompanyId() {
        return sysCompanyId;
    }

    public Object getSysCompanyName() {
        return sysCompanyName;
    }

    public int getTitleId() {
        return titleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public int getOrderType() {
        return orderType;
    }

    public boolean isIsMarketPruchaseModel() {
        return isMarketPruchaseModel;
    }

    public boolean isIsPltReceipt() {
        return isPltReceipt;
    }

    public Object getServiceIds() {
        return serviceIds;
    }

    public List<OrderItemsEntity> getOrderItems() {
        return orderItems;
    }

    public List<?> getForwardingContainer() {
        return forwardingContainer;
    }

    public List<?> getAttachments() {
        return attachments;
    }

    public List<?> getAttachmentTypeIds() {
        return attachmentTypeIds;
    }

    public static class OrderDeliveryEntity implements Serializable {
        private int storageStatus;
        private String declareNo;
        private Object approveDate;
        private boolean generatedDeclarationFlag;
        private int generatedDeclarationPersion;
        private Object generatedDeclarationDate;
        private boolean generatedPreDeclarationFlag;
        private int generatedPreDeclarationPersion;
        private Object generatedPreDeclarationDate;
        private boolean customsAreaEntrySended;
        private int customsAreaEntryStatus;
        private Object businessEnterprise;
        private boolean forwarderConfirmed;
        private int id;
        private int ownerId;
        private int ordOrderExportId;
        private int receiverId;
        private String receiverName;
        private int receiverContactsId;
        private Object receiverContacts;
        private Object receiverContactTellPhone;
        private int businessEnterpriseId;
        private String businessEnterpriseCode;
        private String businessEnterpriseName;
        private String businessEnterpriseContact;
        private String businessEnterpriseContactTelephone;
        private String businessEnterpriseAddress;
        private int deliveryEnterpriseId;
        private String deliveryEnterpriseCode;
        private String deliveryEnterpriseName;
        private int declareEnterpriseId;
        private String declareEnterpriseCode;
        private String declareEnterpriseName;
        private String deliveryInvoiceCode;
        private String customsRecordNo;
        private String estimatedSchedual;
        private String startTimeOfDelivery;
        private String latestTimeOfDelivery;
        private Object deliveryCarNo;
        private String deliveryCarDriverTelephone;
        private int warehouseId;
        private String warehouseName;
        private String warehouseRemark;
        private String warehouseAddress;
        private int warehouseContactsId;
        private String warehouseContacts;
        private String warehouseContactCellPhone;
        private String warehouseContactTellPhone;
        private String warehouseContactFax;
        private boolean isGeneratedLink;
        private String linkAddress;
        private int entryStatus;
        private int exemptionWayId;
        private double domesticSaleRate;
        private String declareDate;
        /**
         * id : 2983301
         * name : 新制
         * seq : 1
         * createDate : null
         * creator : null
         * lockChargeRate : true
         * actionName : 录入
         * positionName : null
         * nodeStatus : 1
         * fallbackFlag : false
         * description : 等待经营单位录入进仓单明细和进仓单附带的报关信息
         * actionStatus : {"id":3139906,"itemId":3139501,"itemMetaDataId":1011,"operatorId":0,"operatorName":null,"operateDate":"2015-11-24T15:19:00","status":"进行中","description":"接收服务项","inner":false}
         * actionExcuted : true
         */

        private List<NodesEntity> nodes;

        public void setStorageStatus(int storageStatus) {
            this.storageStatus = storageStatus;
        }

        public void setDeclareNo(String declareNo) {
            this.declareNo = declareNo;
        }

        public void setApproveDate(Object approveDate) {
            this.approveDate = approveDate;
        }

        public void setGeneratedDeclarationFlag(boolean generatedDeclarationFlag) {
            this.generatedDeclarationFlag = generatedDeclarationFlag;
        }

        public void setGeneratedDeclarationPersion(int generatedDeclarationPersion) {
            this.generatedDeclarationPersion = generatedDeclarationPersion;
        }

        public void setGeneratedDeclarationDate(Object generatedDeclarationDate) {
            this.generatedDeclarationDate = generatedDeclarationDate;
        }

        public void setGeneratedPreDeclarationFlag(boolean generatedPreDeclarationFlag) {
            this.generatedPreDeclarationFlag = generatedPreDeclarationFlag;
        }

        public void setGeneratedPreDeclarationPersion(int generatedPreDeclarationPersion) {
            this.generatedPreDeclarationPersion = generatedPreDeclarationPersion;
        }

        public void setGeneratedPreDeclarationDate(Object generatedPreDeclarationDate) {
            this.generatedPreDeclarationDate = generatedPreDeclarationDate;
        }

        public void setCustomsAreaEntrySended(boolean customsAreaEntrySended) {
            this.customsAreaEntrySended = customsAreaEntrySended;
        }

        public void setCustomsAreaEntryStatus(int customsAreaEntryStatus) {
            this.customsAreaEntryStatus = customsAreaEntryStatus;
        }

        public void setBusinessEnterprise(Object businessEnterprise) {
            this.businessEnterprise = businessEnterprise;
        }

        public void setForwarderConfirmed(boolean forwarderConfirmed) {
            this.forwarderConfirmed = forwarderConfirmed;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setOwnerId(int ownerId) {
            this.ownerId = ownerId;
        }

        public void setOrdOrderExportId(int ordOrderExportId) {
            this.ordOrderExportId = ordOrderExportId;
        }

        public void setReceiverId(int receiverId) {
            this.receiverId = receiverId;
        }

        public void setReceiverName(String receiverName) {
            this.receiverName = receiverName;
        }

        public void setReceiverContactsId(int receiverContactsId) {
            this.receiverContactsId = receiverContactsId;
        }

        public void setReceiverContacts(Object receiverContacts) {
            this.receiverContacts = receiverContacts;
        }

        public void setReceiverContactTellPhone(Object receiverContactTellPhone) {
            this.receiverContactTellPhone = receiverContactTellPhone;
        }

        public void setBusinessEnterpriseId(int businessEnterpriseId) {
            this.businessEnterpriseId = businessEnterpriseId;
        }

        public void setDeliveryEnterpriseId(int deliveryEnterpriseId) {
            this.deliveryEnterpriseId = deliveryEnterpriseId;
        }

        public void setDeliveryEnterpriseCode(String deliveryEnterpriseCode) {
            this.deliveryEnterpriseCode = deliveryEnterpriseCode;
        }

        public void setDeliveryEnterpriseName(String deliveryEnterpriseName) {
            this.deliveryEnterpriseName = deliveryEnterpriseName;
        }

        public void setDeclareEnterpriseId(int declareEnterpriseId) {
            this.declareEnterpriseId = declareEnterpriseId;
        }

        public void setDeclareEnterpriseCode(String declareEnterpriseCode) {
            this.declareEnterpriseCode = declareEnterpriseCode;
        }

        public void setDeclareEnterpriseName(String declareEnterpriseName) {
            this.declareEnterpriseName = declareEnterpriseName;
        }

        public void setDeliveryInvoiceCode(String deliveryInvoiceCode) {
            this.deliveryInvoiceCode = deliveryInvoiceCode;
        }

        public void setCustomsRecordNo(String customsRecordNo) {
            this.customsRecordNo = customsRecordNo;
        }

        public void setEstimatedSchedual(String estimatedSchedual) {
            this.estimatedSchedual = estimatedSchedual;
        }

        public void setStartTimeOfDelivery(String startTimeOfDelivery) {
            this.startTimeOfDelivery = startTimeOfDelivery;
        }

        public void setLatestTimeOfDelivery(String latestTimeOfDelivery) {
            this.latestTimeOfDelivery = latestTimeOfDelivery;
        }

        public void setDeliveryCarNo(Object deliveryCarNo) {
            this.deliveryCarNo = deliveryCarNo;
        }

        public void setDeliveryCarDriverTelephone(String deliveryCarDriverTelephone) {
            this.deliveryCarDriverTelephone = deliveryCarDriverTelephone;
        }

        public void setWarehouseId(int warehouseId) {
            this.warehouseId = warehouseId;
        }

        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
        }

        public void setWarehouseRemark(String warehouseRemark) {
            this.warehouseRemark = warehouseRemark;
        }

        public void setWarehouseAddress(String warehouseAddress) {
            this.warehouseAddress = warehouseAddress;
        }

        public void setWarehouseContactsId(int warehouseContactsId) {
            this.warehouseContactsId = warehouseContactsId;
        }

        public void setWarehouseContacts(String warehouseContacts) {
            this.warehouseContacts = warehouseContacts;
        }

        public void setWarehouseContactCellPhone(String warehouseContactCellPhone) {
            this.warehouseContactCellPhone = warehouseContactCellPhone;
        }

        public void setWarehouseContactTellPhone(String warehouseContactTellPhone) {
            this.warehouseContactTellPhone = warehouseContactTellPhone;
        }

        public void setWarehouseContactFax(String warehouseContactFax) {
            this.warehouseContactFax = warehouseContactFax;
        }

        public void setIsGeneratedLink(boolean isGeneratedLink) {
            this.isGeneratedLink = isGeneratedLink;
        }

        public void setLinkAddress(String linkAddress) {
            this.linkAddress = linkAddress;
        }

        public void setEntryStatus(int entryStatus) {
            this.entryStatus = entryStatus;
        }

        public void setExemptionWayId(int exemptionWayId) {
            this.exemptionWayId = exemptionWayId;
        }

        public void setDomesticSaleRate(double domesticSaleRate) {
            this.domesticSaleRate = domesticSaleRate;
        }

        public void setDeclareDate(String declareDate) {
            this.declareDate = declareDate;
        }

        public void setNodes(List<NodesEntity> nodes) {
            this.nodes = nodes;
        }

        public int getStorageStatus() {
            return storageStatus;
        }

        public String getDeclareNo() {
            return declareNo;
        }

        public Object getApproveDate() {
            return approveDate;
        }

        public boolean isGeneratedDeclarationFlag() {
            return generatedDeclarationFlag;
        }

        public int getGeneratedDeclarationPersion() {
            return generatedDeclarationPersion;
        }

        public Object getGeneratedDeclarationDate() {
            return generatedDeclarationDate;
        }

        public boolean isGeneratedPreDeclarationFlag() {
            return generatedPreDeclarationFlag;
        }

        public int getGeneratedPreDeclarationPersion() {
            return generatedPreDeclarationPersion;
        }

        public Object getGeneratedPreDeclarationDate() {
            return generatedPreDeclarationDate;
        }

        public boolean isCustomsAreaEntrySended() {
            return customsAreaEntrySended;
        }

        public int getCustomsAreaEntryStatus() {
            return customsAreaEntryStatus;
        }

        public Object getBusinessEnterprise() {
            return businessEnterprise;
        }

        public boolean isForwarderConfirmed() {
            return forwarderConfirmed;
        }

        public int getId() {
            return id;
        }

        public int getOwnerId() {
            return ownerId;
        }

        public int getOrdOrderExportId() {
            return ordOrderExportId;
        }

        public int getReceiverId() {
            return receiverId;
        }

        public String getReceiverName() {
            return receiverName;
        }

        public int getReceiverContactsId() {
            return receiverContactsId;
        }

        public Object getReceiverContacts() {
            return receiverContacts;
        }

        public Object getReceiverContactTellPhone() {
            return receiverContactTellPhone;
        }

        public int getBusinessEnterpriseId() {
            return businessEnterpriseId;
        }

        public String getBusinessEnterpriseCode() {
            return businessEnterpriseCode;
        }

        public void setBusinessEnterpriseCode(String businessEnterpriseCode) {
            this.businessEnterpriseCode = businessEnterpriseCode;
        }

        public String getBusinessEnterpriseName() {
            return businessEnterpriseName;
        }

        public void setBusinessEnterpriseName(String businessEnterpriseName) {
            this.businessEnterpriseName = businessEnterpriseName;
        }

        public String getBusinessEnterpriseContact() {
            return businessEnterpriseContact;
        }

        public void setBusinessEnterpriseContact(String businessEnterpriseContact) {
            this.businessEnterpriseContact = businessEnterpriseContact;
        }

        public String getBusinessEnterpriseContactTelephone() {
            return businessEnterpriseContactTelephone;
        }

        public void setBusinessEnterpriseContactTelephone(String businessEnterpriseContactTelephone) {
            this.businessEnterpriseContactTelephone = businessEnterpriseContactTelephone;
        }

        public String getBusinessEnterpriseAddress() {
            return businessEnterpriseAddress;
        }

        public void setBusinessEnterpriseAddress(String businessEnterpriseAddress) {
            this.businessEnterpriseAddress = businessEnterpriseAddress;
        }

        public boolean isGeneratedLink() {
            return isGeneratedLink;
        }

        public int getDeliveryEnterpriseId() {
            return deliveryEnterpriseId;
        }

        public String getDeliveryEnterpriseCode() {
            return deliveryEnterpriseCode;
        }

        public String getDeliveryEnterpriseName() {
            return deliveryEnterpriseName;
        }

        public int getDeclareEnterpriseId() {
            return declareEnterpriseId;
        }

        public String getDeclareEnterpriseCode() {
            return declareEnterpriseCode;
        }

        public String getDeclareEnterpriseName() {
            return declareEnterpriseName;
        }

        public String getDeliveryInvoiceCode() {
            return deliveryInvoiceCode;
        }

        public String getCustomsRecordNo() {
            return customsRecordNo;
        }

        public String getEstimatedSchedual() {
            return estimatedSchedual;
        }

        public String getStartTimeOfDelivery() {
            return startTimeOfDelivery;
        }

        public String getLatestTimeOfDelivery() {
            return latestTimeOfDelivery;
        }

        public Object getDeliveryCarNo() {
            return deliveryCarNo;
        }

        public String getDeliveryCarDriverTelephone() {
            return deliveryCarDriverTelephone;
        }

        public int getWarehouseId() {
            return warehouseId;
        }

        public String getWarehouseName() {
            return warehouseName;
        }

        public String getWarehouseRemark() {
            return warehouseRemark;
        }

        public String getWarehouseAddress() {
            return warehouseAddress;
        }

        public int getWarehouseContactsId() {
            return warehouseContactsId;
        }

        public String getWarehouseContacts() {
            return warehouseContacts;
        }

        public String getWarehouseContactCellPhone() {
            return warehouseContactCellPhone;
        }

        public String getWarehouseContactTellPhone() {
            return warehouseContactTellPhone;
        }

        public String getWarehouseContactFax() {
            return warehouseContactFax;
        }

        public boolean isIsGeneratedLink() {
            return isGeneratedLink;
        }

        public String getLinkAddress() {
            return linkAddress;
        }

        public int getEntryStatus() {
            return entryStatus;
        }

        public int getExemptionWayId() {
            return exemptionWayId;
        }

        public double getDomesticSaleRate() {
            return domesticSaleRate;
        }

        public String getDeclareDate() {
            return declareDate;
        }

        public List<NodesEntity> getNodes() {
            return nodes;
        }

        public static class NodesEntity implements Serializable {
            private int id;
            private String name;
            private int seq;
            private Object createDate;
            private Object creator;
            private boolean lockChargeRate;
            private String actionName;
            private Object positionName;
            private int nodeStatus;
            private boolean fallbackFlag;
            private String description;
            /**
             * id : 3139906
             * itemId : 3139501
             * itemMetaDataId : 1011
             * operatorId : 0
             * operatorName : null
             * operateDate : 2015-11-24T15:19:00
             * status : 进行中
             * description : 接收服务项
             * inner : false
             */

            private ActionStatusEntity actionStatus;
            private boolean actionExcuted;

            public void setId(int id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setSeq(int seq) {
                this.seq = seq;
            }

            public void setCreateDate(Object createDate) {
                this.createDate = createDate;
            }

            public void setCreator(Object creator) {
                this.creator = creator;
            }

            public void setLockChargeRate(boolean lockChargeRate) {
                this.lockChargeRate = lockChargeRate;
            }

            public void setActionName(String actionName) {
                this.actionName = actionName;
            }

            public void setPositionName(Object positionName) {
                this.positionName = positionName;
            }

            public void setNodeStatus(int nodeStatus) {
                this.nodeStatus = nodeStatus;
            }

            public void setFallbackFlag(boolean fallbackFlag) {
                this.fallbackFlag = fallbackFlag;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public void setActionStatus(ActionStatusEntity actionStatus) {
                this.actionStatus = actionStatus;
            }

            public void setActionExcuted(boolean actionExcuted) {
                this.actionExcuted = actionExcuted;
            }

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public int getSeq() {
                return seq;
            }

            public Object getCreateDate() {
                return createDate;
            }

            public Object getCreator() {
                return creator;
            }

            public boolean isLockChargeRate() {
                return lockChargeRate;
            }

            public String getActionName() {
                return actionName;
            }

            public Object getPositionName() {
                return positionName;
            }

            public int getNodeStatus() {
                return nodeStatus;
            }

            public boolean isFallbackFlag() {
                return fallbackFlag;
            }

            public String getDescription() {
                return description;
            }

            public ActionStatusEntity getActionStatus() {
                return actionStatus;
            }

            public boolean isActionExcuted() {
                return actionExcuted;
            }

            public static class ActionStatusEntity implements Serializable {
                private int id;
                private int itemId;
                private int itemMetaDataId;
                private int operatorId;
                private Object operatorName;
                private String operateDate;
                private String status;
                private String description;
                private boolean inner;

                public void setId(int id) {
                    this.id = id;
                }

                public void setItemId(int itemId) {
                    this.itemId = itemId;
                }

                public void setItemMetaDataId(int itemMetaDataId) {
                    this.itemMetaDataId = itemMetaDataId;
                }

                public void setOperatorId(int operatorId) {
                    this.operatorId = operatorId;
                }

                public void setOperatorName(Object operatorName) {
                    this.operatorName = operatorName;
                }

                public void setOperateDate(String operateDate) {
                    this.operateDate = operateDate;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public void setInner(boolean inner) {
                    this.inner = inner;
                }

                public int getId() {
                    return id;
                }

                public int getItemId() {
                    return itemId;
                }

                public int getItemMetaDataId() {
                    return itemMetaDataId;
                }

                public int getOperatorId() {
                    return operatorId;
                }

                public Object getOperatorName() {
                    return operatorName;
                }

                public String getOperateDate() {
                    return operateDate;
                }

                public String getStatus() {
                    return status;
                }

                public String getDescription() {
                    return description;
                }

                public boolean isInner() {
                    return inner;
                }
            }
        }
    }

    public static class DetailListEntity implements Serializable {

        /**
         * saleOrder : null
         * type : 1
         * id : 3140000
         * ownerId : 3139700
         * nameCn : 3232
         * saleAmount : 3232
         * quantity : 32
         * salesOrder : null
         * currency : USD
         * retRate : 0.13
         * supplierId : null
         * supplierName : null
         * vatRate : 0.17
         * inspectionFlag : false
         * stort : 0
         * orderId : 3139400
         * orderCode : 201511241003330001
         * tariffRate : 0
         * placeOfOrigin : null
         * exemptionWayId : 2811650
         * status : 0
         * customsRecordNo : 3114151001700209
         * categoryCode : 27000000
         * hsCode : 9505100090
         * saleUnitPrice : 101
         * grossWeight : 0
         * weightUnitInfo : null
         * weightUnit : QK
         * weightUnitId : 6
         * weightUnitCn : 千克
         * weightUnitEn : KG
         * nameEn : 3232
         * specification : 32
         * purchaseUnitPrice : null
         * purchaseAmount : null
         * netWeight : null
         * l : null
         * w : null
         * h : null
         * unitVol : null
         * unit : 035
         * unitId : 102
         * unitCn : 千克
         * unitEn : KGS
         * unitInfo : null
         * vol : 0
         * volUnit : LFM
         * volUnitId : 9
         * volUnitCn : 立方米
         * volUnitEn : CB.M
         * volUnitInfo : null
         * marksNo : null
         * remark : null
         * unitGWeight : null
         * unitNWeight : null
         * packingAmount : 3232
         * packingUnit : T/12
         * packingUnitId : 2
         * packingUnitCn : 木箱
         * packingUnitEn : CTNS
         * packingUnitInfo : null
         * customsSummary : 用途:
         * 品牌:
         * 其他:
         * <p/>
         * hsSummary : null
         * invoiceAmount : null
         * stallNo : null
         * material : null
         * jldwbm : 035
         * jldwName : 千克
         * jldwNameEn : KGS
         * jldwInfo : null
         * jldwSecondName : null
         * jldwbmSecond : null
         * jldwSecondInfo : null
         * jldwSecondNameEn : null
         * jldwQuantity : 32
         * jldwSecondQuantity : null
         * seq : 332
         * fileId : null
         * filePath : null
         * fileCreateTime : null
         * fileCreateName : null
         * fileCreateId : null
         * categoryName : 其他物品
         * categoryId : 3085915
         * manufacturer : null
         * commodityUse : null
         * supervisionCondition : 禁止进口商品,定向出口商品许可证
         * brand : 332
         * factoryInvoiceQuantity : 32
         * factoryInvoiceUnitId : 102
         * factoryInvoiceUnitCn : 千克
         * factoryInvoiceUnitEn : KGS
         */

        private Object saleOrder;
        private int type;
        private int id;
        private int ownerId;
        private String nameCn;
        private double saleAmount;
        private int quantity;
        private Object salesOrder;
        private String currency;
        private double retRate;
        private Object supplierId;
        private Object supplierName;
        private double vatRate;
        private boolean inspectionFlag;
        private int stort;
        private int orderId;
        private String orderCode;
        private double tariffRate;
        private String placeOfOrigin;
        private int exemptionWayId;
        private int status;
        private String customsRecordNo;
        private String categoryCode;
        private String hsCode;
        private double saleUnitPrice;
        private double grossWeight;
        private Object weightUnitInfo;
        private String weightUnit;
        private int weightUnitId;
        private String weightUnitCn;
        private String weightUnitEn;
        private String nameEn;
        private String specification;
        private Object purchaseUnitPrice;
        private Object purchaseAmount;
        private double netWeight;
        private Object l;
        private Object w;
        private Object h;
        private Object unitVol;
        private String unit;
        private int unitId;
        private String unitCn;
        private String unitEn;
        private Object unitInfo;
        private double vol;
        private String volUnit;
        private int volUnitId;
        private String volUnitCn;
        private String volUnitEn;
        private Object volUnitInfo;
        private Object marksNo;
        private Object remark;
        private Object unitGWeight;
        private Object unitNWeight;
        private int packingAmount;
        private String packingUnit;
        private int packingUnitId;
        private String packingUnitCn;
        private String packingUnitEn;
        private Object packingUnitInfo;
        private String customsSummary;
        private Object hsSummary;
        private Object invoiceAmount;
        private Object stallNo;
        private String material;
        private String jldwbm;
        private String jldwName;
        private String jldwNameEn;
        private Object jldwInfo;
        private String jldwSecondName;
        private Object jldwbmSecond;
        private Object jldwSecondInfo;
        private String jldwSecondNameEn;
        private int jldwQuantity;
        private int jldwSecondQuantity;
        private int seq;
        private Object fileId;
        private Object filePath;
        private Object fileCreateTime;
        private Object fileCreateName;
        private Object fileCreateId;
        private String categoryName;
        private int categoryId;
        private Object manufacturer;
        private Object commodityUse;
        private String supervisionCondition;
        private String brand;
        private int factoryInvoiceQuantity;
        private int factoryInvoiceUnitId;
        private String factoryInvoiceUnitCn;
        private String factoryInvoiceUnitEn;

        public void setSaleOrder(Object saleOrder) {
            this.saleOrder = saleOrder;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setOwnerId(int ownerId) {
            this.ownerId = ownerId;
        }

        public void setNameCn(String nameCn) {
            this.nameCn = nameCn;
        }

        public void setSaleAmount(double saleAmount) {
            this.saleAmount = saleAmount;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void setSalesOrder(Object salesOrder) {
            this.salesOrder = salesOrder;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public void setRetRate(double retRate) {
            this.retRate = retRate;
        }

        public void setSupplierId(Object supplierId) {
            this.supplierId = supplierId;
        }

        public void setSupplierName(Object supplierName) {
            this.supplierName = supplierName;
        }

        public void setVatRate(double vatRate) {
            this.vatRate = vatRate;
        }

        public void setInspectionFlag(boolean inspectionFlag) {
            this.inspectionFlag = inspectionFlag;
        }

        public void setStort(int stort) {
            this.stort = stort;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public void setTariffRate(double tariffRate) {
            this.tariffRate = tariffRate;
        }

        public void setPlaceOfOrigin(String placeOfOrigin) {
            this.placeOfOrigin = placeOfOrigin;
        }

        public void setExemptionWayId(int exemptionWayId) {
            this.exemptionWayId = exemptionWayId;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setCustomsRecordNo(String customsRecordNo) {
            this.customsRecordNo = customsRecordNo;
        }

        public void setCategoryCode(String categoryCode) {
            this.categoryCode = categoryCode;
        }

        public void setHsCode(String hsCode) {
            this.hsCode = hsCode;
        }

        public void setSaleUnitPrice(int saleUnitPrice) {
            this.saleUnitPrice = saleUnitPrice;
        }

        public void setGrossWeight(double grossWeight) {
            this.grossWeight = grossWeight;
        }

        public void setWeightUnitInfo(Object weightUnitInfo) {
            this.weightUnitInfo = weightUnitInfo;
        }

        public void setWeightUnit(String weightUnit) {
            this.weightUnit = weightUnit;
        }

        public void setWeightUnitId(int weightUnitId) {
            this.weightUnitId = weightUnitId;
        }

        public void setWeightUnitCn(String weightUnitCn) {
            this.weightUnitCn = weightUnitCn;
        }

        public void setWeightUnitEn(String weightUnitEn) {
            this.weightUnitEn = weightUnitEn;
        }

        public void setNameEn(String nameEn) {
            this.nameEn = nameEn;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }

        public void setPurchaseUnitPrice(Object purchaseUnitPrice) {
            this.purchaseUnitPrice = purchaseUnitPrice;
        }

        public void setPurchaseAmount(Object purchaseAmount) {
            this.purchaseAmount = purchaseAmount;
        }

        public void setNetWeight(double netWeight) {
            this.netWeight = netWeight;
        }

        public void setL(Object l) {
            this.l = l;
        }

        public void setW(Object w) {
            this.w = w;
        }

        public void setH(Object h) {
            this.h = h;
        }

        public void setUnitVol(Object unitVol) {
            this.unitVol = unitVol;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public void setUnitId(int unitId) {
            this.unitId = unitId;
        }

        public void setUnitCn(String unitCn) {
            this.unitCn = unitCn;
        }

        public void setUnitEn(String unitEn) {
            this.unitEn = unitEn;
        }

        public void setUnitInfo(Object unitInfo) {
            this.unitInfo = unitInfo;
        }

        public void setVol(double vol) {
            this.vol = vol;
        }

        public void setVolUnit(String volUnit) {
            this.volUnit = volUnit;
        }

        public void setVolUnitId(int volUnitId) {
            this.volUnitId = volUnitId;
        }

        public void setVolUnitCn(String volUnitCn) {
            this.volUnitCn = volUnitCn;
        }

        public void setVolUnitEn(String volUnitEn) {
            this.volUnitEn = volUnitEn;
        }

        public void setVolUnitInfo(Object volUnitInfo) {
            this.volUnitInfo = volUnitInfo;
        }

        public void setMarksNo(Object marksNo) {
            this.marksNo = marksNo;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public void setUnitGWeight(Object unitGWeight) {
            this.unitGWeight = unitGWeight;
        }

        public void setUnitNWeight(Object unitNWeight) {
            this.unitNWeight = unitNWeight;
        }

        public void setPackingAmount(int packingAmount) {
            this.packingAmount = packingAmount;
        }

        public void setPackingUnit(String packingUnit) {
            this.packingUnit = packingUnit;
        }

        public void setPackingUnitId(int packingUnitId) {
            this.packingUnitId = packingUnitId;
        }

        public void setPackingUnitCn(String packingUnitCn) {
            this.packingUnitCn = packingUnitCn;
        }

        public void setPackingUnitEn(String packingUnitEn) {
            this.packingUnitEn = packingUnitEn;
        }

        public void setPackingUnitInfo(Object packingUnitInfo) {
            this.packingUnitInfo = packingUnitInfo;
        }

        public void setCustomsSummary(String customsSummary) {
            this.customsSummary = customsSummary;
        }

        public void setHsSummary(Object hsSummary) {
            this.hsSummary = hsSummary;
        }

        public void setInvoiceAmount(Object invoiceAmount) {
            this.invoiceAmount = invoiceAmount;
        }

        public void setStallNo(Object stallNo) {
            this.stallNo = stallNo;
        }

        public void setMaterial(String material) {
            this.material = material;
        }

        public void setJldwbm(String jldwbm) {
            this.jldwbm = jldwbm;
        }

        public void setJldwName(String jldwName) {
            this.jldwName = jldwName;
        }

        public void setJldwNameEn(String jldwNameEn) {
            this.jldwNameEn = jldwNameEn;
        }

        public void setJldwInfo(Object jldwInfo) {
            this.jldwInfo = jldwInfo;
        }

        public void setJldwSecondName(String jldwSecondName) {
            this.jldwSecondName = jldwSecondName;
        }

        public void setJldwbmSecond(Object jldwbmSecond) {
            this.jldwbmSecond = jldwbmSecond;
        }

        public void setJldwSecondInfo(Object jldwSecondInfo) {
            this.jldwSecondInfo = jldwSecondInfo;
        }

        public void setJldwSecondNameEn(String jldwSecondNameEn) {
            this.jldwSecondNameEn = jldwSecondNameEn;
        }

        public void setJldwQuantity(int jldwQuantity) {
            this.jldwQuantity = jldwQuantity;
        }

        public void setJldwSecondQuantity(int jldwSecondQuantity) {
            this.jldwSecondQuantity = jldwSecondQuantity;
        }

        public void setSeq(int seq) {
            this.seq = seq;
        }

        public void setFileId(Object fileId) {
            this.fileId = fileId;
        }

        public void setFilePath(Object filePath) {
            this.filePath = filePath;
        }

        public void setFileCreateTime(Object fileCreateTime) {
            this.fileCreateTime = fileCreateTime;
        }

        public void setFileCreateName(Object fileCreateName) {
            this.fileCreateName = fileCreateName;
        }

        public void setFileCreateId(Object fileCreateId) {
            this.fileCreateId = fileCreateId;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public void setManufacturer(Object manufacturer) {
            this.manufacturer = manufacturer;
        }

        public void setCommodityUse(Object commodityUse) {
            this.commodityUse = commodityUse;
        }

        public void setSupervisionCondition(String supervisionCondition) {
            this.supervisionCondition = supervisionCondition;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public void setFactoryInvoiceQuantity(int factoryInvoiceQuantity) {
            this.factoryInvoiceQuantity = factoryInvoiceQuantity;
        }

        public void setFactoryInvoiceUnitId(int factoryInvoiceUnitId) {
            this.factoryInvoiceUnitId = factoryInvoiceUnitId;
        }

        public void setFactoryInvoiceUnitCn(String factoryInvoiceUnitCn) {
            this.factoryInvoiceUnitCn = factoryInvoiceUnitCn;
        }

        public void setFactoryInvoiceUnitEn(String factoryInvoiceUnitEn) {
            this.factoryInvoiceUnitEn = factoryInvoiceUnitEn;
        }

        public Object getSaleOrder() {
            return saleOrder;
        }

        public int getType() {
            return type;
        }

        public int getId() {
            return id;
        }

        public int getOwnerId() {
            return ownerId;
        }

        public String getNameCn() {
            return nameCn;
        }

        public double getSaleAmount() {
            return saleAmount;
        }

        public int getQuantity() {
            return quantity;
        }

        public Object getSalesOrder() {
            return salesOrder;
        }

        public String getCurrency() {
            return currency;
        }

        public double getRetRate() {
            return retRate;
        }

        public Object getSupplierId() {
            return supplierId;
        }

        public Object getSupplierName() {
            return supplierName;
        }

        public double getVatRate() {
            return vatRate;
        }

        public boolean isInspectionFlag() {
            return inspectionFlag;
        }

        public int getStort() {
            return stort;
        }

        public int getOrderId() {
            return orderId;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public double getTariffRate() {
            return tariffRate;
        }

        public String getPlaceOfOrigin() {
            return placeOfOrigin;
        }

        public int getExemptionWayId() {
            return exemptionWayId;
        }

        public int getStatus() {
            return status;
        }

        public String getCustomsRecordNo() {
            return customsRecordNo;
        }

        public String getCategoryCode() {
            return categoryCode;
        }

        public String getHsCode() {
            return hsCode;
        }

        public double getSaleUnitPrice() {
            return saleUnitPrice;
        }

        public double getGrossWeight() {
            return grossWeight;
        }

        public Object getWeightUnitInfo() {
            return weightUnitInfo;
        }

        public String getWeightUnit() {
            return weightUnit;
        }

        public int getWeightUnitId() {
            return weightUnitId;
        }

        public String getWeightUnitCn() {
            return weightUnitCn;
        }

        public String getWeightUnitEn() {
            return weightUnitEn;
        }

        public String getNameEn() {
            return nameEn;
        }

        public String getSpecification() {
            return specification;
        }

        public Object getPurchaseUnitPrice() {
            return purchaseUnitPrice;
        }

        public Object getPurchaseAmount() {
            return purchaseAmount;
        }

        public double getNetWeight() {
            return netWeight;
        }

        public Object getL() {
            return l;
        }

        public Object getW() {
            return w;
        }

        public Object getH() {
            return h;
        }

        public Object getUnitVol() {
            return unitVol;
        }

        public String getUnit() {
            return unit;
        }

        public int getUnitId() {
            return unitId;
        }

        public String getUnitCn() {
            return unitCn;
        }

        public String getUnitEn() {
            return unitEn;
        }

        public Object getUnitInfo() {
            return unitInfo;
        }

        public double getVol() {
            return vol;
        }

        public String getVolUnit() {
            return volUnit;
        }

        public int getVolUnitId() {
            return volUnitId;
        }

        public String getVolUnitCn() {
            return volUnitCn;
        }

        public String getVolUnitEn() {
            return volUnitEn;
        }

        public Object getVolUnitInfo() {
            return volUnitInfo;
        }

        public Object getMarksNo() {
            return marksNo;
        }

        public Object getRemark() {
            return remark;
        }

        public Object getUnitGWeight() {
            return unitGWeight;
        }

        public Object getUnitNWeight() {
            return unitNWeight;
        }

        public int getPackingAmount() {
            return packingAmount;
        }

        public String getPackingUnit() {
            return packingUnit;
        }

        public int getPackingUnitId() {
            return packingUnitId;
        }

        public String getPackingUnitCn() {
            return packingUnitCn;
        }

        public String getPackingUnitEn() {
            return packingUnitEn;
        }

        public Object getPackingUnitInfo() {
            return packingUnitInfo;
        }

        public String getCustomsSummary() {
            return customsSummary;
        }

        public Object getHsSummary() {
            return hsSummary;
        }

        public Object getInvoiceAmount() {
            return invoiceAmount;
        }

        public Object getStallNo() {
            return stallNo;
        }

        public String getMaterial() {
            return material;
        }

        public String getJldwbm() {
            return jldwbm;
        }

        public String getJldwName() {
            return jldwName;
        }

        public String getJldwNameEn() {
            return jldwNameEn;
        }

        public Object getJldwInfo() {
            return jldwInfo;
        }

        public String getJldwSecondName() {
            return jldwSecondName;
        }

        public Object getJldwbmSecond() {
            return jldwbmSecond;
        }

        public Object getJldwSecondInfo() {
            return jldwSecondInfo;
        }

        public String getJldwSecondNameEn() {
            return jldwSecondNameEn;
        }

        public int getJldwQuantity() {
            return jldwQuantity;
        }

        public int getJldwSecondQuantity() {
            return jldwSecondQuantity;
        }

        public int getSeq() {
            return seq;
        }

        public Object getFileId() {
            return fileId;
        }

        public Object getFilePath() {
            return filePath;
        }

        public Object getFileCreateTime() {
            return fileCreateTime;
        }

        public Object getFileCreateName() {
            return fileCreateName;
        }

        public Object getFileCreateId() {
            return fileCreateId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public Object getManufacturer() {
            return manufacturer;
        }

        public Object getCommodityUse() {
            return commodityUse;
        }

        public String getSupervisionCondition() {
            return supervisionCondition;
        }

        public String getBrand() {
            return brand;
        }

        public int getFactoryInvoiceQuantity() {
            return factoryInvoiceQuantity;
        }

        public int getFactoryInvoiceUnitId() {
            return factoryInvoiceUnitId;
        }

        public String getFactoryInvoiceUnitCn() {
            return factoryInvoiceUnitCn;
        }

        public String getFactoryInvoiceUnitEn() {
            return factoryInvoiceUnitEn;
        }
    }

    public static class OrderExportEntity implements Serializable {
        private int matchType;
        private String matchTypeFormat;
        private int serviceType;
        private Object certificateList;
        private Object goodsInspection;
        private String licenseNo;
        private Object licenseId;
        private int ownerId;
        private String code;
        private Object foreignId;
        private Object foreignName;
        private Object shippmentDate;
        private String exportPort;
        private String exportPortCn;
        private String exportPortEn;
        private Object transshippmentPort;
        private Object transshippmentPortCn;
        private Object transshippmentPortEn;
        private Object destinationPort;
        private String destinationPortCn;
        private String destinationPortEn;
        private Object destinationQpPort;
        private Object destinationQpPortCn;
        private double amount;
        private Object settleAmount;
        private Object settleNo;
        private String currency;
        private String priceTerm;
        private Object priceTermDesc;
        private Object insuranceTerm;
        private String payment1;
        private Object payment1Period;
        private Object payment1Rate;
        private String payment1Desc;
        private Object payment2;
        private Object payment2Period;
        private Object payment2Rate;
        private Object payment2Desc;
        private String remark;
        private String commodityDescCn;
        private Object commodityDescEn;
        private int modeOfTransportation;
        private double grossWeight;
        private double netWeight;
        private String weightUnit;
        private int weightUnitId;
        private String weightUnitCn;
        private String weightUnitEn;
        private double vol;
        private String volUnit;
        private int volUnitId;
        private String volUnitCn;
        private String volUnitEn;
        private Object modeOfPacking;
        private String source;
        private Object rmbRate;
        private Object usdRate;
        private String modeOfTrade;
        private boolean inspectionFlag;
        private Object finalDestCountry;
        private Object finalDestCountryId;
        private String finalDestCountryCn;
        private Object finalDestCountryEn;
        private int packingQuantity;
        private String packingUnit;
        private int packingUnitId;
        private String packingUnitCn;
        private String packingUnitEn;
        private String salesOrder;
        private Object salesOrderSignDate;
        private Object invoicingDate;
        private Object invoicingCompanyId;
        private Object invoicingCompanyName;
        private Object invoiceAmount;
        private Object swapRate;
        private Object consignee;
        private Object consignor;
        private Object notifier;
        private Object customerPurchaseOrder;
        private Object closingDate;
        private Object shippingSchedule;
        private Object shippingContractNo;
        private Object forwarder;
        private Object forwardingContactor;
        private Object forwardingPhone;
        private Object forwardingEmail;
        private Object forwardingFax;
        private Object forwardingRemark;
        private Object forwardingAddress;
        private Object factoryName;
        private Object factoryContactor;
        private Object factoryPhone;
        private Object factoryEmail;
        private Object factoryFax;
        private Object factoryAddress;
        private Object factoryRemark;
        private Object lcNo;
        private Object lcAmount;
        private Object completeDeliveryDate;
        private Object docPaymentBank;
        private Object docPaymentDate;
        private Object forecastReceiptDate;
        private int docPaymentStatus;
        private String actualCustomsClearanceDate;
        private Object rtnBackCustomsAmount;
        private int actualAmount;
        private Object rtnBackCustomsClearanceDate;
        private Object entrustLetter;
        private int freightChargeModeId;
        private String freight;
        private String freightCurrency;
        private int premiumChargeModeId;
        private String premium;
        private String premiumCurrency;
        private int otherFeeChargeModeId;
        private String otherFee;
        private String otherFeeCurrency;
        private Object isSignAgreement;
        private String frontMark;
        private Object sideMark;
        private Object type;
        private Object matchCode;
        private Object eta;
        private Object blId;
        private String blNo;
        private Object blTelexReleasedFlag;
        private Object etd;
        private String vesselName;
        private String voyageNo;
        private Object shipper;
        private Object shippingOrderNo;
        private Object packingFactory;
        private Object packingContactor;
        private Object packingLocale;
        private Object shipperContractNo;
        private boolean operatorConfirmFlag;
        private boolean ckNeedInsurance;
        private boolean ckNeedAuthorityCustomsClearance;
        private String ckCustomsClearanceNo;
        private Object tsActualDate;
        private Object tsActualAmount;
        private Object tsRebateDeadline;
        private Object tsDirectlyPayment;
        private Object tsFormalitiesCharges;
        private Object tsApprovalResults;
        private Object tsApprovalOpinion;
        private Object tsPaymentAmount;
        private String tsRemark;
        private Object tsCustomRebateAmount;
        private boolean tsIsInvoiceMatch;
        private boolean tsOperatorConfirmFlag;
        private boolean tsIsPaymentCost;
        private Object tsRebateReceiveDate;
        private Object tsRebateReceiveCreatorId;
        private Object tsRebateReceiveCreatorName;
        private Object sxUnPaymentAmount;
        private Object zxCompanyId;
        private Object zxCompanyName;
        private Object zxContactor;
        private Object zxContactWay;
        private Object zxEmail;
        private Object zxAddress;
        private Object zxFax;
        private Object zxRemark;
        private Object zxCreditInvestigationAmount;
        private Object sxPayeeId;
        private Object sxFinanceAmount;
        private Object sxPayeeName;
        private Object sxPayeeBank;
        private Object sxPayeeBankNo;
        private Object sxSinosureCurrency;
        private Object sxSinosureAmount;
        private Object sxForceLoan;
        private Object sxForceRefund;
        private Object sxFinacingDays;
        private Object sxPaymentType;
        private Object sxShipmentDate;
        private Object sxSinosureRate;
        private Object sxReservationRate;
        private Object sxFinancingProportion;
        private Object sxFinancionCreditLimit;
        private Object sxSinosureCost;
        private Object sxSinosureCostRate;
        private Object sxSinosureTtCost;
        private Object sxReservationInterest;
        private Object sxEndDate;
        private Object sxPaymentDate;
        private Object sxApprovalResults;
        private Object sxApprovalOpinion;
        private Object sxActualPaymentDate;
        private Object sxActualFkDate;
        private Object sxActualPaymentAmount;
        private Object sxActualInterest;
        private Object sxDifferenceAmount;
        private Object sxFineInterest;
        private Object sxPaymentRemark;
        private Object sxCustomCreditLimit;
        private Object sxSinosureNo;
        private Object sxSinosureCreditLimit;
        private boolean sxIsCustomsConfirm;
        private boolean sxOperatorConfirmFlag;
        private Object tsMustActualAmount;
        private String customs;
        private String customsName;
        private Object sccgJfd;
        private Object bookingType;
        private Object sccgIdentifyingInformation;
        private Object taxNature;
        private Object tsSettleFlag;
        private Object isHaveOraginalDeclaration;
        private Object exportPortAreaName;
        private Object exportPortArea;
        private Object carrierId;
        private Object carrierNameCn;
        private Object carrierNameEn;
        private Object isTransport;
        private Object freightTerms;
        private Object shippingCompanyCode;
        private Object shippingCompanyName;
        private String shippingOrderType;
        private Object expressMailNo;
        private Object deliveryWarehouseNo;
        private Object contractNo;
        private Object containerNo;
        private int id;
        private int accountId;
        private Object accountCode;
        private String accountName;
        private int accountUserId;
        private String accountUserName;
        private Object accountContactor;
        private Object accountContactPhone;
        private Object accountContactMobilePhone;
        private Object accountContactQq;
        private Object accountContactSkype;
        private int titleId;
        private String titleName;
        private Object creates;
        private Object updates;
        private Object deletes;
        private List<?> shippingDetailList;
        private List<DetailListEntity> detailList;
        private List<?> orderContainers;
        private List<?> combineDetailList;
        private List<?> detailAllList;
        private float detailTotalPlanInPackingQuantity;
        private float detailTotalActualInPackingQuantity;
        private float detailTotalPlanOutPackingQuantity;
        private float detailTotalActualOutPackingQuantity;

        public float getDetailTotalPlanInPackingQuantity() {
            return detailTotalPlanInPackingQuantity;
        }

        public void setDetailTotalPlanInPackingQuantity(float detailTotalPlanInPackingQuantity) {
            this.detailTotalPlanInPackingQuantity = detailTotalPlanInPackingQuantity;
        }

        public float getDetailTotalActualInPackingQuantity() {
            return detailTotalActualInPackingQuantity;
        }

        public void setDetailTotalActualInPackingQuantity(float detailTotalActualInPackingQuantity) {
            this.detailTotalActualInPackingQuantity = detailTotalActualInPackingQuantity;
        }

        public float getDetailTotalPlanOutPackingQuantity() {
            return detailTotalPlanOutPackingQuantity;
        }

        public void setDetailTotalPlanOutPackingQuantity(float detailTotalPlanOutPackingQuantity) {
            this.detailTotalPlanOutPackingQuantity = detailTotalPlanOutPackingQuantity;
        }

        public float getDetailTotalActualOutPackingQuantity() {
            return detailTotalActualOutPackingQuantity;
        }

        public void setDetailTotalActualOutPackingQuantity(float detailTotalActualOutPackingQuantity) {
            this.detailTotalActualOutPackingQuantity = detailTotalActualOutPackingQuantity;
        }

        public int getMatchType() {
            return matchType;
        }

        public void setMatchType(int matchType) {
            this.matchType = matchType;
        }

        public String getMatchTypeFormat() {
            return matchTypeFormat;
        }

        public void setMatchTypeFormat(String matchTypeFormat) {
            this.matchTypeFormat = matchTypeFormat;
        }

        public int getServiceType() {
            return serviceType;
        }

        public void setServiceType(int serviceType) {
            this.serviceType = serviceType;
        }

        public Object getCertificateList() {
            return certificateList;
        }

        public void setCertificateList(Object certificateList) {
            this.certificateList = certificateList;
        }

        public Object getGoodsInspection() {
            return goodsInspection;
        }

        public void setGoodsInspection(Object goodsInspection) {
            this.goodsInspection = goodsInspection;
        }

        public String getLicenseNo() {
            return licenseNo;
        }

        public void setLicenseNo(String licenseNo) {
            this.licenseNo = licenseNo;
        }

        public Object getLicenseId() {
            return licenseId;
        }

        public void setLicenseId(Object licenseId) {
            this.licenseId = licenseId;
        }

        public int getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(int ownerId) {
            this.ownerId = ownerId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Object getForeignId() {
            return foreignId;
        }

        public void setForeignId(Object foreignId) {
            this.foreignId = foreignId;
        }

        public Object getForeignName() {
            return foreignName;
        }

        public void setForeignName(Object foreignName) {
            this.foreignName = foreignName;
        }

        public Object getShippmentDate() {
            return shippmentDate;
        }

        public void setShippmentDate(Object shippmentDate) {
            this.shippmentDate = shippmentDate;
        }

        public String getExportPort() {
            return exportPort;
        }

        public void setExportPort(String exportPort) {
            this.exportPort = exportPort;
        }

        public String getExportPortCn() {
            return exportPortCn;
        }

        public void setExportPortCn(String exportPortCn) {
            this.exportPortCn = exportPortCn;
        }

        public String getExportPortEn() {
            return exportPortEn;
        }

        public void setExportPortEn(String exportPortEn) {
            this.exportPortEn = exportPortEn;
        }

        public Object getTransshippmentPort() {
            return transshippmentPort;
        }

        public void setTransshippmentPort(Object transshippmentPort) {
            this.transshippmentPort = transshippmentPort;
        }

        public Object getTransshippmentPortCn() {
            return transshippmentPortCn;
        }

        public void setTransshippmentPortCn(Object transshippmentPortCn) {
            this.transshippmentPortCn = transshippmentPortCn;
        }

        public Object getTransshippmentPortEn() {
            return transshippmentPortEn;
        }

        public void setTransshippmentPortEn(Object transshippmentPortEn) {
            this.transshippmentPortEn = transshippmentPortEn;
        }

        public Object getDestinationPort() {
            return destinationPort;
        }

        public void setDestinationPort(Object destinationPort) {
            this.destinationPort = destinationPort;
        }

        public String getDestinationPortCn() {
            return destinationPortCn;
        }

        public void setDestinationPortCn(String destinationPortCn) {
            this.destinationPortCn = destinationPortCn;
        }

        public String getDestinationPortEn() {
            return destinationPortEn;
        }

        public void setDestinationPortEn(String destinationPortEn) {
            this.destinationPortEn = destinationPortEn;
        }

        public Object getDestinationQpPort() {
            return destinationQpPort;
        }

        public void setDestinationQpPort(Object destinationQpPort) {
            this.destinationQpPort = destinationQpPort;
        }

        public Object getDestinationQpPortCn() {
            return destinationQpPortCn;
        }

        public void setDestinationQpPortCn(Object destinationQpPortCn) {
            this.destinationQpPortCn = destinationQpPortCn;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public Object getSettleAmount() {
            return settleAmount;
        }

        public void setSettleAmount(Object settleAmount) {
            this.settleAmount = settleAmount;
        }

        public Object getSettleNo() {
            return settleNo;
        }

        public void setSettleNo(Object settleNo) {
            this.settleNo = settleNo;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getPriceTerm() {
            return priceTerm;
        }

        public void setPriceTerm(String priceTerm) {
            this.priceTerm = priceTerm;
        }

        public Object getPriceTermDesc() {
            return priceTermDesc;
        }

        public void setPriceTermDesc(Object priceTermDesc) {
            this.priceTermDesc = priceTermDesc;
        }

        public Object getInsuranceTerm() {
            return insuranceTerm;
        }

        public void setInsuranceTerm(Object insuranceTerm) {
            this.insuranceTerm = insuranceTerm;
        }

        public String getPayment1() {
            return payment1;
        }

        public void setPayment1(String payment1) {
            this.payment1 = payment1;
        }

        public Object getPayment1Period() {
            return payment1Period;
        }

        public void setPayment1Period(Object payment1Period) {
            this.payment1Period = payment1Period;
        }

        public Object getPayment1Rate() {
            return payment1Rate;
        }

        public void setPayment1Rate(Object payment1Rate) {
            this.payment1Rate = payment1Rate;
        }

        public String getPayment1Desc() {
            return payment1Desc;
        }

        public void setPayment1Desc(String payment1Desc) {
            this.payment1Desc = payment1Desc;
        }

        public Object getPayment2() {
            return payment2;
        }

        public void setPayment2(Object payment2) {
            this.payment2 = payment2;
        }

        public Object getPayment2Period() {
            return payment2Period;
        }

        public void setPayment2Period(Object payment2Period) {
            this.payment2Period = payment2Period;
        }

        public Object getPayment2Rate() {
            return payment2Rate;
        }

        public void setPayment2Rate(Object payment2Rate) {
            this.payment2Rate = payment2Rate;
        }

        public Object getPayment2Desc() {
            return payment2Desc;
        }

        public void setPayment2Desc(Object payment2Desc) {
            this.payment2Desc = payment2Desc;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCommodityDescCn() {
            return commodityDescCn;
        }

        public void setCommodityDescCn(String commodityDescCn) {
            this.commodityDescCn = commodityDescCn;
        }

        public Object getCommodityDescEn() {
            return commodityDescEn;
        }

        public void setCommodityDescEn(Object commodityDescEn) {
            this.commodityDescEn = commodityDescEn;
        }

        public int getModeOfTransportation() {
            return modeOfTransportation;
        }

        public void setModeOfTransportation(int modeOfTransportation) {
            this.modeOfTransportation = modeOfTransportation;
        }

        public double getGrossWeight() {
            return grossWeight;
        }

        public void setGrossWeight(double grossWeight) {
            this.grossWeight = grossWeight;
        }

        public double getNetWeight() {
            return netWeight;
        }

        public void setNetWeight(double netWeight) {
            this.netWeight = netWeight;
        }

        public String getWeightUnit() {
            return weightUnit;
        }

        public void setWeightUnit(String weightUnit) {
            this.weightUnit = weightUnit;
        }

        public int getWeightUnitId() {
            return weightUnitId;
        }

        public void setWeightUnitId(int weightUnitId) {
            this.weightUnitId = weightUnitId;
        }

        public String getWeightUnitCn() {
            return weightUnitCn;
        }

        public void setWeightUnitCn(String weightUnitCn) {
            this.weightUnitCn = weightUnitCn;
        }

        public String getWeightUnitEn() {
            return weightUnitEn;
        }

        public void setWeightUnitEn(String weightUnitEn) {
            this.weightUnitEn = weightUnitEn;
        }

        public double getVol() {
            return vol;
        }

        public void setVol(double vol) {
            this.vol = vol;
        }

        public String getVolUnit() {
            return volUnit;
        }

        public void setVolUnit(String volUnit) {
            this.volUnit = volUnit;
        }

        public int getVolUnitId() {
            return volUnitId;
        }

        public void setVolUnitId(int volUnitId) {
            this.volUnitId = volUnitId;
        }

        public String getVolUnitCn() {
            return volUnitCn;
        }

        public void setVolUnitCn(String volUnitCn) {
            this.volUnitCn = volUnitCn;
        }

        public String getVolUnitEn() {
            return volUnitEn;
        }

        public void setVolUnitEn(String volUnitEn) {
            this.volUnitEn = volUnitEn;
        }

        public Object getModeOfPacking() {
            return modeOfPacking;
        }

        public void setModeOfPacking(Object modeOfPacking) {
            this.modeOfPacking = modeOfPacking;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public Object getRmbRate() {
            return rmbRate;
        }

        public void setRmbRate(Object rmbRate) {
            this.rmbRate = rmbRate;
        }

        public Object getUsdRate() {
            return usdRate;
        }

        public void setUsdRate(Object usdRate) {
            this.usdRate = usdRate;
        }

        public String getModeOfTrade() {
            return modeOfTrade;
        }

        public void setModeOfTrade(String modeOfTrade) {
            this.modeOfTrade = modeOfTrade;
        }

        public boolean isInspectionFlag() {
            return inspectionFlag;
        }

        public void setInspectionFlag(boolean inspectionFlag) {
            this.inspectionFlag = inspectionFlag;
        }

        public Object getFinalDestCountry() {
            return finalDestCountry;
        }

        public void setFinalDestCountry(Object finalDestCountry) {
            this.finalDestCountry = finalDestCountry;
        }

        public Object getFinalDestCountryId() {
            return finalDestCountryId;
        }

        public void setFinalDestCountryId(Object finalDestCountryId) {
            this.finalDestCountryId = finalDestCountryId;
        }

        public String getFinalDestCountryCn() {
            return finalDestCountryCn;
        }

        public void setFinalDestCountryCn(String finalDestCountryCn) {
            this.finalDestCountryCn = finalDestCountryCn;
        }

        public Object getFinalDestCountryEn() {
            return finalDestCountryEn;
        }

        public void setFinalDestCountryEn(Object finalDestCountryEn) {
            this.finalDestCountryEn = finalDestCountryEn;
        }

        public int getPackingQuantity() {
            return packingQuantity;
        }

        public void setPackingQuantity(int packingQuantity) {
            this.packingQuantity = packingQuantity;
        }

        public String getPackingUnit() {
            return packingUnit;
        }

        public void setPackingUnit(String packingUnit) {
            this.packingUnit = packingUnit;
        }

        public int getPackingUnitId() {
            return packingUnitId;
        }

        public void setPackingUnitId(int packingUnitId) {
            this.packingUnitId = packingUnitId;
        }

        public String getPackingUnitCn() {
            return packingUnitCn;
        }

        public void setPackingUnitCn(String packingUnitCn) {
            this.packingUnitCn = packingUnitCn;
        }

        public String getPackingUnitEn() {
            return packingUnitEn;
        }

        public void setPackingUnitEn(String packingUnitEn) {
            this.packingUnitEn = packingUnitEn;
        }

        public String getSalesOrder() {
            return salesOrder;
        }

        public void setSalesOrder(String salesOrder) {
            this.salesOrder = salesOrder;
        }

        public Object getSalesOrderSignDate() {
            return salesOrderSignDate;
        }

        public void setSalesOrderSignDate(Object salesOrderSignDate) {
            this.salesOrderSignDate = salesOrderSignDate;
        }

        public Object getInvoicingDate() {
            return invoicingDate;
        }

        public void setInvoicingDate(Object invoicingDate) {
            this.invoicingDate = invoicingDate;
        }

        public Object getInvoicingCompanyId() {
            return invoicingCompanyId;
        }

        public void setInvoicingCompanyId(Object invoicingCompanyId) {
            this.invoicingCompanyId = invoicingCompanyId;
        }

        public Object getInvoicingCompanyName() {
            return invoicingCompanyName;
        }

        public void setInvoicingCompanyName(Object invoicingCompanyName) {
            this.invoicingCompanyName = invoicingCompanyName;
        }

        public Object getInvoiceAmount() {
            return invoiceAmount;
        }

        public void setInvoiceAmount(Object invoiceAmount) {
            this.invoiceAmount = invoiceAmount;
        }

        public Object getSwapRate() {
            return swapRate;
        }

        public void setSwapRate(Object swapRate) {
            this.swapRate = swapRate;
        }

        public Object getConsignee() {
            return consignee;
        }

        public void setConsignee(Object consignee) {
            this.consignee = consignee;
        }

        public Object getConsignor() {
            return consignor;
        }

        public void setConsignor(Object consignor) {
            this.consignor = consignor;
        }

        public Object getNotifier() {
            return notifier;
        }

        public void setNotifier(Object notifier) {
            this.notifier = notifier;
        }

        public Object getCustomerPurchaseOrder() {
            return customerPurchaseOrder;
        }

        public void setCustomerPurchaseOrder(Object customerPurchaseOrder) {
            this.customerPurchaseOrder = customerPurchaseOrder;
        }

        public Object getClosingDate() {
            return closingDate;
        }

        public void setClosingDate(Object closingDate) {
            this.closingDate = closingDate;
        }

        public Object getShippingSchedule() {
            return shippingSchedule;
        }

        public void setShippingSchedule(Object shippingSchedule) {
            this.shippingSchedule = shippingSchedule;
        }

        public Object getShippingContractNo() {
            return shippingContractNo;
        }

        public void setShippingContractNo(Object shippingContractNo) {
            this.shippingContractNo = shippingContractNo;
        }

        public Object getForwarder() {
            return forwarder;
        }

        public void setForwarder(Object forwarder) {
            this.forwarder = forwarder;
        }

        public Object getForwardingContactor() {
            return forwardingContactor;
        }

        public void setForwardingContactor(Object forwardingContactor) {
            this.forwardingContactor = forwardingContactor;
        }

        public Object getForwardingPhone() {
            return forwardingPhone;
        }

        public void setForwardingPhone(Object forwardingPhone) {
            this.forwardingPhone = forwardingPhone;
        }

        public Object getForwardingEmail() {
            return forwardingEmail;
        }

        public void setForwardingEmail(Object forwardingEmail) {
            this.forwardingEmail = forwardingEmail;
        }

        public Object getForwardingFax() {
            return forwardingFax;
        }

        public void setForwardingFax(Object forwardingFax) {
            this.forwardingFax = forwardingFax;
        }

        public Object getForwardingRemark() {
            return forwardingRemark;
        }

        public void setForwardingRemark(Object forwardingRemark) {
            this.forwardingRemark = forwardingRemark;
        }

        public Object getForwardingAddress() {
            return forwardingAddress;
        }

        public void setForwardingAddress(Object forwardingAddress) {
            this.forwardingAddress = forwardingAddress;
        }

        public Object getFactoryName() {
            return factoryName;
        }

        public void setFactoryName(Object factoryName) {
            this.factoryName = factoryName;
        }

        public Object getFactoryContactor() {
            return factoryContactor;
        }

        public void setFactoryContactor(Object factoryContactor) {
            this.factoryContactor = factoryContactor;
        }

        public Object getFactoryPhone() {
            return factoryPhone;
        }

        public void setFactoryPhone(Object factoryPhone) {
            this.factoryPhone = factoryPhone;
        }

        public Object getFactoryEmail() {
            return factoryEmail;
        }

        public void setFactoryEmail(Object factoryEmail) {
            this.factoryEmail = factoryEmail;
        }

        public Object getFactoryFax() {
            return factoryFax;
        }

        public void setFactoryFax(Object factoryFax) {
            this.factoryFax = factoryFax;
        }

        public Object getFactoryAddress() {
            return factoryAddress;
        }

        public void setFactoryAddress(Object factoryAddress) {
            this.factoryAddress = factoryAddress;
        }

        public Object getFactoryRemark() {
            return factoryRemark;
        }

        public void setFactoryRemark(Object factoryRemark) {
            this.factoryRemark = factoryRemark;
        }

        public Object getLcNo() {
            return lcNo;
        }

        public void setLcNo(Object lcNo) {
            this.lcNo = lcNo;
        }

        public Object getLcAmount() {
            return lcAmount;
        }

        public void setLcAmount(Object lcAmount) {
            this.lcAmount = lcAmount;
        }

        public Object getCompleteDeliveryDate() {
            return completeDeliveryDate;
        }

        public void setCompleteDeliveryDate(Object completeDeliveryDate) {
            this.completeDeliveryDate = completeDeliveryDate;
        }

        public Object getDocPaymentBank() {
            return docPaymentBank;
        }

        public void setDocPaymentBank(Object docPaymentBank) {
            this.docPaymentBank = docPaymentBank;
        }

        public Object getDocPaymentDate() {
            return docPaymentDate;
        }

        public void setDocPaymentDate(Object docPaymentDate) {
            this.docPaymentDate = docPaymentDate;
        }

        public Object getForecastReceiptDate() {
            return forecastReceiptDate;
        }

        public void setForecastReceiptDate(Object forecastReceiptDate) {
            this.forecastReceiptDate = forecastReceiptDate;
        }

        public int getDocPaymentStatus() {
            return docPaymentStatus;
        }

        public void setDocPaymentStatus(int docPaymentStatus) {
            this.docPaymentStatus = docPaymentStatus;
        }

        public String getActualCustomsClearanceDate() {
            return actualCustomsClearanceDate;
        }

        public void setActualCustomsClearanceDate(String actualCustomsClearanceDate) {
            this.actualCustomsClearanceDate = actualCustomsClearanceDate;
        }

        public Object getRtnBackCustomsAmount() {
            return rtnBackCustomsAmount;
        }

        public void setRtnBackCustomsAmount(Object rtnBackCustomsAmount) {
            this.rtnBackCustomsAmount = rtnBackCustomsAmount;
        }

        public int getActualAmount() {
            return actualAmount;
        }

        public void setActualAmount(int actualAmount) {
            this.actualAmount = actualAmount;
        }

        public Object getRtnBackCustomsClearanceDate() {
            return rtnBackCustomsClearanceDate;
        }

        public void setRtnBackCustomsClearanceDate(Object rtnBackCustomsClearanceDate) {
            this.rtnBackCustomsClearanceDate = rtnBackCustomsClearanceDate;
        }

        public Object getEntrustLetter() {
            return entrustLetter;
        }

        public void setEntrustLetter(Object entrustLetter) {
            this.entrustLetter = entrustLetter;
        }

        public int getFreightChargeModeId() {
            return freightChargeModeId;
        }

        public void setFreightChargeModeId(int freightChargeModeId) {
            this.freightChargeModeId = freightChargeModeId;
        }

        public String getFreight() {
            return freight;
        }

        public void setFreight(String freight) {
            this.freight = freight;
        }

        public String getFreightCurrency() {
            return freightCurrency;
        }

        public void setFreightCurrency(String freightCurrency) {
            this.freightCurrency = freightCurrency;
        }

        public int getPremiumChargeModeId() {
            return premiumChargeModeId;
        }

        public void setPremiumChargeModeId(int premiumChargeModeId) {
            this.premiumChargeModeId = premiumChargeModeId;
        }

        public String getPremium() {
            return premium;
        }

        public void setPremium(String premium) {
            this.premium = premium;
        }

        public String getPremiumCurrency() {
            return premiumCurrency;
        }

        public void setPremiumCurrency(String premiumCurrency) {
            this.premiumCurrency = premiumCurrency;
        }

        public int getOtherFeeChargeModeId() {
            return otherFeeChargeModeId;
        }

        public void setOtherFeeChargeModeId(int otherFeeChargeModeId) {
            this.otherFeeChargeModeId = otherFeeChargeModeId;
        }

        public String getOtherFee() {
            return otherFee;
        }

        public void setOtherFee(String otherFee) {
            this.otherFee = otherFee;
        }

        public String getOtherFeeCurrency() {
            return otherFeeCurrency;
        }

        public void setOtherFeeCurrency(String otherFeeCurrency) {
            this.otherFeeCurrency = otherFeeCurrency;
        }

        public Object getIsSignAgreement() {
            return isSignAgreement;
        }

        public void setIsSignAgreement(Object isSignAgreement) {
            this.isSignAgreement = isSignAgreement;
        }

        public String getFrontMark() {
            return frontMark;
        }

        public void setFrontMark(String frontMark) {
            this.frontMark = frontMark;
        }

        public Object getSideMark() {
            return sideMark;
        }

        public void setSideMark(Object sideMark) {
            this.sideMark = sideMark;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public Object getMatchCode() {
            return matchCode;
        }

        public void setMatchCode(Object matchCode) {
            this.matchCode = matchCode;
        }

        public Object getEta() {
            return eta;
        }

        public void setEta(Object eta) {
            this.eta = eta;
        }

        public Object getBlId() {
            return blId;
        }

        public void setBlId(Object blId) {
            this.blId = blId;
        }

        public String getBlNo() {
            return blNo;
        }

        public void setBlNo(String blNo) {
            this.blNo = blNo;
        }

        public Object getBlTelexReleasedFlag() {
            return blTelexReleasedFlag;
        }

        public void setBlTelexReleasedFlag(Object blTelexReleasedFlag) {
            this.blTelexReleasedFlag = blTelexReleasedFlag;
        }

        public Object getEtd() {
            return etd;
        }

        public void setEtd(Object etd) {
            this.etd = etd;
        }

        public String getVesselName() {
            return vesselName;
        }

        public void setVesselName(String vesselName) {
            this.vesselName = vesselName;
        }

        public String getVoyageNo() {
            return voyageNo;
        }

        public void setVoyageNo(String voyageNo) {
            this.voyageNo = voyageNo;
        }

        public Object getShipper() {
            return shipper;
        }

        public void setShipper(Object shipper) {
            this.shipper = shipper;
        }

        public Object getShippingOrderNo() {
            return shippingOrderNo;
        }

        public void setShippingOrderNo(Object shippingOrderNo) {
            this.shippingOrderNo = shippingOrderNo;
        }

        public Object getPackingFactory() {
            return packingFactory;
        }

        public void setPackingFactory(Object packingFactory) {
            this.packingFactory = packingFactory;
        }

        public Object getPackingContactor() {
            return packingContactor;
        }

        public void setPackingContactor(Object packingContactor) {
            this.packingContactor = packingContactor;
        }

        public Object getPackingLocale() {
            return packingLocale;
        }

        public void setPackingLocale(Object packingLocale) {
            this.packingLocale = packingLocale;
        }

        public Object getShipperContractNo() {
            return shipperContractNo;
        }

        public void setShipperContractNo(Object shipperContractNo) {
            this.shipperContractNo = shipperContractNo;
        }

        public boolean isOperatorConfirmFlag() {
            return operatorConfirmFlag;
        }

        public void setOperatorConfirmFlag(boolean operatorConfirmFlag) {
            this.operatorConfirmFlag = operatorConfirmFlag;
        }

        public boolean isCkNeedInsurance() {
            return ckNeedInsurance;
        }

        public void setCkNeedInsurance(boolean ckNeedInsurance) {
            this.ckNeedInsurance = ckNeedInsurance;
        }

        public boolean isCkNeedAuthorityCustomsClearance() {
            return ckNeedAuthorityCustomsClearance;
        }

        public void setCkNeedAuthorityCustomsClearance(boolean ckNeedAuthorityCustomsClearance) {
            this.ckNeedAuthorityCustomsClearance = ckNeedAuthorityCustomsClearance;
        }

        public String getCkCustomsClearanceNo() {
            return ckCustomsClearanceNo;
        }

        public void setCkCustomsClearanceNo(String ckCustomsClearanceNo) {
            this.ckCustomsClearanceNo = ckCustomsClearanceNo;
        }

        public Object getTsActualDate() {
            return tsActualDate;
        }

        public void setTsActualDate(Object tsActualDate) {
            this.tsActualDate = tsActualDate;
        }

        public Object getTsActualAmount() {
            return tsActualAmount;
        }

        public void setTsActualAmount(Object tsActualAmount) {
            this.tsActualAmount = tsActualAmount;
        }

        public Object getTsRebateDeadline() {
            return tsRebateDeadline;
        }

        public void setTsRebateDeadline(Object tsRebateDeadline) {
            this.tsRebateDeadline = tsRebateDeadline;
        }

        public Object getTsDirectlyPayment() {
            return tsDirectlyPayment;
        }

        public void setTsDirectlyPayment(Object tsDirectlyPayment) {
            this.tsDirectlyPayment = tsDirectlyPayment;
        }

        public Object getTsFormalitiesCharges() {
            return tsFormalitiesCharges;
        }

        public void setTsFormalitiesCharges(Object tsFormalitiesCharges) {
            this.tsFormalitiesCharges = tsFormalitiesCharges;
        }

        public Object getTsApprovalResults() {
            return tsApprovalResults;
        }

        public void setTsApprovalResults(Object tsApprovalResults) {
            this.tsApprovalResults = tsApprovalResults;
        }

        public Object getTsApprovalOpinion() {
            return tsApprovalOpinion;
        }

        public void setTsApprovalOpinion(Object tsApprovalOpinion) {
            this.tsApprovalOpinion = tsApprovalOpinion;
        }

        public Object getTsPaymentAmount() {
            return tsPaymentAmount;
        }

        public void setTsPaymentAmount(Object tsPaymentAmount) {
            this.tsPaymentAmount = tsPaymentAmount;
        }

        public String getTsRemark() {
            return tsRemark;
        }

        public void setTsRemark(String tsRemark) {
            this.tsRemark = tsRemark;
        }

        public Object getTsCustomRebateAmount() {
            return tsCustomRebateAmount;
        }

        public void setTsCustomRebateAmount(Object tsCustomRebateAmount) {
            this.tsCustomRebateAmount = tsCustomRebateAmount;
        }

        public boolean isTsIsInvoiceMatch() {
            return tsIsInvoiceMatch;
        }

        public void setTsIsInvoiceMatch(boolean tsIsInvoiceMatch) {
            this.tsIsInvoiceMatch = tsIsInvoiceMatch;
        }

        public boolean isTsOperatorConfirmFlag() {
            return tsOperatorConfirmFlag;
        }

        public void setTsOperatorConfirmFlag(boolean tsOperatorConfirmFlag) {
            this.tsOperatorConfirmFlag = tsOperatorConfirmFlag;
        }

        public boolean isTsIsPaymentCost() {
            return tsIsPaymentCost;
        }

        public void setTsIsPaymentCost(boolean tsIsPaymentCost) {
            this.tsIsPaymentCost = tsIsPaymentCost;
        }

        public Object getTsRebateReceiveDate() {
            return tsRebateReceiveDate;
        }

        public void setTsRebateReceiveDate(Object tsRebateReceiveDate) {
            this.tsRebateReceiveDate = tsRebateReceiveDate;
        }

        public Object getTsRebateReceiveCreatorId() {
            return tsRebateReceiveCreatorId;
        }

        public void setTsRebateReceiveCreatorId(Object tsRebateReceiveCreatorId) {
            this.tsRebateReceiveCreatorId = tsRebateReceiveCreatorId;
        }

        public Object getTsRebateReceiveCreatorName() {
            return tsRebateReceiveCreatorName;
        }

        public void setTsRebateReceiveCreatorName(Object tsRebateReceiveCreatorName) {
            this.tsRebateReceiveCreatorName = tsRebateReceiveCreatorName;
        }

        public Object getSxUnPaymentAmount() {
            return sxUnPaymentAmount;
        }

        public void setSxUnPaymentAmount(Object sxUnPaymentAmount) {
            this.sxUnPaymentAmount = sxUnPaymentAmount;
        }

        public Object getZxCompanyId() {
            return zxCompanyId;
        }

        public void setZxCompanyId(Object zxCompanyId) {
            this.zxCompanyId = zxCompanyId;
        }

        public Object getZxCompanyName() {
            return zxCompanyName;
        }

        public void setZxCompanyName(Object zxCompanyName) {
            this.zxCompanyName = zxCompanyName;
        }

        public Object getZxContactor() {
            return zxContactor;
        }

        public void setZxContactor(Object zxContactor) {
            this.zxContactor = zxContactor;
        }

        public Object getZxContactWay() {
            return zxContactWay;
        }

        public void setZxContactWay(Object zxContactWay) {
            this.zxContactWay = zxContactWay;
        }

        public Object getZxEmail() {
            return zxEmail;
        }

        public void setZxEmail(Object zxEmail) {
            this.zxEmail = zxEmail;
        }

        public Object getZxAddress() {
            return zxAddress;
        }

        public void setZxAddress(Object zxAddress) {
            this.zxAddress = zxAddress;
        }

        public Object getZxFax() {
            return zxFax;
        }

        public void setZxFax(Object zxFax) {
            this.zxFax = zxFax;
        }

        public Object getZxRemark() {
            return zxRemark;
        }

        public void setZxRemark(Object zxRemark) {
            this.zxRemark = zxRemark;
        }

        public Object getZxCreditInvestigationAmount() {
            return zxCreditInvestigationAmount;
        }

        public void setZxCreditInvestigationAmount(Object zxCreditInvestigationAmount) {
            this.zxCreditInvestigationAmount = zxCreditInvestigationAmount;
        }

        public Object getSxPayeeId() {
            return sxPayeeId;
        }

        public void setSxPayeeId(Object sxPayeeId) {
            this.sxPayeeId = sxPayeeId;
        }

        public Object getSxFinanceAmount() {
            return sxFinanceAmount;
        }

        public void setSxFinanceAmount(Object sxFinanceAmount) {
            this.sxFinanceAmount = sxFinanceAmount;
        }

        public Object getSxPayeeName() {
            return sxPayeeName;
        }

        public void setSxPayeeName(Object sxPayeeName) {
            this.sxPayeeName = sxPayeeName;
        }

        public Object getSxPayeeBank() {
            return sxPayeeBank;
        }

        public void setSxPayeeBank(Object sxPayeeBank) {
            this.sxPayeeBank = sxPayeeBank;
        }

        public Object getSxPayeeBankNo() {
            return sxPayeeBankNo;
        }

        public void setSxPayeeBankNo(Object sxPayeeBankNo) {
            this.sxPayeeBankNo = sxPayeeBankNo;
        }

        public Object getSxSinosureCurrency() {
            return sxSinosureCurrency;
        }

        public void setSxSinosureCurrency(Object sxSinosureCurrency) {
            this.sxSinosureCurrency = sxSinosureCurrency;
        }

        public Object getSxSinosureAmount() {
            return sxSinosureAmount;
        }

        public void setSxSinosureAmount(Object sxSinosureAmount) {
            this.sxSinosureAmount = sxSinosureAmount;
        }

        public Object getSxForceLoan() {
            return sxForceLoan;
        }

        public void setSxForceLoan(Object sxForceLoan) {
            this.sxForceLoan = sxForceLoan;
        }

        public Object getSxForceRefund() {
            return sxForceRefund;
        }

        public void setSxForceRefund(Object sxForceRefund) {
            this.sxForceRefund = sxForceRefund;
        }

        public Object getSxFinacingDays() {
            return sxFinacingDays;
        }

        public void setSxFinacingDays(Object sxFinacingDays) {
            this.sxFinacingDays = sxFinacingDays;
        }

        public Object getSxPaymentType() {
            return sxPaymentType;
        }

        public void setSxPaymentType(Object sxPaymentType) {
            this.sxPaymentType = sxPaymentType;
        }

        public Object getSxShipmentDate() {
            return sxShipmentDate;
        }

        public void setSxShipmentDate(Object sxShipmentDate) {
            this.sxShipmentDate = sxShipmentDate;
        }

        public Object getSxSinosureRate() {
            return sxSinosureRate;
        }

        public void setSxSinosureRate(Object sxSinosureRate) {
            this.sxSinosureRate = sxSinosureRate;
        }

        public Object getSxReservationRate() {
            return sxReservationRate;
        }

        public void setSxReservationRate(Object sxReservationRate) {
            this.sxReservationRate = sxReservationRate;
        }

        public Object getSxFinancingProportion() {
            return sxFinancingProportion;
        }

        public void setSxFinancingProportion(Object sxFinancingProportion) {
            this.sxFinancingProportion = sxFinancingProportion;
        }

        public Object getSxFinancionCreditLimit() {
            return sxFinancionCreditLimit;
        }

        public void setSxFinancionCreditLimit(Object sxFinancionCreditLimit) {
            this.sxFinancionCreditLimit = sxFinancionCreditLimit;
        }

        public Object getSxSinosureCost() {
            return sxSinosureCost;
        }

        public void setSxSinosureCost(Object sxSinosureCost) {
            this.sxSinosureCost = sxSinosureCost;
        }

        public Object getSxSinosureCostRate() {
            return sxSinosureCostRate;
        }

        public void setSxSinosureCostRate(Object sxSinosureCostRate) {
            this.sxSinosureCostRate = sxSinosureCostRate;
        }

        public Object getSxSinosureTtCost() {
            return sxSinosureTtCost;
        }

        public void setSxSinosureTtCost(Object sxSinosureTtCost) {
            this.sxSinosureTtCost = sxSinosureTtCost;
        }

        public Object getSxReservationInterest() {
            return sxReservationInterest;
        }

        public void setSxReservationInterest(Object sxReservationInterest) {
            this.sxReservationInterest = sxReservationInterest;
        }

        public Object getSxEndDate() {
            return sxEndDate;
        }

        public void setSxEndDate(Object sxEndDate) {
            this.sxEndDate = sxEndDate;
        }

        public Object getSxPaymentDate() {
            return sxPaymentDate;
        }

        public void setSxPaymentDate(Object sxPaymentDate) {
            this.sxPaymentDate = sxPaymentDate;
        }

        public Object getSxApprovalResults() {
            return sxApprovalResults;
        }

        public void setSxApprovalResults(Object sxApprovalResults) {
            this.sxApprovalResults = sxApprovalResults;
        }

        public Object getSxApprovalOpinion() {
            return sxApprovalOpinion;
        }

        public void setSxApprovalOpinion(Object sxApprovalOpinion) {
            this.sxApprovalOpinion = sxApprovalOpinion;
        }

        public Object getSxActualPaymentDate() {
            return sxActualPaymentDate;
        }

        public void setSxActualPaymentDate(Object sxActualPaymentDate) {
            this.sxActualPaymentDate = sxActualPaymentDate;
        }

        public Object getSxActualFkDate() {
            return sxActualFkDate;
        }

        public void setSxActualFkDate(Object sxActualFkDate) {
            this.sxActualFkDate = sxActualFkDate;
        }

        public Object getSxActualPaymentAmount() {
            return sxActualPaymentAmount;
        }

        public void setSxActualPaymentAmount(Object sxActualPaymentAmount) {
            this.sxActualPaymentAmount = sxActualPaymentAmount;
        }

        public Object getSxActualInterest() {
            return sxActualInterest;
        }

        public void setSxActualInterest(Object sxActualInterest) {
            this.sxActualInterest = sxActualInterest;
        }

        public Object getSxDifferenceAmount() {
            return sxDifferenceAmount;
        }

        public void setSxDifferenceAmount(Object sxDifferenceAmount) {
            this.sxDifferenceAmount = sxDifferenceAmount;
        }

        public Object getSxFineInterest() {
            return sxFineInterest;
        }

        public void setSxFineInterest(Object sxFineInterest) {
            this.sxFineInterest = sxFineInterest;
        }

        public Object getSxPaymentRemark() {
            return sxPaymentRemark;
        }

        public void setSxPaymentRemark(Object sxPaymentRemark) {
            this.sxPaymentRemark = sxPaymentRemark;
        }

        public Object getSxCustomCreditLimit() {
            return sxCustomCreditLimit;
        }

        public void setSxCustomCreditLimit(Object sxCustomCreditLimit) {
            this.sxCustomCreditLimit = sxCustomCreditLimit;
        }

        public Object getSxSinosureNo() {
            return sxSinosureNo;
        }

        public void setSxSinosureNo(Object sxSinosureNo) {
            this.sxSinosureNo = sxSinosureNo;
        }

        public Object getSxSinosureCreditLimit() {
            return sxSinosureCreditLimit;
        }

        public void setSxSinosureCreditLimit(Object sxSinosureCreditLimit) {
            this.sxSinosureCreditLimit = sxSinosureCreditLimit;
        }

        public boolean isSxIsCustomsConfirm() {
            return sxIsCustomsConfirm;
        }

        public void setSxIsCustomsConfirm(boolean sxIsCustomsConfirm) {
            this.sxIsCustomsConfirm = sxIsCustomsConfirm;
        }

        public boolean isSxOperatorConfirmFlag() {
            return sxOperatorConfirmFlag;
        }

        public void setSxOperatorConfirmFlag(boolean sxOperatorConfirmFlag) {
            this.sxOperatorConfirmFlag = sxOperatorConfirmFlag;
        }

        public Object getTsMustActualAmount() {
            return tsMustActualAmount;
        }

        public void setTsMustActualAmount(Object tsMustActualAmount) {
            this.tsMustActualAmount = tsMustActualAmount;
        }

        public String getCustoms() {
            return customs;
        }

        public void setCustoms(String customs) {
            this.customs = customs;
        }

        public String getCustomsName() {
            return customsName;
        }

        public void setCustomsName(String customsName) {
            this.customsName = customsName;
        }

        public Object getSccgJfd() {
            return sccgJfd;
        }

        public void setSccgJfd(Object sccgJfd) {
            this.sccgJfd = sccgJfd;
        }

        public Object getBookingType() {
            return bookingType;
        }

        public void setBookingType(Object bookingType) {
            this.bookingType = bookingType;
        }

        public Object getSccgIdentifyingInformation() {
            return sccgIdentifyingInformation;
        }

        public void setSccgIdentifyingInformation(Object sccgIdentifyingInformation) {
            this.sccgIdentifyingInformation = sccgIdentifyingInformation;
        }

        public Object getTaxNature() {
            return taxNature;
        }

        public void setTaxNature(Object taxNature) {
            this.taxNature = taxNature;
        }

        public Object getTsSettleFlag() {
            return tsSettleFlag;
        }

        public void setTsSettleFlag(Object tsSettleFlag) {
            this.tsSettleFlag = tsSettleFlag;
        }

        public Object getIsHaveOraginalDeclaration() {
            return isHaveOraginalDeclaration;
        }

        public void setIsHaveOraginalDeclaration(Object isHaveOraginalDeclaration) {
            this.isHaveOraginalDeclaration = isHaveOraginalDeclaration;
        }

        public Object getExportPortAreaName() {
            return exportPortAreaName;
        }

        public void setExportPortAreaName(Object exportPortAreaName) {
            this.exportPortAreaName = exportPortAreaName;
        }

        public Object getExportPortArea() {
            return exportPortArea;
        }

        public void setExportPortArea(Object exportPortArea) {
            this.exportPortArea = exportPortArea;
        }

        public Object getCarrierId() {
            return carrierId;
        }

        public void setCarrierId(Object carrierId) {
            this.carrierId = carrierId;
        }

        public Object getCarrierNameCn() {
            return carrierNameCn;
        }

        public void setCarrierNameCn(Object carrierNameCn) {
            this.carrierNameCn = carrierNameCn;
        }

        public Object getCarrierNameEn() {
            return carrierNameEn;
        }

        public void setCarrierNameEn(Object carrierNameEn) {
            this.carrierNameEn = carrierNameEn;
        }

        public Object getIsTransport() {
            return isTransport;
        }

        public void setIsTransport(Object isTransport) {
            this.isTransport = isTransport;
        }

        public Object getFreightTerms() {
            return freightTerms;
        }

        public void setFreightTerms(Object freightTerms) {
            this.freightTerms = freightTerms;
        }

        public Object getShippingCompanyCode() {
            return shippingCompanyCode;
        }

        public void setShippingCompanyCode(Object shippingCompanyCode) {
            this.shippingCompanyCode = shippingCompanyCode;
        }

        public Object getShippingCompanyName() {
            return shippingCompanyName;
        }

        public void setShippingCompanyName(Object shippingCompanyName) {
            this.shippingCompanyName = shippingCompanyName;
        }

        public String getShippingOrderType() {
            return shippingOrderType;
        }

        public void setShippingOrderType(String shippingOrderType) {
            this.shippingOrderType = shippingOrderType;
        }

        public Object getExpressMailNo() {
            return expressMailNo;
        }

        public void setExpressMailNo(Object expressMailNo) {
            this.expressMailNo = expressMailNo;
        }

        public Object getDeliveryWarehouseNo() {
            return deliveryWarehouseNo;
        }

        public void setDeliveryWarehouseNo(Object deliveryWarehouseNo) {
            this.deliveryWarehouseNo = deliveryWarehouseNo;
        }

        public Object getContractNo() {
            return contractNo;
        }

        public void setContractNo(Object contractNo) {
            this.contractNo = contractNo;
        }

        public Object getContainerNo() {
            return containerNo;
        }

        public void setContainerNo(Object containerNo) {
            this.containerNo = containerNo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public Object getAccountCode() {
            return accountCode;
        }

        public void setAccountCode(Object accountCode) {
            this.accountCode = accountCode;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public int getAccountUserId() {
            return accountUserId;
        }

        public void setAccountUserId(int accountUserId) {
            this.accountUserId = accountUserId;
        }

        public String getAccountUserName() {
            return accountUserName;
        }

        public void setAccountUserName(String accountUserName) {
            this.accountUserName = accountUserName;
        }

        public Object getAccountContactor() {
            return accountContactor;
        }

        public void setAccountContactor(Object accountContactor) {
            this.accountContactor = accountContactor;
        }

        public Object getAccountContactPhone() {
            return accountContactPhone;
        }

        public void setAccountContactPhone(Object accountContactPhone) {
            this.accountContactPhone = accountContactPhone;
        }

        public Object getAccountContactMobilePhone() {
            return accountContactMobilePhone;
        }

        public void setAccountContactMobilePhone(Object accountContactMobilePhone) {
            this.accountContactMobilePhone = accountContactMobilePhone;
        }

        public Object getAccountContactQq() {
            return accountContactQq;
        }

        public void setAccountContactQq(Object accountContactQq) {
            this.accountContactQq = accountContactQq;
        }

        public Object getAccountContactSkype() {
            return accountContactSkype;
        }

        public void setAccountContactSkype(Object accountContactSkype) {
            this.accountContactSkype = accountContactSkype;
        }

        public int getTitleId() {
            return titleId;
        }

        public void setTitleId(int titleId) {
            this.titleId = titleId;
        }

        public String getTitleName() {
            return titleName;
        }

        public void setTitleName(String titleName) {
            this.titleName = titleName;
        }

        public Object getCreates() {
            return creates;
        }

        public void setCreates(Object creates) {
            this.creates = creates;
        }

        public Object getUpdates() {
            return updates;
        }

        public void setUpdates(Object updates) {
            this.updates = updates;
        }

        public Object getDeletes() {
            return deletes;
        }

        public void setDeletes(Object deletes) {
            this.deletes = deletes;
        }

        public List<?> getShippingDetailList() {
            return shippingDetailList;
        }

        public void setShippingDetailList(List<?> shippingDetailList) {
            this.shippingDetailList = shippingDetailList;
        }

        public List<DetailListEntity> getDetailList() {
            return detailList;
        }

        public void setDetailList(List<DetailListEntity> detailList) {
            this.detailList = detailList;
        }

        public List<?> getOrderContainers() {
            return orderContainers;
        }

        public void setOrderContainers(List<?> orderContainers) {
            this.orderContainers = orderContainers;
        }

        public List<?> getCombineDetailList() {
            return combineDetailList;
        }

        public void setCombineDetailList(List<?> combineDetailList) {
            this.combineDetailList = combineDetailList;
        }

        public List<?> getDetailAllList() {
            return detailAllList;
        }

        public void setDetailAllList(List<?> detailAllList) {
            this.detailAllList = detailAllList;
        }
    }

    public static class OrderItemsEntity implements Serializable {
        private Object nodePositionId;
        private Object nodePositionName;
        private String statusCn;
        private boolean operable;
        private String itemMetaDataName;
        private int operatorId;
        private String operatorName;
        private String createDate;
        private Object curNodeDisplay;
        private Object attachments;
        private boolean enabled;
        private boolean isUsed;
        private boolean removeFlag;
        private int id;
        private Object customStatus;
        private int ownerId;
        private int status;
        private int subStatus;
        private Object subStatusName;
        private Object handlingFee;
        private int itemMetaDataId;
        private Object deductType;
        /**
         * id : 3139601
         * ownerId : 3139501
         * settlementId : 700
         * formula : 700#$#
         * deductType : 0
         */

        private List<AgreementEntity> agreement;
        /**
         * id : 2983301
         * name : 新制
         * seq : 1
         * createDate : null
         * creator : null
         * lockChargeRate : true
         * actionName : 录入
         * positionName : null
         * nodeStatus : 1
         * fallbackFlag : false
         * description : 等待经营单位录入进仓单明细和进仓单附带的报关信息
         * actionStatus : {"id":3139906,"itemId":3139501,"itemMetaDataId":1011,"operatorId":0,"operatorName":null,"operateDate":"2015-11-24T15:19:00","status":"进行中","description":"接收服务项","inner":false}
         * actionExcuted : true
         */

        private List<NodesEntity> nodes;
        private List<?> depends;
        private List<?> mutexes;

        public void setNodePositionId(Object nodePositionId) {
            this.nodePositionId = nodePositionId;
        }

        public void setNodePositionName(Object nodePositionName) {
            this.nodePositionName = nodePositionName;
        }

        public void setStatusCn(String statusCn) {
            this.statusCn = statusCn;
        }

        public void setOperable(boolean operable) {
            this.operable = operable;
        }

        public void setItemMetaDataName(String itemMetaDataName) {
            this.itemMetaDataName = itemMetaDataName;
        }

        public void setOperatorId(int operatorId) {
            this.operatorId = operatorId;
        }

        public void setOperatorName(String operatorName) {
            this.operatorName = operatorName;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public void setCurNodeDisplay(Object curNodeDisplay) {
            this.curNodeDisplay = curNodeDisplay;
        }

        public void setAttachments(Object attachments) {
            this.attachments = attachments;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public void setIsUsed(boolean isUsed) {
            this.isUsed = isUsed;
        }

        public void setRemoveFlag(boolean removeFlag) {
            this.removeFlag = removeFlag;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setCustomStatus(Object customStatus) {
            this.customStatus = customStatus;
        }

        public void setOwnerId(int ownerId) {
            this.ownerId = ownerId;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setSubStatus(int subStatus) {
            this.subStatus = subStatus;
        }

        public void setSubStatusName(Object subStatusName) {
            this.subStatusName = subStatusName;
        }

        public void setHandlingFee(Object handlingFee) {
            this.handlingFee = handlingFee;
        }

        public void setItemMetaDataId(int itemMetaDataId) {
            this.itemMetaDataId = itemMetaDataId;
        }

        public void setDeductType(Object deductType) {
            this.deductType = deductType;
        }

        public void setAgreement(List<AgreementEntity> agreement) {
            this.agreement = agreement;
        }

        public void setNodes(List<NodesEntity> nodes) {
            this.nodes = nodes;
        }

        public void setDepends(List<?> depends) {
            this.depends = depends;
        }

        public void setMutexes(List<?> mutexes) {
            this.mutexes = mutexes;
        }

        public Object getNodePositionId() {
            return nodePositionId;
        }

        public Object getNodePositionName() {
            return nodePositionName;
        }

        public String getStatusCn() {
            return statusCn;
        }

        public boolean isOperable() {
            return operable;
        }

        public String getItemMetaDataName() {
            return itemMetaDataName;
        }

        public int getOperatorId() {
            return operatorId;
        }

        public String getOperatorName() {
            return operatorName;
        }

        public String getCreateDate() {
            return createDate;
        }

        public Object getCurNodeDisplay() {
            return curNodeDisplay;
        }

        public Object getAttachments() {
            return attachments;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public boolean isIsUsed() {
            return isUsed;
        }

        public boolean isRemoveFlag() {
            return removeFlag;
        }

        public int getId() {
            return id;
        }

        public Object getCustomStatus() {
            return customStatus;
        }

        public int getOwnerId() {
            return ownerId;
        }

        public int getStatus() {
            return status;
        }

        public int getSubStatus() {
            return subStatus;
        }

        public Object getSubStatusName() {
            return subStatusName;
        }

        public Object getHandlingFee() {
            return handlingFee;
        }

        public int getItemMetaDataId() {
            return itemMetaDataId;
        }

        public Object getDeductType() {
            return deductType;
        }

        public List<AgreementEntity> getAgreement() {
            return agreement;
        }

        public List<NodesEntity> getNodes() {
            return nodes;
        }

        public List<?> getDepends() {
            return depends;
        }

        public List<?> getMutexes() {
            return mutexes;
        }

        public static class AgreementEntity implements Serializable {
            private int id;
            private int ownerId;
            private int settlementId;
            private String formula;
            private int deductType;

            public void setId(int id) {
                this.id = id;
            }

            public void setOwnerId(int ownerId) {
                this.ownerId = ownerId;
            }

            public void setSettlementId(int settlementId) {
                this.settlementId = settlementId;
            }

            public void setFormula(String formula) {
                this.formula = formula;
            }

            public void setDeductType(int deductType) {
                this.deductType = deductType;
            }

            public int getId() {
                return id;
            }

            public int getOwnerId() {
                return ownerId;
            }

            public int getSettlementId() {
                return settlementId;
            }

            public String getFormula() {
                return formula;
            }

            public int getDeductType() {
                return deductType;
            }
        }

        public static class NodesEntity implements Serializable {
            private int id;
            private String name;
            private int seq;
            private Object createDate;
            private Object creator;
            private boolean lockChargeRate;
            private String actionName;
            private Object positionName;
            private int nodeStatus;
            private boolean fallbackFlag;
            private String description;
            /**
             * id : 3139906
             * itemId : 3139501
             * itemMetaDataId : 1011
             * operatorId : 0
             * operatorName : null
             * operateDate : 2015-11-24T15:19:00
             * status : 进行中
             * description : 接收服务项
             * inner : false
             */

            private ActionStatusEntity actionStatus;
            private boolean actionExcuted;

            public void setId(int id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setSeq(int seq) {
                this.seq = seq;
            }

            public void setCreateDate(Object createDate) {
                this.createDate = createDate;
            }

            public void setCreator(Object creator) {
                this.creator = creator;
            }

            public void setLockChargeRate(boolean lockChargeRate) {
                this.lockChargeRate = lockChargeRate;
            }

            public void setActionName(String actionName) {
                this.actionName = actionName;
            }

            public void setPositionName(Object positionName) {
                this.positionName = positionName;
            }

            public void setNodeStatus(int nodeStatus) {
                this.nodeStatus = nodeStatus;
            }

            public void setFallbackFlag(boolean fallbackFlag) {
                this.fallbackFlag = fallbackFlag;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public void setActionStatus(ActionStatusEntity actionStatus) {
                this.actionStatus = actionStatus;
            }

            public void setActionExcuted(boolean actionExcuted) {
                this.actionExcuted = actionExcuted;
            }

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public int getSeq() {
                return seq;
            }

            public Object getCreateDate() {
                return createDate;
            }

            public Object getCreator() {
                return creator;
            }

            public boolean isLockChargeRate() {
                return lockChargeRate;
            }

            public String getActionName() {
                return actionName;
            }

            public Object getPositionName() {
                return positionName;
            }

            public int getNodeStatus() {
                return nodeStatus;
            }

            public boolean isFallbackFlag() {
                return fallbackFlag;
            }

            public String getDescription() {
                return description;
            }

            public ActionStatusEntity getActionStatus() {
                return actionStatus;
            }

            public boolean isActionExcuted() {
                return actionExcuted;
            }

            public static class ActionStatusEntity implements Serializable {
                private int id;
                private int itemId;
                private int itemMetaDataId;
                private int operatorId;
                private Object operatorName;
                private String operateDate;
                private String status;
                private String description;
                private boolean inner;

                public void setId(int id) {
                    this.id = id;
                }

                public void setItemId(int itemId) {
                    this.itemId = itemId;
                }

                public void setItemMetaDataId(int itemMetaDataId) {
                    this.itemMetaDataId = itemMetaDataId;
                }

                public void setOperatorId(int operatorId) {
                    this.operatorId = operatorId;
                }

                public void setOperatorName(Object operatorName) {
                    this.operatorName = operatorName;
                }

                public void setOperateDate(String operateDate) {
                    this.operateDate = operateDate;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public void setInner(boolean inner) {
                    this.inner = inner;
                }

                public int getId() {
                    return id;
                }

                public int getItemId() {
                    return itemId;
                }

                public int getItemMetaDataId() {
                    return itemMetaDataId;
                }

                public int getOperatorId() {
                    return operatorId;
                }

                public Object getOperatorName() {
                    return operatorName;
                }

                public String getOperateDate() {
                    return operateDate;
                }

                public String getStatus() {
                    return status;
                }

                public String getDescription() {
                    return description;
                }

                public boolean isInner() {
                    return inner;
                }
            }
        }
    }
}
