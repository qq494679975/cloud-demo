package com.yihu.cwd.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
import org.apache.hadoop.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

/**
 * Created by Administrator on 2016/11/27.
 */
public class HadoopUtil {
    //private static String uri = "hdfs://192.168.116.132:9000/";
    private static String uri = "hdfs://192.168.59.240:9000/";
    private static Configuration config = new Configuration();

    public static FileSystem getFileSystem() throws Exception {
        ;
        return FileSystem.get(URI.create(uri), config);
    }

    /**
     * 查看目录下的所有文件 包括文件的信息
     */
    public static void dirList(String path) throws Exception {
        FileSystem f = getFileSystem();
        Path p = new Path(path);
        FileStatus[] fss = f.listStatus(p);
        for (FileStatus fs : fss) {
            Path fileStatusPath = fs.getPath();
            System.out.println("是不是目录:" + f.isDirectory(fileStatusPath));
        }
    }

    /**
     * 创建目录
     *
     * @param path
     * @throws Exception
     */
    public static void createDir(String path) throws Exception {
        getFileSystem().mkdirs(new Path(path));
    }

    /**
     * 文件上传
     *
     * @throws Exception
     */
    public static void fileUpload(String path) throws Exception {
        FileSystem fs = getFileSystem();
        // 列出hdfs上/user/fkong/目录下的所有文件和目录
        InputStream in = new BufferedInputStream(new FileInputStream(path));
        Configuration conf = new Configuration();
        //out对应的是Hadoop文件系统中的目录
        OutputStream out = fs.create(new Path("test/c.log"));
        IOUtils.copyBytes(in, out, 4096, true);
        System.out.println("success");
    }

    /**
     * 文件上传
     *
     * @throws Exception
     *///
    public static void fileUpload() throws Exception {
        FileSystem fs = getFileSystem();
        // 列出hdfs上/user/fkong/目录下的所有文件和目录
        FileStatus[] statuses = fs.listStatus(new Path("/user/hadoop/hadoop"));
        for (FileStatus status : statuses) {
            System.out.println(status);
        }

        // 在hdfs的/user/fkong目录下创建一个文件，并写入一行文本
        FSDataOutputStream os = fs.create(new Path("/user/hadoop/hadoop/test.log"));
        os.write("Hello World!".getBytes());
        os.writeUTF("Hello chenweida!");
        os.write("Hello 1111!".getBytes());
        os.write("Hello 2222!".getBytes());
        os.flush();
        os.close();

        // 显示在hdfs的/user/fkong下指定文件的内容
        // InputStream is = fs.open(new Path("/user/Administrator/test/test.log"));
        // IOUtils.copyBytes(is, System.out, 1024, true);
    }

    /**
     * 文件上傳copy的方式
     *
     * @param path
     */
    public static void fileUploadCopy(String path) throws Exception {
        FileSystem fs = getFileSystem();
        fs.copyFromLocalFile(new Path(path), new Path("/test/20160513.txt"));
        fs.close();

    }

    /**
     * 访问文件
     *
     * @param path
     * @throws Exception
     */
    public static void downLoad(String path) throws Exception {
        FileSystem fs = getFileSystem();
        //文件名稱
        Path p = new Path(path);
        //打開文件
        FSDataInputStream d = fs.open(p);
        //读取文件内容顯示到控制台
        IOUtils.copyBytes(d, System.out, 4096, false);
        //關閉流
        IOUtils.closeStream(d);
    }

    /**
     * 文件重命名
     *
     * @throws Exception
     */
    public static void renameFile() throws Exception {
        FileSystem fs = getFileSystem();

        Path old = new Path("/user/Administrator/test/b.txt");
        Path newP = new Path("/user/Administrator/test/bbb.txt");
        fs.rename(old, newP);
    }

    /**
     * 刪除文件
     *
     * @throws Exception
     */
    public static void deleteFile() throws Exception {

        FileSystem fs = getFileSystem();
        Path file = new Path("/user/Administrator/test/b.txt");
        fs.deleteOnExit(file);
    }

    /**
     * 刪除目錄
     *
     * @throws Exception
     */
    public static void deleteDir() throws Exception {
        FileSystem fs = getFileSystem();
        Path dir = new Path("/user/Administrator/test/");
        //删除目录
        fs.delete(dir, true);
    }

    /**
     * 查找某个文件的HDFS集群的位置
     *
     * @throws Exception
     */
    public static void getHDFSAddress() throws Exception {
        FileSystem fs = getFileSystem();
        //大文件 fdfs的路径
        Path p = new Path("/user/Administrator/test/linux.ios");

        FileStatus s = fs.getFileStatus(p);
        //返回塊的路徑
        BlockLocation[] bs = fs.getFileBlockLocations(s, 0, s.getLen());
        for (BlockLocation b : bs) {
            String[] hosts = b.getHosts();
            for (String host : hosts) {
                System.out.println("每个块所在的主机：" + host);
            }
        }
    }

    /**
     * 获取HDFS集群上所有节点名称信息
     *
     * @throws Exception
     */
    public static void getCluster() throws Exception {
        FileSystem fs = getFileSystem();
        //強制轉換成DistributedFileSystem
        DistributedFileSystem ds = (DistributedFileSystem) fs;
        //得到dd上的信息
        DatanodeInfo[] DatanodeInfos = ds.getDataNodeStats();
        for (DatanodeInfo d : DatanodeInfos) {
            System.out.println("主机名称" + d.getHostName());
        }
    }

    /**
     * 文件上傳之後合併成一個大文件
     *
     * @throws Exception
     */
    public static void mergeFile() throws Exception {
        FileSystem fs = getFileSystem();
        //用fileUtil工具類實現
        // FileUtil.copyMerge()

    }

    public static void main(String[] args) throws Exception {
        // File a=new File("G:/tmp/logs/wly.log");
        //a.createNewFile();
        fileUpload();
        //downLoad("/user/Administrator/test/test.log");
       // createDir("hadoop");
        //fileUploadCopy("C:/Users/Administrator/Desktop/linux命令.txt");
    }

}
