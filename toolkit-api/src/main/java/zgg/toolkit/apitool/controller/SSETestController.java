package zgg.toolkit.apitool.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by zgg on 2018/09/14
 */

@RestController
@RequestMapping("/sse")
public class SSETestController {

    @GetMapping(path = "/1", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter handle() throws IOException, InterruptedException {
        SseEmitter emitter = new SseEmitter();
        while (true){
            Thread.sleep(1000);
            emitter.send("hello world");
//        emitter.complete();
            return emitter;
        }
    }

    @GetMapping("/2")
    public void push(HttpServletResponse response) {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("utf-8");
        while (true) {
            Random r = new Random();
            try {
                Thread.sleep(1000);
                PrintWriter pw = response.getWriter();
                pw.write("data:Testing 1,2,3" + r.nextInt() + "\n\n");
                pw.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/3")
    public void test(HttpServletResponse response) throws IOException, InterruptedException {
        response.setContentType("text/event-stream;charset=UTF-8");
        while (true){
            Thread.sleep(1000);
            PrintWriter out = response.getWriter();
            out.println("data: Hello World");
            // 5分钟后重试
            out.println("retry: 30000");
            out.flush();
        }

    }
}
