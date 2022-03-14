package org.bitrix.utility;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;

/*I have created two Slack utilities to send real-time test results and the test execution reports to the Slack channel. 
 * Following are the methods : 

1. sendTestExecutionStatusToSlack  : helps to send the test results to the Slack channel with the help of the Webhook URL 
                                                               and channel name.
2. sendTestExecutionReportToSlack : helps to send the automation test execution report with the help of file upload API, 
                                                              Bot User OAuth Token, and channel name. Following are the detailed implementation of both methods,

*/
public class SendReportOnSlackChannel {
	
	private static String urlSlackWebHook = "https://hooks.slack.com/services/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";
	private static String channelName = "automation";
	private static String botUserOAuthAccessToken = "xoxb-2830092992837-3209302050400-hcBIewdcrrkno4pOBUCbIKGf";
	
	
	
	public void sendTestExecutionStatusToSlack(String message) throws Exception {
		try {
		StringBuilder messageBuider = new StringBuilder();
		messageBuider.append(message);
		Payload payload = Payload.builder().channel(channelName).text(messageBuider.toString()).build();

		WebhookResponse webhookResponse = Slack.getInstance().send(urlSlackWebHook, payload);
		webhookResponse.getMessage();
		} catch (IOException e) {
		System.out.println("Unexpected Error! WebHook : " + urlSlackWebHook);
		}
		}
	
	
	public InputStream sendTestExecutionReportToSlack(String testReportPath) throws Exception {
		String url = "https://slack.com/api/files.upload";
		HttpEntity result = null;
		try {
				HttpClient httpclient = HttpClientBuilder.create().disableContentCompression().build();
				HttpPost httppost = new HttpPost(url);
				MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			FileBody fileBody = new FileBody(new File(testReportPath));
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			builder.addPart("file", fileBody);
			builder.addTextBody("channels", channelName);
			builder.addTextBody("token", botUserOAuthAccessToken);
			httppost.setEntity(builder.build());
			HttpResponse response = null;
			response = httpclient.execute(httppost);
			 result = response.getEntity();
			} catch (Exception e) {
			e.printStackTrace();
			}
	
			return result.getContent();
			}
}
