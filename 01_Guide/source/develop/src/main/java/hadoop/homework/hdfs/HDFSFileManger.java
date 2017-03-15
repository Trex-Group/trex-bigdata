package hadoop.homework.hdfs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

public class HDFSFileManger {
    //
    //Function    :readHDFSFile
    //Description :读取文件指定位置指定长度的内容到aBuf中
    //Input       :aFilePath:文件名，aOffset:起始位置，aLength:指定长度
    //Output      :aBuf:读取到buf中
    //Return      :int:返回实际读取数据长度
    //
    public static int readHDFSFile(String aFilePath, byte[] aBuf, int aOffset, int aLength, Configuration aConf) {
        int ret = aLength;

        FSDataInputStream in = null;
        try {
            String str = String.format("读取文件%s 从 %d字节开始到120字节共%d长度的内容\n", aFilePath, aOffset, aLength);
            System.out.print(str);

            FileSystem fs = FileSystem.get(URI.create(aFilePath), aConf);
            in = fs.open(new Path(aFilePath));
            in.seek(aOffset - 1);
            ret = in.read(aBuf, 0, aLength);
            System.out.print("读取的实际长度为：" + ret + "\n");
            System.out.print("读取的内容为：" + new String(aBuf, "utf-8") + "\n");
        } catch (IOException e) {
            ret = 0;
        }
        IOUtils.closeStream(in);

        return ret;
    }

    //
    //Function    :readHDFSFile
    //Description :
    //Input       :
    //Output      :
    //Return      :
    //
    public static String readHDFSFile(String aHDFSFile) {
        String ret = null;

        Configuration conf = new Configuration();
        FSDataInputStream in = null;

        try {
            FileSystem fs = FileSystem.get(URI.create(aHDFSFile), conf);
            Path path = new Path(aHDFSFile);
            in = fs.open(path);

            byte[] out = new byte[100];

            int readlen = in.read(out);
            ret = new String(out, 0, readlen);
        } catch (IOException e) {

        } finally {
            IOUtils.closeStream(in);
        }

        return ret;
    }

    //
    //Function    :writeData2HDFS
    //Description :将指定的内容写到hdfs中
    //Input       :aHDFSPath:文件名，aLength:指定长度，aBuf:写入内容
    //Output      :
    //Return      :int:返回写入数据长度
    //
    public static int writeData2HDFS(String aHDFSPath, byte[] aBuf, int aLength) {
        int ret = aLength;

        Configuration conf = new Configuration();
        InputStream in = new ByteArrayInputStream(aBuf);

        try {
            FileSystem fs = FileSystem.get(URI.create(aHDFSPath), conf);
            OutputStream out = fs.create(new Path(aHDFSPath), new Progressable() {
                @Override
                public void progress() {
                    // TODO Auto-generated method stub
                }
            });

            IOUtils.copyBytes(in, out, 4096, true);
            System.out.print("写入文件路径为：" + aHDFSPath + "\n");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            return 0;
        }

        return ret;
    }

    //
    //Function    :put2HDFS
    //Description :上传文件
    //Input       :
    //Output      :
    //Return      :
    //
    public static boolean put2HDFS(String aSrcPath, String aDesPath, Configuration aConf) {
        Path dstPath = new Path(aDesPath);
        try {
            FileSystem hdfs = dstPath.getFileSystem(aConf);
            hdfs.copyFromLocalFile(false, new Path(aSrcPath), dstPath);
        } catch (IOException ie) {
            ie.printStackTrace();
            return false;
        }

        return true;
    }

    //
    //Function    :getFromHDFS
    //Description :下载文件
    //Input       :
    //Output      :
    //Return      :
    //
    public static boolean getFromHDFS(String aSrcPath, String aDesPath, Configuration aConf) {
        Path dstPath = new Path(aDesPath);
        try {
            FileSystem fs = dstPath.getFileSystem(aConf);
            fs.copyToLocalFile(false, new Path(aSrcPath), dstPath);
        } catch (IOException ie) {
            ie.printStackTrace();
            return false;
        }
        return true;
    }

    //
    //Function    :deleteFile
    //Description :删除文件
    //Input       :
    //Output      :
    //Return      :
    //
    public static boolean deleteFile(final String aPath, Configuration aConf) {
        Path dstPath = new Path(aPath);
        try {
            FileSystem fs = dstPath.getFileSystem(aConf);

            if (fileExists(aPath, aConf)) {
                fs.delete(dstPath, true);
            }
        } catch (IOException ie) {
            ie.printStackTrace();
            return false;
        }
        return true;
    }

    //
    //Function    :fileExists
    //Description :文件是否存在
    //Input       :
    //Output      :
    //Return      :
    //
    public static boolean fileExists(final String aPath, Configuration aConf) {
        Path dstPath = new Path(aPath);
        try {
            FileSystem fs = dstPath.getFileSystem(aConf);

            if (!fs.exists(dstPath)) {
                return false;
            }
        } catch (IOException ie) {
            ie.printStackTrace();
            return false;
        }
        return true;
    }

    //
    //Function    :listStatus
    //Description :列出目录中的文件
    //Input       :
    //Output      :
    //Return      :
    //
    public static Path[] listStatus(String aPath, Configuration aConf) {
        Path[] ret = null;
        Path dstPath = new Path(aPath);
        try {
            FileSystem fs = dstPath.getFileSystem(aConf);
            FileStatus[] status = fs.listStatus(dstPath);
            ret = FileUtil.stat2Paths(status);
        } catch (IOException ie) {
            ie.printStackTrace();
        }

        return ret;
    }
}
