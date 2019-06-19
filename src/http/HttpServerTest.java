package http;

/**
 * @author xiaoyang.wen
 * @date 2019/6/18 15:38
 */
public class HttpServerTest {

    public static void main(String[] args) throws Exception {
        SimpleHttpServer.setBasePath("C:/Users/wenx004/Desktop");
        SimpleHttpServer.start();
    }

}
