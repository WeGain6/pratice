import com.alibaba.fastjson2.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * @ClassName: ZZTest
 * @Description:
 * @Author: XuWei
 * @Date: 2023-05-24 12:34
 */
public class ZZTest {

    private static String bFolderPathPrefix = "C:\\Users\\Lenovo\\Desktop\\test";

    private static String aFolderPath = "C:\\Users\\Lenovo\\Music\\16K WAV格式";

    private static List<String> bFolderPath = new ArrayList<>();

    private static final String REGISTER_URL = "http://47.108.228.186:8899/wav/enroll";

    private static final String VERIFY_URL = "http://47.108.228.186:8899/wav/verify";

    private static String record_file="C:\\Users\\Lenovo\\Desktop\\result.txt";

    private static PrintWriter pw = null;


    public static void main(String[] args) {
        getFileNames(aFolderPath);
        handleFile(bFolderPath);
    }

    public static void log(String result){
        try {
            FileOutputStream fos = new FileOutputStream(record_file,true);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            pw = new PrintWriter(osw);
            pw.println(result);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            pw.close();
        }
    }

//    public static String doPost(String pathUrl, String data){
//        OutputStreamWriter out = null;
//        BufferedReader br = null;
//        String result = "";
//        try {
//            URL url = new URL(pathUrl);
//
//            //打开和url之间的连接
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//            //设定请求的方法为"POST"，默认是GET
//            //post与get的不同之处在于post的参数不是放在URL字串里面，而是放在http请求的正文内。
//            conn.setRequestMethod("POST");
//
//            //设置30秒连接超时
//            conn.setConnectTimeout(30000);
//            //设置30秒读取超时
//            conn.setReadTimeout(30000);
//
//            // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true, 默认情况下是false;
//            conn.setDoOutput(true);
//            // 设置是否从httpUrlConnection读入，默认情况下是true;
//            conn.setDoInput(true);
//
//            // Post请求不能使用缓存
//            conn.setUseCaches(false);
//
//            //设置通用的请求属性
//            conn.setRequestProperty("accept", "*/*");
//            conn.setRequestProperty("connection", "Keep-Alive");  //维持长链接
//            conn.setRequestProperty("Content-Type", "multipart/form-data;charset=utf-8");
//
//            //连接，从上述url.openConnection()至此的配置必须要在connect之前完成，
//            conn.connect();
//
//            /**
//             * 下面的三句代码，就是调用第三方http接口
//             */
//            //获取URLConnection对象对应的输出流
//            //此处getOutputStream会隐含的进行connect(即：如同调用上面的connect()方法，所以在开发中不调用上述的connect()也可以)。
//            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
//            //发送请求参数即数据
//            out.write(data);
//            //flush输出流的缓冲
//            out.flush();
//
//            /**
//             * 下面的代码相当于，获取调用第三方http接口后返回的结果
//             */
//            //获取URLConnection对象对应的输入流
//            InputStream is = conn.getInputStream();
//            //构造一个字符流缓存
//            br = new BufferedReader(new InputStreamReader(is));
//            String str = "";
//            while ((str = br.readLine()) != null){
//                result += str;
//            }
////            System.out.println(result);
//            //关闭流
//            is.close();
//            //断开连接，disconnect是在底层tcp socket链接空闲时才切断，如果正在被其他线程使用就不切断。
//            conn.disconnect();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                if (out != null){
//                    out.close();
//                }
//                if (br != null){
//                    br.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return result;
//    }


