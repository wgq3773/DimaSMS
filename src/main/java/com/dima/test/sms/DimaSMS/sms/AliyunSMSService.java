package com.dima.test.sms.DimaSMS.sms;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.dima.test.sms.DimaSMS.dto.SmsSendDTO;

/**
 * Created on 17/6/7.
 * 短信API产品的DEMO程序,工程中包含了一个SmsDemo类，直接通过
 * 执行main函数即可体验短信产品API功能(只需要将AK替换成开通了云通信-短信产品功能的AK即可)
 * 工程依赖了2个jar包(存放在工程的libs目录下)
 * 1:aliyun-java-sdk-core.jar
 * 2:aliyun-java-sdk-dysmsapi.jar
 * 备注:Demo工程编码采用UTF-8
 * 国际短信发送请勿参照此DEMO
 */
public class AliyunSMSService {
	
	private static Log log = LogFactory.getLog(AliyunSMSService.class);

    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";
    
    /**
     * 发送短信
     * @param smsSendDTO
     * @return
     * @throws ClientException
     */
	public static SendSmsResponse sendSms(SmsSendDTO smsSendDTO) {
		log.info("开始发送短信,请求smsSendDTO-->" + smsSendDTO);
		SendSmsResponse sendSmsResponse = null;
		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		// 必填:待发送手机号
		request.setPhoneNumbers(smsSendDTO.getPhoneNumbers());
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName(smsSendDTO.getSignName());
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(smsSendDTO.getTemplateCode());
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		// 您有新订单，收货人：${consignee}，联系方式：${number}，请您及时查收！谢谢！
		request.setTemplateParam(smsSendDTO.getTemplateParam());

		// 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");

		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId(smsSendDTO.getOutId());

		try {
			// 初始化acsClient,暂不支持region化
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsSendDTO.getAccessKeyId(), smsSendDTO.getAccessKeySecret());
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);

			// hint 此处可能会抛出异常，注意catch
			sendSmsResponse = acsClient.getAcsResponse(request);
		} catch (Exception e) {
			log.info("短信发送异常:" + e);
		}
		if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			log.info("短信发送成功！");
		} else {
			log.info("短信发送失败！");
		}
		return sendSmsResponse;
	}

    /**
     * 查询短信发送结果
     * @param smsSendDTO
     * @return
     * @throws ClientException
     */
    public static QuerySendDetailsResponse querySendDetails(SmsSendDTO smsSendDTO) throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsSendDTO.getAccessKeyId(), smsSendDTO.getAccessKeySecret());
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber(smsSendDTO.getPhoneNumbers());
        //可选-流水号
        request.setBizId(smsSendDTO.getBizId());
        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd
        request.setSendDate(DateFormatUtils.format(new Date(), "yyyyMMdd"));
        //必填-页大小
        request.setPageSize(10L);
        //必填-当前页码从1开始计数
        request.setCurrentPage(1L);

        //hint 此处可能会抛出异常，注意catch
        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);

        return querySendDetailsResponse;
    }

//    public static void main(String[] args) throws ClientException, InterruptedException {
//        //发短信
//        SendSmsResponse response = sendSms();
//        System.out.println("短信接口返回的数据----------------");
//        System.out.println("Code=" + response.getCode());
//        System.out.println("Message=" + response.getMessage());
//        System.out.println("RequestId=" + response.getRequestId());
//        System.out.println("BizId=" + response.getBizId());
//
//        Thread.sleep(3000L);
//
//        //查明细
//        if(response.getCode() != null && response.getCode().equals("OK")) {
//            QuerySendDetailsResponse querySendDetailsResponse = querySendDetails(response.getBizId());
//            System.out.println("短信明细查询接口返回数据----------------");
//            System.out.println("Code=" + querySendDetailsResponse.getCode());
//            System.out.println("Message=" + querySendDetailsResponse.getMessage());
//            int i = 0;
//            for(QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs())
//            {
//                System.out.println("SmsSendDetailDTO["+i+"]:");
//                System.out.println("Content=" + smsSendDetailDTO.getContent());
//                System.out.println("ErrCode=" + smsSendDetailDTO.getErrCode());
//                System.out.println("OutId=" + smsSendDetailDTO.getOutId());
//                System.out.println("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
//                System.out.println("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());
//                System.out.println("SendDate=" + smsSendDetailDTO.getSendDate());
//                System.out.println("SendStatus=" + smsSendDetailDTO.getSendStatus());
//                System.out.println("Template=" + smsSendDetailDTO.getTemplateCode());
//            }
//            System.out.println("TotalCount=" + querySendDetailsResponse.getTotalCount());
//            System.out.println("RequestId=" + querySendDetailsResponse.getRequestId());
//        }
//
//    }
}
