package org.example;

import org.example.calculator.domain.PositiveNumber;
import org.example.calculator.domain.Calculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class CustomWebApplicationServer {
    private final int port;

    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);
    public CustomWebApplicationServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(port)){
            logger.info("[CustomWebApplicationServer] Listening on port {}", port);

            Socket clientSocket;
            logger.info("[CustomWebApplicationServer] Waiting for client connection...");

            while ((clientSocket = serverSocket.accept()) != null){
                logger.info("[CustomWebApplicationServer] Accepted connection from {}", clientSocket.getRemoteSocketAddress());
                /**
                 * Step1 - 사용자 요청을 메인 Thread가 처리하도록 한다.
                 */
                try(
                    //STREAM IN
                    InputStream in = clientSocket.getInputStream();
                    //STREAM OUT
                    OutputStream out = clientSocket.getOutputStream()){
                        // reader
                        BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                        // output
                        DataOutputStream dos = new DataOutputStream(out);

                        //HttpRequest ,프로토콜에 맞게 request 객체를 생성 -> 클라이언트와 통신~
                        HttpRequest httpRequest = new HttpRequest(br);
                        //RequestLine.method 가 GET && RequestLine.urlpath 와 /calculate가 같으면
                        if(httpRequest.isGetRequest() && httpRequest.matchPath("/calculate")){
                            QueryStrings queryStrings = httpRequest.getQueryStrings(); //QueryStrings(일급 컬렉션)이 만들어진다.(get~)

                            // getValue 는 입력한 key값이 queryStrings의 key값과 같으면 value 값을 가져와 첫번째줄을 가져오는
                            int operand1 = Integer.parseInt(queryStrings.getValue("operand1"));
                            String operator = queryStrings.getValue("operator");
                            int operand2 =  Integer.parseInt(queryStrings.getValue("operand2"));

                            // 연산을 해준다.
                            int result = Calculator.calculate(new PositiveNumber(operand1),operator,new PositiveNumber(operand2));

                            // result 로 HttpResponse 만들어준다
                            byte[] body = String.valueOf(result).getBytes();
                            // 프로토콜에 맞게 response 객체를 생성 -> 클라이언트와 통신~
                            HttpResponse response = new HttpResponse((dos));
                            response.response200Header("application/json", body.length);
                            response.responseBody(body);
                        }
                    }

            }
        }
    }



}