    private static void handleFile(List<String> bFolderPath) {
        Map<String, String> data = new HashMap<>();
        String accessToken;
        String groupId;
        for (String bfp : bFolderPath) {
            accessToken = "";
            groupId = "";
            File file = new File(bfp);
            if (!file.exists()){
                continue;
            }
            int fileNumbers = file.listFiles().length;
            Random random = new Random();
            createSixRandom(accessToken);
            createSixRandom(groupId);
            data.put("accessToken", accessToken);
            data.put("groupId", groupId);
            if (fileNumbers < 3){
                File[] files = file.listFiles();
//                data.put("myfiles", files);
                for (File f : files) {
                    System.out.println(f.getName());
                    System.out.println("------------------------------------");
                }
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                String result1 = fileToByte(REGISTER_URL, data, files);
//                String result1 = doPost(REGISTER_URL, JSON.toJSONString(data));
                System.out.println(result1);
                System.out.println("==================================");
                data.remove("groupId");
//                data.replace("myfiles", files[0]);
//                String result2 = doPost(VERIFY_URL, JSON.toJSONString(data));
                File[] f1 = new File[1];
                f1[0] = files[0];
//                String result2 = fileToByte(VERIFY_URL, data, f1);
                String result2 = doPostUploadFile(VERIFY_URL, data, f1[0]);
                System.out.println(result2);
                System.out.println("==================================");

                log(result1 + result2);
            }else{
                Set<Integer> fileIndexs = new HashSet<>();
                File[] files = new File[3];
                for (int i = 0; i < 3; i++) {
                    int fileIndex = random.nextInt(fileNumbers + 1) == 0 ? 1 : random.nextInt((int) fileNumbers);
                    boolean isSuccess = fileIndexs.add(fileIndex);
                    if (!isSuccess){
                        i--;
                        continue;
                    }
                    String[] strings = bfp.split("\\\\");
                    int length = strings.length;
                    String filePrefix = strings[length - 1];
                    String filePath = bfp + "\\" + filePrefix + "_" + fileIndex + ".wav";
                    File bFile = new File(filePath);
                    if (!bFile.exists()){
                        i--;
                        continue;
                    }
                    files[i] = bFile;
                    System.out.println(bFile.getName());
                    System.out.println("------------------------------------");
                }
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//                data.put("myfiles", files);
//                String result1 = doPost(REGISTER_URL, JSON.toJSONString(data));
                String result1 = fileToByte(REGISTER_URL, data, files);
                System.out.println(result1);
                System.out.println("==================================");
                data.remove("groupId");
//                data.replace("myfiles", files[0]);
//                String result2 = doPost(VERIFY_URL, JSON.toJSONString(data));
                File[] f1 = new File[1];
                f1[0] = files[0];
//                String result2 = fileToByte(VERIFY_URL, data, f1);
                String result2 = doPostUploadFile(VERIFY_URL, data, f1[0]);
                System.out.println(result2);
                System.out.println("==================================");

                log(result1 + result2);
            }
        }
    }


