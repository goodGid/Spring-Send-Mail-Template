package dev.be.smtp.service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    public void sendMail() {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);

            /**
             220214 (Mon)
             - from의 format이 "이름 <주소@도메인>" 형태로 작성해야한다.
             - 주소를 다른 값을 넣어도 application.yaml에 정의한 값이 들어간다.
             - 이유를 debug 해봤으나 모르겠다.
             */
            String from = "신기용 <hello.goodgid@gmail.com>";
            String to = "hello.goodgid@gmail.com";
            String replyTo = "goodgid-reply@be.com";
            String subject = "[Subject] 제목 테스트";
            String text = "[Text] 내용 테스트 !";

            // validate address
            InternetAddress internetAddress = new InternetAddress(to);
            internetAddress.validate();

            helper.setFrom(from);
            helper.setTo(to);
            helper.setReplyTo(replyTo);
            helper.setSubject(subject);
            helper.setText(text, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            log.error("[MessagingException] ", e);
        }
    }
}


/*
https://mjml.io/try-it-live/

// 피치플래너 email format
<mjml>
  <mj-font name="Pretendard" href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css" />
  <mj-body background-color="#f8f9fa">
    <mj-spacer height="10%" />
    <mj-section background-color="white" padding="4% 2% 2% 2%">
      <mj-column>

        <mj-image align='left' width="150px" src="https://peach-planner-static.s3.ap-northeast-2.amazonaws.com/peachplanner/ic_logo.png" />

        <mj-spacer height="15px" />

        <mj-text font-size="20px" font-weight="1000" color="#000000" font-family="SpoqaHanSans">피치플래너 회원가입이 완료되었습니다.</mj-text>
        <mj-spacer height="10px" />

        <mj-text font-size="16px" color="#495057" font-family="SpoqaHanSans">
          안녕하세요 {{nickName}}님!<br />
          웨딩플래너 검색 서비스 피치플래너 가입을 진심으로 축하드립니다.
        </mj-text>
        <mj-spacer height="10px" />
        <mj-divider border-color="#E5E7EB" border-width="1px" />

        <mj-button href="https://peachplanner.com" font-family="SpoqaHanSans" background-color="#e64980" color="white" width="240px" height="40px" padding-top="20px" padding-bottom="30px">피치플래너 홈
        </mj-button>
      </mj-column>
    </mj-section>
    <mj-section background-color="#f1f3f5">
      <mj-column>
        <mj-text align="left" font-family="SpoqaHanSans" font-size="12px" line-height="0.5" color="#868e96">
          본 메일은 발신 전용으로 회신이 되지 않습니다.
        </mj-text>
        <mj-text align="left" font-family="SpoqaHanSans" font-size="12px" line-height="0.5" color="#868e96">
          Peachplanner. All Rights Reserved.
        </mj-text>
        <mj-text align="left" font-family="Pretendard, Arial" font-size="12px" line-height="0.5" color="#868e96">
          <a href="https://peachplanner.com/termsOfUse" target="_blank" rel="noopener noreferrer nofollow">이용약관</a>
          <span>·</span>
          <a href="https://peachplanner.com/privacyPolicy" target="_blank" rel="noopener noreferrer nofollow">개인정보처리방침</a>
        </mj-text>
      </mj-column>
    </mj-section>
  </mj-body>
</mjml>
 */