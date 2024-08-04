package org.example;

import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

// 클라이언트 요청을 핸들러 해주는 클래스
// 이 클래스는 스레드 여야 한다.
// Runnable을 구현한 구현체(쓰레드)
public class ClientRequestHandler implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ClientRequestHandler.class);

    private final Socket clientSocket;

    public ClientRequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        /**
         * Step2. - 사용자 요청이 들어올 때마다 Thread를 새로 생성해서 사용자 요청을 처리하도록 한다.
         */
        // 미니 웹 어플리케이션 서버를 만들기
        // 미니 톰캣
        // 단점 ) 클라이언트에 요청시 메인 스레드에서 작업한다는것
        // => 해당 작업을 수행하며 블럭킹이 걸리게 된다면 다음 클라이언트의 요청이 해당요청이 올때까지 기다려야하는 문제 발생
        // => 그래서, 해당 요청이 클라이언트의 요청이 들어올때 마다 별도의 메인스레드가 아닌 별도 스레드에서 수행할수 있도록 한다.(step2)

        // 클라이언트의 요청이 들어오면, 클라이언트의 요청 별로 별도의 스레드가 만들어지고,
        // 그 스레드 별로, 인풋스트림과 아웃풋스트림을 얻을 것,
        // 그다음, 해당하는 작업을 수행후,
        // response 를 클라이언트 에게 전달해주는 형태로 구현
        logger.info("Starting ClientRequestHandler : new client {} started.",Thread.currentThread().getName());
        try (
                //STREAM IN
                InputStream in = clientSocket.getInputStream();
                //STREAM OUT
                OutputStream out = clientSocket.getOutputStream()
        ){
            // reader
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            // output
            DataOutputStream dos = new DataOutputStream(out);

            //HttpRequest ,프로토콜에 맞게 request 객체를 생성 -> 클라이언트와 통신~
            HttpRequest httpRequest = new HttpRequest(br);
            //RequestLine.method 가 GET && RequestLine.urlpath 와 /calculate가 같으면
            if (httpRequest.isGetRequest() && httpRequest.matchPath("/calculate")) {
                QueryStrings queryStrings = httpRequest.getQueryStrings(); //QueryStrings(일급 컬렉션)이 만들어진다.(get~)

                // getValue 는 입력한 key값이 queryStrings의 key값과 같으면 value 값을 가져와 첫번째줄을 가져오는
                int operand1 = Integer.parseInt(queryStrings.getValue("operand1"));
                String operator = queryStrings.getValue("operator");
                int operand2 = Integer.parseInt(queryStrings.getValue("operand2"));

                // 연산을 해준다.
                int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));

                // result 로 HttpResponse 만들어준다
                byte[] body = String.valueOf(result).getBytes();
                // 프로토콜에 맞게 response 객체를 생성 -> 클라이언트와 통신~
                HttpResponse response = new HttpResponse((dos));
                response.response200Header("application/json", body.length);
                response.responseBody(body);
            }
        }
        catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

    }
}
