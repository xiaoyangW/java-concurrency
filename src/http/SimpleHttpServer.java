package http;

import threadpool.DefaultThreadPool;
import threadpool.ThreadPool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 一个简单的Web服务器
 *
 * @author xiaoyang.wen
 * @date 2019/6/18 14:27
 */
public class SimpleHttpServer {

    static ThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<>(1);

    static String basePath;

    static ServerSocket serverSocket;

    static int port = 8080;

    public static void setPort(int port) {
        if (port>0) {
            SimpleHttpServer.port = port;
        }
    }
    public static void setBasePath(String basePath){
        if (basePath!=null&&new File(basePath).exists()&&new File(basePath).isDirectory()){
            SimpleHttpServer.basePath=basePath;
        }
    }

    public static void start()throws Exception{
        serverSocket = new ServerSocket(port);
        Socket socket = null;
        while ((socket = serverSocket.accept())!=null){
            threadPool.execute(new HttpRequestHandler(socket));
        }
    }

    static class HttpRequestHandler implements Runnable {

        private Socket socket;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            BufferedReader bufferedReader =null;
            PrintWriter out = null;
            InputStream in = null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
                String line = bufferedReader.readLine();
                String filePath = basePath + line.split(" ")[1];
                out = new PrintWriter(socket.getOutputStream());
                if (filePath.endsWith("jpg") || filePath.endsWith("ico")) {
                    in = new FileInputStream(filePath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int i;
                    while ((i = in.read()) != -1) {
                        baos.write(i);
                    }
                    byte[] array = baos.toByteArray();
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("content-Type: image/jpge");
                    out.println("Content-Length: " + array.length);
                    out.println("");
                    //socket.getOutputStream().write(array, 0, array.length);
                } else {
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    out = new PrintWriter(socket.getOutputStream());
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type: text/html; charset=UTF-8");
                    out.println("");
                    while ((line = bufferedReader.readLine()) != null) {
                        out.println(line);
                    }
                }
                out.flush();
            } catch (Exception e) {
                if (out != null) {
                    out.println("HTTP/1.1 500");
                    out.println("");
                    out.flush();
                }
                e.printStackTrace();
            } finally {
                close(bufferedReader, out, socket);
            }
        }
        // 关闭流或者Socket
        private static void close(Closeable... closeables) {
            if (closeables != null) {
                for (Closeable closeable : closeables) {
                    try {
                        closeable.close();
                    } catch (Exception ex) {
                    }
                }
            }
        }
    }

}
