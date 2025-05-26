package com.project.likelion13thbe.global.auth.email;


import org.springframework.stereotype.Component;

@Component
public class EmailTemplateBuilder {
    /**
     * 임시 비밀번호 발급 이메일 HTML 템플릿
     */
    public String buildTempPasswordHtml(String tempPassword) {
        return """
            <html>
              <body style="font-family: Arial, sans-serif;">
                <h2>임시 비밀번호 발급 안내</h2>
                <p>임시 비밀번호는 아래와 같습니다. 로그인 후 반드시 비밀번호를 변경해주세요.</p>
                <div style="font-size: 20px; font-weight: bold; color: #dc3545; margin-top: 10px;">
                  %s
                </div>
                <p style="margin-top: 10px;">보안을 위해 임시 비밀번호는 1회성입니다.</p>
              </body>
            </html>
        """.formatted(tempPassword);
    }
}
