package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CustomWebApplicationServer {
    private final int port;

    // 다양한 api 가 있지만 해당 newfixedthreadpool 을 사용해 보겠따.
    private ExecutorService executorService = Executors.newFixedThreadPool(10);
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
                 * step3 - Thread Pool을 적용해 안정적인 서비스가 가능하도록 한다.
                 */
                executorService.execute(new ClientRequestHandler(clientSocket));

//                /**
//                 * step2 - 사용자 요청이 들어올때 마다 Tread를 새로 생성해서 사용자 요청을 처리하도록 한다.
//                 */
//                // 단점 ) 사용자가 요청이 들어올때마다 스레드를 새로 생성하기 때문에,
//                // 스레드는 생성될때마다 독립적인 스택 메모리 공간을 할당받는데, 상당히 비싼 작업이다.
//                // 따라서, 성능이 매우 떨어진다. 퍼포먼스 측면에서도 좋지 않고
//                // 동시 접속자 수가 많아질 경우 많은 스레드가 생성되어 cpu 컨테스트 스위칭 횟수도 증가, cpu 와 메모리 사용량이 굉장히 증가
//                // 서버의 리소스가 감당하지 못해 서버가 다운될수도 있는 가능성도 잇다.
//                // 그래서 사용자으 요청이 들어올때마다 스레드를 생성하는 게 아니라
//                // 스레드를 이미 고정된 개수만큼 생성해두고 이를 재활용하는 스레드 풀 개념을 적용해야!
//
//                // 클라이언트가 연결될때 마다.new 쓰레드를 만들고, clientSocket을 가지고 인풋스트림을 하고 아웃풋스트림을 하는 형태로 구현될것.
//                new Thread(new ClientRequestHandler(clientSocket)).start();


//                /**
//                 * Step1 - 사용자 요청을 메인 Thread가 처리하도록 한다.
//                 */
//                try(
//                        //STREAM IN
//                        InputStream in = clientSocket.getInputStream();
//                        //STREAM OUT
//                        OutputStream out = clientSocket.getOutputStream()
//                ){
//                    // reader
//                    BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
//                    // output
//                    DataOutputStream dos = new DataOutputStream(out);
//
//                    //HttpRequest ,프로토콜에 맞게 request 객체를 생성 -> 클라이언트와 통신~
//                    HttpRequest httpRequest = new HttpRequest(br);
//                    //RequestLine.method 가 GET && RequestLine.urlpath 와 /calculate가 같으면
//                    if(httpRequest.isGetRequest() && httpRequest.matchPath("/calculate")){
//                        QueryStrings queryStrings = httpRequest.getQueryStrings(); //QueryStrings(일급 컬렉션)이 만들어진다.(get~)
//
//                        // getValue 는 입력한 key값이 queryStrings의 key값과 같으면 value 값을 가져와 첫번째줄을 가져오는
//                        int operand1 = Integer.parseInt(queryStrings.getValue("operand1"));
//                        String operator = queryStrings.getValue("operator");
//                        int operand2 =  Integer.parseInt(queryStrings.getValue("operand2"));
//
//                        // 연산을 해준다.
//                        int result = Calculator.calculate(new PositiveNumber(operand1),operator,new PositiveNumber(operand2));
//
//                        // result 로 HttpResponse 만들어준다
//                        byte[] body = String.valueOf(result).getBytes();
//                        // 프로토콜에 맞게 response 객체를 생성 -> 클라이언트와 통신~
//                        HttpResponse response = new HttpResponse((dos));
//                        response.response200Header("application/json", body.length);
//                        response.responseBody(body);
//                    }
//                }

            }
        }
    }



}