    private static void createSixRandom(String numberStr) {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            numberStr += random.nextInt(9);
        }
    }


    private static void getFileNames(String filePathPrefix){
        File filePath = new File(filePathPrefix);
        if (!filePath.exists()) {
            return ;
        }
        try {
            classificationFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void classificationFile(File filePath) throws IOException {
        File[] files = filePath.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                classificationFile(f);
            } else {
                int index = f.getName().indexOf('_');
                String saveFolderPath = bFolderPathPrefix + "\\" +  f.getName().substring(0, index);
                //创建file文件对象
                File saveFolder = new File(saveFolderPath);
                //文件夹或目录不存在，则创建
                if (!saveFolder.exists()) {
                    saveFolder.mkdirs();
                    bFolderPath.add(saveFolderPath);
                }
                //文件不存在，则创建
                File saveFile = new File(saveFolder, f.getName());
                if (!saveFile.exists()){
                    saveFile.createNewFile();
                }
                //输出流
                OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(saveFile));
                //获取输入流，读取文件
                InputStreamReader in = new InputStreamReader(new FileInputStream(aFolderPath + "\\" +  f.getName()));
                char[] buffer = new char[4*1024];
                int length;
                //读取文件
                while((length = in.read(buffer))!= -1){
                    //写出
                    out.write(buffer, 0, length);
                }
                out.flush();
                in.close();
                out.close();
            }
        }
    }


    public static String fileToByte(String serverUrl, Map<String, String> data, File[] files) {
        List<byte[]> body_data = new ArrayList<>();
        for (File file : files) {
            BufferedInputStream bis = null;
            try {
                bis = new BufferedInputStream(new FileInputStream(file));
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                byte[] bd = null;
                int c = 0;
                byte[] buffer = new byte[8 * 1024];
                try {
                    while ((c = bis.read(buffer)) != -1) {
                        baos.write(buffer, 0, c);
                        baos.flush();
                    }
                    bd = baos.toByteArray();
                    body_data.add(bd);
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }

        // 调用自定义的post数据方法，提交表单数据及上传文件
        String result = null;
        try {
            result = sendFileData(serverUrl, data,  body_data, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    private static String sendFileData(String serverUrl, Map<String, String> data,  List<byte[]> body_data, String charset) throws IOException {
        // 设置三个常用字符串常量：换行、前缀、分界线（NEWLINE、PREFIX、BOUNDARY）；
        final String NEWLINE = "\r\n";
        final String PREFIX = "--";
        final String BOUNDARY = "#";

        HttpURLConnection httpConn = null;
        BufferedInputStream bis = null;
        DataOutputStream dos = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            // 实例化URL对象。调用URL有参构造方法，参数是一个url地址；
            URL urlObj = new URL(serverUrl);
            // 调用URL对象的openConnection()方法，创建HttpURLConnection对象；
            httpConn = (HttpURLConnection) urlObj.openConnection();
            // 调用HttpURLConnection对象setDoOutput(true)、setDoInput(true)、setRequestMethod("POST")；
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            httpConn.setRequestMethod("POST");
            // 设置Http请求头信息；（Accept、Connection、Accept-Encoding、Cache-Control、Content-Type、User-Agent）
            httpConn.setUseCaches(false);
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            httpConn.setRequestProperty("Cache-Control", "no-cache");
            // 这个比较重要，按照上面分析的拼装出Content-Type头的内容
            httpConn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + BOUNDARY);
            // 这个参数可以参考浏览器中抓出来的内容写，用chrome或者Fiddler抓吧看看就行
            httpConn.setRequestProperty(
                    "User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30)");
            // 调用HttpURLConnection对象的connect()方法，建立与服务器的真实连接；
            httpConn.connect();

            // 调用HttpURLConnection对象的getOutputStream()方法构建输出流对象；
            dos = new DataOutputStream(httpConn.getOutputStream());
            // 获取表单中上传控件之外的控件数据，写入到输出流对象（根据上面分析的抓包的内容格式拼凑字符串）；
            if (data != null && !data.isEmpty()) { // 这时请求中的普通参数，键值对类型的，相当于上面分析的请求中的username，可能有多个
                for (Map.Entry<String, String> entry : data.entrySet()) {
                    String key = entry.getKey(); // 键，相当于上面分析的请求中的username
                    Object value = data.get(key); // 值，相当于上面分析的请求中的sdafdsa
                    dos.writeBytes(PREFIX + BOUNDARY + NEWLINE); // 像请求体中写分割线，就是前缀+分界线+换行
                    dos.writeBytes("Content-Disposition: form-data; "
                            + "name=\"" + key + "\"" + NEWLINE); // 拼接参数名，格式就是Content-Disposition: form-data; name="key" 其中key就是当前循环的键值对的键，别忘了最后的换行
                    dos.writeBytes(NEWLINE); // 空行，一定不能少，键和值之间有一个固定的空行
                    dos.writeBytes(URLEncoder.encode(value.toString(), charset)); // 将值写入
                    // 或者写成：dos.write(value.toString().getBytes(charset));
                    dos.writeBytes(NEWLINE); // 换行
                }
            }

            // 获取表单中上传附件的数据，写入到输出流对象（根据上面分析的抓包的内容格式拼凑字符串）；
            if (body_data != null && body_data.size() > 0) {
                dos.writeBytes(PREFIX + BOUNDARY + NEWLINE);// 像请求体中写分割线，就是前缀+分界线+换行
                String fileName = "声纹";
                // 格式是:Content-Disposition: form-data; name="请求参数名"; filename="文件名"
                // 我这里吧请求的参数名写成了uploadFile，实际应用要根据自己的情况修改
                // 不要忘了换行
                dos.writeBytes("Content-Disposition: form-data; " + "name=\""
                        + "myfiles" + "\"" + "; filename=\"" + fileName
                        + "\"" + NEWLINE);
                // 换行，重要！！不要忘了
                dos.writeBytes(NEWLINE);
                for (byte[] file : body_data) {
                    dos.write(file); // 上传文件的内容
                    dos.writeBytes(NEWLINE); // 最后换行
                }
            }
            dos.writeBytes(PREFIX + BOUNDARY + PREFIX + NEWLINE);
            // 最后的分割线，与前面的有点不一样是前缀+分界线+前缀+换行，最后多了一个前缀
            dos.flush();

            // 调用HttpURLConnection对象的getInputStream()方法构建输入流对象；
            byte[] buffer = new byte[8 * 1024];
            int c = 0;
            // 调用HttpURLConnection对象的getResponseCode()获取客户端与服务器端的连接状态码。如果是200，则执行以下操作，否则返回null；
            if (httpConn.getResponseCode() == 200) {
                bis = new BufferedInputStream(httpConn.getInputStream());
                while ((c = bis.read(buffer)) != -1) {
                    baos.write(buffer, 0, c);
                    baos.flush();
                }
            }else{
                System.out.println("调用失败");
                System.out.println(httpConn.getResponseCode() + httpConn.getResponseMessage());
            }
            // 将输入流转成字节数组，返回给客户端。
            return new String(baos.toByteArray(), charset);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (dos != null)
                    dos.close();
                if (bis != null)
                    bis.close();
                if (baos != null)
                    baos.close();
                httpConn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    /**
     * 调用第三方接口
     * @param urlStr
     * @param textMap
     * @return 返回response数据
     */
    public static String httpURLConnectionPOST(String urlStr, Map<String, String> textMap) {
        String res = "";
        HttpURLConnection conn = null;
        // boundary就是request头和上传文件内容的分隔符
        String BOUNDARY = "---------------------------123821742118716";
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            // conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            conn.setRequestProperty("Content-Type","multipart/form-data; boundary=" + BOUNDARY);
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            // text
            if (textMap != null) {
                StringBuffer strBuf = new StringBuffer();
                Iterator iter = textMap.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String inputName = (String) entry.getKey();
                    String inputValue = (String) entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
                    strBuf.append(inputValue);
                }
                out.write(strBuf.toString().getBytes());
            }

            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();
            // 读取返回数据
            StringBuffer strBuf = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                strBuf.append(line).append("\n");
            }
            res = strBuf.toString();
            reader.close();
            reader = null;
        } catch (Exception e) {
            System.out.println("发送POST请求出错。" + urlStr);
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        return res;
    }


    /**
     * post请求提交form-data上传文件
     *
     * @param url 上传地址
     * @param headers 请求头
     * @param file 上传文件
     * @return
     */
    public static String doPostUploadFile(String url, Map<String, String> headers, File file) {
        HttpPost httpPost = new HttpPost(url);
        packageHeader(headers, httpPost);
        String fileName = file.getName();

        CloseableHttpResponse response = null;

        String respContent = null;

        long startTime = System.currentTimeMillis();

        // 设置请求头 boundary边界不可重复，重复会导致提交失败
        String boundary = "-------------------------" + UUID.randomUUID().toString();
        httpPost.setHeader("Content-Type", "multipart/form-data; boundary=" + boundary);

        // 创建MultipartEntityBuilder
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        // 设置字符编码
        builder.setCharset(StandardCharsets.UTF_8);
        // 模拟浏览器
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        // 设置边界
        builder.setBoundary(boundary);
        // 设置multipart/form-data流文件
        builder.addPart("myfiles", new FileBody(file));
        builder.addTextBody("fileType", "1");
        // application/octet-stream代表不知道是什么格式的文件
        builder.addBinaryBody("media", file, ContentType.create("application/octet-stream"), fileName);

        HttpEntity entity = builder.build();
        httpPost.setEntity(entity);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            response = httpClient.execute(httpPost);
            if (response != null && response.getStatusLine() != null && response.getStatusLine().getStatusCode() < 400) {
                HttpEntity he = response.getEntity();
                if (he != null) {
                    respContent = EntityUtils.toString(he, "UTF-8");
                }
            } else {
//                logger.error("对方响应的状态码不在符合的范围内!");
                System.out.println("对方响应的状态码不在符合的范围内!");
                throw new RuntimeException();
            }
            return respContent;
        } catch (Exception e) {
//            logger.error("网络访问异常,请求url地址={},响应体={},error={}", url, response, e);
            System.out.println("网络访问异常,请求url地址=" + url + ",响应体=" + response + ",error=" + e);
            throw new RuntimeException();
        } finally {
//            logger.info("统一外网请求参数打印,post请求url地址={},响应={},耗时={}毫秒", url, respContent, (System.currentTimeMillis() - startTime));
            try {
                if (response != null) {
                    response.close();
                }
                if(null != httpClient){
                    httpClient.close();
                }
            } catch (IOException e) {
//                logger.error("请求链接释放异常", e);
                System.out.println("请求链接释放异常" + e);
            }
        }
    }



    /**
     * 封装请求头
     *
     * @param paramsHeads
     * @param httpMethod
     */
    private static void packageHeader(Map<String, String> paramsHeads, HttpRequestBase httpMethod) {
        if (null != paramsHeads && paramsHeads.size() > 0) {
            Set<Map.Entry<String, String>> entrySet = paramsHeads.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                httpMethod.setHeader(entry.getKey(), entry.getValue());
            }
        }

    }
}
