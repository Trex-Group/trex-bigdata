package hadoop.homework.kpi;

import java.io.IOException;
import java.util.Iterator;

import hadoop.homework.kpi.dto.AccessRecord;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

import hadoop.homework.hdfs.HDFSFileManger;

public class CountPageSize {
    public static class KPICountPageSizeMapper extends MapReduceBase implements Mapper<Object, Text, Text, Text> {
        private Text word = new Text();
        private Text size = new Text();

        private boolean isInteger(String value) {
            try {
                Integer.parseInt(value);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        @Override
        public void map(Object key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
            AccessRecord kpi = AccessRecord.getInstance().filterPageSize(value.toString());
            if (kpi.isValid()) {
                word.set(kpi.getRequest());
                if (isInteger(kpi.getBody_bytes_sent())) {
                    size.set(kpi.getBody_bytes_sent());
                    output.collect(word, size);
                }
            }
        }
    }

    public static class KPICountPageSizeReducer extends MapReduceBase implements Reducer<Text, Text, Text, Text> {
        private Text result = new Text();

        @Override
        public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
            float size = 0;

            while (values.hasNext()) {
                String str = values.next().toString();
                float pagesize = Integer.parseInt(str);
                size += pagesize;
            }

            result.clear();
            result.set("页面总大小 : " + " " + String.valueOf(size));
            output.collect(key, result);
            size = 0;
        }
    }

    public static void main(String[] args) throws Exception {

//        String input = "hdfs://192.168.101.231:9000/input/";
//        String output = "hdfs://192.168.101.231:9000/output/";

        String input = "input/";
        String output = "output/";

        JobConf conf = new JobConf(CountPageSize.class);

        // 如果输出文件夹存在，则删除，否则会报错
//        if (HDFSFileManger.fileExists(output, conf)) {
//            HDFSFileManger.deleteFile(output, conf);
//        }

        conf.setJobName("COUNT-PAGE-SIZE");

        conf.setMapOutputKeyClass(Text.class);
        conf.setMapOutputValueClass(Text.class);

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);

        conf.setMapperClass(KPICountPageSizeMapper.class);
        conf.setReducerClass(KPICountPageSizeReducer.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(conf, new Path(input));
        FileOutputFormat.setOutputPath(conf, new Path(output));

        JobClient.runJob(conf);
        System.exit(0);
    }
}
